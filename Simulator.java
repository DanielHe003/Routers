/*
 * Name: Daniel He
 * Solar ID: 114457594
 * Homework #4
 * Email: daniel.he@stonybrook.edu
 * Course: CSE214
 * Recitation #: R01 TA: Ulfeen Ayevan & Wesley Mui  
 */

import java.util.*;

/*
 * This class contains the main method that tests my simulation. All values are received from user input. It creates a simulation
 * and tests, within a given amount of time, if packets will arrive at a router, if it will go to the intermediate router, if it gets processed
 * and transfered to the destination and noted the information within all steps listed above and prints it out in a neat format. It also returns
 * the totalTime/totalPacketsSent.
 * 
 * @author Daniel He
 * email: daniel.he@stonybrook.edu
 * 114457594
 */
public class Simulator {
	public static int maxBufferSize;
	public static final int MAX_PACKETS = 3;

	/*
	 * Prompts the user for the the data that they want to use to simulate a simple network and packets.
	 * This main method gets the user inputted fields and then simulates it using that data. The main() method 
	 * will prompt the user for inputs to the simulator. It will then run the simulator, and outputs the result. 
	 * Prompt the user whether he or she wants to run another simulation.
	 * 
	 * @throws NumberFormatException
	 * 		It throws this error when the user-inputted fields are negative, 0, or not a number.
	 * 
	 * @throws Exception 
	 * 		There was a error in the code
	 */
	public static void main(String args[]) {
		double arrivalProb;
		int numIntRouters;
		int minPacketSize;
		int maxBufferSize;
		int maxPacketSize;
		int bandwidth;
		int duration;
		try {
			Scanner scan = new Scanner(System.in);
			String temp;

			System.out.println("Starting simulator...");
			Scanner startingScan = new Scanner(System.in);
			String startingInput = "y";

			while(startingInput.equalsIgnoreCase("y")) {
				System.out.print("Enter the number of Intermediate routers: ");
				temp = scan.nextLine();
				numIntRouters = Integer.parseInt(temp);

				System.out.print("Enter the arrival probability of a packet: ");
				temp = scan.nextLine();
				arrivalProb = Double.parseDouble(temp);

				System.out.print("Enter the maximum buffer size of a router: ");
				temp = scan.nextLine();
				maxBufferSize = Integer.parseInt(temp);

				System.out.print("Enter the minimum size of a packet: ");
				temp = scan.nextLine();
				minPacketSize = Integer.parseInt(temp);

				System.out.print("Enter the maximum size of a packet: ");
				temp = scan.nextLine();
				maxPacketSize = Integer.parseInt(temp);

				System.out.print("Enter the bandwidth size: ");
				temp = scan.nextLine();
				bandwidth = Integer.parseInt(temp);

				System.out.print("Enter the simulation duration: ");
				temp = scan.nextLine();
				duration = Integer.parseInt(temp);

				if(numIntRouters <=0 || arrivalProb <=0 || maxBufferSize <=0 || minPacketSize <=0 
						|| maxPacketSize <=0 || bandwidth <=0 || duration <=0)
					throw new NumberFormatException();

				simulate(numIntRouters, arrivalProb, maxBufferSize, minPacketSize, maxPacketSize, bandwidth, duration);

				System.out.printf("\nDo you want to try another simulation? (y/n): ");
				startingInput = scan.nextLine();
			} if(startingInput.equals("n")) {
				System.out.println();
				System.out.println("Program terminating successfully...");
			}
			scan.close();
		} catch(NumberFormatException e) {
			System.out.println("Input needs to be a number, positive, and greater than 0.");
			//		} catch(Exception e) {
			//			System.out.println("Input was wrong, please try again.");
		}
	}

