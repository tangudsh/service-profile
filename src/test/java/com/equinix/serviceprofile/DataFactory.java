package com.equinix.serviceprofile;


public class DataFactory {

    public static String getSpeedCreatePayload() {
        return "[{\"speed\":10,\"uom\":\"GBPS\"}]";
    }

    public static String getMetroPayload() {
        return "[{\"metro\":\"SV\"}]";
    }

    public static String getServiceProfilePayload(){
        return "{\"name\":\"abc\",\"serviceProfileSpeeds\":[{\"id\":1,\"speed\":10,\"uom\":\"GBPS\"}],\"serviceProfileMetros\":[{\"id\":2,\"metro\":\"SV\"}]}";
    }

}
