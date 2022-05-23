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
        List<Double> qRavg = new ArrayList<>();
        List<Double> qTavg = new ArrayList<>();
        List<Double> qavg = new ArrayList<>();
        for(int i =0 ; i < 101; i++){
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
            qRavg.add(sim.getQRAVG());
            qTavg.add(sim.getQTAVG());
            qavg.add(sim.getQAVG());
        }


        CSVwriter csv = new CSVwriter();
        csv.exportData(arrTimeRegAvg, machineTimeRegAvg, leavingTimeRegAvg, "dataReg.csv");
        csv.exportData(arrTimeTAvg, machineTimeTAvg, leavingTimeTAvg, "dataT.csv");
        csv.exportData(arrTimeOAvg, machineTimeOAvg, leavingTimeOAvg, "dataO.csv");
        csv.exportQueueData(qRavg,qTavg,qavg);


    }


}