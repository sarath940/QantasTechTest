package com.example.qantastechtest.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class QantasDataModel : ArrayList<QantasDataModel.QantasDataModelItem>(){
    @Entity(tableName = "qantas")
    data class QantasDataModelItem(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int? = null,
        @SerializedName("airportCode")
        val airportCode: String? = null,
        @SerializedName("airportName")
        val airportName: String? = null,
        @SerializedName("city")
        val city: City? = null,
        @SerializedName("country")
        val country: Country? = null,
        @SerializedName("domesticAirport")
        val domesticAirport: Boolean? = false,
        @SerializedName("eticketableAirport")
        val eticketableAirport: Boolean? = false,
        @SerializedName("internationalAirport")
        val internationalAirport: Boolean? = false,
        @SerializedName("location")
        val location: Location? = null,
        @SerializedName("onlineIndicator")
        val onlineIndicator: Boolean? = false,
        @SerializedName("preferredInternationalAirportCode")
        val preferredInternationalAirportCode: String? = null,
        @SerializedName("region")
        val region: Region? = null,
        @SerializedName("regionalAirport")
        val regionalAirport: Boolean? = false,
        @SerializedName("state")
        val state: State? = null
    ) :Serializable{
        data class City(
            @SerializedName("cityCode")
            val cityCode: String? = null,
            @SerializedName("cityName")
            val cityName: String? = null,
            @SerializedName("timeZoneName")
            val timeZoneName: String? = null
        )
    
        data class Country(
            @SerializedName("countryCode")
            val countryCode: String? = null,
            @SerializedName("countryName")
            val countryName: String? = null
        )
    
        data class Location(
            @SerializedName("aboveSeaLevel")
            val aboveSeaLevel: Int? = 0,
            @SerializedName("latitude")
            val latitude: Double? = 0.0,
            @SerializedName("latitudeDirection")
            val latitudeDirection: String? = null,
            @SerializedName("latitudeRadius")
            val latitudeRadius: Double? = 0.0,
            @SerializedName("longitude")
            val longitude: Double? = 0.0,
            @SerializedName("longitudeDirection")
            val longitudeDirection: String? = null,
            @SerializedName("longitudeRadius")
            val longitudeRadius: Double? = 0.0
        )
    
        data class Region(
            @SerializedName("regionCode")
            val regionCode: String? = null,
            @SerializedName("regionName")
            val regionName: String? = null
        )
    
        data class State(
            @SerializedName("stateCode")
            val stateCode: String? = null,
            @SerializedName("stateName")
            val stateName: String? = null
        )
    }
}