package org.marproject.reusableadaptersample.withoutsearchview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list_negara.*
import kotlinx.android.synthetic.main.item_negara.view.*
import org.marproject.reusableadaptersample.Negara
import org.marproject.reusableadaptersample.R
import org.marproject.reusablerecyclerviewadapter.ReusableAdapter
import org.marproject.reusablerecyclerviewadapter.interfaces.AdapterCallback

class ListNegaraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_negara)

        // create data
        val listNegara = listOf(
            Negara("Indonesia","jakarta"),
            Negara("Malaysia","Kuala Lumpur"),
            Negara("Thailand","Bangkok"),
            Negara("Vietnam","Hanoi"),
            Negara("Filipina","Manila")
        )

        // create adapter callback for init component
        val adapterCallback = object : AdapterCallback<Negara> {
            override fun initComponent(itemView: View, data: Negara, itemIndex: Int) {
                itemView.tv_nama_negara.text = data.nama_negara
                itemView.tv_ibukota.text = data.ibukota
            }

            override fun onItemClicked(itemView: View, data: Negara, itemIndex: Int) {
                Toast.makeText(this@ListNegaraActivity, data.nama_negara, Toast.LENGTH_SHORT).show()
            }
        }

        // create adapter
        val adapter = ReusableAdapter<Negara>(R.layout.item_negara).apply {
            this.addData(listNegara)
            this.adapterCallback(adapterCallback)
        }

        // create and set adapter on recyclerview
        val recyclerview = rv_negara
        recyclerview.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@ListNegaraActivity)
        }
    }
}
