package com.jitusolution.week8materialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_add_playlist.*
import org.json.JSONObject

class AddPlaylistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_playlist)

        buttonCancel.setOnClickListener {
            finish()
        }

        buttonSubmit.setOnClickListener {
            if (editTitle.text.toString().isEmpty()
                || editSubtitle.text.toString().isEmpty()
                || editDescription.text.toString().isEmpty()
                || editUrl.text.toString().isEmpty()) {
                Toast.makeText(this, "All field must be filled", Toast.LENGTH_SHORT).show()
            } else {
                val q = Volley.newRequestQueue(this)
                val url = "https://ubaya.fun/flutter/ferdi/music/add_playlist.php"
                val stringRequest = object: StringRequest(
                    Request.Method.POST, url,
                    {
                        Log.d("apiresult", it)
                        val obj = JSONObject(it)
                        if (obj.getString("result") == "OK") {
                            finish()
                        }
                    },
                    {
                        Log.e("apiresult", it.message.toString())
                    }
                ) {
                    override fun getParams(): MutableMap<String, String>? =hashMapOf(
                        "title" to editTitle.text.toString(),
                        "subtitle" to editSubtitle.text.toString(),
                        "description" to editDescription.text.toString(),
                        "url" to editUrl.text.toString()
                    )
                }

                q.add(stringRequest)
            }
        }
    }
}