package com.example.myapplication

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FragmentConfigBinding
import java.util.UUID

class ConfigFragment : Fragment() {

    private var _binding: FragmentConfigBinding? = null
    private val binding get() = _binding!!
    private var selectedDimension = 3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfigBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.radioGroup.setOnCheckedChangeListener{_,checkedID ->
            selectedDimension = when(checkedID){
                binding.three.id -> 3
                binding.four.id -> 4
                binding.five.id -> 5
                else -> 3
            }
        }



        binding.start.setOnClickListener {
            val bundle = Bundle()
            val gameFragment = GameFragment()
            bundle.putInt("selectedDimension", selectedDimension)
            gameFragment.arguments = bundle

            parentFragmentManager.beginTransaction().apply{
                replace(R.id.inner_game_fragment, gameFragment)
                addToBackStack("GameFragment")
                binding.start.visibility = View.INVISIBLE
                commit()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}