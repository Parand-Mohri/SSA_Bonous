package simulation;

import java.util.ArrayList;
import java.util.List;

public class CashRegisterServiceDisk implements CProcess, ProductAcceptor, Machine {
    /**
     * Product that is being handled
     */
    private Product product;
    /**
     * Eventlist that will manage events
     */
    private final CEventList eventlist;
    /**
     * Queue from which the machine has to take products
     */
    private List<Queue> queue;
    /**
     * Sink to dump products
     */
    private ProductAcceptor sink;
    /**
     * Status of the machine (b=busy, i=idle)
     */
    private char status;
    /**
     * Machine name
     */
    private final String name;
    /**
     * Mean processing time
     */
    private double meanProcTime;
    /**
     * Processing time iterator
     */
    private int procCnt;
    private double sd;
    private List<Product> people;

    /**
     * Constructor
     * Service times are exponentially distributed with specified mean
     *
     * @param q Queue from which the machine has to take products
     * @param s Where to send the completed products
     * @param e Eventlist that will manage events
     * @param n The name of the machine
     * @param m Mean processing time
     */
    public CashRegisterServiceDisk(List<Queue> q, ProductAcceptor s, CEventList e, String n, double m, double sd) {
        status = 'i';
        queue = q;
        sink = s;
        eventlist = e;
        name = n;
        meanProcTime = m;
        this.sd = sd;
        //given priority to service customers since the beginning
        if (!queue.get(1).askProduct(this)) {
            queue.get(0).askProduct(this);
        }
        people = new ArrayList<>();
//        peopleR = new ArrayList<>();
    }


    /**
     * Method to have this object execute an event
     *
     * @param type The type of the event that has to be executed
     * @param tme  The current time
     */
    public void execute(int type, double tme) {
        // show arrival
//        System.out.println("Product finished at  " + name + " time = " + tme);
        // Remove product from system
        product.stamp(tme, "Production complete", name);
        sink.giveProduct(product);
        people.add(product);

        product = null;
        // set machine status to idle
        status = 'i';
        // Ask the queue for products
        // give priority to the service desk queue
        if (!queue.get(1).askProduct(this)) {
            queue.get(0).askProduct(this);
        }
    }

    /**
     * Let the machine accept a product and let it start handling it
     *
     * @param p The product that is offered
     * @return true if the product is accepted and started, false in all other cases
     */
    @Override
    public boolean giveProduct(Product p) {
        // Only accept something if the machine is idle
        if (status == 'i') {
            // accept the product
            product = p;
            // mark starting time
            product.stamp(eventlist.getTime(), "Production started", name);
            // start production
            startProduction();
            // Flag that the product has arrived
            return true;
        }
        // Flag that the product has been rejected
        else return false;
    }

    @Override
    public List<Product> getPeople() {
        return people;
    }

    /**
     * Starting routine for the production
     * Start the handling of the current product with an exponentionally distributed processingtime with average 30
     * This time is placed in the eventlist
     */
    private void startProduction() {
        // generate duration
        double duration = drawNormalDistributions(meanProcTime, sd);
        // Create a new event in the eventlist
        double tme = eventlist.getTime();
        eventlist.add(this, 0, tme + duration); //target,type,time
        // set status to busy
        status = 'b';

    }

    //generating normally distributed variated by box and muller method,
    //it generates pairs of STANDARD NORMAL DIST variates, we only need one

    public static double drawNormalDistributions(double mean, double std) {
        //uniform dist (0;1)
        double res = 0;
        while (res < 1) {
            double u1 = Math.random();
            double u2 = Math.random();

            //generating two vars standard normal distributed
            double x1 = Math.sqrt(-2 * Math.log(u1));
            x1 = x1 * Math.cos(2 * Math.PI * u2);
            // not needed second variate

            //cast to N(mean, std)
            res = (std * x1) + mean;
        }
        return res;
    }
}
