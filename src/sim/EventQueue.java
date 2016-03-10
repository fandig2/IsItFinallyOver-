package sim;

import java.util.ArrayList;

/**
 * EventQueue holds events for a simulation and provides an execution eventLoop
 * and time-based sorting for the list of events.
 */

public class EventQueue {
	public ArrayList<SimEvent> eventList;
	SimState state;

	/**
	 * The constructor adds the startEvent to the eventList.
	 * @param state any SimState
	 * @param startEvent pre-created startEvent for current simulation
	 */
	public EventQueue(SimState state, SimEvent startEvent){
		this.state = state;
		this.eventList = new ArrayList<>();
		eventList.add(startEvent);
	}


	/**
	 * First eventLoop() requests an updated eventList from the current SimState,
	 * then sorts it, executes the first event and if the first event is removable
	 * it removes it from the queue.
	 */
	public void eventLoop(){
		eventList = state.addNextEvent(eventList);
		sortTime();
		eventList.get(0).execute();
		shift();
	}


	private void sortTime(){
		eventList.sort((e1, e2) -> Double.compare(e1.getTime(), e2.getTime()));
	}
	
	private void shift(){
		if(eventList.get(0).getRemove()){
			eventList.remove(0);
		}
	}
}
