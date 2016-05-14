package com.example.rauf00n.intracityroutefinder.AI;

import com.example.rauf00n.intracityroutefinder.AI.Util.Constraint;
import com.example.rauf00n.intracityroutefinder.AI.Util.Node;

import java.util.ArrayList;

/**
 * Created by RAUF00N on 5/15/2016.
 */
public class Machine {
    public static Machine machine = null;

    Constraint constraint;
    Node source;
    Node destination;

    private Machine()
    {

    }

    public static Machine getInstance()
    {
        if(machine == null) machine = new Machine();
        return machine;
    }

    public void takeInput(Node source, Node destination, Constraint constraint)
    {
        this.constraint = constraint;
        this.source = source;
        this.destination = destination;
    }

    public ArrayList<Node> getOutput()
    {
        // not implemented
        return null;
    }

}
