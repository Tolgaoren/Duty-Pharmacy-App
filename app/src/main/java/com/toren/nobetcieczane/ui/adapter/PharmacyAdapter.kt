package com.toren.nobetcieczane.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toren.nobetcieczane.databinding.PharmacyRowBinding
import com.toren.nobetcieczane.data.model.Pharmacy
import com.toren.nobetcieczane.data.model.PharmacyResponse

class PharmacyAdapter(private val pharmacyList: MutableList<Pharmacy>) :
    RecyclerView.Adapter<PharmacyAdapter.ViewHolder>() {

        inner class ViewHolder(val binding: PharmacyRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PharmacyRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pharmacyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.pharmacyTw.text = pharmacyList[position].name
        holder.binding.adressTw.text = pharmacyList[position].address
        holder.binding.phoneTw.text = pharmacyList[position].phone
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatePharmacies(pharmacies: MutableList<Pharmacy>) {
        pharmacyList.clear()
        pharmacyList.addAll(pharmacies)
        notifyDataSetChanged()
    }
}