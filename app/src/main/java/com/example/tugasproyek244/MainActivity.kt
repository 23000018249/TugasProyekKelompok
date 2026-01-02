package com.example.tugasproyek244

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var rvBarangHariIni: RecyclerView
    private lateinit var rvBarangTemuan: RecyclerView
    private val listHorizontal = ArrayList<Barang>()
    private val listVertical = ArrayList<Barang>()
    private lateinit var barangVerticalAdapter: BarangVerticalAdapter

    // Launcher untuk menangkap data dari AmbilGambarActivity
    private val addBarangLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val namaBarang = data?.getStringExtra("nama_barang") ?: "Barang Baru"
            val lokasiBarang = data?.getStringExtra("lokasi_barang") ?: "Lokasi tidak diketahui"

            val newItem = Barang(R.drawable.ic_launcher_background, namaBarang, lokasiBarang)
            listVertical.add(0, newItem)
            barangVerticalAdapter.notifyItemInserted(0)
            rvBarangTemuan.scrollToPosition(0)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inisialisasi View
        rvBarangHariIni = findViewById(R.id.rv_hari_ini)
        rvBarangTemuan = findViewById(R.id.rv_barang_temuan)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Referensi container layout (Beranda vs Fragment)
        val mainContent = findViewById<View>(R.id.main_content_layout)
        val fragmentContainer = findViewById<View>(R.id.fragment_container)

        // 2. Persiapkan Data & Tampilkan List Beranda
        prepareData()
        showRecyclerList()

        // 3. Logika Floating Action Button (Tambah Barang)
        fab.setOnClickListener {
            val intent = Intent(this, AmbilGambarActivity::class.java)
            addBarangLauncher.launch(intent)
        }

        // 4. Logika Navigasi Menu Bawah
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Tampilkan konten utama Beranda, sembunyikan wadah Fragment
                    mainContent.visibility = View.VISIBLE
                    fragmentContainer.visibility = View.GONE
                    true
                }
                R.id.navigation_box -> {
                    // Sembunyikan Beranda, tampilkan Fragment Daftar Barang
                    mainContent.visibility = View.GONE
                    fragmentContainer.visibility = View.VISIBLE
                    loadFragment(BarangFragment())
                    true
                }
                R.id.navigation_profile -> {
                    // Sembunyikan Beranda, tampilkan Fragment Profil
                    mainContent.visibility = View.GONE
                    fragmentContainer.visibility = View.VISIBLE
                    loadFragment(ProfileFragment())
                    true
                }
                R.id.navigation_mail -> {
                    Toast.makeText(this, "Fitur Pesan segera hadir", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    // Mengisi data dummy untuk tampilan awal Beranda
    private fun prepareData() {
        listHorizontal.clear() // Membersihkan list agar tidak duplikat saat recreate
        listHorizontal.add(Barang(R.drawable.ic_launcher_foreground, "Kunci Motor", "Parkiran"))
        listHorizontal.add(Barang(R.drawable.ic_launcher_foreground, "Dompet", "Kantin"))

        listVertical.clear()
        listVertical.add(Barang(R.drawable.ic_launcher_foreground, "Botol Minum", "Ruang 204"))
        listVertical.add(Barang(R.drawable.ic_launcher_foreground, "Payung", "Lobby Gedung"))
    }

    // Mengatur Adapter untuk RecyclerView di Beranda
    private fun showRecyclerList() {
        rvBarangHariIni.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvBarangHariIni.adapter = BarangHorizontalAdapter(listHorizontal)

        rvBarangTemuan.layoutManager = LinearLayoutManager(this)
        barangVerticalAdapter = BarangVerticalAdapter(listVertical)
        rvBarangTemuan.adapter = barangVerticalAdapter
    }

    // Fungsi pembantu untuk mengganti Fragment di dalam FrameLayout
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}