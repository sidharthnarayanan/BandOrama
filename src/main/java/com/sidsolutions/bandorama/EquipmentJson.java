package com.sidsolutions.bandorama;

public class EquipmentJson {
    String equipment;
    String brand;
    String instrument;
    
    public EquipmentJson() {}
    
    public EquipmentJson(String equipment, String brand, String instrument) {
        this.equipment = equipment;
        this.brand = brand;
        this.instrument = instrument;
    }

    public String getEquipment() {
        return equipment;
    }
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getInstrument() {
        return instrument;
    }
    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
    

}   

