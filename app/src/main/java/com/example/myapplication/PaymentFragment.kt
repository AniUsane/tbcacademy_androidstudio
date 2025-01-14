package com.example.myapplication

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentPaymentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Date
import java.util.UUID

class PaymentFragment : BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {

    private lateinit var dialog: BottomSheetDialog
    private val args: PaymentFragmentArgs by navArgs()

    val cardInfo = mutableListOf<CardInfo>(
        CardInfo(id = UUID.randomUUID(), cardNumber = "43641345", holderName = "Ani Ani", expireDate = Date(), cardStatus = CardType.VISA),
        CardInfo(id = UUID.randomUUID(), cardNumber = "47574624", holderName = "Mari Mari", expireDate = Date(), cardStatus = CardType.MASTERCARD),
        CardInfo(id = UUID.randomUUID(), cardNumber = "34656345", holderName = "Nia Niafsffbiu", expireDate = Date(), cardStatus = CardType.VISA)
    )

    override fun start() {
        val adapter = ViewPagerAdapter { card ->
            showBottomSheet()
        }
        binding.viewPager.adapter = adapter
        adapter.submitList(cardInfo)

        binding.addNewText.setOnClickListener {
            findNavController().navigate(R.id.action_paymentFragment_to_addCardFragment)
        }


    }

    private fun showBottomSheet(){
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet, null)
        dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogView)

        dialog.show()
    }


}