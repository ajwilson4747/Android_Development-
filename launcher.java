package com.example.intentlab1;

public class launcher {
    private final String location;
    private final String closeIn;
    public launcher(){
        location = "34.04274913265226, -117.15937542913956";
        closeIn = "3";
    }

    public String getLocation(){
        return "geo: " + location + "," + closeIn;
    }
}
