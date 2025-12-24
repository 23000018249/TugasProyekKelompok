package com.example.tugasproyek244

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class AmbilGambarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ambil_gambar)

        val btnAmbilGambar: ImageButton = findViewById(R.id.btn_ambil_gambar)
        btnAmbilGambar.setOnClickListener {
            val intent = Intent(this, KeteranganActivity::class.java)
            startActivity(intent)
        }
    }
}