package com.myfitness

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.myfitness.adapters.UserRvAdapter
import com.myfitness.databinding.ActivityMainBinding
import com.myfitness.repository.Resource
import com.myfitness.viewmodel.MyFitnessViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel : MyFitnessViewModel by viewModels()
    private val userRvAdapter : UserRvAdapter by lazy { UserRvAdapter() }
    var page : Int = 1
    var isScrolling : Boolean = false

    var currentItems : Int? = null
    var totalItems : Int? = null
    var scrollOutItems : Int? = null

    private lateinit var binding : ActivityMainBinding
    lateinit var layoutManager : LinearLayoutManager


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        rvUserList.adapter = userRvAdapter
        layoutManager = LinearLayoutManager(this)
        rvUserList.layoutManager = layoutManager


        viewModel.userLiveData.observe(this, Observer {
            when(it){
                is Resource.Loading -> {
                    Log.e("Loading","Loading")
                    rvUserList.visibility = View.GONE
                    shimmerFrameLayout.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    shimmerFrameLayout.visibility = View.GONE
                    rvUserList.visibility = View.VISIBLE
                    Log.e("API Call",Gson().toJson(it.data))
                    it.data?.results?.let { userList -> userRvAdapter.setData(userList) }

                }
                is Resource.Error -> {
                    shimmerFrameLayout.visibility = View.GONE
                    rvUserList.visibility = View.VISIBLE
                    Log.e("API Error",it.errorMessage.toString())
                    Toast.makeText(this,""+it.errorMessage,Toast.LENGTH_LONG).show()
                }
            }
        })


        /*
        nestedSV.setOnScrollChangeListener(object : NestedScrollView.OnScrollChangeListener{

            override fun onScrollChange(
                v: NestedScrollView?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
               if(scrollY == v?.getChildAt(0)?.measuredHeight?.minus(v.measuredHeight) ?: v?.getChildAt(0)?.measuredHeight){
                   page++
                   viewModel.getUserList(page,10)
               }
            }

        })

         */

        rvUserList.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){

                    isScrolling = true
                }

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


                currentItems = layoutManager.childCount //currently visible items
                totalItems = layoutManager.itemCount //total items in adapter
                scrollOutItems = layoutManager.findFirstVisibleItemPosition()

                if(isScrolling && (currentItems!! + scrollOutItems!! == totalItems)){

                    isScrolling = false
                    page++
                    fetchData(page)
                }
            }

        })



    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun fetchData(page : Int) {
        viewModel.getUserList(page,10)
    }
}