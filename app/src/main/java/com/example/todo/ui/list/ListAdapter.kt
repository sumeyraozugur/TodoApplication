package com.example.todo.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.ItemListBinding
import com.example.todo.listener.OnListItemListener
import com.example.todo.model.TodoModel

class ListAdapter(
    private val onListItemListener: OnListItemListener, private val listViewModel: ListViewModel
) : RecyclerView.Adapter<ListAdapter.ListHolder>() {
    private var todoList = emptyList<TodoModel>()


    class ListHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListHolder(binding)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        //var favoritesList = TodoDatabase.getDatabase(holder.itemView.context).todoDao().getDoneList()
        val currentItem = todoList[position]
        //val customName = holder.itemView.findViewById<TextView>(R.id.itemTextTodo)
        //val textTodo = holder.binding.itemTextTodo

        with(holder.binding) {
            itemTextTodo.text = currentItem.task

            itemDeleteImage.setOnClickListener {
                onListItemListener.delete(currentItem)
            }

            itemListStar.setOnClickListener {
                if (currentItem.isFav) {
                    currentItem.isFav = false
                    //Update Current User
                    val updateTodoObj =
                        TodoModel(currentItem.id, currentItem.task, currentItem.isFav)
                    listViewModel.updateTodo(updateTodoObj)
                    itemListStar.setBackgroundResource(R.drawable.ic_star_border)

                } else {
                    currentItem.isFav = true
                    val updateTodo = TodoModel(currentItem.id, currentItem.task, currentItem.isFav)
                    listViewModel.updateTodo(updateTodo)
                    itemListStar.setBackgroundResource(R.drawable.ic_star)
                }
            }

            /*
            Log.v("ListAdapterrr",favoritesList.value?.contains(currentItem).toString())

           if (favoritesList.value?.contains(currentItem) == true) {
                Log.v("ListAdapterrr",favoritesList.value.toString())
               // itemListStar.setBackgroundResource(R.drawable.ic_star_border)
            }else {
                Log.v("ListAdapterrNot",favoritesList.value.toString())
               //itemListStar.setBackgroundResource(R.drawable.ic_star)
            }
            if(listViewModel.readAllDone.value?.contains(currentItem)  ==true){
                itemListStar.setBackgroundResource(R.drawable.ic_star)
                Log.v("ListAdapterDone", currentItem.task)

            }else{

                itemListStar.setBackgroundResource(R.drawable.ic_star_border)
                Log.v("ListAdapterDone", listViewModel.isFav.toString() )

            }*/


            //Log.v("ListAdapter", todoList.toString())

            rowLayout.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    fun setData(todo: List<TodoModel>) {
        this.todoList = todo
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}


