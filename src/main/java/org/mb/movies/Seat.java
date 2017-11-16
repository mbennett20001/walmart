/*
 * Movie Theater Seating
 * 
 * Seat.java
 * 
 * Michael Bennett <mbennett20001@gmail.com>
 */
package org.mb.movies;

/**
 * A seat in a Row in a Theater.  A seat may only be assigned to a Reservation once.
 *
 */
public class Seat {
	private int num;
	private Character row;
	private Reservation reservation;
	
	/**
	 * Creates a seat in the given Row, with the seat num
	 * @param row
	 * @param num
	 */
	public Seat(Character row, int num) {
		this.row = row;
		this.num = num;
		this.reservation = null;
	}
	
	public String toString() {
		return row.toString() + num;
	}
	
	public boolean isReserved() {
		return (this.reservation != null);
	}
	
	public void setReservation(Reservation r) {
		if (reservation != null) {
			throw new IllegalStateException("seat already reserved");
		}
		this.reservation = r;
	}
}
