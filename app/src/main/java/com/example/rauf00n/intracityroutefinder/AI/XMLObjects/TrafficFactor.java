package com.example.rauf00n.intracityroutefinder.AI.XMLObjects;

import java.sql.Time;

/**
 * Created by RAUF00N on 5/16/2016.
 */
public class TrafficFactor {
    public static int m_H=5,m_M=30, n_H=9, n_M=30, N_H=19, N_M=30;

    public int morning;
    public int noon;
    public int night;

    public TrafficFactor(int morning,int noon, int night)
    {
        this.morning = morning;
        this.noon = noon;
        this.night = night;
    }
}
