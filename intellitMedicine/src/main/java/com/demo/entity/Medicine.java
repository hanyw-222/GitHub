package com.demo.entity;

public class Medicine {

    private int medicineId;
    private String medicineName;
    private String medicineEffect;
    private String medicinePro;
    private String headAdd;
    private String medicineLabel;

    public String getMedicineLabel() {
        return medicineLabel;
    }

    public void setMedicineLabel(String medicineLabel) {
        this.medicineLabel = medicineLabel;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineEffect() {
        return medicineEffect;
    }

    public void setMedicineEffect(String medicineEffect) {
        this.medicineEffect = medicineEffect;
    }

    public String getMedicinePro() {
        return medicinePro;
    }

    public void setMedicinePro(String medicinePro) {
        this.medicinePro = medicinePro;
    }

    public String getHeadAdd() {
        return headAdd;
    }

    public void setHeadAdd(String headAdd) {
        this.headAdd = headAdd;
    }

    @Override
    public String toString() {
        return "medicine{" +
                "medicineId=" + medicineId +
                ", medicineName='" + medicineName + '\'' +
                ", medicineEffect='" + medicineEffect + '\'' +
                ", medicinePro='" + medicinePro + '\'' +
                ", headAdd='" + headAdd + '\'' +
                '}';
    }
}
