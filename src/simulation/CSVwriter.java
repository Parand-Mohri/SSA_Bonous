package simulation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class CSVwriter {

    public void exportData(List<Double> arrivalTimes, List<Double> machineTimes, List<Double> departures, String fileName) {

        try{
            BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
            StringBuilder sb = new StringBuilder();

            sb.append("Customers");
            sb.append(",");
            sb.append("Arrivals");
            sb.append(",");
            sb.append("Arrivals at Machine");
            sb.append(",");
            sb.append("Departures");
            sb.append("\r\n");

            // Append strings from array
            for (int i = 0; i < arrivalTimes.size(); i++) {
                sb.append(i);
                sb.append(",");
                sb.append(arrivalTimes.get(i));
                sb.append(",");
                sb.append(machineTimes.get(i));
                sb.append(",");
                sb.append(departures.get(i));
                sb.append("\r\n");
            }

            br.write(sb.toString());
            br.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void exportQueueData(List<Integer> queue1, List<Integer> queue2, List<Integer> queue3, List<Integer> queue4, List<Integer> queue5, List<Integer> queue6, List<Integer> queue7) {

        try{
            BufferedWriter br = new BufferedWriter(new FileWriter("queueData.csv"));
            StringBuilder sb = new StringBuilder();

            sb.append("Timestep");
            sb.append(",");
            sb.append("Queue 1");
            sb.append(",");
            sb.append("Queue 2");
            sb.append(",");
            sb.append("Queue 3");
            sb.append(",");
            sb.append("Queue 4");
            sb.append(",");
            sb.append("Queue 5");
            sb.append(",");
            sb.append("Queue 6");
            sb.append(",");
            sb.append("Queue 7");
            sb.append("\r\n");

            // Append strings from array
            for (int i = 0; i < queue1.size(); i++) {
                sb.append(i);
                sb.append(",");
                sb.append(queue1.get(i));
                sb.append(",");
                sb.append(queue2.get(i));
                sb.append(",");
                sb.append(queue3.get(i));
                sb.append(",");
                sb.append(queue4.get(i));
                sb.append(",");
                sb.append(queue5.get(i));
                sb.append(",");
                sb.append(queue6.get(i));
                sb.append(",");
                sb.append(queue7.get(i));
                sb.append("\r\n");
            }

            br.write(sb.toString());
            br.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}