package com.example.nichenejvercosa.projectoaula

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import android.util.JsonReader



class MyAsyncTask(private val mContext: Context, private val mListener: TaskListener): AsyncTask<URL, Void, String>() {

    private var urlConnection: HttpURLConnection? = null
    private val mDialog: ProgressDialog

    init {
        mDialog = ProgressDialog(mContext)
    }


    override fun onPreExecute() {
        super.onPreExecute()
        mDialog.setTitle(R.string.app_name)
        mDialog.setMessage("Retrieving data...")
        mDialog.show()
    }


    override fun doInBackground(vararg params: URL?): String {
        val result = StringBuilder()
        var messages = mutableListOf<BitCoinNews>()

        try {
            val url = params[0]
            urlConnection = url?.openConnection() as HttpURLConnection
            urlConnection?.doInput = true
            urlConnection?.connectTimeout = 20 * 1000
            urlConnection?.readTimeout = 20 * 1000

            if (urlConnection?.responseCode == HttpURLConnection.HTTP_OK){
                val response = BufferedInputStream(urlConnection?.inputStream)

                val reader = BufferedReader(InputStreamReader(response))

                var line : String?

                do {
                    line = reader.readLine()
                    if (line == null){
                        break
                    }
                    result.append(line)
                }while (true)

            }else if(urlConnection?.responseCode == -1){
                Toast.makeText(mContext, "Erro Http", Toast.LENGTH_SHORT).show()
            }



        }catch (e: Exception){
            e.printStackTrace()
        }finally {
            urlConnection?.disconnect()
        }
        return result.toString()

    }

    fun readMessage(reader: JsonReader) : BitCoinNews{
        var bitCoin = BitCoinNews("", "")


        reader.beginArray();
        while (reader.hasNext()) {
            var name = reader.nextName();
            if (name.equals("title")) {
                bitCoin.title = reader.nextString();
            } else if (name.equals("description")) {
                bitCoin.description = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endArray();

        return bitCoin
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        mDialog.dismiss()
        mListener.onTaskComplete(result)
    }


}

interface TaskListener{
    fun onTaskComplete(s: String)
}