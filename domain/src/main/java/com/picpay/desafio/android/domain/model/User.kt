package com.picpay.desafio.android.domain.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class User(
    @JsonProperty("img") val img: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("id") val id: Int,
    @JsonProperty("username") val username: String
) : Parcelable
