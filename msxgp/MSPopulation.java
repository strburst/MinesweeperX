package msxgp;

import gpjpp.*;

/**
 * Represents a population of Minesweeper solvers.
 */
public class MSPopulation extends GPPopulation {

    //this constructor called when new populations are created
    MSPopulation(GPVariables gpVar, GPAdfNodeSet adfNs) {
        super(gpVar, adfNs);
    }

    //populations are not cloned in standard runs
    //AntPopulation(AntPopulation gpo) { super(gpo); }
    //protected Object clone() { return new AntPopulation(this); }

    //ID routine required for streams
    //?
    public byte isA() { return GPObject.USERPOPULATIONID; }

    //must override GPPopulation.createGP to create AntGP instances
    public GP createGP(int numOfGenes) { return new MSGP(numOfGenes); }
}
