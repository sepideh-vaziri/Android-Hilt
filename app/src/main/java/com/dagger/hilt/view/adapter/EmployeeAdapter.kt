package com.dagger.hilt.view.adapter

import android.content.Context
import android.view.View
import com.dagger.hilt.view.adapter.base.BaseRecyclerAdapter
import com.dagger.hilt.view.item.EmployeeItem
import com.dagger.hilt.view.item.base.BaseItem
import com.dagger.hilt.R
import com.dagger.hilt.model.Employee

class EmployeeAdapter(context: Context) : BaseRecyclerAdapter<Employee>(context) {

    override fun getItemResId(viewType: Int): Int {
        return R.layout.item_employee
    }

    override fun getItemViewHolder(view: View): BaseItem<Employee> {
        return EmployeeItem(view)
    }

}