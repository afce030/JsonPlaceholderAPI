package com.example.jsonplaceholderapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        tvIdUI.text = "Id: "+ intent.getStringExtra("id_user")
        tvUserName.text = "Nombre: "+intent.getStringExtra("nombre")
        tvWebsite.text = "Sitio web: "+intent.getStringExtra("website")
        tvCity.text = "Ciudad: "+intent.getStringExtra("city")
        tvPhone.text = "Teléfono: "+intent.getStringExtra("phone")
        tvCompanyName.text = "Compañia: "+intent.getStringExtra("company")
        tvEmail.text = "Email: "+intent.getStringExtra("email")

        btnBack.setOnClickListener{
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
