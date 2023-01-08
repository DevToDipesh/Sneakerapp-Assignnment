package com.cricbuzz.assignment.dipesh.sneakerapp.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.cricbuzz.assignment.dipesh.sneakerapp.R
import com.cricbuzz.assignment.dipesh.sneakerapp.databinding.FragmentHomeBinding
import com.cricbuzz.assignment.dipesh.sneakerapp.home.model.SneakerItem
import com.cricbuzz.assignment.dipesh.sneakerapp.home.ui.adapter.SneakerAdapter
import com.cricbuzz.assignment.dipesh.sneakerapp.home.ui.viewModel.SneakerViewModel

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val viewModel: SneakerViewModel by viewModels { SneakerViewModel.Factory }
    private var sneakerAdapter: SneakerAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSneakerView()
        getTopSneakers()
        initViewObservers()
        binding.ivSearch.setOnClickListener {
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    private fun initViewObservers() {
        viewModel.sneakersItems.observe(viewLifecycleOwner) { sneakers ->
            sneakerAdapter?.submitList(sneakers)
        }
    }

    private fun getTopSneakers() {
        viewModel.loadSneakers()
    }

    private fun setSneakerView() {
        sneakerAdapter = SneakerAdapter(onClickAction)
        binding.rvHome.adapter = sneakerAdapter

    }

    private val onClickAction = object : SneakerAdapter.OnItemAction {
        override fun onItemAction(isItemClick: Boolean, item: SneakerItem) {
            if (isItemClick) {
                val navHostFragment =
                    activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val navController = navHostFragment.navController
                val bundle = Bundle().apply {
                    putString("sneakerId", item.id)
                }
                navController.navigate(R.id.action_homeFragment_to_sneakerDetailFragment, bundle)
            } else {
                activity?.let { viewModel.addToCart(it, item) }
            }
        }

    }


}