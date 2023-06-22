package com.example.dinamicfeature.presentation.searchpeople.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dinamicfeature.R
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.databinding.FragmentSearchPeopleListBinding
import com.example.dinamicfeature.domain.models.PersonsFake
import com.example.dinamicfeature.presentation.searchpeople.adapter.CardListener
import com.example.dinamicfeature.presentation.searchpeople.adapter.PeopleCardAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ListPeopleResult : BaseFragment(R.layout.fragment_search_people_list), CardListener {

  private lateinit var binding: FragmentSearchPeopleListBinding
  private val viewModel: SearchViewModel by sharedViewModel()
  private var listFake = mutableListOf<PersonsFake?>()
  private lateinit var person : PersonsFake
  private var position : Int = 0

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    getDataList()
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentSearchPeopleListBinding.bind(view)
  }

  private fun getDataList() {
    viewModel.getListPerson()
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.listPerson.collect { list ->
            listFake = list.toMutableList()
            viewModel.getRegisterUserGeneralData()
          }
        }

        launch {
          viewModel.savePersonFake.collect {
            if (it){
              findNavController().navigate(ListPeopleResultDirections.actionListPeopleToNavigationDetailsPeople(position))
            }
          }
        }

        launch {
          viewModel.getDataProfileGeneralData.collect { result ->
            if (result?.masculino == true) {
              val type = "man"
              val list1 = listFake.filter { it?.type == type }
              upDateList(list1 as MutableList<PersonsFake>)

            } else if (result?.feminino == true) {
              val type = "femi"

              val list2 = listFake.filter { it?.type == type }
              upDateList(list2 as MutableList<PersonsFake>)

            } else if (result?.lgbtqa == true) {
              val type = "lgbtqa"
              val list3 = listFake.filter { it?.type == type }
              upDateList(list3 as MutableList<PersonsFake>)

            } else if (result?.masculino == true && result.feminino == true) {
              val list4 = listFake.filter { result.masculino == true && result.masculino == true }
              upDateList(list4 as MutableList<PersonsFake>)

            } else {
              upDateList(listFake as MutableList<PersonsFake>)
            }

          }
        }
      }
    }
  }

  private fun upDateList(emmanuelleModeViewObject: List<PersonsFake>) {
    binding.apply {
      val gridView = rcPeople
      val adapter = PeopleCardAdapter(this@ListPeopleResult, requireContext(), emmanuelleModeViewObject)
      gridView.adapter = adapter
    }
  }

  override fun onItemClick(personsFake: PersonsFake,positionInt: Int) {
    person = personsFake
    position = positionInt
    viewModel.savePeopleFakeData(personsFake)
  }
}