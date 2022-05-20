package simulation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class CSVwriter {

    public void exportData(List<Double> arrivalTimes, List<Double> machineTimes, List<Double> departures) {

        try{
            BufferedWriter br = new BufferedWriter(new FileWriter("data.csv"));
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
}