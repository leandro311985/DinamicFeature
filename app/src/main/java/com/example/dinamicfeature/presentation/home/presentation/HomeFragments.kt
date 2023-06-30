package com.example.dinamicfeature.presentation.home.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentHomeBinding
import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.dinamicfeature.domain.models.ProfileGeneralData
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragments : BaseFragment(R.layout.fragment_home),HomeListener {

  private lateinit var binding: FragmentHomeBinding
  private val viewModel: HomeViewModel by viewModel()
  private var data = ProfileGeneralData()


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    getData()
    loading(true)
    setElements()
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentHomeBinding.bind(view)
  }

  private fun setElements() = binding.apply {
    containerToolbar.title.text = "Home"
    containerToolbar.imgBack.setOnClickListener {
      findNavController().navigateUp()
    }
    containerTimeLine.iconeMenu4.setOnClickListener {
      getList(TypeVisual.GRADE)
    }
    containerTimeLine.iconeVisial.setOnClickListener {
      getList(TypeVisual.NORMAL)
    }
  }

  private fun loading(isVisible: Boolean) = binding.apply {
    loadingContainerHome.loadingContainer.isVisible = isVisible
    containerToolbar.backgraund.isVisible = !isVisible
    containerTimeLine.containerLineTime.isVisible = !isVisible
    rcHome.isVisible = !isVisible
  }

  private fun getData() {
    viewModel.getRegisterUserGeneralData()
  }


  private fun getList(typeVisual: TypeVisual) {
    viewModel.getList(typeVisual)
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.getDataProfileGeneralData.collect { dataResult ->
            if (dataResult != null) {
              data = dataResult
              getList(TypeVisual.GRADE)
            }else{
              getList(TypeVisual.GRADE)

            }
          }
        }
        launch {
          viewModel.listHomeGrade.collect { result ->
            binding.rcHome.isVisible = true
            binding.rcHomeNormal.isVisible = false
            if (data == null){
              upDateList(result as MutableList<PersonsFake>)
            } else if (data.masculino == true) {
              val type = "man"
              val list1 = result.filter { it.type == type }
              upDateList(list1 as MutableList<PersonsFake>)

            } else if (data.feminino == true) {
              val type = "femi"

              val list2 = result.filter { it.type == type }
              upDateList(list2 as MutableList<PersonsFake>)

            } else if (data.lgbtqa == true) {
              val type = "lgbtqa"
              val list3 = result.filter { it.type == type }
              upDateList(list3 as MutableList<PersonsFake>)

            } else if (data.masculino == true && data.feminino == true) {
              val list4 = result.filter { data.masculino == true && data.feminino == true }
              upDateList(list4 as MutableList<PersonsFake>)

            } else {
              upDateList(result as MutableList<PersonsFake>)
            }
            loading(false)
          }
        }

        launch {
          viewModel.listHomeNormal.collect { result ->
            binding.rcHome.isVisible = false
            binding.rcHomeNormal.isVisible = true
            if (data.masculino == true) {
              val type = "man"
              val list1 = result.filter { it?.type == type }
              upDateListNormal(list1 as MutableList<PersonsFake>)

            } else if (data.feminino == true) {
              val type = "femi"

              val list2 = result.filter { it.type == type }
              upDateListNormal(list2 as MutableList<PersonsFake>)

            } else if (data.lgbtqa == true) {
              val type = "lgbtqa"
              val list3 = result.filter { it.type == type }
              upDateListNormal(list3 as MutableList<PersonsFake>)

            } else if (data.masculino == true && data.feminino == true) {
              val list4 = result.filter { data.masculino == true && data.feminino == true }
              upDateListNormal(list4 as MutableList<PersonsFake>)

            } else {
              upDateListNormal(result as MutableList<PersonsFake>)
            }
            loading(false)
          }
        }
      }
    }
  }

  private fun upDateList(emmanuelleModeViewObject: List<PersonsFake>) {
    binding.apply {
      val gridView = binding.rcHome
      val adapter = ListHomeAdapter(this@HomeFragments,requireContext(), emmanuelleModeViewObject)
      gridView.adapter = adapter
    }
  }

  private fun upDateListNormal(personsFake: List<PersonsFake>) {
    binding.apply {
      val gridView = binding.rcHomeNormal
      gridView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
      val adapter = ListHomeNormalAdapter(this@HomeFragments,requireContext(), personsFake)
      gridView.adapter = adapter
    }
  }

  override fun onItemClick(personsFake: PersonsFake, positionInt: Int) {
    findNavController().navigate(HomeFragmentsDirections.actionHomeFragmentListToFragmentDetail(personsFake))
    Toast.makeText(requireContext(), "${personsFake.name}", Toast.LENGTH_SHORT).show()  }
}