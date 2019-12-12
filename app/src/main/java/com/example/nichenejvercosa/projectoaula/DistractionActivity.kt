package com.example.nichenejvercosa.projectoaula

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import java.net.URL
import org.json.JSONException
import org.json.JSONObject




class DistractionActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BitCoinAdapter
    private lateinit var asyncTask: MyAsyncTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distraction)

        recyclerView = findViewById<RecyclerView>(R.id.rcl_view_bit_coin)
        recyclerView.layoutManager = LinearLayoutManager(this)



        asyncTask = MyAsyncTask(this, object : TaskListener{

            override fun onTaskComplete(s: String) {
                var list = parseResult(s)
                adapter = BitCoinAdapter(list)
                recyclerView.adapter = adapter
            }
        })
        asyncTask.execute(
                URL("https://newsapi.org/v2/top-headlines?sources=google-news-br&apiKey=253924b74eec4d20bb501667a78a029b")
        )



    }

    private fun parseResult(result: String): MutableList<BitCoinNews> {
        var list = kotlin.collections.mutableListOf<BitCoinNews>()
        try {
            val response = JSONObject(result)
            val posts = response.optJSONArray("articles")
            var item: BitCoinNews

            for (i in 0 until posts.length()) {
                val post = posts.optJSONObject(i)
                val title = post.optString("title")
                val description = post.optString("description")
                item = BitCoinNews(title, description)

                list.add(item)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        when (item.getItemId()) {
            R.id.distracao -> {
                return true
            }
            R.id.activityList -> {
                goToListActivity()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun goToListActivity(){
        val intentEnterList = Intent(this, ActivityList::class.java)
        startActivity(intentEnterList)
    }
}
