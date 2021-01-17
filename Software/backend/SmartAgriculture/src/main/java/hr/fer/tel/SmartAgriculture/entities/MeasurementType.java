package hr.fer.tel.SmartAgriculture.entities;

public enum MeasurementType {
    SOIL_TEMPERATURE("temperature zemlje"),
    AIR_TEMPERATURE("temperature zraka"),
    SOIL_HUMIDITY("vlage zemlje"),
    AIR_HUMIDITY("vlage zraka"),
    PRESSURE("tlaka"),
    LUMINOSITY("osvjetljenja");

    String text;

    MeasurementType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
