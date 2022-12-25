/*
 * Name: Daniel He
 * Solar ID: 114457594
 * Homework #4
 * Email: daniel.he@stonybrook.edu
 * Course: CSE214
 * Recitation #: R01 TA: Ulfeen Ayevan & Wesley Mui  
 */

import java.util.LinkedList;
import java.util.Collection;
import java.util.Queue;

/*
* This class represents a router in the network, which is ultimately a queue. Extends a linkedlist to simplify the class definition. 
* I used the same definitions for all the different types of routers in the network. Router would have methods that extends linkedlist
* and also a method to find the router with the least amount of packets and return it's index.
* 
* @author Daniel He
* email: daniel.he@stonybrook.edu
* 114457594
*/
public class Router extends LinkedList<Packet>{
	private boolean haveSent = false;
	private int capacity;
	private int index;
	Queue<Packet> buffer;
	
	/*
	 * Constructs an Router objects with null or 0 fields.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> The Router has been initialized with empty fields.
	 * 
	 */
	
	//do I need enque and deque or should I remove it like professor said
	//wdwdw
	public Router() {
		buffer = new LinkedList<Packet>();
	}
	
	
	/*
	 * Does the same thing as the Router() method but uses inputted fields to set the fields.
	 * 
	 * @param capacity
	 * 		New capacity for the Router
	 */
	public Router(int capacity) {
		this.capacity = capacity;
		buffer = new LinkedList<Packet>();
	}
	
	
	/*
	 * Pushes packet p into the queue
	 * 
	 * @param p
	 * 		New p for the queue
	 * 
	 * <dt> Preconditions:
	 * 	<dd>This Router object has been instantiated.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The queue in router now has packet p in it
	 * 
	 */
	public void enqueue(Packet p) {
		buffer.add(p);
	}
	
	/*
	 * Removed packet p from the queue
	 * 
	 * <dt> Preconditions:
	 * 	<dd>This Router object has been instantiated.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The queue in router now does not has packet p in it
	 * 
	 */
	public Packet dequeue() {
		return buffer.poll();
	}
	
	/*
	 * Pushes packet p into the queue
	 * 
	 * <dt> Preconditions:
	 * 	<dd>This Router object has been instantiated.
	 * 
	 * @returns
	 * 		the first packet in the router queue without removing it
	 * 
	 */
	public Packet peek() {
		return buffer.peek();
	}
	
	/*
	 * Returns the size of the router size
	 * 
	 * <dt> Preconditions:
	 * 	<dd>This Router object has been instantiated.
	 * 
	 * @returns
	 * 		the router size
	 * 
	 */
	public int size() {
		return buffer.size();
	}
	
	/*
	 * Returns true if the router is empty, false if not
	 * 
	 * <dt> Preconditions:
	 * 	<dd>This Router object has been instantiated.
	 * 
	 * @returns
	 * 		true if the router is empty, false if not.
	 */
	public boolean isEmpty() {
		return buffer.isEmpty();
	}
	
	/*
	 *this method loop through the list Intermediate routers. Find the router with the most free buffer space 
	 *(contains least Packets), and return the index of the router. If there are multiple routers, any 
	 *corresponding indices will be acceptable. If all router buffers are full, throw an exception. 
	 *You must handle this in your code.
	 * 
	 * <dt> Preconditions:
	 * 	<dd>This Router object has been instantiated.
	 * 
	 * @returns
	 * 		the index of the router with the least packets
	 */
	public int sentPacketTo(Collection<Router> intRouters) throws NetworkException{
		int min = 0;
		int counter = 1;
		int firstInstance = 1;
		int index = 1;
		boolean routersFull = false;
		for(Router i: intRouters) {
			if(firstInstance == 1) {
				min = i.size();
				firstInstance++;
			}
			if(i.size()<min) {
				min = i.size();
			}
		}
		for(Router i: intRouters) {
			if(i.size() == min) {
				break;
			}
			index++;
		}
		return index;
	}
	
	/*
	 * Returns a neatly formatted String representation of the Router object.
	 * 
	 * @returns
	 * 		A neatly formatted string representing the Packet object.
	 */
	public String toString() {
		int count = 0;
		String s = "{";
		Queue<Packet> temp = new LinkedList<Packet>();
		for(Packet p:buffer) {
			s += p.toString() + ", ";
			count++;
		}
		if(count == 1) {
			s = s.substring(0, s.length()-2);
		}
		if(count > 1) {
			s = s.substring(0, s.length()-2);
		}
		s+= "}";
		return s;
	}
}
