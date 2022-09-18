package com.example.todo.ui.add

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.FragmentAddBinding
import com.example.todo.delegate.viewBinding
import com.example.todo.model.TodoModel
import com.example.todo.util.showToast


class AddFragment : Fragment(R.layout.fragment_add) {
    private val binding by viewBinding(FragmentAddBinding::bind)
    private lateinit var addViewModel: AddViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addViewModel = ViewModelProvider(this)[AddViewModel::class.java]

        binding.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val todoTask = binding.etAdd.text.toString()
        if (inputCheck(todoTask)) {
            //Create User Object
            val todo = TodoModel(0, todoTask)

            addViewModel.addTodo(todo)
           // Toast.makeText(requireContext(), "Successfully added!!!", Toast.LENGTH_SHORT).show()
            requireContext().showToast("Successfully Added!!")

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            requireContext().showToast("Please fill out all fields!")
        }
    }

    private fun inputCheck(toDo:String): Boolean {
        return !(TextUtils.isEmpty(toDo))
    }

}