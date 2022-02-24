package com.mirkamol.demo_facebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mirkamol.demo_facebook.adapter.FeedAdapter
import com.mirkamol.demo_facebook.model.Feed
import com.mirkamol.demo_facebook.model.Post
import com.mirkamol.demo_facebook.model.Posts
import com.mirkamol.demo_facebook.model.Story

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        refreshAdapter(getAllFeeds())



    }

    private fun refreshAdapter(feeds:ArrayList<Feed>) {
        val adapter = FeedAdapter(this, feeds)
        recyclerView.adapter = adapter

    }

    private fun getAllFeeds():ArrayList<Feed>{
        val stories = ArrayList<Story>()
        stories.add(Story(R.drawable.im_user, "Mirkamol Egamberdiyev"))
        stories.add(Story(R.drawable.im_user, "Mirkamol Egamberdiyev"))
        stories.add(Story(R.drawable.im_user, "Mirkamol Egamberdiyev"))
        stories.add(Story(R.drawable.im_user, "Mirkamol Egamberdiyev"))
        stories.add(Story(R.drawable.im_user, "Mirkamol Egamberdiyev"))
        stories.add(Story(R.drawable.im_user, "Mirkamol Egamberdiyev"))


        val feeds = ArrayList<Feed>()

        val posts=arrayListOf(
            R.drawable.im_post,
            R.drawable.im_post,
            R.drawable.im_post,
            R.drawable.im_post,
            R.drawable.im_post,
            R.drawable.im_post,
            R.drawable.im_post,
            R.drawable.im_post,
            R.drawable.im_post)


        feeds.add(Feed(Post(R.drawable.im_user, "Mirkamol Egamberdiyev", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.im_user, "Mirkamol Egamberdiyev", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.im_user, "Mirkamol Egamberdiyev", R.drawable.im_post)))
        feeds.add(Feed(stories, posts))
        feeds.add(Feed(Post(R.drawable.im_user, "Mirkamol Egamberdiyev", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.im_user, "Mirkamol Egamberdiyev", R.drawable.im_post)))
        feeds.add(Feed(stories, posts))
        feeds.add(Feed(Post(R.drawable.im_user, "Mirkamol Egamberdiyev", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.im_user, "Mirkamol Egamberdiyev", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.im_user, "Mirkamol Egamberdiyev", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.im_user, "Mirkamol Egamberdiyev", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.im_user, "Mirkamol Egamberdiyev", R.drawable.im_post)))
        feeds.add(Feed(Post(R.drawable.im_user, "Mirkamol Egamberdiyev", R.drawable.im_post)))

        return feeds
    }

    fun openSecondActivity(){
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }


}