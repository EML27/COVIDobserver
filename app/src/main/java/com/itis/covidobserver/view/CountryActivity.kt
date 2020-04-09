package com.itis.covidobserver.view

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
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
import com.itis.covidobserver.databinding.ActivityCountryBinding
import com.itis.covidobserver.viewmodel.CountryViewModel
import com.itis.covidobserver.viewmodel.SuperViewModel
import kotlinx.android.synthetic.main.activity_country.*

class CountryActivity : SuperActivity<SuperViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        initData()
        bind = DataBindingUtil.setContentView(this, R.layout.activity_country)
        detailed_refresh_layout.setOnRefreshListener {
            Runnable { refresh() }.run()
        }
        refresh()
    }



    private lateinit var bind: ActivityCountryBinding

    override fun getViewModel() =
        ViewModelProvider(this, viewModelFactory).get(CountryViewModel::class.java)

    fun initData() {
        getViewModel().apply {
            getCountryResponse(
                intent?.extras?.getString(COUNTRY_NAME) ?: ""
            ).observe(this@CountryActivity,
                Observer {
                    when {
                        it.data != null -> {
                            bind.data = it.data
                        }
                        it.error != null -> {
                            Toast.makeText(this@CountryActivity, "Error, bro", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                })
        }
    }

    companion object {

        private const val COUNTRY_NAME = "countryName"

//        fun createIntent(activity: Activity, countryResponse: CountryResponse) {
//            createIntent(activity,countryResponse.country)
//        }

        fun createIntent(activity: Activity, countryName: String) =
            Intent(activity, CountryActivity::class.java).apply {
                putExtra(
                    COUNTRY_NAME, countryName
                )
            }

    }

    private fun refresh() {

        detailed_refresh_layout.isRefreshing = true
        initData()

        chart_country.data = PieData(
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
        chart_country.description.isEnabled = false
        chart_country.centerText = "Total: ${bind.data?.cases ?: 0}"
        chart_country.setEntryLabelColor(Color.BLACK)
        chart_country.setEntryLabelTextSize(11f)
        detailed_refresh_layout.isRefreshing = false
        chart_country.animateXY(1400, 1400)
    }
}
