package com.cricbuzz.assignment.dipesh.sneakerapp.search.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.cricbuzz.assignment.dipesh.sneakerapp.R
import com.cricbuzz.assignment.dipesh.sneakerapp.databinding.FragmentSearchBinding
import com.cricbuzz.assignment.dipesh.sneakerapp.home.model.SneakerItem
import com.cricbuzz.assignment.dipesh.sneakerapp.search.ui.viewModel.SearchViewModel


class SearchFragment : Fragment() {
    private var searchAdapter: SearchAdapter? = null
    lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels { SearchViewModel.Factory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSneakerView()
        getTopSneakers()
        initObservers()
        binding.ivBack.setOnClickListener { activity?.onBackPressed() }
        binding.etSearchSneaker.requestFocus()
        val imm: InputMethodManager? =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(binding.etSearchSneaker, InputMethodManager.SHOW_IMPLICIT)
        binding.etSearchSneaker.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.isEmpty() == true) {
                    getTopSneakers()
                } else {
                    viewModel.searchItem(p0.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun getTopSneakers() {
        viewModel.loadSneakers()
    }

    private fun setSneakerView() {
        searchAdapter = SearchAdapter(onClickAction)
        binding.rvHome.adapter = searchAdapter

    }

    private fun initObservers() {
        viewModel.sneakerItems.observe(viewLifecycleOwner) { sneakers ->
            if (sneakers.isEmpty()) {
                binding.rvHome.visibility = View.GONE
                binding.ivEmpty.llEmpty.visibility = View.VISIBLE
            } else {
                searchAdapter?.submitList(sneakers)
                binding.rvHome.visibility = View.VISIBLE
                binding.ivEmpty.llEmpty.visibility = View.GONE
            }
        }
    }

    private val onClickAction = object : SearchAdapter.OnItemAction {
        override fun onItemAction(isItemClick: Boolean, item: SneakerItem) {
            if (isItemClick) {
                val navHostFragment =
                    activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val navController = navHostFragment.navController
                val bundle = Bundle().apply {
                    putString("sneakerId", item.id)
                }
                navController.navigate(R.id.action_searchFragment_to_sneakerDetailFragment, bundle)
            } else {
                activity?.let { viewModel.addToCart(it, item) }
            }
        }

    }

}