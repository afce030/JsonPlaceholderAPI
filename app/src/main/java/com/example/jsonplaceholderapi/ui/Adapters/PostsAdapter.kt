package com.example.jsonplaceholderapi.ui.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholderapi.LocalData.RoomEntities.PostEntity
import com.example.jsonplaceholderapi.LocalData.RoomEntities.UserEntity
import com.example.jsonplaceholderapi.R
import com.example.jsonplaceholderapi.UserInfo
import com.example.jsonplaceholderapi.ui.Holders.PostsHolder
import kotlinx.android.synthetic.main.post_item.view.*

class PostsAdapter(var posts: MutableList<PostEntity>, var users: MutableList<UserEntity>, var context: Context?) : RecyclerView.Adapter<PostsHolder>() {

    fun refreshPost(postList: List<PostEntity>){
        posts = ArrayList()
        if(users.size>0) {
            posts.addAll(postList)
        }
        notifyDataSetChanged()
    }

    fun refreshUsers(userList: List<UserEntity>){
        users.addAll(userList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        context = parent.context
        return PostsHolder(v)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostsHolder, position: Int) {
        holder.itemView.tvId.setText(posts.get(position).id.toString())
        holder.itemView.tvTitle.setText(posts.get(position).title)

        val pos:Int = posts.get(position).userId.toInt()
        holder.itemView.tvUserId.setText(users.get(pos-1).username)

        holder.itemView.tvBody.setText(posts.get(position).body)

        holder.itemView.setOnClickListener {

            val intent = Intent(context, UserInfo::class.java)
            intent.putExtra("id_user",users.get(pos-1).id_user.toString())
            intent.putExtra("nombre",users.get(pos-1).name)
            intent.putExtra("website",users.get(pos-1).website)
            intent.putExtra("city",users.get(pos-1).city)
            intent.putExtra("phone",users.get(pos-1).phone)
            intent.putExtra("company",users.get(pos-1).companyName)
            intent.putExtra("email",users.get(pos-1).email)
            context?.startActivity(intent)

        }

    }
}