package com.example.rauf00n.intracityroutefinder.AI.XMLObjects;

import java.util.ArrayList;

/**
 * Created by RAUF00N on 5/16/2016.
 */
public class Edge {
    public int Src;
    public int Dest;
    public TrafficFactor trafficFactor;
    public ArrayList bus_ids = new ArrayList();
    public int distance;

    public Edge(int Src,int Dest,TrafficFactor trafficFactor,ArrayList bus_ids,int distance)
    {
        this.Src = Src;
        this.Dest = Dest;
        this.trafficFactor = trafficFactor;
        this.bus_ids.addAll(bus_ids);
        this.distance = distance;
    }

}
