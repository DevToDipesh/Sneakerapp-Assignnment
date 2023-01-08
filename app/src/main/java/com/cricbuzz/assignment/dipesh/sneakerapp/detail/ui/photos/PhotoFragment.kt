package com.cricbuzz.assignment.dipesh.sneakerapp.detail.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cricbuzz.assignment.dipesh.sneakerapp.databinding.ItemSneakerBinding

class PhotoFragment : Fragment() {
    lateinit var binding: ItemSneakerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemSneakerBinding.inflate(inflater, container, false)
        return binding.root
    }
}