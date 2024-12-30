package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentPendingOrdersBinding
import java.util.Date
import java.util.UUID

class OrdersFragment : Fragment() {

    private var _binding: FragmentPendingOrdersBinding? = null
    private val binding get() = _binding!!
    var filters = mutableListOf(
        FilterClass(id = UUID.randomUUID(), filterStatus = FilterStatus.Pending),
        FilterClass(id = UUID.randomUUID(), filterStatus = FilterStatus.Delivered),
        FilterClass(id = UUID.randomUUID(), filterStatus = FilterStatus.Cancelled),
    )

    var cards = mutableListOf<CardsData>(
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#1524",
            trackingNumber = "IK2873218897", quantity = 3, total = "\$230", orderStatus = FilterStatus.Pending),
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#1524",
            trackingNumber = "IK287368838", quantity = 2, total = "\$110", orderStatus = FilterStatus.Pending),
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#1524",
            trackingNumber = "IK237368820", quantity = 5, total = "\$490", orderStatus = FilterStatus.Pending),
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#1524",
            trackingNumber = "IK2873218897", quantity = 3, total = "\$230", orderStatus = FilterStatus.Pending),
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#1524",
            trackingNumber = "IK2873218897", quantity = 3, total = "\$230", orderStatus = FilterStatus.Pending),
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#1524",
            trackingNumber = "IK2873218897", quantity = 3, total = "\$230", orderStatus = FilterStatus.Pending),
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#1679",
            trackingNumber = "IK2873218897", quantity = 3, total = "\$500", orderStatus = FilterStatus.Pending),
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#1524",
            trackingNumber = "IK2873218897", quantity = 3, total = "\$230", orderStatus = FilterStatus.Pending),
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#1514",
            trackingNumber = "IK987362341", quantity = 2, total = "\$110", orderStatus = FilterStatus.Delivered),
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#1524",
            trackingNumber = "IK2873218897", quantity = 3, total = "\$230", orderStatus = FilterStatus.Delivered),
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#1524",
            trackingNumber = "IK2873218897", quantity = 3, total = "\$230", orderStatus = FilterStatus.Delivered),
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#0000",
            trackingNumber = "IK2873218897", quantity = 3, total = "\$230", orderStatus = FilterStatus.Delivered),
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#1524",
            trackingNumber = "IK2873218897", quantity = 3, total = "\$230", orderStatus = FilterStatus.Cancelled),
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#1514",
            trackingNumber = "IK987362341", quantity = 2, total = "\$110", orderStatus = FilterStatus.Cancelled),
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#1524",
            trackingNumber = "IK2873218897", quantity = 3, total = "\$230", orderStatus = FilterStatus.Delivered),
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#1524",
            trackingNumber = "IK2873218897", quantity = 3, total = "\$230", orderStatus = FilterStatus.Cancelled),
        CardsData(id =UUID.randomUUID(), date = Date(1638648000000), orderNumber = "#0000",
            trackingNumber = "IK2873218897", quantity = 3, total = "\$230", orderStatus = FilterStatus.Delivered)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPendingOrdersBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filterRecycler.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.filterRecycler.adapter = FilterAdapter(filters) {selectedStatus -> filterCards(selectedStatus)}

        binding.cardRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.cardRecycler.adapter = CardsAdapter(cards) {cardsModel -> clickedDetailsButton(cardsModel)}

        filterCards(FilterStatus.Pending)


    }

    private fun filterCards(selectedStatus: FilterStatus){
        val filteredCards = cards.filter { it.orderStatus == selectedStatus}
        (binding.cardRecycler.adapter as CardsAdapter).submitList(filteredCards)
    }

    private fun clickedDetailsButton(cardsModel: CardsData){
        val detailsFragment = DetailsFragment()
        val bundle = Bundle().apply {
            putString("order number is: ", cardsModel.orderNumber)
            putString("tracking number is: ", cardsModel.trackingNumber)
            putInt("quantity is: ", cardsModel.quantity)
            putString("total is: ", cardsModel.total)
            putString("order status is: ", cardsModel.orderStatus.name)
            putLong("date: ", cardsModel.date.time)
        }

        detailsFragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.details_fragment, detailsFragment)
            addToBackStack("DetailsRecyclerView")
            binding.filterRecycler.visibility = View.GONE
            binding.cardRecycler.visibility = View.GONE
            binding.title.text = "Order details"
            binding.title.visibility = View.VISIBLE
            commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}