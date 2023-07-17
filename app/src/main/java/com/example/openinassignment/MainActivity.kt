package com.example.openinassignment

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceDataStore
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openinassignment.Adapters.DataAdapter1
import com.example.openinassignment.Adapters.RecentLinkAdapter
import com.example.openinassignment.Adapters.Repository
import com.example.openinassignment.Adapters.TopLinkAdapter
import com.example.openinassignment.Data.*
import com.example.openinassignment.RetrofitObject.getRetrofitObj
import com.example.openinassignment.databinding.ActivityMainBinding
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import java.util.Calendar
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {

   private lateinit var binding:ActivityMainBinding
         var result:Response<Hello>?=null

  private lateinit var viewModels:ViewModelRetro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences=getSharedPreferences("MY_TOKEN",Context.MODE_PRIVATE)
        var editor=sharedPreferences.edit()
        editor.putString("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI")
        editor.commit()

        val pass=sharedPreferences.getString("token",null)
        greeting()

        val interceptor=HttpLoggingInterceptor()
        interceptor.level=HttpLoggingInterceptor.Level.BODY
        val okHttpClientobj=OkHttpClient.Builder().addInterceptor(AuthInterceptor(
            "Bearer",pass!!
        )).addInterceptor(interceptor).build()
        val retrofit=getRetrofitObj(okHttpClientobj).create(ApiService::class.java)
        val repo=Repository(retrofit)
        viewModels=ViewModelProvider(this,viewModelFactory(repo)).get(ViewModelRetro::class.java)

        GlobalScope.launch {
            result =viewModels!!.getData()
//
            runOnUiThread(Runnable() {
                run {
                    setRecyclerView(result!!)
                    binding.btnTopLinks.setOnClickListener{
                       setRecyclerViewTop(result!!)
                    }
                    binding.btnRecentLinks.setOnClickListener{
                        setRecyclerViewRecent(result!!)
                    }
                }
            })


        }






    }







    private fun greeting() {
      val calendar=Calendar.getInstance()
        val time=calendar.get(Calendar.HOUR_OF_DAY)
        if(time>0 && time<12) binding.tvGreeting.text="Good Morning"
        else if(time>=12 && time<17) binding.tvGreeting.text="Good Afternoon"
        else if(time>=17 && time<21) binding.tvGreeting.text="Good Evening"
        else binding.tvGreeting.text="Good night"
    }

    private fun setRecyclerViewRecent(result: Response<Hello>) {
  val recentArray=ArrayList<RecentLink>()
        for(i in result.body()!!.data.recent_links)
        {
            recentArray.add(RecentLink(i.original_image,i.times_ago,i.title,i.total_clicks,i.web_link))
        }
        recy_view2.layoutManager=LinearLayoutManager(this)
        recy_view2.adapter= RecentLinkAdapter(this,recentArray)
    }

    private fun setRecyclerViewTop(result: Response<Hello>) {
     val topArray=ArrayList<TopLink>()
     for(i in result.body()!!.data.top_links)
     {
         topArray.add(TopLink(i.original_image,i.times_ago,i.title,i.total_clicks,i.web_link))
     }
        recy_view2.layoutManager=LinearLayoutManager(this)
        recy_view2.adapter=TopLinkAdapter(this,topArray)
    }

    private fun setRecyclerView(r:Response<Hello>) {
        val arr=ArrayList<dataClass1>()
        arr.add(dataClass1("Today's clicks",r.body()!!.today_clicks.toString()))
        arr.add(dataClass1("Top Location",r.body()!!.top_location))
        arr.add(dataClass1("Top Source",r.body()!!.top_source))
        arr.add(dataClass1("Best Time",r.body()!!.startTime))

      recy_view.layoutManager=LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
      recy_view.adapter=DataAdapter1(this,arr)
    }


}