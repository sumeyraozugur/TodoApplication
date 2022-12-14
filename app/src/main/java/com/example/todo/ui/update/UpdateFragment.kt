package com.example.todo.ui.update

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import com.example.todo.R
import com.example.todo.databinding.FragmentUpdateBinding
import com.example.todo.delegate.viewBinding
import com.example.todo.model.TodoModel
import com.example.todo.util.sent
import com.example.todo.util.showToast

class UpdateFragment : Fragment(R.layout.fragment_update) {
    private val binding by viewBinding(FragmentUpdateBinding::bind)
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var updateViewModel: UpdateViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //updateViewModel = ViewModelProvider(this)[UpdateViewModel::class.java]  or
        val tempViewModel:UpdateViewModel by viewModels()
        updateViewModel = tempViewModel

        //ıt shows what you chose by safeArgs
        binding.etUpdate.setText(args.objectUpdate.task)

        with(binding){
            btnUpdate.setOnClickListener {
                updateItem()
            }
            imageBack.setOnClickListener {
                Navigation.sent(it,R.id.action_updateFragment_to_listFragment)

            }
        }
    }

    private fun updateItem() {
        val firstName = binding.etUpdate.text.toString()
        if (inputCheck(firstName)) {
            //Create User Object
            val updateData = TodoModel(args.objectUpdate.id, firstName,true)

            //Update Current User
            updateViewModel.updateTodo(updateData)
            requireContext().showToast("Updated Successfully!")
            //Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        } else {
           requireContext().showToast("Please fill out all fields!")
        }
    }


    private fun inputCheck(firstName: String): Boolean {
        return !(TextUtils.isEmpty(firstName))
    }
}