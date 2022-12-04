package com.rendiputra.storyapp.data.network.response

import com.google.gson.annotations.SerializedName

data class CommonNetworkErrorResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("error")
	val error: Boolean? = null

)
