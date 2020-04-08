package com.itis.covidobserver.view

//import com.anychart.AnyChart
//import com.anychart.chart.common.dataentry.DataEntry
//import com.anychart.chart.common.dataentry.ValueDataEntry
//import com.anychart.enums.Align
//import com.anychart.enums.LegendLayout
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.itis.covidobserver.R
import com.itis.covidobserver.databinding.ActivityMainBinding
import com.itis.covidobserver.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : SuperActivity<MainViewModel>() {

    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initWorldData()
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        refresh()
        swipeRefreshLayout.setOnRefreshListener {
            var runnable = Runnable {
                refresh()
            }
            runnable.run()
        }

    }

    private fun initWorldData() {
        getViewModel().apply {
            getWorldStats().observe(this@MainActivity, Observer {
                when {
                    it.data != null -> {
                        bind.data = it.data
                    }
                    it.error != null -> {
                        Toast.makeText(this@MainActivity, it.error.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                    else -> {
                        Toast.makeText(this@MainActivity, "Null, bro", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun refresh() {

        swipeRefreshLayout.isRefreshing = true
        initWorldData()

        chart1.data = PieData(
            PieDataSet(
                arrayListOf(
                    PieEntry((bind.data?.active ?: 0).toFloat(), "Infected"),
                    PieEntry((bind.data?.deaths ?: 0).toFloat(), "Dead"),
                    PieEntry((bind.data?.recovered ?: 0).toFloat(), "Recovered")
                ), ""
            ).also {
                it.sliceSpace = 3f
                it.iconsOffset = MPPointF((0).toFloat(), (40).toFloat())
                it.selectionShift = 5f
                it.colors = ColorTemplate.createColors(
                    intArrayOf(
                        ColorTemplate.rgb("d50000"),
                        ColorTemplate.rgb("212121"),
                        ColorTemplate.rgb("f5f5f5")
                    )
                )

            }
        )
        swipeRefreshLayout.isRefreshing = false
        chart1.animateXY(1400, 1400)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        val searchBarItem = menu?.findItem(R.id.search_bar)
        val searchBar = searchBarItem?.actionView as SearchView?

        searchBar?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


    override fun getViewModel() =
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


}
