package com.example.todo.ui.viewpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ItemBannerBinding
import com.example.todo.model.ViewPageModel


class BannerAdapter(
    var bannerList: List<ViewPageModel>, private val onNavigateListener: OnNavigateListener
) :
    RecyclerView.Adapter<BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBannerBinding.inflate(inflater, parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner = bannerList[position]

        holder.binding.apply {
            itemBannerImage.setImageResource(banner.imageResId) //setBackgroundResource(banner.imageResId)
            itemBannerText.text = banner.text
        }
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }

}

class BannerViewHolder(val binding: ItemBannerBinding) :
    RecyclerView.ViewHolder(binding.root)