package com.example.jsonplaceholderapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholderapi.LocalData.RoomEntities.PostEntity
import com.example.jsonplaceholderapi.ui.Adapters.PostsAdapter
import com.example.jsonplaceholderapi.ui.ViewModels.postViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var postViewModel: postViewModel
    private val adapter = PostsAdapter(ArrayList(), this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postViewModel = postViewModel(application)

        val layout = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        var recycler = findViewById<RecyclerView>(R.id.rvPosts)
        recycler.adapter = adapter
        recycler.layoutManager = layout

        addObserverViewModel()

    }

    private fun addObserverViewModel() {
        val observer = Observer<List<PostEntity>> { posts ->
            Toast.makeText(this, posts.size.toString(),Toast.LENGTH_SHORT).show()
            if (posts != null) {
                adapter.refreshPost(posts)
            }
        }
        postViewModel.getAllPostViewModel().observe(this, observer)
    }
}
