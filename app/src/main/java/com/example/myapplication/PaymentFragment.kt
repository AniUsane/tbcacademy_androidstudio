package com.example.myapplication

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.BottomSheetBinding
import com.example.myapplication.databinding.FragmentPaymentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class PaymentFragment : BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {



    private lateinit var dialog: BottomSheetDialog
    private val args: PaymentFragmentArgs by navArgs()
    private val viewModel: CardViewModel by viewModels()

    override fun start() {
        val adapter = ViewPagerAdapter { card ->
            showBottomSheet(card)
        }
        binding.viewPager.adapter = adapter
        viewModel.cardInfo.observe(viewLifecycleOwner, Observer { cardList ->
            adapter.submitList(cardList)
        })

        binding.addNewText.setOnClickListener {
            findNavController().navigate(R.id.action_paymentFragment_to_addCardFragment)
        }

        //crashes the app
//        val cardInfoList = listOf(args.cardInfo)
//        adapter.submitList(cardInfoList)


    }

    private fun showBottomSheet(selectedCard: CardInfo){
        val dialogBinding = BottomSheetBinding.inflate(layoutInflater)
        dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogBinding.root)

        dialog.show()

        dialogBinding.yesBtn.setOnClickListener {
            viewModel.removeCard(selectedCard)
            dialog.dismiss()
        }

        dialogBinding.noBtn.setOnClickListener {
            dialog.dismiss()
        }

    }


}