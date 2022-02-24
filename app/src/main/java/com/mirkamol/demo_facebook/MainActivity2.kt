package com.mirkamol.demo_facebook

import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val enterUrl = findViewById<TextView>(R.id.edt_text)
        //val title = findViewById<TextView>(R.id.tv_title)
        val description = findViewById<TextView>(R.id.tv_description)
        val btnCancel = findViewById<ImageView>(R.id.cancel)
        val btnPost = findViewById<Button>(R.id.btn_post)



        enterUrl.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (Patterns.WEB_URL.matcher(enterUrl.text.toString()).matches()) {

                    MyTask(enterUrl.toString())
                } else {

                    enterUrl.error = "Invalid Url"
                }
//                imageView.visibility = View.VISIBLE;
//                btnCancel.visibility = View.VISIBLE
//                title.visibility = View.VISIBLE
//                description.visibility = View.VISIBLE
//                btnPost.setBackgroundColor(0xFF4A0DD3.toInt())

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })


    }


    private inner class MyTask(private val urlComing: String) : AsyncTask<Void?, Void?, String?>() {

        var title: String? = null
        var image: String? = null
        var url: String? = null

        override fun doInBackground(vararg p0: Void?): String? {

            val doc: Document

            try {
                doc = Jsoup.connect(urlComing).get()
                Log.d("TAGDOC", "doInBackground: ${doc.head().html()}")

                val metaPropertiesOgTags = doc.select("meta[property^=og:]")
                metaPropertiesOgTags.forEachIndexed { index, _ ->
                    val tag = metaPropertiesOgTags[index]
                    when (tag.attr("property")) {
                        "og:image" -> image = tag.attr("content")
                        "og:url" -> url = tag.attr("content")
                        "og:title" -> title = tag.attr("content")
                    }
                }

                Log.d("TAGTAG", "doInBackground: ${doc.head().html()}")

                Log.d("image", " $image")
                Log.d("title", "doInBackground: $title")
                Log.d("url", "doInBackground: $url")

            } catch (e: IOException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(result: String?) {
            //if you had a ui element, you could display the title
        }
    }


}