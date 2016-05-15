package com.example.rauf00n.intracityroutefinder.AI.XMLObjects;

import java.sql.Time;

/**
 * Created by RAUF00N on 5/16/2016.
 */
public class TrafficFactor {
    public static Time morning_start= Time.valueOf("05:00:00");
    public static Time noon_start= Time.valueOf("09:00:00");
    public static Time night_start= Time.valueOf("20:00:00");

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
