package com.sidsolutions.bandorama;

public class BandUser {
    String username;
    String password;
    
    EquipmentJson equipJson;
    
    public BandUser(String username, String password){
        this.username = username.trim();
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public EquipmentJson getEquipJson() {
        return equipJson;
    }
    public void setEquipJson(EquipmentJson equipJson) {
        this.equipJson = equipJson;
    }
}


