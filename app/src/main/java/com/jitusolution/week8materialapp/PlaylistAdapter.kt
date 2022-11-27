package com.jitusolution.week8materialapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_playlist.view.*

class PlaylistAdapter(private val playlists: ArrayList<Playlist>): RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {
    class PlaylistViewHolder(val v: View): RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_playlist, parent, false)

        return PlaylistViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = playlists[position]
        val url = playlist.image_url

        Picasso.get().load(url).into(holder.v.imagePlaylist)
        holder.v.textPlaylistName.text = playlist.title
        holder.v.textSecondary.text = playlist.subtitle
        holder.v.textSupport.text = playlist.subtitle
        holder.v.buttonLike.text = playlist.num_likes.toString()

        holder.v.buttonLike.setOnClickListener {
            var q = Volley.newRequestQueue(holder.v.context)
            val url = "https://ubaya.fun/flutter/ferdi/music/set_likes.php"
            val stringRequest = object: StringRequest(
                Request.Method.POST, url,
                Response.Listener<String> {
                    Log.d("cekparams", it)
                    playlist.num_likes++
                    var newLikes = playlist.num_likes
                    holder.v.buttonLike.text = "$newLikes LIKES"
                },
                Response.ErrorListener {
                    Log.e("cekparams", it.message.toString())
                }
            ) {
                override fun getParams(): MutableMap<String, String>? = hashMapOf(
                    "id" to playlist.id.toString()
                )
            }

            q.add(stringRequest)
        }
    }

    override fun getItemCount(): Int = playlists.size
}