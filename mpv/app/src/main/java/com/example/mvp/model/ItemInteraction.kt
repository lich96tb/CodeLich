package com.example.mvp.model

import com.example.mvp.interfaces.IMainPresenter
import kotlin.collections.ArrayList

class ItemInteraction(var iMainPrisenter:IMainPresenter) {
    init {
        this!!.getListData()?.let { iMainPrisenter.getListDataSuccess(it) }
    }
    fun getListData(): ArrayList<String>? {
        var list: ArrayList<String> = ArrayList()
        list.add("Item 1")
        list.add("Item 2")
        list.add("Item 3")
        list.add("Item 4")
        list.add("Item 5")
        list.add("Item 6")
        list.add("Item 7")
        list.add("Item 8")
        return list
    }
}