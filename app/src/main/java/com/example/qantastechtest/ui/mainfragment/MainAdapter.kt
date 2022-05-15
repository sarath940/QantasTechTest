package com.example.qantastechtest.ui.mainfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.qantastechtest.R
import com.example.qantastechtest.data.model.QantasDataModel.QantasDataModelItem
import com.example.qantastechtest.utils.ItemCallback
import com.example.qantastechtest.utils.setSafeOnClickListener
import kotlinx.android.synthetic.main.qantas_item_layout.view.*

class MainAdapter(private val itemCallback: ItemCallback) :
    RecyclerView.Adapter<MainAdapter.QantasViewHolder>() {

    inner class QantasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QantasViewHolder {
        return QantasViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.qantas_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QantasViewHolder, position: Int) {
        val airData = differ.currentList[position]
        holder.itemView.apply {
            airportName.text = airData.airportName
            air_item.setSafeOnClickListener {
                itemCallback.itemClick(airData)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private val differCallback =
        object : DiffUtil.ItemCallback<QantasDataModelItem>() {
            override fun areItemsTheSame(
                oldItem: QantasDataModelItem,
                newItem: QantasDataModelItem
            ): Boolean {
                return oldItem.airportName == newItem.airportName
            }

            override fun areContentsTheSame(
                oldItem: QantasDataModelItem,
                newItem: QantasDataModelItem
            ): Boolean {
                return oldItem.airportCode == newItem.airportCode
            }
        }

    val differ = AsyncListDiffer(this, differCallback)
}