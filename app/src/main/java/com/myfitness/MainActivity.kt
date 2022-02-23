package com.myfitness

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.myfitness.adapters.UserRvAdapter
import com.myfitness.repository.Resource
import com.myfitness.viewmodel.MyFitnessViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val viewModel : MyFitnessViewModel by viewModels()
    private val userRvAdapter : UserRvAdapter by lazy { UserRvAdapter() }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rvUserList.adapter = userRvAdapter
        rvUserList.layoutManager = LinearLayoutManager(this)

        viewModel.userLiveData.observe(this, Observer {
            when(it){
                is Resource.Loading -> {
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
                    Log.e("API Error",it.errorMessage.toString())
                }
            }
        })

    }
}