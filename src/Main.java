//import static simulation.Source.drawNormalDistributions;


import simulation.CSVwriter;
import simulation.Simulation;

import java.util.ArrayList;
import java.util.List;

public class Main {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){

        List<Double> arrTimeRegAvg = new ArrayList<>();
        List<Double> machineTimeRegAvg = new ArrayList<>();
        List<Double> leavingTimeRegAvg = new ArrayList<>();
        List<Double> arrTimeTAvg = new ArrayList<>();
        List<Double> machineTimeTAvg = new ArrayList<>();
        List<Double> leavingTimeTAvg = new ArrayList<>();
        List<Double> arrTimeOAvg = new ArrayList<>();
        List<Double> machineTimeOAvg = new ArrayList<>();
        List<Double> leavingTimeOAvg = new ArrayList<>();
        List<Double> q1avg = new ArrayList<>();
        List<Double> q2avg = new ArrayList<>();
        List<Double> q3avg = new ArrayList<>();
        List<Double> q4avg = new ArrayList<>();
        List<Double> q5avg = new ArrayList<>();
        List<Double> q6avg = new ArrayList<>();
        List<Double> q7avg = new ArrayList<>();
        arrTimeRegAvg = new ArrayList<>();
        for(int i =0 ; i < 100; i++){
            System.out.println("Sim" + i);
            Simulation sim = new Simulation();
            arrTimeRegAvg.add(sim.getArrivalTimeRegAVG());
            machineTimeRegAvg.add(sim.getMachineTimeRegAVG());
            leavingTimeRegAvg.add(sim.getLeavingTimeRegAVG());
            arrTimeTAvg.add(sim.getArrivalTimeTAVG());
            machineTimeTAvg.add(sim.getMachineTimeTAVG());
            leavingTimeTAvg.add(sim.getLeavingTimeTAVG());
            arrTimeOAvg.add(sim.getArrivalTimeOAVG());
            machineTimeOAvg.add(sim.getMachineTimeOAVG());
            leavingTimeOAvg.add(sim.getLeavingTimeOAVG());
            q1avg.add(sim.getQl1AVG());
            q2avg.add(sim.getQl2AVG());
            q3avg.add(sim.getQl3AVG());
            q4avg.add(sim.getQl4AVG());
            q5avg.add(sim.getQl5AVG());
            q6avg.add(sim.getQl6AVG());
            q7avg.add(sim.getQl7AVG());
        }


        CSVwriter csv = new CSVwriter();
        csv.exportData(arrTimeRegAvg, machineTimeRegAvg, leavingTimeRegAvg, "dataReg.csv");
        csv.exportData(arrTimeTAvg, machineTimeTAvg, leavingTimeTAvg, "dataT.csv");
        csv.exportData(arrTimeOAvg, machineTimeOAvg, leavingTimeOAvg, "dataO.csv");
        csv.exportQueueData(q1avg,q2avg,q3avg,q4avg,q5avg,q6avg,q7avg);


    }


}