package com.example.tugasproyek244

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi tombol (Gunakan view. agar ID ditemukan)
        val btnEdit = view.findViewById<Button>(R.id.btn_edit_profile)
        val btnLogout = view.findViewById<Button>(R.id.btn_logout)

        btnEdit.setOnClickListener {
            Toast.makeText(requireContext(), "Membuka Fitur Edit Profil", Toast.LENGTH_SHORT).show()
        }

        btnLogout.setOnClickListener {
            Toast.makeText(requireContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, SplashActivity::class.java)
            // Hapus tumpukan activity agar tidak bisa di-back
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            activity?.finish()
        }
    }
}