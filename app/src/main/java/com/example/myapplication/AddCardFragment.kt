package com.example.myapplication

import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentAddCardBinding
import java.util.Date
import java.util.UUID

class AddCardFragment : BaseFragment<FragmentAddCardBinding>(FragmentAddCardBinding::inflate) {

    override fun start() {

        addCard()

    }



    private fun addCard(){
        binding.addBtn.setOnClickListener {
            val selectedCardType = when (binding.radioGroup.checkedRadioButtonId) {
                binding.visaRadBtn.id -> CardType.VISA
                binding.mastercardRadBtn.id -> CardType.MASTERCARD
                else -> {
                    return@setOnClickListener
                }
            }

            val newCard = CardInfo(
                id = UUID.randomUUID(),
                cardNumber = StringBuilder().append(binding.cardNumber1.text)
                    .append(binding.cardNumber2.text)
                    .append(binding.cardNumber3.text)
                    .append(binding.cardNumber4.text).toString(),
                holderName = binding.enterHolderName.text.toString(),
                expireDate = Date(),
                ccv = binding.enterCcv.text.toString(),
                cardStatus = selectedCardType
            )

            val action = AddCardFragmentDirections.actionAddCardFragmentToPaymentFragment(newCard)
            findNavController().navigate(action)
        }
    }


}