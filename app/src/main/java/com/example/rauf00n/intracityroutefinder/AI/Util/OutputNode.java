package com.example.rauf00n.intracityroutefinder.AI.Util;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by RAUF00N on 5/15/2016.
 */


/*
Node represents a physical location.
You may give it a String as name
But the GPS co-ordinate of that location must be an attribute of this class
*/
public class OutputNode {
    // name of the location area
    String name;
    // latitude and longitude
    LatLng position;
    //short message
    String msg;

    public OutputNode(double Lat, double Long)
    {
        position = new LatLng(Lat,Long);
        this.msg = "";
        this.name = "";
    }

    public OutputNode(String name,double Lat, double Long, String msg)
    {
        position = new LatLng(Lat,Long);
        this.msg = msg;
        this.name = name;
    }

    public String getName() {return name;}

    public LatLng getPosition() {return position;}

}
