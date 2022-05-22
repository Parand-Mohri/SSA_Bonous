/**
 *	Example program for using eventlists
 *	@author Joel Karel
 *	@version %I%, %G%
 */

//TODO: change csv files
//TODO: queue length

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
        SourceServiceDesk s2 = new SourceServiceDesk(q7,l,si,"customers for service desk", 5*60);
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
        l.start(200000); // 2000 is maximum time
        Machine[] machines = {m1, m2, m3,m4,m5,m6};
        List<Double> arrivalTimeReg = new ArrayList<>();
        List<Double> machineTimeReg = new ArrayList<>();
        List<Double> leavingTimeReg = new ArrayList<>();
        List<Double> arrivalTimeT = new ArrayList<>();
        List<Double> machineTimeT = new ArrayList<>();
        List<Double> leavingTimeT = new ArrayList<>();

        List<Integer> ql1 = new ArrayList<>();
        List<Integer> ql2 = new ArrayList<>();
        List<Integer> ql3 = new ArrayList<>();
        List<Integer> ql4 = new ArrayList<>();
        List<Integer> ql5 = new ArrayList<>();
        List<Integer> ql6 = new ArrayList<>();
        List<Integer> ql7 = new ArrayList<>();
        List<List<Integer>> queueLength = s1.getQueueLength();
        for(List li: queueLength){
            ql1.add((Integer) li.get(0));
            ql2.add((Integer) li.get(1));
            ql3.add((Integer) li.get(2));
            ql4.add((Integer) li.get(3));
            ql5.add((Integer) li.get(4));
            ql6.add((Integer) li.get(5));
            ql7.add((Integer) li.get(6));
        }
        System.out.println(queueLength.size());


        for(Machine m: machines){
            for(Product p: m.getPeople()){
                if(p.type == 'R'){
                    arrivalTimeReg.add(p.getTimes().get(0));
                    machineTimeReg.add(p.getTimes().get(1));
                    leavingTimeReg.add(p.getTimes().get(2));
                } else if (p.type == 'T'){
                    arrivalTimeT.add(p.getTimes().get(0));
                    machineTimeT.add(p.getTimes().get(1));
                    leavingTimeT.add(p.getTimes().get(2));
                }

            }
        }
//
//        System.out.println(ql1);
//        System.out.println("machine times:" + machineTimeReg);
//        System.out.println("leaving times:" + leavingTimeReg);
//        System.out.println("arival times:" + arrivalTimeT.size());
//        System.out.println("machine times:" + machineTimeT);
//        System.out.println("leaving times:" + leavingTimeT);


        CSVwriter csv = new CSVwriter();
        csv.exportData(arrivalTimeReg, machineTimeReg, leavingTimeReg, "dataReg.csv");
        csv.exportData(arrivalTimeT, machineTimeT, leavingTimeT, "dataT.csv");
        csv.exportQueueData(ql1,ql2,ql3,ql4,ql5,ql6,ql7);
    }
}

