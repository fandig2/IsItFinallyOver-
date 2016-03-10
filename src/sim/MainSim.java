package sim;

import carwash.CWEvent;
import carwash.CWQueue;
import carwash.CWState;
import carwash.CWView;

public class MainSim {
	
	public static void main(String[] args){
		CWQueue carQueue = new CWQueue();
		// Labspec output 1
		SimState carWashState = new CWState(1234, 2.8, 4.6, 3.5, 6.7, 2, 2, 5, 2.0, 15.0, carQueue);
		// Labspec output 2
//		SimState carWashState = new CWState(1234, 2.8, 5.6, 4.5, 6.7, 2, 4, 7, 1.5, 15.0, carQueue);

		SimView carWashView = new CWView(carWashState);
		CWEvent startEvent = new CWEvent(0, 0, (CWState) carWashState, carQueue);
		Simulator carWashSim = new Simulator(carWashState, carWashView,  startEvent);
		carWashSim.start();
	}
}
