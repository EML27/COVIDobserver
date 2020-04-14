package com.itis.covidobserver.view


import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.itis.covidobserver.App
import com.itis.covidobserver.R
import com.itis.covidobserver.databinding.ActivityMainBinding
import com.itis.covidobserver.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    @Inject
    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initWorldData()
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)

        swipeRefreshLayout.setOnRefreshListener {
            Runnable {
                refresh()
            }.run()
        }
        refresh()
    }


    private fun initWorldData() {
        viewModel.apply {
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
                        ColorTemplate.rgb("757575"),
                        ColorTemplate.rgb("FFFFFF")
                    )
                )

            }
        ).also {
            it.setValueTextColor(Color.BLACK)
            it.setValueTextSize(11f)

        }
        chart1.description.isEnabled = false
        chart1.centerText = "Total: ${bind.data?.cases ?: 0}"
        chart1.setEntryLabelColor(Color.BLACK)
        chart1.setEntryLabelTextSize(11f)
        swipeRefreshLayout.isRefreshing = false
        chart1.animateXY(1400, 1400)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        val searchBarItem = menu?.findItem(R.id.search_bar)
        val searchBar = searchBarItem?.actionView as SearchView?

        searchBar?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.apply {
                    getQueryResponse(p0 ?: "").observe(this@MainActivity, Observer {
                        when {
                            it.data != null -> {
                                startActivity(
                                    CountryActivity.createIntent(
                                        this@MainActivity,
                                        it.data.country
                                    )
                                )
                            }
                            it.error != null -> {
                                Toast.makeText(
                                    this@MainActivity,
                                    "No such country found",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }



}
