package org.marproject.reusableadaptersample.searchview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list_negara_searchview.*
import kotlinx.android.synthetic.main.item_negara.view.*
import org.marproject.reusableadaptersample.Negara
import org.marproject.reusableadaptersample.R
import org.marproject.reusablerecyclerviewadapter.AdapterCallback
import org.marproject.reusablerecyclerviewadapter.ReusableAdapter

class ListNegaraSearchviewActivity : AppCompatActivity() {

    // create adapter
    private lateinit var adapter: ReusableAdapter<Negara>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_negara_searchview)

        // title
        this.title = "Searchview Sample"

        // init adapter
        adapter = ReusableAdapter(R.layout.item_negara, true)

        // create data
        val listNegara = listOf(
            Negara("Indonesia","Jakarta"),
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
                Toast.makeText(this@ListNegaraSearchviewActivity, data.nama_negara, Toast.LENGTH_SHORT).show()
            }
        }

        // set data and callback adapter
        adapter.apply {
            this.addData(listNegara)
            this.adapterCallback = adapterCallback
        }

        // create and set adapter on recyclerview
        val recyclerview = rv_negara
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_bar, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView
        searchView.queryHint = "Cari negara"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
}
