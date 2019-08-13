package com.example.jsonplaceholderapi

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholderapi.LocalData.RoomEntities.PostEntity
import com.example.jsonplaceholderapi.ui.Adapters.PostsAdapter
import com.example.jsonplaceholderapi.ui.Adapters.ViewPagerAdapter
import com.example.jsonplaceholderapi.ui.Fragments.PostsFragment
import com.example.jsonplaceholderapi.ui.ViewModels.postViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var postViewModel: postViewModel

    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        this.viewPager.adapter = viewPagerAdapter
        this.tabLayout.setupWithViewPager(viewPager)

    }

}
