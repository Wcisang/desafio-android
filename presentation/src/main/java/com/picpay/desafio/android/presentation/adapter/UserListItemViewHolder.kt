package com.picpay.desafio.android.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.presentation.R
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {
        itemView.name.text = user.name
        itemView.username.text = user.username
        itemView.progressBar.visibility = View.VISIBLE
        itemView.picture.load(user.img) {
            error(R.drawable.ic_round_account_circle)
        }

        val imageLoader = ImageLoader.Builder(itemView.context)
            .crossfade(true)
            .build()

        val request = ImageRequest.Builder(itemView.context)
            .data(user.img)
            .placeholder(R.drawable.ic_round_account_circle)
            .error(R.drawable.ic_round_account_circle)
            .target(
                onStart = { placeholder ->
                    itemView.picture.setImageDrawable(placeholder)
                    itemView.progressBar.visibility = View.VISIBLE
                },
                onSuccess = { result ->
                    itemView.progressBar.visibility = View.GONE
                    itemView.picture.setImageDrawable(result)
                },
                onError = { error ->
                    itemView.picture.setImageDrawable(error)
                    itemView.progressBar.visibility = View.GONE
                }
            ).build()
        imageLoader.enqueue(request)
    }
}