	/*
	 * Calculate and return the average time each packet spends within the network. Uses the inputted fields for the simulation.
	 * 
	 * @param numIntRouters
	 * 		Number of Intermediate routers
	 * @param arrivalProb 
	 * 		arrival probability of the packet
	 * @param maxBufferSize 
	 * 		maxBufferSize for the routers
	 * @param minPacketSize
	 * 		minimum packet size.
	 * @param maxPacketSize 
	 * 		max packet size.
	 * @param bandwidth 
	 * 		How many packets can arrive to the destination
	 * @param duration 
	 * 		how long the simulator runs for
	 * 
	 * @returns 
	 * 	 return the average time each packet spends within the network.
	 */
	public static double simulate(int numIntRouters, double arrivalProb, int maxBufferSize,
			int minPacketSize, int maxPacketSize, int bandwidth, int duration) {
		int totalPacketsSent = 0;
		int packetsDropped = 0;
		int id = 1;
		int packetsSent = 1;
		int totalTime = 0;
		Queue<Router> routerQue = new LinkedList<Router>();
		Queue<Router> toRemove = new LinkedList<Router>();
		Queue<Router> routers = new LinkedList<>();
		Router dispatcher = new Router(maxBufferSize);

		//creating routers
		for(int j = 0; j < numIntRouters; j++)
			routers.add(new Router(maxBufferSize));

		//time counter
		for(int timeCount = 1; timeCount <= duration; timeCount++) {
			System.out.printf("\nTime: %d\n",timeCount);

			//this tests if a packet is dispatched and if it is, it queues it
			for(int p = 0; p < MAX_PACKETS; p++) {
				if(Math.random() < arrivalProb) {
					Simulator g = new Simulator();
					int randomPacketSize = g.randInt(minPacketSize, maxPacketSize);
					Packet arrivedPacket = new Packet(timeCount, randomPacketSize, id);
					dispatcher.enqueue(arrivedPacket);
					System.out.printf("Packet %d arrives at dispatcher with size %d.\n", arrivedPacket.getId(), arrivedPacket.getPacketSize());
					id++;
				}
			}
			if(dispatcher.isEmpty())
				System.out.println("No packets arrived.");
			//while the dispatches is not empty, it keeps trying to push them into routers
			while(!dispatcher.isEmpty()) {
				int routerCount = 1;
				for(Router r: routers) {
					try {
						if(r.sentPacketTo(routers) == routerCount) {
							if(r.size() >= maxBufferSize) {
								throw new NetworkException();
							}
							if(!dispatcher.isEmpty()) {
								System.out.printf("Packet %d sent to Router %d\n", dispatcher.peek().getId(), routerCount);
								r.enqueue(dispatcher.dequeue());
							}
						}
						routerCount++;
					} catch(NetworkException e) {
						if(!dispatcher.isEmpty()) {
							System.out.printf("Network is conjested. Packet %d is dropped.\n", dispatcher.dequeue().getId());
							packetsDropped++;
						}
					} 
				}
			}
			for(Router r: routers) {
				if(r.peek() != null) {
					if(r.peek().getTimeToDest() != 0) 
						r.peek().setTimeToDest(r.peek().getTimeToDest()-1);
					if(r.peek().getTimeToDest() == 0) {
						routerQue.add(r);
						packetsSent++;
					}
				}
			}
			for(int i = 0; i<bandwidth; i++) {
				Router r = new Router();
				if(!routerQue.isEmpty()) {
					r = routerQue.poll();
					int time = 0;
					if(r.peek() != null)
						time = Math.abs(r.peek().getTimeArrive()-timeCount);
					totalTime += time;
					if(!r.isEmpty()) {
						System.out.printf("Packet %d has successfully reached its destination: +%d\n", r.peek().getId(), time);
						totalPacketsSent++;
					}
				}
				for(Router p: routers)
					if(!(p.isEmpty()))
						if(p.peek().equals(r.peek()))
							p.dequeue();
			}
			packetsSent = 1;
			int routerCounter = 1;
			for(Router r: routers) {
				System.out.printf("R%d: %s\n", routerCounter, r.toString());
				routerCounter++;
			}
		}
		System.out.println();
		System.out.println("Simulation ending...");
		System.out.println("Total service time: " + totalTime);
		System.out.println("Total packets served: " + totalPacketsSent);
		double d = (double)totalTime/(double)totalPacketsSent;
		System.out.printf("Average service time per packet: %s\n", String.format("%.2f",d));
		System.out.println("Total packets dropped: " + packetsDropped);

		double average = (double)duration/(double)totalPacketsSent;
		return average;
	}

	/*
	 * this will be my helper method that can generate a random number between minVal and maxVal, 
	 * inclusively. Return that randomly generated number.
	 * 
	 * @param minVal
	 * 		minimum value
	 * @param maxVal 
	 * 		maximum value
	 * 
	 * @returns 
	 * 	 return randomly generated number between max and min value.
	 */
	public int randInt(int minVal, int maxVal) {
		return (int)(Math.random()*(maxVal - minVal + 1) + minVal);
	}
}