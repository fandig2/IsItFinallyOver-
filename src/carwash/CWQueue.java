package carwash;

import java.util.ArrayList;

public class CWQueue {
	
	public ArrayList<double[]> carWashQueue = new ArrayList<>();
	
	/**
	 * sortCarWasQueue sorts the queue of cars waiting to be washed in time order
	 */
	public void sortCarWashQueue(){
		carWashQueue.sort((e1, e2) -> Double.compare(e1[0], e2[0]));	
	}
}
