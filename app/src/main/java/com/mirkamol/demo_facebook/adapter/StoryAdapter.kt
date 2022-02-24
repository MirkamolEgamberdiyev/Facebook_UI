package com.mirkamol.demo_facebook.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.mirkamol.demo_facebook.R
import com.mirkamol.demo_facebook.model.Story

class StoryAdapter(var context: Context, var items: ArrayList<Story>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ITEM_CREATE = 0
    private val TYPE_ITEM_STORY = 1

    override fun getItemViewType(position: Int): Int {
        if (position == 0){
          return  TYPE_ITEM_CREATE
        }
        return TYPE_ITEM_STORY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM_CREATE){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_create_story, parent, false)
            return  CreateViewHolder(view)
        }
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_story_view, parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val story = items[position]
        if (holder is StoryViewHolder){
            var iv_background = holder.iv_backround
            var iv_profile = holder.iv_profile
            var tv_fullname = holder.tv_fullname


            Glide.with(context).load(story.profile).into(iv_background)
            Glide.with(context).load(story.profile).into(iv_profile)
            tv_fullname.text = story.fullname

            // iv_background.setImageResource(story.profile)
            // iv_profile.setImageResource(story.profile)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class StoryViewHolder(view:View):RecyclerView.ViewHolder(view){
        var iv_backround:ShapeableImageView
        var iv_profile:ShapeableImageView
        var tv_fullname:TextView

        init {
            iv_backround = view.findViewById(R.id.iv_backround)
            iv_profile = view.findViewById(R.id.iv_profile)
            tv_fullname = view.findViewById(R.id.tv_fullname)
        }
    }

    inner class CreateViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

}