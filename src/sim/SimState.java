package sim;

import java.util.ArrayList;
import java.util.Observable;

public abstract class SimState extends Observable {

    public abstract ArrayList<SimEvent> addNextEvent(ArrayList<SimEvent> carWashEventList);

}
