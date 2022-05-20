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
    public SourceCashRegister source;
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

        Sink si = new Sink("Sink 1");

        //sources
        SourceServiceDesk s2 = new SourceServiceDesk(q7,l,"customers for service desk", 5*60);
        SourceCashRegister s1 = new SourceCashRegister(cashRegisterQueues,s2,si, l,"customers for cash register ", 60);

        List<Queue> cashRegisterANDServiceDeskQueues = new ArrayList<>();
        cashRegisterANDServiceDeskQueues.add(q7);
        cashRegisterANDServiceDeskQueues.add(q6);

        // A machine
        CashRegister m1 = new CashRegister(q1,si,l,"cash registers 1",2.6 * 60, 1.1*60 );
        CashRegister m2 = new CashRegister(q2,si,l,"cash registers 2",2.6 * 60, 1.1*60);
        CashRegister m3= new CashRegister(q3,si,l,"cash registers 3",2.6 * 60, 1.1*60);
        CashRegister m4 = new CashRegister(q4,si,l,"cash registers 4",2.6 * 60, 1.1*60);
        CashRegister m5 = new CashRegister(q5,si,l,"cash registers 5",2.6 * 60, 1.1*60);
        CashRegisterServiceDisk m6 = new CashRegisterServiceDisk(cashRegisterANDServiceDeskQueues,si,l,"cash register/service desk 6", 4.1*60, 1.1*60);
        // start the eventlist
        l.start(3000); // 2000 is maximum time
        System.out.println(m1.getPeople().get(0).getTimes());
        System.out.println(m1.getPeople().get(1).getTimes());
        System.out.println(m1.getPeople().get(2).getTimes());
        System.out.println(m1.getPeople().get(3).getTimes());
        System.out.println(m1.getPeople().get(4).getTimes());

    }

}
