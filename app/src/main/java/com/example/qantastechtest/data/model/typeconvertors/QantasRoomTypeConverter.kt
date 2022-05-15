package com.example.qantastechtest.data.model.typeconvertors

import androidx.room.TypeConverter
import com.example.qantastechtest.data.model.QantasDataModel.QantasDataModelItem.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class QantasRoomTypeConverter {

    val gson = Gson()

    //state
    @TypeConverter
    fun stringToState(data: String?): State? {
        if (data == null) {
            return State()
        }
        val state: Type =
            object :
                TypeToken<State>() {}.type
        return gson.fromJson<State>(
            data,
            state
        )
    }

    @TypeConverter
    fun stateToString(state: State?): String? {
        return gson.toJson(state)
    }

    //city

    @TypeConverter
    fun stringToCity(data: String?): City? {
        if (data == null) {
            return City()
        }
        val city: Type =
            object :
                TypeToken<City>() {}.type
        return gson.fromJson<City>(
            data,
            city
        )
    }

    @TypeConverter
    fun cityToString(city: City?): String? {
        return gson.toJson(city)
    }


    //region
    @TypeConverter
    fun stringToRegion(data: String?): Region? {
        if (data == null) {
            return Region()
        }
        val region: Type =
            object :
                TypeToken<Region>() {}.type
        return gson.fromJson<Region>(
            data,
            region
        )
    }

    @TypeConverter
    fun regionToString(region: Region?): String? {
        return gson.toJson(region)
    }


   //location
    @TypeConverter
    fun stringToLocation(data: String?): Location? {
        if (data == null) {
            return Location()
        }
        val location: Type =
            object :
                TypeToken<Location>() {}.type
        return gson.fromJson<Location>(
            data,
            location
        )
    }

    @TypeConverter
    fun locationToString(location: Location?): String? {
        return gson.toJson(location)
    }


    //country

    @TypeConverter
    fun stringToCountry(data: String?): Country? {
        if (data == null) {
            return Country()
        }
        val country: Type =
            object :
                TypeToken<Country>() {}.type
        return gson.fromJson<Country>(
            data,
            country
        )
    }

    @TypeConverter
    fun countryToString(country: Country?): String? {
        return gson.toJson(country)
    }
}
