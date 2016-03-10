package carwash;


import sim.SimState;
import sim.SimView;

import java.util.Observable;

/**
 * CWView is a command line view for the Car Wash in CWState.
 * It extends SimView which in turn implements Observer,
 * and it Observes CWState.
 */
public class CWView extends SimView {

    CWState CWState;

    private static String dashedLine = String.format("%-32s %n", "-------------------------------------------");

    /**
     * Takes a CWState as a parameter so it can use .get() methods to
     * reflect the changing state.
     * @param carWashState the state
     */
    public CWView(SimState carWashState){
        this.CWState = (CWState) carWashState;
        }


    /**
     * The core of Observer/Observable.
     * Prints current state on state change, and when the simulation ends
     * it prints a final footer with a summary of the data from the simulation.
     * @param arg0 Observable
     * @param arg1 Object
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        if(this.CWState.getTime() == this.CWState.getMaxTime()){
            printCurrentState();
            printFooter();
        }
        else{
            printCurrentState();
        }
    }


    /**
     * Prints the current state.
     */
    public void printCurrentState(){
        String currentTime = String.format("%2.2f \t %3s", this.CWState.getTime(), " ");
        String fastMachinesFree = String.format("%-12s", this.CWState.getFastWashers(), "");
        String slowMachinesFree = String.format("%d %-9s", this.CWState.getSlowWashers(), "");
        String carID = String.format("%-10s", this.CWState.getCarId(), "");
        String event = String.format("%-10s", this.CWState.getEventName(), "");
        String idleTime = String.format("%2.2f %-17s", this.CWState.getIdleTime(), "");
        String queueTime = String.format("%2.2f \t %-13s", this.CWState.getQueueTime(), "");
        String queueSize = String.format("%d %-22s", this.CWState.getQueueSize(), "");
        String rejected = String.format("%d %5s", this.CWState.getRejectedCars(), "");
        System.out.println(currentTime+ fastMachinesFree+ slowMachinesFree+ carID+ event+ idleTime+ queueTime+ queueSize+ rejected);
    }

    /**
     * Prints introductory parameters for the simulation.
     */
    public void outputSimParams()
    {
        String seedString = String.format("%-8s %d%n", "Seed =", this.CWState.getSeed());
        String fastMachinesString = String.format("%-12s %d %n", "Fast machines:", this.CWState.getFastWashers());
        String slowMachinesString = String.format("%-12s %d %n", "Slow machines:", this.CWState.getSlowWashers());
        String fastDistribution = String.format("%-16s%1.1f%s %1.1f%s%n", "Fast distribution: (",
                this.CWState.getLowerFast(), ",", this.CWState.getUpperFast(), ")");

        String slowDistribution = String.format("%-16s" +
                                                "%1.1f%s %1.1f%s %n",
                                                "Slow distribution: (",
                                                this.CWState.getLowerSlow(), ",", this.CWState.getUpperSlow(), ")");

        String lambdaString = String.format("%-5s%1.1f %n","Exponential distribution with lambda = ",this.CWState.getLambda());
        String maxQueueSize = String.format("%s%d %n", "Max Queue Size: ", this.CWState.getMaxQueueSize());

        String columnHeadersShort = String.format("%-8s   %-8s    %-8s    %-8s  %-8s",
                                                  "Time", "Fast", "Slow", "Id", "Event");

        String columnHeadersLong = String.format("%-16s      %-12s        %-16s        %-16s %n",
                                                 "IdleTime", "QueueTime", "QueueSize", "Rejected");

        System.out.println(seedString +
                fastMachinesString +
                slowMachinesString +
                fastDistribution +
                slowDistribution +
                lambdaString +
                maxQueueSize +
                dashedLine +
                columnHeadersShort +
                columnHeadersLong
        );

    }


    /**
     * Prints a summary of the simulation data.
     */
    public void printFooter(){
        String totalIdleTime = String.format("%-32s %2.2f %n", "Total idle machine time: ", CWState.getIdleTime() );
        String totalQueueingTime = String.format("%-32s %2.2f %n", "Total queueing time: ", CWState.getQueueTime());
        String meanQueueingTime = String.format("%-32s %2.2f %n", "Mean queueing time: ", CWState.getMeanQueue());
        String rejectedCars = String.format("%-32s %d %n", "Rejected cars: ", CWState.getRejectedCars());

        System.out.println(dashedLine
                            + totalIdleTime
                            + totalQueueingTime
                            + meanQueueingTime
                            + rejectedCars);
    }

}