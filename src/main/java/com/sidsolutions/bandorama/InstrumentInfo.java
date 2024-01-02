package com.sidsolutions.bandorama;

public class InstrumentInfo {
    String username;
    EquipmentJson equipment;


    public InstrumentInfo(String username, EquipmentJson equipment) {
        this.username = username;
        this.equipment = equipment;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public EquipmentJson getEquipment() {
        return equipment;
    }


    public void setEquipment(EquipmentJson equipment) {
        this.equipment = equipment;
    }

    
    
}
