package com.dagger.hilt.model

class ServerResponse<TEntity> {

    companion object {
        const val SUCCESS_STATUS = "success"
    }

    var data: TEntity? = null
    var status: String? = null
    var message: String? = null

    fun isSuccess() : Boolean {
        return status == SUCCESS_STATUS
    }

}