package com.example.todo.ui.done

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.R
import com.example.todo.databinding.FragmentDoneBinding
import com.example.todo.delegate.viewBinding
import com.example.todo.listener.OnDoneItemListener
import com.example.todo.model.TodoModel
import com.example.todo.util.gone
import com.example.todo.util.visible

class DoneFragment : Fragment(R.layout.fragment_done) {
    private val binding by viewBinding(FragmentDoneBinding::bind)
    private lateinit var doneViewModel: DoneViewModel
    private lateinit var adapter: DoneAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doneViewModel = ViewModelProvider(this)[DoneViewModel::class.java]

        adapter = DoneAdapter(doneViewModel)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        doneViewModel.readAllDone.observe(viewLifecycleOwner) { list ->
            adapter.setData(list)
            Log.v("DoneFragment", list.toString())
            if(list.isEmpty()){
                binding.textEmpty.visible()
            }else{
                binding.textEmpty.gone()
            }

        }
    }
}