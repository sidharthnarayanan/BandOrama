package com.sidsolutions.bandorama;

public class MouthpieceJson {
    String instrument;
    String equipment;
    
    public MouthpieceJson(String instrument, String equipment) {
        this.instrument = instrument;
        this.equipment = equipment;
    }
    public String getInstrument() {
        return instrument;
    }
    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
    public String getEquipment() {
        return equipment;
    }
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    
}
