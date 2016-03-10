package sim;

public class Simulator {
	SimState simState;
	EventQueue eventQueue;
	SimView simView;

	public Simulator(SimState simState, SimView simView, SimEvent startEvent){
		this.simState = simState;
		this.eventQueue = new EventQueue(simState, startEvent);
		this.simView = simView;
		setUp();
		}

	private void setUp() {
		this.simState.addObserver(this.simView);
		this.simView.printHeader();
	}

	public void start(){

		do{
			this.eventQueue.eventLoop();
		}while(!eventQueue.eventList.get(0).getSTOP());

	}
}
