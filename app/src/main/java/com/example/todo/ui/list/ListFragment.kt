package com.example.todo.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.R
import com.example.todo.databinding.FragmentListBinding
import com.example.todo.delegate.viewBinding
import com.example.todo.listener.OnListItemListener
import com.example.todo.model.TodoModel
import com.example.todo.util.gone
import com.example.todo.util.sent
import com.example.todo.util.showToast
import com.example.todo.util.visible

class ListFragment : Fragment(R.layout.fragment_list){
    private val binding by viewBinding(FragmentListBinding::bind)
    private lateinit var  listViewModel: ListViewModel
    private lateinit var  adapter: ListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //UserViewModel
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]

        binding.floatingActionButton.setOnClickListener{
            //findNavController().navigate(R.id.action_listFragment_to_addFragment)
            Navigation.sent(it,R.id.action_listFragment_to_addFragment)
        }

        //Recyclerview
        adapter = ListAdapter(object : OnListItemListener {
            override fun delete(todo: TodoModel) {
                listViewModel.deleteTodo(todo)
                requireContext().showToast("Delete!!")
            }
        },listViewModel)

        binding.recyclerView.adapter= adapter
        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())

        listViewModel.readAllData.observe(viewLifecycleOwner){todoList->
            adapter.setData(todoList)
            if(todoList.isEmpty()){
                binding.textEmpty.visible()

            }else{
                binding.textEmpty.gone()
            }
        }
    }
}