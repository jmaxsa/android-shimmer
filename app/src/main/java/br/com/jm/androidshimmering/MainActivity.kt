package br.com.jm.androidshimmering

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val friendsPictureContent by lazy { findViewById<ShimmerFrameLayout>(R.id.content_story_shimmer) }
    private val postsContent by lazy { findViewById<ShimmerFrameLayout>(R.id.content_posts) }
    private val content by lazy { findViewById<TextView>(R.id.after_load_content) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* NEVER use a Delay to close your loading screen.
           The best way is to use a callback from some request you are making

           it's a simple example to simulate a back-end request time. */
        lifecycleScope.launch {
            delay(3000)
            finishLoading()
        }
    }

    private fun finishLoading() {
        friendsPictureContent.apply {
            stopShimmer()
            isGone = true
        }
        postsContent.apply {
            stopShimmer()
            isGone = true
        }

        content.isVisible = true
    }
}