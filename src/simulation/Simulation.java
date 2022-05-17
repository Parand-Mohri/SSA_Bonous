/**
 *	Example program for using eventlists
 *	@author Joel Karel
 *	@version %I%, %G%
 */

package simulation;

public class Simulation {

    public CEventList list;
    public Queue queue;
    public Source source;
    public Sink sink;
    public Machine mach;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create an eventlist
        CEventList l = new CEventList();

        // A queue for the machine
        Queue qR = new Queue();
        Queue qT = new Queue();

        // A source
        Source sR = new Source(qR,l,"Source R", 1);
        Source sT = new Source(qT,l,"Source T", 5);

        // A sink
        Sink si = new Sink("Sink 1");

        // A machine
        Machine mR = new Machine(qR,si,l,"Machine R");
        Machine mT = new Machine(qT,si,l,"Machine T");

        // start the event list
        l.start(2000); // 2000 is maximum time
    }
}

