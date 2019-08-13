package com.thecodeside.mvvmca.features.drivers

import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.thecodeside.mvvmca.R
import com.thecodeside.mvvmca.network.DriverRemoteModel
import kotlinx.android.synthetic.main.drivers_item.view.*


class DriversItem(
    var driver: DriverRemoteModel
) : AbstractItem<DriversItem.ViewHolder>() {

    override val layoutRes: Int
        get() = R.layout.drivers_item

    override val type: Int
        get() = R.id.buttons_item_type

    override fun getViewHolder(v: View) = ViewHolder(v)

    class ViewHolder(view: View) : FastAdapter.ViewHolder<DriversItem>(view) {
        override fun bindView(item: DriversItem, payloads: MutableList<Any>) {
            itemView.first_name.text = item.driver.firstname
            itemView.last_name.text = item.driver.lastname
        }

        override fun unbindView(item: DriversItem) {
        }

    }

}
