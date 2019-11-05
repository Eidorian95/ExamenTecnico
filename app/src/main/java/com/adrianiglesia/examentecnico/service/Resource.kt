package com.adrianiglesia.examentecnico.service

data class Resource(val status: ResponseCode, val data: Any?, val message: String?) {
    companion object {
        fun success(data: Any?): Resource {
            return Resource(ResponseCode.OK, data, null)
        }

        fun error(msg: String, data: Any?): Resource {
            return Resource(ResponseCode.SERVICE_ERROR, data, msg)
        }

        fun loading(data: Any?): Resource{
            return Resource(ResponseCode.LOADING, data, null)
        }
    }
}
enum class ResponseCode {
    OK, SERVICE_ERROR, LOADING
}