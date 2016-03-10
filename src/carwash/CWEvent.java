/**
 * the package carwash holds all classes that are specific for the carwash machine.
 */

package carwash;


import sim.SimEvent;

/**
 * CWEvent creates the different events that can occur.
 */

public class CWEvent implements SimEvent {
	private int START = 0;
	private int ARRIVE = 1;
	private int LEAVE = 2;
	private int STOP = 3;

	CWState state;
	private double time = 0;
	private int carId = 0;
	private int action = ARRIVE;

	private boolean stopping = false;
	private boolean removing = false;

	private boolean fast = false;
	private boolean slow = false;


	/**
	 * the constructor CWEvent initializes time, carID and state.
	 * @param time
	 * @param carId
	 * @param state
	 */

	public CWEvent(double time, int carId, CWState state){
		this.time = time;
		state.setPreviousEventTime(time);
		this.state = state;
		this.carId = carId;
	}

	/**
	 * Execute checks state and then executes the proper event
	 */


	public void execute(){
		if(time == 0){
			startEvent();
		}

		else if(time >= state.getMaxTime()){
			stopEvent();
		}

		else if(action == ARRIVE){
			arrivalEvent();

		}else if(action == LEAVE){
			leaveEvent();
		}
	}

	private void startEvent(){
		calcIdle();
		action = START;
		state.setEventName(action);
		removing = true;
	}

	private void stopEvent(){
		calcIdle();
		state.setQueueTime(state.getMaxTime());
		action = STOP;
		state.setSimulationTime(state.getMaxTime());
		state.setEventName(action);
		stopping = true;
	}

	/**
	 * Arrival checks what washing machine can be used at current event and
	 * whether a car needs to be placed in queue due to lack of available
	 * washing machines.
	 */

	private void arrivalEvent(){
		double[] saveLeaveTime = new double[2];
		calcIdle();
		state.setQueueTime(time);

		if(state.getFastWashers() > 0){
			enterFastWash(saveLeaveTime);
		}
		else if(state.getSlowWashers() > 0){
			enterSlowWash(saveLeaveTime);

		}
		else if(state.getQueueSize() < state.getMaxQueueSize()){
			enterCarWashQueue(saveLeaveTime);
		}
		else{
			rejectCar();
		}

		state.sortCarWashQueue();
	}

	private void rejectCar() {
		state.setSimulationTime(time);
		state.setCarId(carId);
		state.setEventName(action);

		state.setRejected(1);
		removing = true;
	}

	private void enterCarWashQueue(double[] saveLeaveTime) {
		double t = time; //Spara ARRIVE tiden
		double wash = state.carWashQueue.get(0)[1]; //spara tv�tten
		state.setSimulationTime(time);
		state.setCarId(carId);
		state.setEventName(action); //S�tter event arrival (Updaterar observer i view)

		if(wash == 1){
            time += state.getFastRandom();	//tiden f�r att tv�ttas l�ggs till
            time += (state.carWashQueue.get(0)[0] - t);

            state.carWashQueue.remove(0);
            saveLeaveTime[0] = time;
            saveLeaveTime[1] = 1;
            state.carWashQueue.add(saveLeaveTime);
        }
        else if(wash == 2){
            time += state.getSlowRandom();
            time += (state.carWashQueue.get(0)[0] - t); // v�ntetiden f�r n�sta maskin l�ggs till

            state.carWashQueue.remove(0);
            saveLeaveTime[0] = time;
            saveLeaveTime[1] = 2;
            state.carWashQueue.add(saveLeaveTime);
        }
		state.setQueueSize(1);
		action = LEAVE;

		if(wash == 1){
            fast = true;
        }
        else{
            slow = true;
        }
	}

	private void enterSlowWash(double[] saveLeaveTime) {
		state.setSimulationTime(time);
		state.setCarId(carId);
		state.setEventName(action);
		state.changeSlowWashers(-1);
		time += state.getSlowRandom();
		saveLeaveTime[0] = time;
		saveLeaveTime[1] = 2;
		state.carWashQueue.add(saveLeaveTime);
		action = LEAVE;
		slow = true;
	}

	private void enterFastWash(double[] saveLeaveTime) {
		state.setSimulationTime(time);
		state.setCarId(carId);
		state.setEventName(action);
		state.changeFastWashers(-1);
		time += state.getFastRandom();
		saveLeaveTime[0] = time;
		saveLeaveTime[1] = 1;
		state.carWashQueue.add(saveLeaveTime);
		action = LEAVE;
		fast = true;
	}

	private void calcIdle(){
		double diff = state.getTime();
		diff = time - diff;
		state.setIdle(diff * (state.getFastWashers() + state.getSlowWashers()));
	}

	private void leaveEvent(){
		calcIdle();
		state.setQueueTime(time);
		state.setCarId(carId);
		state.setSimulationTime(time);
		state.setEventName(action);

		if(state.getQueueSize() == 0){ //Tar bort den senaste k�andes tid och tv�tt om k�n �r tom
			while(state.carWashQueue.size() > 0){
				state.carWashQueue.remove(0);
			}
		}

		if(fast){
			if(state.getQueueSize() == 0){ 	//Om k�n till tv�tten �r tom s� blir tv�ttmaskinen ledig
				state.changeFastWashers(1);
			}
			else{						//Annars s� minskas k�n med 1;
				state.setQueueSize(-1);
			}
		}
		else if (slow){
			if(state.getQueueSize() == 0){	//Om k�n till tv�tten �r tom s� blir tv�ttmaskinen ledig
				state.changeSlowWashers(1);
			}
			else{						//Annars s� minskas k�n med 1;
				state.setQueueSize(-1);
			}
		}
		removing = true;
	}

	public double getTime(){
		return time;
	}

	public boolean getSTOP(){
		return stopping;
	}

	public boolean getRemove(){
		return removing;
	}
}
