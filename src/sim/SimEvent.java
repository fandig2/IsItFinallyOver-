package sim;

/**
 * A general Simulation Event
 */
public interface SimEvent {

    /**
     * execute should :
     * execute the state changes on the event and trigger the generation of additional events
     */
    void execute();

    /**
     * @return a double representing the time of the event
     */
    double getTime();

    /**
     * @return a boolean that indicates if an event should be removed or not
     */
    boolean getRemove();

    /**
     * @return a boolean that STOPS the event loop
     */
    boolean getSTOP();
}
