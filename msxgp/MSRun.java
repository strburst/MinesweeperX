package msxgp;

import gpjpp.*;

/**
 * This class is responsible for running a round of Mineweeper evolution.
 */
public class MSRun extends GPRun {

    //must override GPRun.createVariables to return ms-specific variables
    protected GPVariables createVariables() {
        return new MSVariables();
    }

    //must override GPRun.createNodeSet to return
    //  initialized set of functions & terminals
    protected GPAdfNodeSet createNodeSet(GPVariables cfg) {
        GPAdfNodeSet adfNs = new GPAdfNodeSet();

        //no ADFs used regardless of cfg setting
        adfNs.reserveSpace(1);

        /* boolean bigTest = (((MSVariables)cfg).WorldHorizontal > 32); */
        GPNodeSet ns;
        /*if (bigTest)
            ns = new GPNodeSet(8);
        else*/
        ns = new GPNodeSet(16);
        adfNs.put(0, ns);

        ns.putNode(new GPNode(MSIndiv.MOV, "mov", 1));
        ns.putNode(new GPNode(MSIndiv.UNC, "unc"));
        ns.putNode(new GPNode(MSIndiv.MRK, "mrk"));
        ns.putNode(new GPNode(MSIndiv.UNMRK, "unmrk"));
        ns.putNode(new GPNode(MSIndiv.IFCOV, "ifcov", 2));
        ns.putNode(new GPNode(MSIndiv.NUM, "num"));
        //ns.putNode(new GPNode(MSIndiv.ADFn, "adfn"));
        ns.putNode(new GPNode(MSIndiv.PROG2, "prog2", 2));
        ns.putNode(new GPNode(MSIndiv.PROG3, "prog3", 3));
        ns.putNode(new GPNode(MSIndiv.ZER, "zer"));
        ns.putNode(new GPNode(MSIndiv.ONE, "one"));
        ns.putNode(new GPNode(MSIndiv.TWO, "two"));
        ns.putNode(new GPNode(MSIndiv.THR, "thr"));
        ns.putNode(new GPNode(MSIndiv.FOU, "fou"));
        ns.putNode(new GPNode(MSIndiv.FIV, "fiv"));
        ns.putNode(new GPNode(MSIndiv.SIX, "six"));
        ns.putNode(new GPNode(MSIndiv.SEV, "sev"));

        /*if (bigTest)
            ns.putNode(new GPNode(AntIndiv.PROG4, "prog4", 4));*/

        return adfNs;
    }

    //must override GPRun.createPopulation to return
    //  MS-specific population
    protected GPPopulation createPopulation(GPVariables cfg,
        GPAdfNodeSet adfNs) {
        return new MSPopulation(cfg, adfNs);
    }

    //construct this test case
    MSRun(String baseName) { super(baseName, true); }

    //main application function
    public static void main(String[] args) {

        String baseName; // Used to find config file, name output
        if (args.length == 1)
            baseName = args[0];
        else
            baseName = "default";

        //construct the test case
        MSRun test = new MSRun(baseName);

        //run the test
        test.run();

        //make sure all threads are killed
        System.exit(0);
    }
}
