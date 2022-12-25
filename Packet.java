/*
 * Name: Daniel He
 * Solar ID: 114457594
 * Homework #4
 * Email: daniel.he@stonybrook.edu
 * Course: CSE214
 * Recitation #: R01 TA: Ulfeen Ayevan & Wesley Mui  
 */

/*
* This class represents a packet that will be sent through the network. It will create and 
* initialize it with fields with a size, timeArrive, and timeToDest that is randomly generated.
* 
* @author Daniel He
* email: daniel.he@stonybrook.edu
* 114457594
*/
public class Packet {
	private static int packetCount = 0;
	private int id;
	private int packetSize;
	private int timeArrive;
	private int timeToDest;
	
	/*
	 * Constructs an Packet objects with null or 0 fields.
	 * 
	 * <dt> Postconditions:
	 * 	<dd> The Packet has been initialized with empty fields.
	 * 
	 */
	Packet(){
	}
	
	/*
	 * Does the same thing as the Packet() method but uses inputted fields to set the fields.
	 * Also sets time to dest as packetsize/100
	 * 
	 * @param time
	 * 		New time for the Packet
	 * @param size 
	 * 		New size for the Packet
	 *  @param id 
	 * 		New id for the Packet

	 */
	Packet(int time, int size, int id){
		this.setPacketSize(size);
		this.setTimeToDest((packetSize)/100);
		this.setTimeArrive(time);
		this.setId(id);
	}
	
	/*
	 * Replaces the int id with the parameter id
	 * 
	 * @param id
	 * 		New id for the Packet
	 * 
	 * <dt> Preconditions:
	 * 	<dd>This Packet object has been instantiated.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new Packet now has a new id field with the parameter id.
	 * 
	 * @throws Exception
	 * 		Throws a exception when the id is null
	 * 
	 */
	public void setId(int id) {
		try {
			this.id = id;
		} catch(Exception e) {
			System.out.println("id is null");
		}
	}
	
	/*
	 * Replaces the int packetSize with the parameter packetSize
	 * 
	 * @param packetSize
	 * 		New packetSize for the Packet
	 * 
	 * <dt> Preconditions:
	 * 	<dd>This Packet object has been instantiated.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new Packet now has a new packetSize field with the parameter packetSize.
	 * 
	 * @throws Exception
	 * 		Throws a exception when the packetSize is null
	 * 
	 */
	public void setPacketSize(int packetSize) {
		try {
			this.packetSize = packetSize;
		} catch(Exception e) {
			System.out.println("packetSize is null");
		}
	}

	/*
	 * Replaces the int timeArrive with the parameter timeArrive
	 * 
	 * @param timeArrive
	 * 		New timeArrive for the Packet
	 * 
	 * <dt> Preconditions:
	 * 	<dd>This Packet object has been instantiated.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new Packet now has a new timeArrive field with the parameter timeArrive.
	 * 
	 * @throws Exception
	 * 		Throws a exception when the timeArrive is null
	 * 
	 */
	public void setTimeArrive(int timeArrive) {
		try {
			this.timeArrive = timeArrive;
		} catch(Exception e) {
			System.out.println("timeArrive is null");
		}
	}

	/*
	 * Replaces the int timeToDest with the parameter timeToDest
	 * 
	 * @param timeToDest
	 * 		New timeToDest for the Packet
	 * 
	 * <dt> Preconditions:
	 * 	<dd>This Packet object has been instantiated.
	 * 
	 * <dt> Postconditions:
	 * 	<dd>The new Packet now has a new timeToDest field with the parameter timeToDest.
	 * 
	 * @throws Exception
	 * 		Throws a exception when the timeToDest is null
	 * 
	 */
	public void setTimeToDest(int timeToDest) {
		this.timeToDest = timeToDest;
	}
	
	/*
	 * returns the int id
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This Packet object has been instantiated.
	 * 
	 * @return
	 * 	The Packet id
	 * 
	 */
	public int getId() {
		return id;
	}
	
	/*
	 * returns the int packetSize
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This Packet object has been instantiated.
	 * 
	 * @return
	 * 	The Packet packetSize
	 * 
	 */
	public int getPacketSize() {
		return packetSize;
	}
	
	/*
	 * returns the int timeArrive
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This Packet object has been instantiated.
	 * 
	 * @return
	 * 	The Packet timeArrive
	 * 
	 */
	public int getTimeArrive() {
		return timeArrive;
	}
	
	/*
	 * returns the int timeToDest
	 * 
	 * <dt> Preconditions:
	 * 	<dd> This Packet object has been instantiated.
	 * 
	 * @return
	 * 	The Packet timeToDest
	 * 
	 */
	public int getTimeToDest() {
		return timeToDest;
	}
	
	/*
	 * Returns a neatly formatted String representation of the Packet object.
	 * 
	 * @returns
	 * 		A neatly formatted string representing the Packet object.
	 */
	public String toString() {
		String s = String.format("[%d, %d, %d]", id, timeArrive, timeToDest);
		return s;
	}
	
	/*
	 * Compares two packets and returns true if they are the same, false if not.
	 * 
	 * @returns
	 * 		Returns true if they are the same, false if not.
	 */
	public boolean equals() {
		if(this.id == id && this.packetSize == packetSize && this.timeArrive == timeArrive && this.timeToDest == timeToDest)
			return true;
		return false;
	}
}
