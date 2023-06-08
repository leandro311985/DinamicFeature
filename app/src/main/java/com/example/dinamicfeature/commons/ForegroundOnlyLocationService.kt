package com.example.dinamicfeature.commons

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.location.Location
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.dinamicfeature.MainActivity
import com.example.dinamicfeature.R
import com.example.extension.SharedPreferenceUtil
import com.example.extension.toText
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.concurrent.TimeUnit

class ForegroundOnlyLocationService : Service() {
  /*
   * Checks whether the bound activity has really gone away (foreground service with notification
   * created) or simply orientation change (no-op).
   */
  private var configurationChange = false

  private var serviceRunningInForeground = false

  private val localBinder = LocalBinder()

  private lateinit var notificationManager: NotificationManager


  // FusedLocationProviderClient - Main class for receiving location updates.
  private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

  // LocationRequest - Requirements for the location updates, i.e., how often you should receive
  // updates, the priority, etc.
  private lateinit var locationRequest: LocationRequest

  // LocationCallback - Called when FusedLocationProviderClient has a new Location.
  private lateinit var locationCallback: LocationCallback

  // Used only for local storage of the last known location. Usually, this would be saved to your
  // database, but because this is a simplified sample without a full database, we only need the
  // last location to create a Notification if the user navigates away from the app.
  private var currentLocation: Location? = null

  override fun onCreate() {
    Log.d(TAG, "onCreate()")

    notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // TODO: Step 1.2, Review the FusedLocationProviderClient.
    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

    // TODO: Step 1.3, Create a LocationRequest.
    locationRequest = LocationRequest.create().apply {
      // Sets the desired interval for active location updates. This interval is inexact. You
      // may not receive updates at all if no location sources are available, or you may
      // receive them less frequently than requested. You may also receive updates more
      // frequently than requested if other applications are requesting location at a more
      // frequent interval.
      //
      // IMPORTANT NOTE: Apps running on Android 8.0 and higher devices (regardless of
      // targetSdkVersion) may receive updates less frequently than this interval when the app
      // is no longer in the foreground.
      interval = TimeUnit.SECONDS.toMillis(60)

      // Sets the fastest rate for active location updates. This interval is exact, and your
      // application will never receive updates more frequently than this value.
      fastestInterval = TimeUnit.SECONDS.toMillis(30)

      // Sets the maximum time when batched location updates are delivered. Updates may be
      // delivered sooner than this interval.
      maxWaitTime = TimeUnit.MINUTES.toMillis(2)

      priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    // TODO: Step 1.4, Initialize the LocationCallback.
    locationCallback = object : LocationCallback() {
      override fun onLocationResult(locationResult: LocationResult) {
        super.onLocationResult(locationResult)

        currentLocation = locationResult.lastLocation

        val intent = Intent(ACTION_FOREGROUND_ONLY_LOCATION_BROADCAST)
        intent.putExtra(EXTRA_LOCATION, currentLocation)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)

        if (serviceRunningInForeground) {
          notificationManager.notify(
            NOTIFICATION_ID,
            generateNotification(currentLocation))
        }
      }
    }
  }

  override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
    Log.d(TAG, "onStartCommand()")

    val cancelLocationTrackingFromNotification =
      intent.getBooleanExtra(EXTRA_CANCEL_LOCATION_TRACKING_FROM_NOTIFICATION, false)

