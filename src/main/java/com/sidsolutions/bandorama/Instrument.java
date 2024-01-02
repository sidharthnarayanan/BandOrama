package com.sidsolutions.bandorama;

public class Instrument {
    String instrument;
    String imageURL;


    
    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

   

    public Instrument(String instrument, String imageURL) {
        this.instrument = instrument;
        this.imageURL = imageURL;
    }
}

 /* FLUTE("Flute", "imageurl"), 
    CLARINET("Clarinet", "imageurl");*/
