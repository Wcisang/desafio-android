package com.picpay.desafio.android.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import com.picpay.desafio.android.domain.model.User
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) = with(itemView){
        name.text = user.name
        username.text = user.username
        loadUserImage(user.img, itemView)
    }

    private fun loadUserImage(imageUrl: String, itemView: View) = with(itemView){
        val imageLoader = ImageLoader.Builder(context)
            .crossfade(true)
            .build()

        val request = createImageLoadRequest(imageUrl, itemView)
        imageLoader.enqueue(request)
    }

    private fun createImageLoadRequest(imageUrl: String, itemView: View) : ImageRequest {
        with(itemView) {
            return ImageRequest.Builder(context)
                .data(imageUrl)
                .target(
                    onStart = { placeholder ->
                        picture.setImageDrawable(placeholder)
                        progressBar.visibility = View.VISIBLE
                    },
                    onSuccess = { result ->
                        progressBar.visibility = View.GONE
                        picture.setImageDrawable(result)
                    },
                    onError = { error ->
                        picture.setImageDrawable(error)
                        progressBar.visibility = View.GONE
                    }
                ).build()
        }
    }
}