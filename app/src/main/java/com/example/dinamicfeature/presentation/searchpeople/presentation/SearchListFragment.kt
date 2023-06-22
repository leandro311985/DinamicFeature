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

    courseRVAdapter = SearchAdapter(courseList)
    courseRV.adapter = courseRVAdapter
    courseList.add(Region("Africa"))
    courseList.add(Region("Asia"))
    courseList.add(Region("Europe"))
    courseList.add(Region("West America"))
    courseList.add(Region("Australia"))
    courseList.add(Region("East America"))
    courseRVAdapter.notifyDataSetChanged()
  }

}