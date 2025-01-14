package com.example.myapplication

import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentAddCardBinding
import java.util.Date
import java.util.UUID

class AddCardFragment : BaseFragment<FragmentAddCardBinding>(FragmentAddCardBinding::inflate) {

    private lateinit var cardInfo:CardInfo

    override fun start() {


        binding.addBtn.setOnClickListener {
            addNewCard()
        }
    }

    private fun addNewCard() {
        val card1 = binding.cardNumber1.toString()
        val card2 = binding.cardNumber2.toString()
        val card3 = binding.cardNumber3.toString()
        val card4 = binding.cardNumber4.toString()

        val cardNumber = StringBuilder().append(card1).append(card2)
            .append(card3).append(card4)

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            cardInfo = when (checkedId) {
                binding.visaRadBtn.id -> CardInfo(
                    id = UUID.randomUUID(),
                    cardNumber = cardNumber.toString(),
                    holderName = binding.enterHolderName.text.toString(),
                    expireDate = Date(),
                    ccv = binding.enterCcv.text.toString(),
                    cardStatus = CardType.VISA
                )

                binding.mastercardRadBtn.id -> CardInfo(
                    id = UUID.randomUUID(),
                    cardNumber = cardNumber.toString(),
                    holderName = binding.enterHolderName.text.toString(),
                    expireDate = Date(),
                    ccv = binding.enterCcv.text.toString(),
                    cardStatus = CardType.MASTERCARD
                )

                else -> return@setOnCheckedChangeListener
            }
        }

        val action = AddCardFragmentDirections.actionAddCardFragmentToPaymentFragment(cardInfo)
        findNavController().navigate(action)
    }

//    private fun addCard(){
//        binding.addBtn.setOnClickListener {
//            val cardNumber = StringBuilder().append(binding.cardNumber1.text).append(binding.cardNumber2.text)
//                .append(binding.cardNumber3.text).append(binding.cardNumber4.text)
//
////            if (cardNumber.isEmpty() || binding.enterHolderName.text?.isEmpty() || binding.enterCcv.text.isEmpty()) {
////                // Show a validation error message
////                return@setOnClickListener
////            }
//
//            val selectedCardStatus = when (binding.radioGroup.checkedRadioButtonId) {
//                binding.visaRadBtn.id -> CardType.VISA
//                binding.mastercardRadBtn.id -> CardType.MASTERCARD
//                else -> {
//                    // Show a validation error message for card type
//                    return@setOnClickListener
//                }
//            }
//
//            cardInfo = CardInfo(
//                id = UUID.randomUUID(),
//                cardNumber = cardNumber.toString(),
//                holderName = binding.enterHolderName.text.toString(),
//                expireDate = Date(),
//                ccv = binding.enterCcv.text.toString(),
//                cardStatus = selectedCardStatus
//            )
//
//            val action = AddCardFragmentDirections.actionAddCardFragmentToPaymentFragment(cardInfo)
//            findNavController().navigate(action)
//        }
//    }
}