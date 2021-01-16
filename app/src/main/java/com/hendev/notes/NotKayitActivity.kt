package com.hendev.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_not_kayit.*

class NotKayitActivity : AppCompatActivity() {

    private lateinit var vt:VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_kayit)

        toolbarNotKayit.title="Not Kayıt"
        setSupportActionBar(toolbarNotKayit)

        vt = VeritabaniYardimcisi(this)

        btnSave.setOnClickListener {
            val dersAdi = edtKayitDersAdi.text.toString().trim()
            val not1 = edtEx1.text.toString().trim()
            val not2 = edtEx2.text.toString().trim()

            if (TextUtils.isEmpty(dersAdi)){
                Snackbar.make(toolbarNotKayit,"Ders Adını Giriniz", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(not1)){
                Snackbar.make(toolbarNotKayit,"1. Notu Giriniz", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(not2)){
                Snackbar.make(toolbarNotKayit,"2. Notu Giriniz", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            NotlarDao().notEkle(vt,dersAdi,not1.toInt(),not2.toInt())

            startActivity(Intent(this@NotKayitActivity,MainActivity::class.java))
            finish()
        }

    }

    private fun notKaydet():Boolean {
        val dersAdi = edtKayitDersAdi.text.toString().trim()
        val not1 = edtEx1.text.toString().trim()
        val not2 = edtEx2.text.toString().trim()

        if (TextUtils.isEmpty(dersAdi)){
            Snackbar.make(toolbarNotKayit,"Ders Adını Giriniz", Snackbar.LENGTH_SHORT).show()
            return false // return setOnClickListener yapılamadı
        }

        if (TextUtils.isEmpty(not1)){
            Snackbar.make(toolbarNotKayit,"1. Notu Giriniz", Snackbar.LENGTH_SHORT).show()
            return false
        }

        if (TextUtils.isEmpty(not2)){
            Snackbar.make(toolbarNotKayit,"2. Notu Giriniz", Snackbar.LENGTH_SHORT).show()
            return false
        }
        NotlarDao().notEkle(vt,dersAdi,not1.toInt(),not2.toInt())
        return true
    }
}