/*
 * Movie Theater Seating
 * 
 * Row.java
 * 
 * Michael Bennett <mbennett20001@gmail.com>
 */
package org.mb.movies;

/**
 * A Row in a Theater with a number of seats.
 *
 */
public class Row {
	private Seat[] seats;
	private Character rowName;

	int maxEmptyContiguousSeats;
	int totalEmptySeats;
	int blockStart;
	int blockEnd;
	int numAssignments;

	/**
	 * Constructs a row with the proper number of seats.
	 * 
	 * @param rowNum 0 = A, 1 = B, ...
	 * @param numSeats
	 */
	public Row(int rowNum, int numSeats) {
		totalEmptySeats = maxEmptyContiguousSeats = numSeats;
		numAssignments = 0;
		blockStart = blockEnd = numSeats/2;
		seats = new Seat[numSeats];
		rowName = new Character((char) (rowNum + 'A'));
		for (int i = 0; i < seats.length; i++) {
			seats[i] = new Seat(rowName, i + 1);
		}
	}

	public int getTotalEmptySeats() {
		return totalEmptySeats;
	}
	
	/**
	 * Assigns entire reservation in one contiguous block
	 * 
	 * IllegalStateException if unable to accomodate in one block.
	 * 
	 * @param r  - Reservation to assign
	 */
	public void assignReservationBlock(Reservation r) {
		if (maxEmptyContiguousSeats - r.numSeatsUnfilled() < 0) {
			throw new IllegalStateException("unable to assign entire reservation block");
		}

		int startingSeatNum = this.allocateBlock(r.getNumSeats());
		int i = startingSeatNum;
		while (i < startingSeatNum + r.getNumSeats()) {
			r.assignSeat(this.seats[i++]);
		}
	}
	
	/**
	 * Assigns as many possible seats on the left side of the row to a reservation as possible
	 * 
	 * @param r
	 */
	public void assignMaxSeatsPossibleLeft(Reservation r) {
		while (!r.isComplete() && (this.blockStart > 0)) {
			r.assignSeat(this.seats[this.blockStart-1]);
			this.blockStart--;
			this.totalEmptySeats--;
			maxEmptyContiguousSeats = Math.max(leftSideRemaining(), rightSideRemaining());
			this.numAssignments++;
		}
	}

	/**
	 * Assigns as many possible seats on the right side of the row to a reservation as possible
	 * 
	 * @param r
	 */
	public void assignMaxSeatsPossibleRight(Reservation r) {
		while (!r.isComplete() && (this.blockEnd < this.seats.length)) {
			r.assignSeat(this.seats[this.blockEnd]);
			this.blockEnd++;
			this.totalEmptySeats--;
			maxEmptyContiguousSeats = Math.max(leftSideRemaining(), rightSideRemaining());
			this.numAssignments++;

		}
	}
	
	public Character getRowName() {
		return rowName;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Seat s : seats) {
			sb.append(s.toString() + " ");
		}
		return sb.toString();

	}

	private int leftSideRemaining() {
		return this.blockStart;
	}
	
	private int rightSideRemaining() {
		return this.seats.length - this.blockEnd;
	}
	
	/**
	 * Given a seat count, return the index to the start of a contiguous block of seats.
	 * Allocation starts from the middle of the row to maximize customer satisfaction. (People
	 * prefer to be towards the center of the theater.)
	 * 
	 * @param seatCount
	 * @return
	 */
	private int allocateBlock(int seatCount) {
		int startSeat = 0;
		// first assignment gets middle of the row
		if (this.numAssignments == 0) {
			this.blockStart = (this.seats.length / 2) - (seatCount / 2);
			this.blockEnd = blockStart + seatCount;
			this.numAssignments++;
			startSeat = this.blockStart;
		} else if (leftSideRemaining() > rightSideRemaining()) {
			this.blockStart -= seatCount;
			startSeat = this.blockStart;
			this.numAssignments++;
		} else {
			startSeat = this.blockEnd;
			this.blockEnd += seatCount;
			this.numAssignments++;
		}
		this.totalEmptySeats -= seatCount;
		maxEmptyContiguousSeats = Math.max(leftSideRemaining(), rightSideRemaining());
		return startSeat;
	}

	public int getMaxEmptyContiguousSeats() {
		return this.maxEmptyContiguousSeats;
	}
}
