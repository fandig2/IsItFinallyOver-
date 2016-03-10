package sim;

import java.util.*;

/**
 * SimView is an abstract class that implements Observer.
 * All SimViews must have an update method as per the
 * Java Observer/Observable pattern and an outPutSimParams
 * initial print method
 */
public abstract class SimView implements Observer{

	protected SimView() {

	}

	public void update(Observable arg0, Object arg1) {
	}

	/**
	 *  Output the initial simulation parameters.
	 */
	public void outputSimParams() {
	}
}
