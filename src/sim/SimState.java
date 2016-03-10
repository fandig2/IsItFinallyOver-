package sim;

import java.util.ArrayList;
import java.util.Observable;

/**
 * A SimState extends Observable and has a method to add events to the EventQueue.
 */
public abstract class SimState extends Observable {

    /**
     * addNextEvent should add whatever new events are triggered to the eventlist and return it.
     * @param simEvents list of events from EventQueue
     * @return simEvents updated list of events to EventQueue
     */
    public abstract ArrayList<SimEvent> addNextEvent(ArrayList<SimEvent> simEvents);

}
