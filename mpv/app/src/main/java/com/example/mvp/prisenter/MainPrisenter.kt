package com.example.mvp.prisenter

import com.example.mvp.interfaces.IMainPresenter
import com.example.mvp.interfaces.IMainViewApp
import com.example.mvp.model.ItemInteraction

class MainPrisenter(var iMainViewApp: IMainViewApp) : IMainPresenter {
    init {
       ItemInteraction(this)
    }

    override fun getListDataSuccess(listData: ArrayList<String>) {
       iMainViewApp.getListSuccess(listData)
    }


}