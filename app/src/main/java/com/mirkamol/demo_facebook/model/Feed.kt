package com.mirkamol.demo_facebook.model

class Feed {
    var isHeader:Boolean = false
    var post:Post? = null
    var stories:ArrayList<Story> = ArrayList()
    var posts= arrayListOf<Int>()

    constructor(post:Post){
        this.post = post
    }
    constructor(stories:ArrayList<Story>, posts: ArrayList<Int>){
        this.stories = stories
        this.posts = posts
    }





}