package com.example.tddcitygames.ViewModel

import android.os.Environment
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.tddcitygames.model.City
import com.example.tddcitygames.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream

class GameViewModel {
    val mutableList = mutableListOf<City>()
    private var cityListlLivedata = MutableLiveData<List<City>>()
    fun getResult(city1: String): Int {
        return 1
    }
    fun getCityList(){
        RetrofitInstance.cityApi.getCityList().enqueue(object : Callback<List<City>> {
            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                if (response.body() != null){//!! - NotNUll
                    mutableList.addAll(response.body()!!)
                    writeFile("Download/", "Cities.txt", mutableList)
                    Log.d("GameFragment","All good")
                }else{
                    Log.d("GameFragment","Call is empty")
                    return
                }
            }

            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                Log.d("GameFragment",t.message.toString())
            }
        })
    }
    fun writeFile(filePath: String, fileName: String, list:MutableList<City>) {
        try {
            //Создается объект файла, при этом путь к файлу находиться методом Environment
            val myFile = File(Environment.getExternalStorageDirectory().toString() + "/" + filePath + fileName)
            // Создается файл, если он не был создан
            myFile.createNewFile()
            // После чего создаем поток для записи
            val outputStream = FileOutputStream(myFile)
            // Производим непосредственно запись
            for (i in list){
                outputStream.write(i.name.toByteArray())
                outputStream.write("\n".toByteArray())
            }
            // Закрываем поток
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun checkCity(city:String,list:List<String>,prevCity:String):Boolean{
        var res = false
        if (city != null) {
            list.forEach {
                if (it == city) {
                    res = true
                }
            }
        }
        // var a = prevCity[prevCity.length-1].toUpperCase()
        //var b = city[city.length-1]
        if (prevCity != "No" && prevCity[prevCity.length-1].toUpperCase() != city[0]){
            res = false
        }
        return res
    }

}