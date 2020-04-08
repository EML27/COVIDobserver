package com.itis.covidobserver.net.responses


import com.google.gson.annotations.SerializedName

data class WorldResponse(
    @SerializedName("active")
    var active: Int,
    @SerializedName("affectedCountries")
    var affectedCountries: Int,
    @SerializedName("cases")
    var cases: Int,
    @SerializedName("casesPerOneMillion")
    var casesPerOneMillion: Double,
    @SerializedName("critical")
    var critical: Int,
    @SerializedName("deaths")
    var deaths: Int,
    @SerializedName("deathsPerOneMillion")
    var deathsPerOneMillion: Double,
    @SerializedName("recovered")
    var recovered: Int,
    @SerializedName("tests")
    var tests: Int,
    @SerializedName("testsPerOneMillion")
    var testsPerOneMillion: Double,
    @SerializedName("todayCases")
    var todayCases: Int,
    @SerializedName("todayDeaths")
    var todayDeaths: Int,
    @SerializedName("updated")
    var updated: Long
)
