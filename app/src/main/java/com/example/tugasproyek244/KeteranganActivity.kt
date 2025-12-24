package com.example.tugasproyek244

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class KeteranganActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keterangan)

        val btnUpload: ImageButton = findViewById(R.id.btn_upload)
        val etKeterangan: EditText = findViewById(R.id.et_keterangan)

        btnUpload.setOnClickListener {
            val resultIntent = Intent()
            // Untuk saat ini, kita akan teruskan nama barang dan lokasi
            resultIntent.putExtra("nama_barang", "Barang Ditemukan") // Nama sementara
            resultIntent.putExtra("lokasi_barang", etKeterangan.text.toString())
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}