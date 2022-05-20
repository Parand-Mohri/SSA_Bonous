import simulation.Source;

import static simulation.Source.drawNormalDistributions;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        double mean = 2.6 * 60;
        double std = 1.1 * 60;
        double norm = drawNormalDistributions(mean, std);
        System.out.println(norm);
    }


}