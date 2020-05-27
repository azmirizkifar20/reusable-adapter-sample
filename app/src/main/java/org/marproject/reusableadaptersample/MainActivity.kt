package org.marproject.reusableadaptersample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.marproject.reusableadaptersample.searchview.ListNegaraSearchviewActivity
import org.marproject.reusableadaptersample.withoutsearchview.ListNegaraActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_with_searchview.setOnClickListener {
            startActivity(
                Intent(this, ListNegaraSearchviewActivity::class.java)
            )
        }

        btn_without_searchview.setOnClickListener {
            startActivity(
                Intent(this, ListNegaraActivity::class.java)
            )
        }
    }
}
