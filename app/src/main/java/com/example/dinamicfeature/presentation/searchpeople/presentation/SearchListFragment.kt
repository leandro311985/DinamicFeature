package com.example.dinamicfeature.presentation.searchpeople.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentSearchListBinding
import com.example.dinamicfeature.presentation.searchpeople.adapter.SearchAdapter
import com.example.dinamicfeature.presentation.searchpeople.data.Region

class SearchListFragment : BaseFragment(R.layout.fragment_search_list) {

  private lateinit var binding: FragmentSearchListBinding
  lateinit var courseRV: RecyclerView
  lateinit var courseRVAdapter: SearchAdapter
  lateinit var courseList: ArrayList<Region>

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    setElement()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentSearchListBinding.bind(view)
  }

  private fun setElement() {
    courseRV = binding.idRVCourses
    courseList = ArrayList()

    // on below line we are initializing our adapter
    courseRVAdapter = SearchAdapter(courseList)

    // on below line we are setting adapter to our recycler view.
    courseRV.adapter = courseRVAdapter

    // on below line we are adding data to our list
    courseList.add(Region("Africa"))
    courseList.add(Region("Asia"))
    courseList.add(Region("Europe"))
    courseList.add(Region("West America"))
    courseList.add(Region("Australia"))
    courseList.add(Region("East America"))

    // on below line we are notifying adapter
    // that data has been updated.
    courseRVAdapter.notifyDataSetChanged()
  }


  private fun filter(text: String) {
    // creating a new array list to filter our data.
    val filteredlist: ArrayList<Region> = ArrayList()

    for (item in courseList) {
      // checking if the entered string matched with any item of our recycler view.
      if (item.regionName.toLowerCase().contains(text.toLowerCase())) {
        // if the item is matched we are
        // adding it to our filtered list.
        filteredlist.add(item)
      }
    }
    if (filteredlist.isEmpty()) {

      Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show()
    } else {
      courseRVAdapter.filterList(filteredlist)
    }
  }
}