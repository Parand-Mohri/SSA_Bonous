package simulation;

import java.util.ArrayList;
import java.util.List;

/**
 *	A source of products
 *	This class implements CProcess so that it can execute events.
 *	By continuously creating new events, the source keeps busy.
 *	@author Joel Karel
 *	@version %I%, %G%
 */
public class Source implements CProcess
{
	/** Eventlist that will be requested to construct events */
	private CEventList list;
	/** Queue that buffers products for the machine */
	private List<Queue> queue;
	/** Name of the source */
	private String name;
	/** Mean interarrival time */
	private double meanArrTime;
	/** Interarrival times (in case pre-specified) */
	private double[] interarrivalTimes;
	/** Interarrival time iterator */
	private int interArrCnt;

	/**
	 *	Constructor, creates objects
	 *        Interarrival times are exponentially distributed with mean 33
	 *	@param q	The receiver of the products
	 *	@param l	The eventlist that is requested to construct events
	 *	@param n	Name of object
	 */
	public Source(List<Queue> q,CEventList l,String n)
	{
		list = l;
		queue = q;
		name = n;
		meanArrTime=33;
		// put first event in list for initialization
		list.add(this,0,drawRandomExponential(meanArrTime)); //target,type,time
	}
	//TODO: make constructure for poisson distribution
	/**
	 *	Constructor, creates objects
	 *        Interarrival times are exponentially distributed with specified mean
	 *	@param q	The receiver of the products
	 *	@param l	The eventlist that is requested to construct events
	 *	@param n	Name of object
	 *	@param m	Mean arrival time
	 */
	public Source(List<Queue> q,CEventList l,String n,double m)
	{
		list = l;
		queue = q;
		name = n;
		meanArrTime=m;
		// put first event in list for initialization
		list.add(this,0,drawRandomExponential(meanArrTime)); //target,type,time
	}

	/**
	 *	Constructor, creates objects
	 *        Interarrival times are prespecified
	 *	@param q	The receiver of the products
	 *	@param l	The eventlist that is requested to construct events
	 *	@param n	Name of object
	 *	@param ia	interarrival times
	 */
	public Source(List<Queue> q,CEventList l,String n,double[] ia)
	{
		list = l;
		queue = q;
		name = n;
		meanArrTime=-1;
		interarrivalTimes=ia;
		interArrCnt=0;
		// put first event in list for initialization
		list.add(this,0,interarrivalTimes[0]); //target,type,time
	}

	public Queue getNextQueue(){
		List<Queue> available = new ArrayList<>();
		for(Queue q: queue){
			if(q.getActive()){
				available.add(q);
			}
		}

		Queue smallest = available.get(0);
		for(Queue q: available){
			if(smallest.getQueueSize() > q.getQueueSize()){
				smallest = q;
			}
		}
		if(smallest.askLimit()){
			return smallest;
		}else{
			for(Queue q: queue){
				if(!q.getActive()){
					q.setActive(true);
					return q;
				}
			}
		}
		//TODO: if we got here there is no queue available
		return null;

	}


	@Override
	public void execute(int type, double tme)
	{
		// show arrival
		System.out.println("Arrival at "+  name  + "time = " + tme);
		// give arrived product to queue
		Product p = new Product();
		p.stamp(tme,"Creation",name);
		Queue next = getNextQueue();
		next.giveProduct(p);
		System.out.println("product to Queue =  " + next.getNumber());


		// generate duration
		if(meanArrTime>0)
		{
			double duration = drawRandomExponential(meanArrTime);
			// Create a new event in the eventlist
			list.add(this,0,tme+duration); //target,type,time
		}
		else
		{
			interArrCnt++;
			if(interarrivalTimes.length>interArrCnt)
			{
				list.add(this,0,tme+interarrivalTimes[interArrCnt]); //target,type,time
			}
			else
			{
				list.stop();
			}
		}
	}

	public static double drawRandomExponential(double mean)
	{
		// draw a [0,1] uniform distributed number
		double u = Math.random();
		// Convert it into a exponentially distributed random variate with mean 33
		double res = -mean*Math.log(u);
		return res;
	}
}