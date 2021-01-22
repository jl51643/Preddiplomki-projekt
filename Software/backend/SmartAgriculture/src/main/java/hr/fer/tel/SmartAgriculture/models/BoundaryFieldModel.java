package hr.fer.tel.SmartAgriculture.models;

public class BoundaryFieldModel {

    private String type;

    private boolean isMax;

    private Long cultureId;

    public BoundaryFieldModel() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMax() {
        return isMax;
    }

    public void setMax(boolean max) {
        isMax = max;
    }

    public Long getCultureId() {
        return cultureId;
    }

    public void setCultureId(Long cultureId) {
        this.cultureId = cultureId;
    }
}
