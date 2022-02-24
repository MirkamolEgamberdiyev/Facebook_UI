package com.mirkamol.demo_facebook.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.mirkamol.demo_facebook.App
import com.mirkamol.demo_facebook.MainActivity
import com.mirkamol.demo_facebook.MainActivity2
import com.mirkamol.demo_facebook.R
import com.mirkamol.demo_facebook.model.Feed
import com.mirkamol.demo_facebook.model.Posts
import com.mirkamol.demo_facebook.model.Story

class FeedAdapter(var context: MainActivity, var items: ArrayList<Feed>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_ITEM_HEAD = 0
    private val TYPE_ITEM_STORY = 1
    private val TYPE_ITEM_POST = 2
    private val TYPE_ITEM_POST_UPDATE = 3
    private val TYPE_ITEM_POST_MORE_IMAGE = 4


    override fun getItemViewType(position: Int): Int {
        val feed = items[position]

        if (position==0)
            return TYPE_ITEM_HEAD
        else if (feed.stories.size == 1)
            return TYPE_ITEM_STORY
        else if (position == 2) {
            return TYPE_ITEM_POST_UPDATE
        } else if (position == 3 || position == 6) {
            return TYPE_ITEM_POST_MORE_IMAGE
        }
        return TYPE_ITEM_POST
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            TYPE_ITEM_POST_UPDATE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_feed_update_post, parent, false)
                return PostUpdateViewHolder(view)
            }
            TYPE_ITEM_HEAD -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_feed_head, parent, false)
                return HeadViewHolder(context, view)
            }
            TYPE_ITEM_STORY -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_feed_story, parent, false)
                return StoryViewHolder(context, view)
            }
            TYPE_ITEM_POST_MORE_IMAGE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_post_more_image_view, parent, false)
                return PostsViewHolder(context, view)
            }
            else -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_feed_post, parent, false)
                return PostViewHolder(view)
            }
        }



    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feed = items[position]
        if (holder is HeadViewHolder) {
            val editText: EditText = holder.editText

            editText.setOnClickListener {
                context.openSecondActivity()
            }

        }


        if (holder is PostUpdateViewHolder) {

        }

        if (holder is StoryViewHolder) {
            holder.apply {
                if (adapter == null){
                    adapter = StoryAdapter(context, feed.stories)
                    recyclerView.adapter = adapter
                }
            }
//
//            val recyclerView = holder.recyclerView
//            refreshAdapter(feed.stories, recyclerView)
        }

        if (holder is PostsViewHolder) {
            val post = items[position].posts
            if (post.size> 5) {
                holder.tvImageCount.text = "+"+(post.size - 5).toString()
                holder.tvImageCount.visibility = View.VISIBLE
            } else {
                holder.tvImageCount.visibility = View.GONE
            }

            Glide.with(context).load(post[0]).into(holder.post1)
            Glide.with(context).load(post[1]).into(holder.post2)
            Glide.with(context).load(post[2]).into(holder.post3)
            Glide.with(context).load(post[3]).into(holder.post4)
            Glide.with(context).load(post[4]).into(holder.post5)
        }

        if (holder is PostViewHolder) {
            var iv_profile = holder.iv_profile
            var iv_photo = holder.iv_photo
            var tv_fullname = holder.tv_fullname

            Glide.with(context).load(feed.post!!.profile).into(iv_profile)
            Glide.with(context).load(feed.post!!.photo).into(iv_photo)
            tv_fullname.text = feed.post!!.fullname

            // iv_profile.setImageResource(feed.post!!.profile)
            //iv_photo.setImageResource(feed.post!!.photo)
        }
    }

//    fun refreshAdapter(stories: ArrayList<Story>, recyclerView: RecyclerView) {
//        val adapter = StoryAdapter(context, stories)
//        recyclerView.adapter = adapter
//    }



    override fun getItemCount(): Int {
        return items.size
    }


    inner class HeadViewHolder(context: Context, view: View) : RecyclerView.ViewHolder(view) {
        val editText: EditText = view.findViewById(R.id.edit_text)


    }

    inner class StoryViewHolder(context: Context, view: View) : RecyclerView.ViewHolder(view) {
        var recyclerView: RecyclerView
         var adapter:StoryAdapter? = null
        init {
            recyclerView = view.findViewById(R.id.recyclerView)
            val manager = LinearLayoutManager(App.instance.applicationContext, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = manager
        }
    }

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var iv_profile: ShapeableImageView
        var iv_photo: ImageView
        var tv_fullname: TextView

        init {
            iv_profile = view.findViewById(R.id.iv_profile)
            iv_photo = view.findViewById(R.id.iv_photo)
            tv_fullname = view.findViewById(R.id.tv_fullname)
        }
    }

    inner class PostUpdateViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    inner class PostsViewHolder(context: Context, view: View) : RecyclerView.ViewHolder(view) {
        var post1 = view.findViewById<ImageView>(R.id.post_1)
        val post2 = view.findViewById<ImageView>(R.id.post_2)
        val post3 = view.findViewById<ImageView>(R.id.post_3)
        val post4 = view.findViewById<ImageView>(R.id.post_4)
        val post5 = view.findViewById<ImageView>(R.id.post_5)
        val tvImageCount = view.findViewById<TextView>(R.id.tv_imageCount)




    }


}