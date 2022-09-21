package com.example.todo.ui.viewpage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.todo.R
import com.example.todo.databinding.FragmentViewPageBinding
import com.example.todo.delegate.viewBinding
import com.example.todo.model.ViewPageModel
import com.google.android.material.tabs.TabLayoutMediator

class ViewPageFragment : Fragment(R.layout.fragment_view_page) {
    private val binding by viewBinding(FragmentViewPageBinding::bind)
    private lateinit var adapter: BannerAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bannerList = listOf(
            ViewPageModel(R.drawable.exam, "Welcome to TodoApp"),
            ViewPageModel(R.drawable.homeworks, "You can plan your life easly."),
            ViewPageModel(
                R.drawable.homeworkk,
                "Planning your life gives you control ."
            ),
        )
        adapter = BannerAdapter(bannerList, object : OnNavigateListener {
            override fun navigate() {
                Navigation.findNavController(view)
                    .navigate(ViewPageFragmentDirections.actionViewPageFragmentToListFragment())
            }
        })
        binding.bannerViewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.bannerViewPager2) { tab, position ->
            //Some implementation
        }.attach()

        binding.cancelButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(ViewPageFragmentDirections.actionViewPageFragmentToListFragment())
        }
    }

}

interface OnNavigateListener {
    fun navigate()
}