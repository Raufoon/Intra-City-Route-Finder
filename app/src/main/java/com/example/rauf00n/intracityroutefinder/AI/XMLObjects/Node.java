package com.example.rauf00n.intracityroutefinder.AI.XMLObjects;

/**
 * Created by RAUF00N on 5/16/2016.
 */
public class Node {
    public int id;
    public String Name;
    public double Lat;
    public double Long;

    public Node(int id,String name,double Lat, double Long)
    {
        this.id = id;
        this.Lat = Lat;
        this.Long = Long;
        this.Name = name;
    }

}
