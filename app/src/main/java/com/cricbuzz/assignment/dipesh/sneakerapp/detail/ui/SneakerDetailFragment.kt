package com.cricbuzz.assignment.dipesh.sneakerapp.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.PagerAdapter
import com.cricbuzz.assignment.dipesh.sneakerapp.R
import com.cricbuzz.assignment.dipesh.sneakerapp.databinding.FragmentSneakerDetailBinding
import com.cricbuzz.assignment.dipesh.sneakerapp.detail.ui.photos.PhotoAdapter
import com.cricbuzz.assignment.dipesh.sneakerapp.home.model.SneakerItem


private const val ITEM_ID = "sneakerId"

class SneakerDetailFragment : Fragment() {
    private var itemId: String? = null
    private var sneakerItem: SneakerItem? = null
    private val sampleItems=arrayListOf(
        R.drawable.sample_shoes_image,
        R.drawable.sample_shoes_image,
        R.drawable.sample_shoes_image
    )
    lateinit var binding: FragmentSneakerDetailBinding
    private val viewmodel: SneakerDetailViewModel by viewModels { SneakerDetailViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            if (it.containsKey(ITEM_ID)) {
                itemId = it.getString(ITEM_ID)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemId?.let {
            viewmodel.loadSneakerDetailBasedOnId(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSneakerDetailBinding.inflate(inflater, container, false)
        initObservers()
        binding.ivBack.setOnClickListener { activity?.onBackPressed() }
        binding.btnAddToCart.setOnClickListener {
            sneakerItem?.let { it1 -> viewmodel.addToCart(requireContext(), it1) }
        }
        binding.ivPrevious.setOnClickListener {
            var currentPosition=binding.photosViewpager.currentItem
            if (currentPosition>0){
                binding.photosViewpager.setCurrentItem(--currentPosition,true)
            }else{
                binding.photosViewpager.setCurrentItem(sampleItems.size,true)

            }
        }
        binding.ivNext.setOnClickListener {
            var currentPosition=binding.photosViewpager.currentItem
            if (currentPosition<sampleItems.size){
                binding.photosViewpager.setCurrentItem(++currentPosition,true)
            }else{
                binding.photosViewpager.setCurrentItem(0,true)

            }
        }
        return binding.root
    }

    private fun initObservers() {
        viewmodel.sneakerItem.observe(viewLifecycleOwner) {
            sneakerItem = it
            setData(it)
        }
    }

    private fun setData(it: SneakerItem) {
        binding.tvTitle.text = it.title
        binding.tvPrice.text =
            activity?.getString(R.string.price_, it.retailPrice.toString())
        binding.tvDesc.text = it.brand
        initImages()
        binding.rvSize.removeAllTabs()
        binding.rvColor.removeAllTabs()
        binding.rvSize.addTab(binding.rvSize.newTab().apply { text = "7" })
        binding.rvSize.addTab(binding.rvSize.newTab().apply { text = "8" })
        binding.rvSize.addTab(binding.rvSize.newTab().apply { text = "9" })
        binding.rvColor.addTab(binding.rvColor.newTab().apply {
            text = ""
            view.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_color1)
        })
        binding.rvColor.addTab(binding.rvColor.newTab().apply {
            text = ""
            view.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_color2)
        })
        binding.rvColor.addTab(binding.rvColor.newTab().apply {
            text = ""
            view.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_color3)
        })

    }

    private fun initImages() {

        val adapter: PagerAdapter = PhotoAdapter(
            childFragmentManager,
           sampleItems
        )
        binding.photosViewpager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.photosViewpager, true)
    }


}