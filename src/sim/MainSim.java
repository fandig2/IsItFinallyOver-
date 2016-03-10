package sim;

import carwash.CWEvent;
import carwash.CWState;
import carwash.CWView;

public class MainSim {
	
	public static void main(String[] args){

		// Labspec output 1
		SimState carWashState = new CWState(1234, 2.8, 4.6, 3.5, 6.7, 2, 2, 5, 2.0, 15.0);
		// Labspec output 2
//		SimState carWashState = new CWState(1234, 2.8, 5.6, 4.5, 6.7, 2, 4, 7, 1.5, 15.0);

		SimView carWashView = new CWView(carWashState);
		CWEvent startEvent = new CWEvent(0, 0, (CWState) carWashState);
		Simulator carWashSim = new Simulator(carWashState, carWashView,  startEvent);
		carWashSim.start();
	}
}
