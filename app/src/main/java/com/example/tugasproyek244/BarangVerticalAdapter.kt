package com.example.tugasproyek244

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BarangVerticalAdapter(private val listBarang: ArrayList<Barang>) : RecyclerView.Adapter<BarangVerticalAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_barang_vertical, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (gambar, nama, lokasi) = listBarang[position]
        holder.imgGambar.setImageResource(gambar)
        holder.tvNama.text = nama
        holder.tvLokasi.text = lokasi
    }

    override fun getItemCount(): Int = listBarang.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgGambar: ImageView = itemView.findViewById(R.id.iv_barang)
        val tvNama: TextView = itemView.findViewById(R.id.tv_nama_barang)
        val tvLokasi: TextView = itemView.findViewById(R.id.tv_lokasi)
    }
}