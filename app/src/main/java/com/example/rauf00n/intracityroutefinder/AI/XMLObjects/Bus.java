package com.example.rauf00n.intracityroutefinder.AI.XMLObjects;

import java.util.ArrayList;

/**
 * Created by RAUF00N on 5/16/2016.
 */
public class Bus {
    public int id;
    public String Name;
    public int FareRate_CostPerKM;

    public Bus(int id, String Name, int FareRate)
    {
        this.id = id;
        this.FareRate_CostPerKM = FareRate;
        this.Name = Name;
    }
}
