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

    public Edge(int Src,int Dest,TrafficFactor trafficFactor)
    {
        this.Src = Src;
        this.Dest = Dest;
        this.trafficFactor = trafficFactor;
    }

}
