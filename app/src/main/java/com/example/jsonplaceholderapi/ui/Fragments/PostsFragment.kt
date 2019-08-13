package com.example.jsonplaceholderapi.ui.Fragments

import android.app.Application
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholderapi.LocalData.RoomEntities.PostEntity
import com.example.jsonplaceholderapi.LocalData.RoomEntities.UserEntity

import com.example.jsonplaceholderapi.R
import com.example.jsonplaceholderapi.ui.Adapters.PostsAdapter
import com.example.jsonplaceholderapi.ui.Adapters.ViewPagerAdapter
import com.example.jsonplaceholderapi.ui.ViewModels.postViewModel
import kotlinx.android.synthetic.main.activity_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PostsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PostsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PostsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var recycler: RecyclerView

    private lateinit var postViewModel: postViewModel
    private val adapter = PostsAdapter(ArrayList(), ArrayList(), context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val vista: View = inflater.inflate(R.layout.fragment_posts, container, false)

        postViewModel = postViewModel(activity!!.application)

        val layout = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        recycler = vista.findViewById<RecyclerView>(R.id.rvAllPosts)
        recycler.adapter = adapter
        recycler.layoutManager = layout

        addObserverViewModel()
        setRecyclerViewItemTouchListener()

        return vista
    }

    private fun addObserverViewModel() {
        val observer1 = Observer<List<PostEntity>> { posts ->
            if (posts != null) {
                adapter.refreshPost(posts)
            }
        }
        postViewModel.getAllPostViewModel().observe(this, observer1)

        val observer2 = Observer<List<UserEntity>> { users ->
            if (users != null) {
                adapter.refreshUsers(users)
            }
        }
        postViewModel.getAllUsersViewModel().observe(this, observer2)

    }


    private fun setRecyclerViewItemTouchListener() {

        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, viewHolder1: RecyclerView.ViewHolder): Boolean {

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {

                val position = viewHolder.adapterPosition
                postViewModel.inserFavoriteVM(adapter.posts.get(position))
                Toast.makeText(context,"Añadido a favoritos", Toast.LENGTH_SHORT).show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recycler)
    }




    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }
/*
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }
*/
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PostsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PostsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
