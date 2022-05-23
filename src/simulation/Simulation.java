/**
 * Example program for using eventlists
 *
 * @author Joel Karel
 * @version %I%, %G%
 */

//TODO: change csv files
//TODO: queue length

package simulation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    List<Double> arrivalTimeReg;
    List<Double> machineTimeReg;
    List<Double> leavingTimeReg;
    List<Double> arrivalTimeT;
    List<Double> machineTimeT;
    List<Double> leavingTimeT;
    List<Double> arrivalTimeO;
    List<Double> machineTimeO;
    List<Double> leavingTimeO;
    List<Integer> qR;
    List<Integer> qT;
    List<Integer> q;
    @Getter
    double arrivalTimeRegAVG;
    @Getter
    double machineTimeRegAVG;
    @Getter
    double leavingTimeRegAVG;
    @Getter
    double arrivalTimeTAVG;
    @Getter
    double machineTimeTAVG;
    @Getter
    double leavingTimeTAVG;
    @Getter
    double arrivalTimeOAVG;
    @Getter
    double machineTimeOAVG;
    @Getter
    double leavingTimeOAVG;
    @Getter
    double qRAVG;
    @Getter
    double qTAVG;
    @Getter
    double qAVG;


    public Simulation() {
        // Create an eventlist
        CEventList l = new CEventList();
        // A queue for the machine
        Queue q1 = new Queue(1, true);
        Queue q2 = new Queue(2, true);
        Queue q3 = new Queue(3, false);
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
        SourceServiceDesk s2 = new SourceServiceDesk(q7, l, si, "customers for service desk", 5 * 60);
        SourceCashRegister s1 = new SourceCashRegister(cashRegisterQueues, s2, si, l, "customers for cash register ", 60);

        List<Queue> cashRegisterANDServiceDeskQueues = new ArrayList<>();
        cashRegisterANDServiceDeskQueues.add(q7);
        cashRegisterANDServiceDeskQueues.add(q6);

        // A machine
        CashRegister m1 = new CashRegister(q1, si, l, "cash registers 1", 2.6 * 60, 1.1 * 60);
        CashRegister m2 = new CashRegister(q2, si, l, "cash registers 2", 2.6 * 60, 1.1 * 60);
        CashRegister m3 = new CashRegister(q3, si, l, "cash registers 3", 2.6 * 60, 1.1 * 60);
        CashRegister m4 = new CashRegister(q4, si, l, "cash registers 4", 2.6 * 60, 1.1 * 60);
        CashRegister m5 = new CashRegister(q5, si, l, "cash registers 5", 2.6 * 60, 1.1 * 60);
        CashRegisterServiceDisk m6 = new CashRegisterServiceDisk(cashRegisterANDServiceDeskQueues, si, l, "cash register/service desk 6", 4.1 * 60, 1.1 * 60);
        // start the eventlist
        l.start(200000); // 2000 is maximum time
        Machine[] machines = {m1, m2, m3, m4, m5, m6};
        arrivalTimeReg = new ArrayList<>();
        machineTimeReg = new ArrayList<>();
        leavingTimeReg = new ArrayList<>();
        arrivalTimeT = new ArrayList<>();
        machineTimeT = new ArrayList<>();
        leavingTimeT = new ArrayList<>();
        arrivalTimeO = new ArrayList<>();
        machineTimeO = new ArrayList<>();
        leavingTimeO = new ArrayList<>();

        qR = new ArrayList<>();
        qT = new ArrayList<>();
        q = new ArrayList<>();
        List<List<Integer>> queueLength = s1.getQueueLength();
        for (List li : queueLength) {
            q.addAll(li);
            qR.add((Integer) li.get(0));
            qR.add((Integer) li.get(1));
            qR.add((Integer) li.get(2));
            qR.add((Integer) li.get(3));
            qR.add((Integer) li.get(4));
            qR.add((Integer) li.get(5));
            qT.add((Integer) li.get(6));
        }

        for (Machine m : machines) {
            for (Product p : m.getPeople()) {
                arrivalTimeO.add(p.getTimes().get(0));
                machineTimeO.add(p.getTimes().get(1));
                leavingTimeO.add(p.getTimes().get(2));
                if (p.type == 'R') {
                    arrivalTimeReg.add(p.getTimes().get(0));
                    machineTimeReg.add(p.getTimes().get(1));
                    leavingTimeReg.add(p.getTimes().get(2));
                } else if (p.type == 'T') {
                    arrivalTimeT.add(p.getTimes().get(0));
                    machineTimeT.add(p.getTimes().get(1));
                    leavingTimeT.add(p.getTimes().get(2));
                }

            }
        }
        arrivalTimeOAVG = calMeandouble(arrivalTimeO);
        machineTimeOAVG = calMeandouble(machineTimeO);
        leavingTimeOAVG = calMeandouble(leavingTimeO);

        arrivalTimeRegAVG = calMeandouble(arrivalTimeReg);
        machineTimeRegAVG = calMeandouble(machineTimeReg);
        leavingTimeRegAVG = calMeandouble(leavingTimeReg);

        arrivalTimeTAVG = calMeandouble(arrivalTimeT);
        machineTimeTAVG = calMeandouble(machineTimeT);

        qRAVG = calMeanint(qR);
        qTAVG = calMeanint(qT);
        qAVG = calMeanint(q);

    }

    public double calMeandouble(List<Double> l) {
        double sum = 0;
        for (Double d : l) {
            sum += d;
        }
        return sum / l.size();
    }

    public double calMeanint(List<Integer> l) {
        double sum = 0;
        for (Integer d : l) {
            sum += d;
        }
        return sum / l.size();
    }


}

