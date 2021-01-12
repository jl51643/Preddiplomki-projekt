package hr.fer.projekt.smartAgriculture.model

data class CultureModel (
    private val cultureId : Long,
    private val title: String,
    private val devices:  List<DeviceModel>,
    private val description: String
)
