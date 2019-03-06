package com.example.husgo.ortalamahesaplama

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.yeni_ders_layout.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.*
import java.util.zip.Inflater
import kotlin.math.roundToInt
import kotlin.math.roundToLong


class MainActivity : AppCompatActivity() {
    val context:Context=this
    private val dersler= arrayOf("Matematik","Türkçe","Fizik","Edebiyat","Tarih","Otomata")
    var dersBilgileri=ArrayList<Dersler>(5)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var adapter=ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,dersler)
        tvDers.setAdapter(adapter)

        if (linearParent.childCount==0){
            btnHesapla.visibility=View.INVISIBLE
        }
        else
        {
            btnHesapla.visibility=View.VISIBLE
        }

        btnEkle.setOnClickListener {
            if (!tvDers.text.isNullOrEmpty()) {
                var inflater=layoutInflater
                var yenidersLayout=inflater.inflate(R.layout.yeni_ders_layout,null)
                yenidersLayout.tvYeniDers.setAdapter(adapter)

                yenidersLayout.tvYeniDers.setText(tvDers.text.toString())
                yenidersLayout.spnYeniHarf.setSelection(spnHarf.selectedItemPosition)
                yenidersLayout.spnYeniKredi.setSelection(spnKredi.selectedItemPosition)

                yenidersLayout.btnDersSil.setOnClickListener {
                    linearParent.removeView(yenidersLayout)
                    if (linearParent.childCount==0){
                        btnHesapla.visibility=View.INVISIBLE
                    }
                }


                //java.lang.IllegalStateException: ScrollView can host only one direct child Bundan ötürü scroll view'ın içine başka bir root eklendi

                linearParent.addView(yenidersLayout)
                btnHesapla.visibility=View.VISIBLE
                clearFields()

                /*
                Teknik 2
                var inflater2=LayoutInflater.from(this)
                inflater2.inflate(R.layout.yeni_ders_layout,null)
                */

                /*
                Teknik 3
                var inflater3=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                inflater3.inflate(R.layout.yeni_ders_layout,null)
                */
            }
            else
            {

                Toasty.info(context, "Ders Adı Boş Bırakılamaz", Toast.LENGTH_SHORT, true).show();
                clearFields()
            }

        }
    }

    fun clearFields(){
        spnHarf.setSelection(0)
        spnKredi.setSelection(0)
        tvDers.setText("")
    }

    public fun ortalamaHesapla(v:View){
        var toplamNot=0.0
        var toplamKredi=0.0
        for (i in 0..linearParent.childCount-1){
            var currLesson=linearParent.getChildAt(i)
            var ders=Dersler(currLesson.tvYeniDers.text.toString(),
                    (currLesson.spnYeniKredi.selectedItemPosition+1).toString()
                    ,currLesson.spnYeniHarf.selectedItem.toString())
            dersBilgileri.add(ders)

        }


        for (i in dersBilgileri){
            toplamNot+=harfiNotaCevir(i.dersHarf)*i.dersKredi.toDouble()
            toplamKredi+=i.dersKredi.toDouble()
        }

        Toasty.success(context, "Ortalamanız :"+"%.2f".format(toplamNot/toplamKredi), Toast.LENGTH_LONG, true).show();
        dersBilgileri.clear()

    }


    fun harfiNotaCevir(harfNotu:String):Double{
        var deger=0.0
        when(harfNotu){

            "AA"->deger=4.0
            "BA"->deger=3.5
            "BB"->deger=3.0
            "CB"->deger=2.5
            "CC"->deger=2.0
            "DC"->deger=1.5
            "DD"->deger=1.0
            "FD"->deger=0.5
            "FF"->deger=0.0
        }

        return deger
    }
}
