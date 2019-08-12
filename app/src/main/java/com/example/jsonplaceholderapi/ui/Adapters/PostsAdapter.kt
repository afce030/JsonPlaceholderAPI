package com.example.jsonplaceholderapi.ui.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholderapi.LocalData.RoomEntities.PostEntity
import com.example.jsonplaceholderapi.R
import com.example.jsonplaceholderapi.ui.Holders.PostsHolder
import kotlinx.android.synthetic.main.post_item.view.*

class PostsAdapter(var posts: MutableList<PostEntity>, val context: Context) : RecyclerView.Adapter<PostsHolder>() {

    fun refreshPost(postList: List<PostEntity>){
        posts = ArrayList()
        posts.addAll(postList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostsHolder(v)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostsHolder, position: Int) {
        holder.itemView.tvId.setText(posts.get(position).id)
        holder.itemView.tvTitle.setText(posts.get(position).title)
        holder.itemView.tvUserId.setText(posts.get(position).userId)
        holder.itemView.tvBody.setText(posts.get(position).body)
    }
}