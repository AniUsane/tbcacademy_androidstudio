package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentReviewBinding

class ReviewFragment : Fragment() {

    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!

    private val args:ReviewFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        return _binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardInfo = args.cardInfo

        binding.cardButton.text = cardInfo.cardButton
        binding.price.text = cardInfo.price.toString()
        binding.cardImage.setImageResource(cardInfo.cardImage)
        binding.productName.text = cardInfo.productName
        binding.quantity.text = cardInfo.quantity.toString()
        binding.cardStatus.text = cardInfo.orderStatus

        binding.ratingBar.rating = 0f
        binding.ratingBar.stepSize = 1f
        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Toast.makeText(requireContext(), "Rating: $rating", Toast.LENGTH_SHORT).show()}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}