package com.example.jsonplaceholderapi.ui.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.jsonplaceholderapi.ui.Fragments.FavoritesFragment
import com.example.jsonplaceholderapi.ui.Fragments.PostsFragment

class ViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        if(position == 0){
            fragment = PostsFragment()
        }
        else if(position == 1){
            fragment = FavoritesFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: CharSequence? = null
        if(position == 0){
            title = "Todos"
        }
        else if(position == 1){
            title = "Favoritos"
        }
        return title
    }

}