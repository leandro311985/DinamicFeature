package com.example.dinamicfeature.searchpeople

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dinamicfeature.baseApp.commons.BaseFragment
import com.example.dinamicfeature.searchpeople.databinding.FragmentSearchPeopleBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment(R.layout.fragment_search_people) {

  private lateinit var binding: FragmentSearchPeopleBinding
  private val viewModel: SearchViewModel by viewModel()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(view)
  }

  override fun initView(view: View) {
    setViewBinding(view)
    viewModel.getPhoto()
    setCollectors()
  }

  override fun setViewBinding(view: View) {
    binding = FragmentSearchPeopleBinding.bind(view)
  }

  private fun setCollectors() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

        launch {
          viewModel.photoUser.collect { photo ->
            setElements(photo)
          }
        }
      }
    }
  }

  private fun setElements(photo:String) = binding.apply{
    Picasso.get()
      .load(photo)
      .resize(50, 50)
      .centerCrop()
      .into(containerUserr.logoImageView)


    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        // Lógica a ser executada quando o usuário envia a busca (por exemplo, iniciar uma pesquisa)
        return true
      }

      override fun onQueryTextChange(newText: String?): Boolean {
        // Lógica a ser executada quando o texto da busca é alterado (por exemplo, filtrar resultados)
        return true
      }
    })

  }
}