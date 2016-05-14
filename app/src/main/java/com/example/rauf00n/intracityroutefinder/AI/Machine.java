package com.example.rauf00n.intracityroutefinder.AI;

import com.example.rauf00n.intracityroutefinder.AI.Util.Constraint;
import com.example.rauf00n.intracityroutefinder.AI.Util.Node;
import com.example.rauf00n.intracityroutefinder.AI.Util.Output;

import java.util.ArrayList;

/**
 * Created by RAUF00N on 5/15/2016.
 */
public class Machine {
    public static Machine machine = null;

    /*
    Inputs
    */
    Constraint constraint;
    Node source;
    Node destination;

    /* no need to look at bellow section, i just made it singleton which is unnecessary*/
    private Machine()
    {

    }

    public static Machine getInstance()
    {
        if(machine == null) machine = new Machine();
        return machine;
    }
    //.......................................


    /*
    this method receives a Source, Destination and Constraint object.
    the UI will pass user-given inputs in this method
    */
    public void takeInput(Node source, Node destination, Constraint constraint)
    {
        this.constraint = constraint;
        this.source = source;
        this.destination = destination;
    }

    /*
    UI will receive the output by calling this method.
    it will return a Output object
    */
    public Output getOutput()
    {
        return calculateRoute();
    }


    /*
    This is the method we will use to calculate our output as an Output object.
    We will use our AI from the inside of this function
    */
    Output calculateRoute()
    {
        return null;
    }
}
