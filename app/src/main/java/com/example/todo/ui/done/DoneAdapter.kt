package com.example.todo.ui.done

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ItemDoneBinding
import com.example.todo.listener.OnDoneItemListener
import com.example.todo.model.TodoModel

class DoneAdapter() : RecyclerView.Adapter<DoneAdapter.DoneHolder>() {
    private var doneList = emptyList<TodoModel>()

    class DoneHolder(val binding: ItemDoneBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneHolder {
        val binding = ItemDoneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoneHolder(binding)
    }

    override fun onBindViewHolder(holder: DoneHolder, position: Int) {
        val currentItem = doneList[position]
        with(holder.binding){
            itemDoneText.text = currentItem.task

        }

        Log.v("DoneAdapter", doneList.toString())


    }

    override fun getItemCount() = doneList.size

    fun setData(todo:List<TodoModel>){
        this.doneList = todo
        notifyDataSetChanged()

    }
}