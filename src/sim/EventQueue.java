package sim;

import java.util.ArrayList;

public class EventQueue {
	public ArrayList<SimEvent> eventList;
	SimState state;

	public EventQueue(SimState state, SimEvent startEvent){
		this.state = state;
		this.eventList = new ArrayList<>();
		eventList.add(startEvent);
	}
	
	public void loop(){
		eventList = state.addNextEvent(eventList);
		sort();
		eventList.get(0).execute();
		shift();
	}


	private void sort(){
		eventList.sort((e1, e2) -> Double.compare(e1.getTime(), e2.getTime()));
	}
	
	private void shift(){
		if(eventList.get(0).getRemove()){
			eventList.remove(0);
		}
	}
}
