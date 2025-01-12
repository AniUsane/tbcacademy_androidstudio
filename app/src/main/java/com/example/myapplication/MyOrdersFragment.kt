package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentMyOrdersBinding
import java.util.UUID

class MyOrdersFragment : Fragment() {

    private var _binding: FragmentMyOrdersBinding? = null
    private val binding get() = _binding!!

    private var CardInfoList = mutableListOf(
        CardClass(id = UUID.randomUUID(), cardImage = R.drawable.image1, productName = "Modern Wingback", quantity = 2, orderStatus = "Completed", price = 280.00f, cardButton = "Leave Review"),
        CardClass(id = UUID.randomUUID(), cardImage = R.drawable.image2, productName = "Wooden Chair", quantity = 3, orderStatus = "Completed", price = 140.00f, cardButton = "Buy Again"),
        CardClass(id = UUID.randomUUID(), cardImage = R.drawable.image3, productName = "Mirrorred Reflector", quantity = 1, orderStatus = "Completed", price = 90.00f, cardButton = "Buy Again"),
        CardClass(id = UUID.randomUUID(), cardImage = R.drawable.image1, productName = "Modern Wingback", quantity = 2, orderStatus = "Completed", price = 280.00f, cardButton = "Leave Review"),
        CardClass(id = UUID.randomUUID(), cardImage = R.drawable.image2, productName = "Wooden Chair", quantity = 3, orderStatus = "Completed", price = 140.00f, cardButton = "Buy Again"),
        CardClass(id = UUID.randomUUID(), cardImage = R.drawable.image3, productName = "Mirrorred Reflector", quantity = 1, orderStatus = "Completed", price = 90.00f, cardButton = "Buy Again"),
        CardClass(id = UUID.randomUUID(), cardImage = R.drawable.image1, productName = "Modern Wingback", quantity = 2, orderStatus = "Completed", price = 280.00f, cardButton = "Leave Review"),
        CardClass(id = UUID.randomUUID(), cardImage = R.drawable.image2, productName = "Wooden Chair", quantity = 3, orderStatus = "Completed", price = 140.00f, cardButton = "Buy Again"),
        CardClass(id = UUID.randomUUID(), cardImage = R.drawable.image3, productName = "Mirrorred Reflector", quantity = 1, orderStatus = "Completed", price = 90.00f, cardButton = "Buy Again"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyOrdersBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = OrderAdapter { card ->
            if (card.orderStatus == "Completed") {
                val navOptions = NavOptions.Builder()
                    .setEnterAnim(R.anim.slide_up_animation)
                    .setExitAnim(R.anim.slide_down_animation)
                    .setPopEnterAnim(R.anim.slide_up_animation)
                    .setPopExitAnim(R.anim.slide_down_animation)
                    .build()

                val cardInfo = CardClass(
                    id = card.id,
                    cardImage = card.cardImage,
                    productName = card.productName,
                    quantity = card.quantity,
                    orderStatus = card.orderStatus,
                    price = card.price,
                    cardButton = card.cardButton
                )

                val action = MyOrdersFragmentDirections.actionMyOrdersFragmentToReviewFragment(cardInfo)
                findNavController().navigate(action, navOptions)

            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        adapter.submitList(CardInfoList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
