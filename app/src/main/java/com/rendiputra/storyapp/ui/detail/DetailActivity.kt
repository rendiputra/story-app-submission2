package com.rendiputra.storyapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.rendiputra.storyapp.R
import com.rendiputra.storyapp.databinding.ActivityDetailBinding
import com.rendiputra.storyapp.domain.Response
import com.rendiputra.storyapp.domain.Story
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val detailViewModel: DetailViewModel by viewModels()

    private lateinit var storyId: String
    private lateinit var storyName: String
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storyId = intent.getStringExtra(EXTRA_ID) ?: ""
        storyName = intent.getStringExtra(EXTRA_NAME) ?: ""
        token = intent.getStringExtra(EXTRA_TOKEN) ?: ""

        setupToolbar()
        observeDetailStory()
    }

    private fun setupToolbar() {
        binding.toolbar.title = storyName
        binding.toolbar.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun setupDetailStory(story: Story) {
        binding.ivStory.load(story.photoUrl) {
            placeholder(R.drawable.ic_image)
            placeholder(R.drawable.ic_image_error)
        }
        binding.tvName.text = story.name
        binding.tvDescription.text = story.description
    }

    private fun observeDetailStory() {
        detailViewModel.getDetailStory("Bearer $token", storyId)
            .observe(this) { response ->
                when (response) {
                    is Response.Loading -> toggleLoading(true)
                    is Response.Empty -> toggleLoading(false)
                    is Response.Success -> {
                        toggleLoading(false)
                        setupDetailStory(response.data)
                    }
                    is Response.Error -> {
                        toggleLoading(false)
                        binding.ivStory.setImageResource(R.drawable.ic_image_error)
                    }
                }
            }
    }

    private fun toggleLoading(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_TOKEN = "extra_token"
    }
}