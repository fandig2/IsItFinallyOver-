package sim;


/**
 * Simulator is the part of the application that connects all the other parts,
 * and starts the simulation.
 */
public class Simulator {
	SimState simState;
	EventQueue eventQueue;
	SimView simView;


	/**
	 * Initializes and sets up simulation, but does not start it.
	 * @param simState simulation state
	 * @param simView simulation view
	 * @param startEvent a start event
	 */
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

	/**
	 * Start runs the eventLoop in EventQueue until a STOP event is transmitted.
	 */
	public void start(){

		do{
			this.eventQueue.eventLoop();
		}while(!eventQueue.eventList.get(0).getSTOP());

	}
}
