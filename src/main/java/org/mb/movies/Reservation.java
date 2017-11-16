/*
 * Movie Theater Seating
 * 
 * Reservation.java
 * 
 * Michael Bennett <mbennett20001@gmail.com>
 */

package org.mb.movies;

import java.util.ArrayList;
import java.util.Iterator;

public class Reservation {
	private String reservationID;
	private int numSeats;
	private int numFilled;
	private ArrayList<Seat> assignedSeats;
	
	public Reservation(String reservationID, int numSeats) {
		this.reservationID = reservationID;
		this.numSeats = numSeats;
		this.numFilled = 0;
		this.assignedSeats = new ArrayList<Seat>(numSeats);
	}

	public String getReservationID() {
		return reservationID;
	}
	
	public int numSeatsUnfilled() {
		return numSeats - numFilled;
	}
	
	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}
	
	public int getNumSeats() {
		return numSeats;
	}
	
	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}
	
	public boolean isComplete() {
		return assignedSeats.size() == numSeats;
	}
	
	public void assignSeat(Seat s) {
		if (assignedSeats.size() < numSeats) {
			assignedSeats.add(s);
			this.numFilled++;
			s.setReservation(this);
		}
		else {
			throw new IllegalStateException("all seats already assigned");
		}
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer(this.reservationID + " ");
		if (this.assignedSeats != null) {
			Iterator<Seat> sIter = this.assignedSeats.iterator();
			while (sIter.hasNext()) {
				Seat s = sIter.next();
				sb.append(s.toString());
				if (sIter.hasNext()) {
					sb.append(',');
				}
			}
		}
		else {
			for (int i=0; i< this.numSeats; i++) {
				sb.append('x');
				if (i < this.numSeats) {
					sb.append(',');
				}
			}
		}
		
		return sb.toString();
	}

	public boolean getCompleted() {
		return this.numFilled == this.numSeats;
	}
}
