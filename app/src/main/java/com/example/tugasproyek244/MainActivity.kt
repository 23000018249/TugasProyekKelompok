package com.example.tugasproyek244

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var rvBarangHariIni: RecyclerView
    private lateinit var rvBarangTemuan: RecyclerView
    private val listHorizontal = ArrayList<Barang>()
    private val listVertical = ArrayList<Barang>()
    private lateinit var barangVerticalAdapter: BarangVerticalAdapter

    private val addBarangLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val namaBarang = data?.getStringExtra("nama_barang") ?: "Barang Baru"
            val lokasiBarang = data?.getStringExtra("lokasi_barang") ?: "Lokasi tidak diketahui"
            // Menggunakan placeholder untuk gambar untuk saat ini
            val newItem = Barang(R.drawable.ic_launcher_background, namaBarang, lokasiBarang)
            listVertical.add(0, newItem)
            barangVerticalAdapter.notifyItemInserted(0)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBarangHariIni = findViewById(R.id.rv_hari_ini)
        rvBarangHariIni.setHasFixedSize(true)

        rvBarangTemuan = findViewById(R.id.rv_barang_temuan)
        rvBarangTemuan.setHasFixedSize(true)

        showRecyclerList()

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, AmbilGambarActivity::class.java)
            addBarangLauncher.launch(intent)
        }
    }

    private fun showRecyclerList() {
        rvBarangHariIni.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val barangHorizontalAdapter = BarangHorizontalAdapter(listHorizontal)
        rvBarangHariIni.adapter = barangHorizontalAdapter

        rvBarangTemuan.layoutManager = LinearLayoutManager(this)
        barangVerticalAdapter = BarangVerticalAdapter(listVertical)
        rvBarangTemuan.adapter = barangVerticalAdapter
    }
}