package hr.fer.ruazosa.iotagriculture.net

import retrofit.RestAdapter

class RestRetrofit : RestInterface{

    private val service : Service

    init {
        val baseURL = "http://" + RestFactory.BASE_IP + ":8080/api/"
        val retrofit = RestAdapter.Builder()
            .setEndpoint(baseURL)
            .build()

        service = retrofit.create(Service::class.java)
    }
}