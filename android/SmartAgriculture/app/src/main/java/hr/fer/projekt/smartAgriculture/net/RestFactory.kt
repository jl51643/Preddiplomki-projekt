package hr.fer.projekt.smartAgriculture.net

object RestFactory {

    val BASE_IP = "0b87ca3d76e9.ngrok.io"//"10.0.2.2"

    val instance : RestInterface

    get() = RestRetrofit()
}