package com.hendev.notes

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detay.*
import kotlinx.android.synthetic.main.activity_not_kayit.*

class DetayActivity : AppCompatActivity() {

    private lateinit var not:Notlar
    private lateinit var vt:VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay)

        toolbarDetay.title="Not Detay"
        setSupportActionBar(toolbarDetay)

        not = intent.getSerializableExtra("nesne") as Notlar
        vt = VeritabaniYardimcisi(this)

        txtDetayDersAdi.text = not.ders_adi
        edtDetayEx1.setText((not.not1).toString())
        edtDetayEx2.setText((not.not2).toString())
        txtDetayOrtalama.text = "Ders Ortalamanız : ${(not.not1 + not.not2)/2} || Geçer Not : 55 tir."
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_sil -> {
                Snackbar.make(toolbarDetay,"Kayıt Silinsinmi",Snackbar.LENGTH_LONG)
                    .setBackgroundTint(Color.BLACK)
                    .setTextColor(Color.WHITE)
                    .setActionTextColor(Color.RED)
                    .setAction("EVET"){
                        NotlarDao().notSil(vt,not.not_id)
                        startActivity(Intent(this@DetayActivity,MainActivity::class.java))
                        finish()
                    }.show()
                return true
            }
            R.id.action_duzenle ->{

                val dersAdi = txtDetayDersAdi.text
                val not1 = edtDetayEx1.text.toString().trim()
                val not2 = edtDetayEx2.text.toString().trim()

                if (TextUtils.isEmpty(dersAdi)){
                    Snackbar.make(toolbarDetay,"Ders Adını Giriniz", Snackbar.LENGTH_SHORT).show()
                    return false
                }

                if (TextUtils.isEmpty(not1)){
                    Snackbar.make(toolbarDetay,"1. Notu Giriniz", Snackbar.LENGTH_SHORT).show()
                    return false
                }

                if (TextUtils.isEmpty(not2)){
                    Snackbar.make(toolbarDetay,"2. Notu Giriniz", Snackbar.LENGTH_SHORT).show()
                    return false
                }

                NotlarDao().notGuncelle(vt, not.not_id,not1.toInt(),not2.toInt())
                startActivity(Intent(this@DetayActivity,MainActivity::class.java))
                finish()
                return true
            }
            else -> return false
        }
    }
}