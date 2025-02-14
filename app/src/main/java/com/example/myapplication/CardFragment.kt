package com.example.myapplication

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.example.myapplication.databinding.FragmentCardBinding

class CardFragment : BaseFragment<FragmentCardBinding>(FragmentCardBinding::inflate) {

    private lateinit var adapter: ViewPagerAdapter
    private val cardViewModel: CardViewModel by viewModels()

    override fun start() {
        adapter = ViewPagerAdapter(requireContext(), emptyList())
        binding.viewPager.adapter = adapter
        observeData()
    }

    private fun observeData() {
        cardViewModel.cardList.observe(viewLifecycleOwner, Observer { cards ->
            adapter.updateData(cards)
        })

        cardViewModel.getData()
    }

}