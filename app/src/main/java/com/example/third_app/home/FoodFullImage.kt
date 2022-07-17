package com.example.third_app.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.third_app.R
import com.example.third_app.databinding.ActivityFoodFullImageBinding
import com.example.third_app.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodFullImage : AppCompatActivity(){
//    var userId=null
//    var userName=null
//    var foodData=null
    private lateinit var binding : ActivityFoodFullImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodFullImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemId = intent.getStringExtra("userId")
        var data : ItemFullImage ?= null
        var item : Product ?= null

        // 레트로핏 객체 생성
        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.249.18.195:80")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //itemListService
        var foodFullImageService: FoodFullImageService = retrofit.create(FoodFullImageService::class.java)
        if (itemId != null) {
            foodFullImageService.requestItemList(itemId).enqueue(object : Callback<ItemFullImage> {
                override fun onFailure(call: Call<ItemFullImage>, t: Throwable) {
                    Log.e("FullImage","error : itemList 호출 실패")
                }
                override fun onResponse(call: Call<ItemFullImage>, response: Response<ItemFullImage>) {
                    data = response.body()
                    Log.d("FullImage", "statusCode : "+data?.statusCode.toString())
                    Log.d("FullImage", "Msg:"+data?.statusMsg.toString())
                    if(data!=null){
                        item = data?.data
                        binding.foodMainTitle.text = item?.name
                        Glide.with(binding.foodFullImg)
                            .load(item?.imgUrl)
                            .into(binding.foodFullImg)
                    }
    //                val intent = Intent(context, FoodFullImage::class.java)
    //                intent.putExtra("userId", mDatas[position].id)

                        }
                    })
                }
        }

        //val foodData= intent.getSerializableExtra("foodData") as FoodData
    }


    //class 에 , HomeFragment.OnDataPassListener 추가해야 함!!!
//    override fun onUserIDPass(userId: String?) {
//        System.out.println("FoodFullImage: foodDataName: $userId")
//    }
//
//    override fun onUserNamePass(userName: String?) {
//        System.out.println("FoodFullImage: foodDataName: $userName")
//    }
//    override fun OnDataPass(data: FoodData) {
//        System.out.println("FoodFullImage: foodDataName:  "+data.food_name)
//    } }