    if (cancelLocationTrackingFromNotification) {
      unsubscribeToLocationUpdates()
      stopSelf()
    }
    // Tells the system not to recreate the service after it's been killed.
    return START_NOT_STICKY
  }

  override fun onBind(intent: Intent): IBinder {
    Log.d(TAG, "onBind()")

    // MainActivity (client) comes into foreground and binds to service, so the service can
    // become a background services.
    stopForeground(true)
    serviceRunningInForeground = false
    configurationChange = false
    return localBinder
  }

  override fun onRebind(intent: Intent) {
    Log.d(TAG, "onRebind()")

    // MainActivity (client) returns to the foreground and rebinds to service, so the service
    // can become a background services.
    stopForeground(true)
    serviceRunningInForeground = false
    configurationChange = false
    super.onRebind(intent)
  }

  override fun onUnbind(intent: Intent): Boolean {
    Log.d(TAG, "onUnbind()")

    // MainActivity (client) leaves foreground, so service needs to become a foreground service
    // to maintain the 'while-in-use' label.
    // NOTE: If this method is called due to a configuration change in MainActivity,
    // we do nothing.
    if (!configurationChange && SharedPreferenceUtil.getLocationTrackingPref(this)) {
      Log.d(TAG, "Start foreground service")
      val notification = generateNotification(currentLocation)
      startForeground(NOTIFICATION_ID, notification)
      serviceRunningInForeground = true
    }

    // Ensures onRebind() is called if MainActivity (client) rebinds.
    return true
  }

  override fun onDestroy() {
    Log.d(TAG, "onDestroy()")
  }

  override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
    configurationChange = true
  }

  fun subscribeToLocationUpdates() {
    Log.d(TAG, "subscribeToLocationUpdates()")

    // Binding to this service doesn't actually trigger onStartCommand(). That is needed to
    // ensure this Service can be promoted to a foreground service, i.e., the service needs to
    // be officially started (which we do here).
    startService(Intent(applicationContext, ForegroundOnlyLocationService::class.java))

    try {
      // TODO: Step 1.5, Subscribe to location changes.
      fusedLocationProviderClient.requestLocationUpdates(
        locationRequest, locationCallback, Looper.getMainLooper())
    } catch (unlikely: SecurityException) {
      SharedPreferenceUtil.saveLocationTrackingPref(this, false)
      Log.e(TAG, "Lost location permissions. Couldn't remove updates. $unlikely")
    }
  }

  fun unsubscribeToLocationUpdates() {
    Log.d(TAG, "unsubscribeToLocationUpdates()")

    try {
      val removeTask = fusedLocationProviderClient.removeLocationUpdates(locationCallback)
      removeTask.addOnCompleteListener { task ->
        if (task.isSuccessful) {
          Log.d(TAG, "Location Callback removed.")
          stopSelf()
        } else {
          Log.d(TAG, "Failed to remove Location Callback.")
        }
      }
      SharedPreferenceUtil.saveLocationTrackingPref(this, false)
    } catch (unlikely: SecurityException) {
      SharedPreferenceUtil.saveLocationTrackingPref(this, true)
      Log.e(TAG, "Lost location permissions. Couldn't remove updates. $unlikely")
    }
  }

  /*
   * Generates a BIG_TEXT_STYLE Notification that represent latest location.
   */
  private fun generateNotification(location: Location?): Notification {
    Log.d(TAG, "generateNotification()")

    val mainNotificationText = location?.toText() ?: getString(R.string.no_location_text)
    val titleText = getString(R.string.app_name)

    // 1. Create Notification Channel for O+ and beyond devices (26+).
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

      val notificationChannel = NotificationChannel(
        NOTIFICATION_CHANNEL_ID, titleText, NotificationManager.IMPORTANCE_DEFAULT)

      notificationManager.createNotificationChannel(notificationChannel)
    }

    val bigTextStyle = NotificationCompat.BigTextStyle()
      .bigText(mainNotificationText)
      .setBigContentTitle(titleText)

    val launchActivityIntent = Intent(this, MainActivity::class.java)

    val cancelIntent = Intent(this, ForegroundOnlyLocationService::class.java)
    cancelIntent.putExtra(EXTRA_CANCEL_LOCATION_TRACKING_FROM_NOTIFICATION, true)

    val servicePendingIntent = PendingIntent.getService(
      this, 0, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT)

    val activityPendingIntent = PendingIntent.getActivity(
      this, 0, launchActivityIntent, 0)

    val notificationCompatBuilder =
      NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)

    return notificationCompatBuilder
      .setStyle(bigTextStyle)
      .setContentTitle(titleText)
      .setContentText(mainNotificationText)
      .setSmallIcon(R.mipmap.ic_launcher_app)
      .setDefaults(NotificationCompat.DEFAULT_ALL)
      .setOngoing(true)
      .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
      .addAction(
        R.drawable.ic_launch, getString(R.string.launch_activity),
        activityPendingIntent
      )
      .addAction(
        R.drawable.ic_cancel,
        getString(R.string.stop_location_updates_button_text),
        servicePendingIntent
      )
      .build()
  }

  /**
   * Class used for the client Binder.  Since this service runs in the same process as its
   * clients, we don't need to deal with IPC.
   */
  inner class LocalBinder : Binder() {
    internal val service: ForegroundOnlyLocationService
      get() = this@ForegroundOnlyLocationService
  }

  companion object {
    private const val TAG = "ForegroundOnlyLocationService"

    private const val PACKAGE_NAME = "com.example.android.whileinuselocation"

    internal const val ACTION_FOREGROUND_ONLY_LOCATION_BROADCAST =
      "$PACKAGE_NAME.action.FOREGROUND_ONLY_LOCATION_BROADCAST"

    internal const val EXTRA_LOCATION = "$PACKAGE_NAME.extra.LOCATION"

    private const val EXTRA_CANCEL_LOCATION_TRACKING_FROM_NOTIFICATION =
      "$PACKAGE_NAME.extra.CANCEL_LOCATION_TRACKING_FROM_NOTIFICATION"

    private const val NOTIFICATION_ID = 12345678

    private const val NOTIFICATION_CHANNEL_ID = "while_in_use_channel_01"
  }
}