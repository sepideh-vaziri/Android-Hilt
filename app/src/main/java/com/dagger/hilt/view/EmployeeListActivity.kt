package com.dagger.hilt.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dagger.hilt.view.adapter.EmployeeAdapter
import com.dagger.hilt.R
import com.dagger.hilt.databinding.ActivityEmployeeListBinding
import com.dagger.hilt.viewmodel.EmployeeListViewModel
import com.dagger.hilt.viewmodel.base.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_employee_list.*

@AndroidEntryPoint
class EmployeeListActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        private val TAG = EmployeeListActivity::class.java.simpleName
    }

    private val mEmployeeListViewModel: EmployeeListViewModel by viewModels()

    private lateinit var mEmployeeAdapter: EmployeeAdapter

    //**********************************************************************************************
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEmployeeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set refresh listener
        swipeRefresh.setOnRefreshListener(this)

        //List config
        listConfig()
    }

    override fun onResume() {
        super.onResume()
        loadChallengeList()
    }

    override fun onRefresh() {
        swipeRefresh.isRefreshing = false

        loadChallengeList()
    }

    //**********************************************************************************************
    private fun listConfig() {
        mEmployeeAdapter = EmployeeAdapter(this)

        recyclerViewChallenge.apply {
            layoutManager = LinearLayoutManager(
                this@EmployeeListActivity,
                LinearLayoutManager.VERTICAL,
                false
            )

            adapter = mEmployeeAdapter
        }
    }

    //**********************************************************************************************
    private fun loadChallengeList() {

        mEmployeeListViewModel.getEmployeeList().observe(this, Observer { dataWrapper ->

            when (dataWrapper.status) {

                Status.LOADING -> {
                    progressbarLoading.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    progressbarLoading.visibility = View.INVISIBLE

                    dataWrapper.data?.let {
                        mEmployeeAdapter.setDataList(it)
                    }
                }

                Status.ERROR -> {
                    Log.e(TAG, "loadChallengeList: ", dataWrapper.error)
                    progressbarLoading.visibility = View.INVISIBLE

                    val message = resources.getString(R.string.employee_list_load_data_failed_message)

                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

                }

            } //End of when

        })


    }

}