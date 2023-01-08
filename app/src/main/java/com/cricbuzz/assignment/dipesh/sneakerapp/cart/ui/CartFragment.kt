package com.cricbuzz.assignment.dipesh.sneakerapp.cart.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.cricbuzz.assignment.dipesh.sneakerapp.R
import com.cricbuzz.assignment.dipesh.sneakerapp.databinding.FragmentCartBinding
import com.cricbuzz.assignment.dipesh.sneakerapp.roomdb.CartItem


class CartFragment : Fragment() {
    private var adapter: CartAdapter? = null
    lateinit var binding: FragmentCartBinding
    private var subTotal: Int = 0
    private val viewModel: CartViewModel by viewModels { CartViewModel.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initObservers()
        loadCartItems()
        binding.ivBack.setOnClickListener { activity?.onBackPressed() }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initObservers() {
        viewModel.sneakersItems.observe(viewLifecycleOwner) { cartItem ->
            if (cartItem.isEmpty()) {
                binding.gpCart.visibility = View.GONE
                binding.ivEmpty.llEmpty.visibility = View.VISIBLE
            } else {
                adapter?.submitList(cartItem)
                updateCartTotal(cartItem)
                binding.gpCart.visibility = View.VISIBLE
                binding.ivEmpty.llEmpty.visibility = View.GONE
            }

        }

    }

    private fun updateCartTotal(cartItem: List<CartItem>) {
        subTotal = cartItem.sumOf { it.price }
        binding.tvSubtotal.text = activity?.getString(R.string.subtotal_s, subTotal.toString())
        binding.tvTaxes.text = activity?.getString(R.string.taxes_and_charges_s, "40")
        binding.tvTotal.text = activity?.getString(R.string.total_s, (subTotal + 40).toString())
    }

    private fun loadCartItems() {
        activity?.let { viewModel.loadCartItems(it) }
    }

    private fun initAdapter() {
        adapter = CartAdapter(onClick)
        binding.rvCartItems.adapter = adapter
    }

    private val onClick = object : CartAdapter.onItemClick {
        override fun onClick(isItemClick: Boolean, item: CartItem) {
            if (isItemClick) {
                val navHostFragment =
                    activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val navController = navHostFragment.navController
                val bundle = Bundle().apply {
                    putParcelable("cartItem", item)
                }
                navController.navigate(R.id.action_cartFragment_to_sneakerDetailFragment, bundle)
            } else {
                activity?.let { viewModel.removeFromCart(it, item) }
            }
        }

    }


}