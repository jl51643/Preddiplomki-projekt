package hr.fer.ruazosa.iotagriculture.net

object RestFactory {

    val BASE_IP = "10.0.2.2"

    val instance : RestInterface

    get() = RestRetrofit()
}