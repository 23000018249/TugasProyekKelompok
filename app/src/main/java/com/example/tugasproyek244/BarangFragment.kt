package com.example.tugasproyek244

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BarangFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_barang, container, false)

        val rvBarang = view.findViewById<RecyclerView>(R.id.rv_daftar_barang_lengkap)
        rvBarang.layoutManager = LinearLayoutManager(context)

        // Ambil data dummy (bisa disamakan dengan listVertical di MainActivity)
        val list = ArrayList<Barang>()
        list.add(Barang(R.drawable.ic_launcher_foreground, "Kunci Motor", "Gedung C"))
        list.add(Barang(R.drawable.ic_launcher_foreground, "Dompet Hitam", "Kantin"))

        rvBarang.adapter = BarangVerticalAdapter(list)

        return view
    }
}