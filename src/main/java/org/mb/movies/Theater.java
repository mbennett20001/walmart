/*
 * Movie Theater Seating
 * 
 * Theater.java
 * 
 * Michael Bennett <mbennett20001@gmail.com>
 */
package org.mb.movies;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A Theater with a number of Rows, each row with a number of seats.
 * 
 * @author micbe
 *
 */
public class Theater {
	private Row[] row;
	private int numSeatsPerRow;
	
	/**
	 * Creates a theater with the number of rows and number of seats per row
	 * 
	 * @param numRows
	 * @param numSeatsPerRow
	 */
	public Theater(int numRows, int numSeatsPerRow) {
		this.numSeatsPerRow = numSeatsPerRow;
		row = new Row[numRows];
		for (byte i = 0; i < numRows; i++) {
			row[i] = new Row(i, numSeatsPerRow);
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Theater:\n\n");
		
		for (Row r : row) {
			sb.append(r.toString() + "\n");
		}
		
		
		return sb.toString();
	}

	public int computeRowIndex(int iterCount) {
		if ((row.length % 2) == 0) {
			// Computation for EVEN number of rows
			return ((iterCount % 2) == 0) ? (row.length/2) - (iterCount/2) -1 :(row.length/2) + (iterCount/2);
		}
		// Computation for ODD number of rows
		return ((iterCount % 2) == 0) ? (row.length/2) - (iterCount/2) : (row.length/2) + (iterCount/2) + 1 ;
	}

	/**
	 * Recursively attempts to assign entire reservation to a row as one block
	 * 
	 * @param res - Reservation
	 * @param iterationCount 
	 * @return
	 */
	protected boolean assignReservationToRow(Reservation res, int iterationCount) {
		if (iterationCount  < this.row.length) {
			int rowIndex = computeRowIndex(iterationCount);
			if (this.row[rowIndex].getMaxEmptyContiguousSeats() >= res.getNumSeats()) {
				this.row[rowIndex].assignReservationBlock(res);
				return true;
			}
		
			return assignReservationToRow(res, iterationCount+1);
		}
		return false;
	}
	
	/**
	 * Recursively assigns as many seats as possible to a reservation to a row, attempting to keep
	 * seats close together across rows.
	 * 
	 * @param res - Reservation
	 * @param iterationCount
	 * @return
	 */
	protected boolean assignPartialReservationToRow(Reservation res, int iterationCount) {
		if (iterationCount  < this.row.length) {
			int rowIndex = computeRowIndex(iterationCount);
			if (this.row[rowIndex].getTotalEmptySeats() > 0) {
				this.row[rowIndex].assignMaxSeatsPossibleLeft(res);
				if (res.getCompleted()) {
					return true;
				}
				
				boolean assigned =  assignPartialReservationToRow(res, iterationCount+1);
				if (assigned) {
					return true;
				}
				
				this.row[rowIndex].assignMaxSeatsPossibleRight(res);
				if (res.getCompleted()) {
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * Perform the assignment of seats.  Best seats, in the middle of the theater,
	 * are given to the earliest reservations on record.  Seats are kept together until there
	 * are no more contiguous seats left that can hold the reservation.  At that point the
	 * reservation is divided across seats while attempting to keep these seats close together across
	 * rows.
	 * 
	 * @param reservations
	 */
	public void assignSeats(ArrayList<Reservation> reservations) {
		int totalSeatsAll = reservations.stream().collect(Collectors.summingInt(Reservation::getNumSeats));

		if (totalSeatsAll > (row.length * numSeatsPerRow)) {
			throw new RuntimeException("Number of seats in reservation file:"+ totalSeatsAll + " exceeds capacity");
		}

		for (Reservation r : reservations) {
			boolean completed = assignReservationToRow(r, 0);
			if (!completed) {
				completed = assignPartialReservationToRow(r, 0);
			}
		}
	}
}
