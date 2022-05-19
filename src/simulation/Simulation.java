/**
 *	Example program for using eventlists
 *	@author Joel Karel
 *	@version %I%, %G%
 */

package simulation;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    public CEventList list;
    public Queue queue;
    public Source source;
    public Sink sink;
    public CashRegister mach;
	

        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	// Create an eventlist
	CEventList l = new CEventList();
	// A queue for the machine
	Queue q1 = new Queue();
	Queue q2 = new Queue();
	Queue q3= new Queue();
	Queue q4 = new Queue();
	Queue q5 = new Queue();
	List<Queue> q6 = new ArrayList<>();
	// A source
	Source s1 = new Source(q1,l,"customers for cash register");
	Source s2 = new Source(q2,l,"customers for service desk");

	// A sink
	Sink si = new Sink("Sink 1");
	// A machine
	CashRegister m1 = new CashRegister(q1,si,l,"cash registers 1");
	CashRegister m2 = new CashRegister(q2,si,l,"cash registers 2");
	CashRegister m3= new CashRegister(q3,si,l,"cash registers 3");
	CashRegister m4 = new CashRegister(q4,si,l,"cash registers 4");
	CashRegister m5 = new CashRegister(q5,si,l,"cash registers 5");
	CashRegisterServiceDisk m6 = new CashRegisterServiceDisk(q6,si,l,"cash register/service desk 6");
	// start the eventlist
	l.start(2000); // 2000 is maximum time
    }
    
}
