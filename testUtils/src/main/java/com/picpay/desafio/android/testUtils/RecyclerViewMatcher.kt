package com.picpay.desafio.android.testUtils

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class RecyclerViewMatcher private constructor(private val recyclerViewId: Int) {

    companion object {

        fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
            return RecyclerViewMatcher(recyclerViewId)
        }
    }

    fun atPosition(position: Int): Matcher<View> {
        return atPositionOnView(position, -1)
    }

    private fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            var resources: Resources? = null
            var childView: View? = null

            override fun describeTo(description: Description) {
                var idDescription = Integer.toString(recyclerViewId)
                if (this.resources != null) {
                    try {
                        idDescription = this.resources!!.getResourceName(recyclerViewId)
                    } catch (var4: Resources.NotFoundException) {
                        idDescription = String.format(
                            "%s (resource name not found)",
                            *arrayOf<Any>(
                                Integer.valueOf(recyclerViewId)
                            )
                        )
                    }
                }

                description.appendText("with id: $idDescription")
            }

            public override fun matchesSafely(view: View): Boolean {

                this.resources = view.resources

                if (childView == null) {
                    val recyclerView =
                        view.rootView.findViewById<View>(recyclerViewId) as RecyclerView
                    if (recyclerView.id == recyclerViewId) {
                        val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                        if (viewHolder != null) {
                            childView = viewHolder.itemView
                        }
                    } else {
                        return false
                    }
                }

                if (targetViewId == -1) {
                    return view === childView
                } else {
                    val targetView = childView!!.findViewById<View>(targetViewId)
                    return view === targetView
                }
            }
        }
    }
}
