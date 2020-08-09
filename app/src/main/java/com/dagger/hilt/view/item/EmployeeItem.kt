package com.dagger.hilt.view.item

import android.content.Context
import android.view.View
import com.dagger.hilt.view.item.base.BaseItem
import com.dagger.hilt.model.Employee
import kotlinx.android.synthetic.main.item_employee.view.*

class EmployeeItem(itemView: View) : BaseItem<Employee>(itemView) {

    override fun setContent(context: Context, contentObject: Employee?) {
        if (contentObject == null) {
            return
        }

        itemView.textName.text = contentObject.employee_name
    }

    override fun loading(isVisible: Boolean) {}

}