package com.sidsolutions.bandorama;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class WebApp extends Application { 
    private Set<Class<?>> classes = new HashSet<Class<?>>();

    public WebApp() {
        classes.add(BandServer.class);
        BandServer.addInstruments();
    }

    @Override
    public Set<Class<?>> getClasses() {
        return super.getClasses();
    }
}