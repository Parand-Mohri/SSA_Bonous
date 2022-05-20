package simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * A source of products
 * This class implements CProcess so that it can execute events.
 * By continuously creating new events, the source keeps busy.
 *
 * @author Joel Karel
 * @version %I%, %G%
 */
public class SourceServiceDesk implements CProcess {
    /**
     * Eventlist that will be requested to construct events
     */
    private CEventList list;
    /**
     * Queue that buffers products for the machine
     */
    private Queue queue;

    public Queue getQueue(){return queue;}
    /**
     * Name of the source
     */
    private String name;
    /**
     * Mean interarrival time
     */
    private double meanArrTime;
    /**
     * Interarrival time iterator
     */
    private int interArrCnt;
    private List<Double> arrivalTime = new ArrayList<>();
    public List<Double> getArrivalTime(){return arrivalTime;}

    /**
     * Constructor, creates objects
     * Interarrival times are poisson distributed with specified mean
     *
     * @param q The receiver of the products
     * @param l The eventlist that is requested to construct events
     * @param n Name of object
     * @param m Mean arrival time
     */
    public SourceServiceDesk(Queue q, CEventList l, String n, double m) {
        list = l;
        queue = q;
        name = n;
        meanArrTime = m;
        // put first event in list for initialization
        list.add(this, 0, drawRandomPoisson(meanArrTime)); //target,type,time
    }


    @Override
    public void execute(int type, double tme) {
        // show arrival
//        System.out.println("Arrival at " + name + "time = " + tme);
        arrivalTime.add(tme);
        // give arrived product to queue
        Product p = new Product();
        p.stamp(tme, "Creation", name);
        queue.giveProduct(p);
        System.out.println("product to Queue =  " + queue.getNumber());


        // generate duration
        double duration = drawRandomExponential(meanArrTime);
        // Create a new event in the eventlist
        list.add(this, 0, tme + duration); //target,type,time

    }

    public static double drawRandomExponential(double mean) {
        // draw a [0,1] uniform distributed number
        double u = Math.random();
        // Convert it into a exponentially distributed random variate with mean 33
        double res = -mean * Math.log(u);
        return res;
    }

    public static double drawRandomPoisson(double mean) {
        // draw a [0,1] uniform distributed number
        double u = Math.random();

        double l = Math.exp(-mean);
        int k = 0;
        double p = 1.0;

        while (p > l) {
            p = p * u;
            k++;
        }
        return k - 1;
    }

}