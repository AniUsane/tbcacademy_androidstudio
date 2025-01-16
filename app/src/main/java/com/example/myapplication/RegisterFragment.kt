package com.example.myapplication

import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val _fieldGroups = MutableLiveData<List<FieldDataDto>>()
    val fieldGroups: LiveData<List<FieldDataDto>> get() = _fieldGroups
    private val viewModel: DataViewModel by viewModels()
    val jsonData:String = ("""[[{"field_id":1,"hint":"UserName","field_type":"input","keyboard":"text","required":false,"is_active":true,"icon":"https://jemala.png"},{"field_id":2,"hint":"Email","field_type":"input","required":true,"keyboard":"text","is_active":true,"icon":"https://jemala.png"},{"field_id":3,"hint":"phone","field_type":"input","required":true,"keyboard":"number","is_active":true,"icon":"https://jemala.png"}],[{"field_id":4,"hint":"FullName","field_type":"input","keyboard":"text","required":true,"is_active":true,"icon":"https://jemala.png" },{"field_id":14,"hint":"Jemali","field_type":"input","keyboard":"text","required":false,"is_active":true,"icon":"https://jemala.png" },{"field_id":89,"hint":"Birthday","field_type":"chooser","required":false,"is_active":true,"icon":"https://jemala.png" },{"field_id":898,"hint":"Gender","field_type":"chooser","required":"false","is_active":true,"icon":"https://jemala.png" }]]""")


    override fun start() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.fields.observe(viewLifecycleOwner, Observer { fieldList ->
            val adapter = FieldAdapter(
                fields = fieldList,
                viewModel = viewModel,
                getInputType = { keyboard ->
                    if (keyboard == "text") android.text.InputType.TYPE_CLASS_TEXT
                    else android.text.InputType.TYPE_CLASS_NUMBER
                }
            )
            binding.recyclerView.adapter = adapter
        })

        viewModel.loadFields(jsonData)

        binding.registerButton.setOnClickListener{
            checkFields()
        }
    }

    private fun checkFields(){
        val resultMap = mutableMapOf<String, String>()

        var isValid = true

        viewModel.fields.value?.forEach { field ->
            val fieldValue = when (field.fieldType) {
                "input" -> {
                    val inputField = binding.root.findViewById<EditText>(field.fieldId)
                    inputField?.text.toString()
                }
                "chooser" -> {
                    val spinnerField = binding.root.findViewById<Spinner>(field.fieldId)
                    spinnerField?.selectedItem.toString()
                }
                else -> ""
            }

            if (field.required && fieldValue.isEmpty()) {
                isValid = false
                Toast.makeText(requireContext(), "The field is empty: ${field.hint}", Toast.LENGTH_SHORT).show()
            } else {
                resultMap[field.fieldId.toString()] = fieldValue
            }
        }

        if (isValid) {
            println("Valid registration data: $resultMap")
        }
    }





}