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
        Queue q1 = new Queue(1,true);
        Queue q2 = new Queue(2,true);
        Queue q3= new Queue(3, false);
        Queue q4 = new Queue(4, false);
        Queue q5 = new Queue(5, false);
        Queue q6 = new Queue(6, true);
        Queue q7 = new Queue(7, true);
        List<Queue> cashRegisterQueues = new ArrayList<>();
        cashRegisterQueues.add(q1);
        cashRegisterQueues.add(q2);
        cashRegisterQueues.add(q3);
        cashRegisterQueues.add(q4);
        cashRegisterQueues.add(q5);
        cashRegisterQueues.add(q6);
        List<Queue> cashRegisterANDServiceDeskQueues = new ArrayList<>();
        cashRegisterANDServiceDeskQueues.add(q7);

        // A source
        Source s1 = new Source(cashRegisterQueues,l,"customers for cash register ");
        //TODO: make a source just for 1 queue
//	Source s2 = new Source(cashRegisterANDServiceDeskQueues,l,"customers for service desk");
        cashRegisterANDServiceDeskQueues.add(q6);

        // A sink
        Sink si = new Sink("Sink 1");
        // A machine
        CashRegister m1 = new CashRegister(q1,si,l,"cash registers 1");
        CashRegister m2 = new CashRegister(q2,si,l,"cash registers 2");
        CashRegister m3= new CashRegister(q3,si,l,"cash registers 3");
        CashRegister m4 = new CashRegister(q4,si,l,"cash registers 4");
        CashRegister m5 = new CashRegister(q5,si,l,"cash registers 5");
        CashRegisterServiceDisk m6 = new CashRegisterServiceDisk(cashRegisterANDServiceDeskQueues,si,l,"cash register/service desk 6");
        // start the eventlist
        l.start(2000); // 2000 is maximum time
    }

}
