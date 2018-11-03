package com.melodymediation.sleepstories.data.remote

import android.util.Log
import com.google.gson.*
import java.lang.reflect.Type

class MelodyDeserializer<T> : JsonDeserializer<T> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): T = Gson().run {
        Log.d("PageListCateBoundary", json.toString())
        val moviesJsonObject = json?.asJsonObject?.get("content")?.asJsonArray
        fromJson(moviesJsonObject, typeOfT)
    }
}
