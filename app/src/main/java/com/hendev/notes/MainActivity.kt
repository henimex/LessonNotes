package com.hendev.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /** Standart Tanımlamalar Yapılıyor */
    private lateinit var notlarListe:ArrayList<Notlar>
    private lateinit var adapter:NotlarAdapter
    private lateinit var vt:VeritabaniYardimcisi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** ToolBar Ayarlarını Yapıyoruz */
        toolbar.title = "Ders Not Takibi"
        setSupportActionBar(toolbar)

        /** xml e koydugumuz RecyclerView ın düzgün görünmesi icin size fixleme yapıyoruz
         * ve Düzenlemesini ayarlıyoruz. */
        rv.setHasFixedSize(true)
        rv.layoutManager=LinearLayoutManager(this)

        /** Veritabanından tüm kayıtları listeliyoruz */
        vt = VeritabaniYardimcisi(this)
        notlarListe = NotlarDao().tumNotlar(vt)
        adapter= NotlarAdapter(this,notlarListe)
        rv.adapter=adapter

        var toplam = 0
        for (i in notlarListe){toplam += (i.not1+i.not2)/2}
        if (toplam != 0){toolbar.subtitle = "Ortalama : ${toplam/notlarListe.size}"}

        fabBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity,NotKayitActivity::class.java))
        }
    }

    private fun subtitleOrtalama() {
        var toplam = 0

        for (i in notlarListe){
            toplam += (i.not1+i.not2)/2
        }

        if (toplam != 0){
            toolbar.subtitle = "Ortalama : ${toplam/notlarListe.size}"
        }
    }

    /** MainActivity nin stacklamasına engel olduk Önemli */
    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}