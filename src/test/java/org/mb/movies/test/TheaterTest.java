package org.mb.movies.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.mb.movies.Reservation;
import org.mb.movies.Theater;

public class TheaterTest {

	@Test
	public void testRowIndexCalculation() {
		Theater t = new Theater(10,20);
		// expected sequence for 10:
		int[] expectedResult = { 4, 5, 3, 6, 2, 7, 1, 8, 0, 9};
		
		for (int i=0; i < 10; i++) {
			assertEquals(expectedResult[i], t.computeRowIndex(i));
		}
		
	}
	@Test
	public void testTheater() {
		Theater t = new Theater(10,20);
		
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		reservations.add(new Reservation("R001", 8));
		reservations.add(new Reservation("R002", 8));
		reservations.add(new Reservation("R003", 2));
		
		t.assignSeats(reservations);
		
		for (Reservation r : reservations) {
			assertTrue(r.getCompleted());
			System.out.println(r);
		}
	}

	@Test
	public void testTheater2() {
		Theater t = new Theater(10,20);
		
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		reservations.add(new Reservation("R001", 18));
		reservations.add(new Reservation("R002", 18));
		reservations.add(new Reservation("R003", 18));
		reservations.add(new Reservation("R004", 19));
		reservations.add(new Reservation("R005", 19));
		reservations.add(new Reservation("R006", 19));
		reservations.add(new Reservation("R007", 19));
		reservations.add(new Reservation("R008", 19));
		reservations.add(new Reservation("R009", 19));
		reservations.add(new Reservation("R010", 19));
		reservations.add(new Reservation("R011", 13));
		
		
		t.assignSeats(reservations);
		
		for (Reservation r : reservations) {
			assertTrue(r.getCompleted());
			System.out.println(r);
		}
	}

	@Test
	public void testTheater3() {
		Theater t = new Theater(10,20);
		
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		reservations.add(new Reservation("R001", 25));
		reservations.add(new Reservation("R002", 25));
		reservations.add(new Reservation("R003", 10));
		reservations.add(new Reservation("R004", 3));
		reservations.add(new Reservation("R005", 5));
		

		t.assignSeats(reservations);
		
		for (Reservation r : reservations) {
			assertTrue(r.getCompleted());
			System.out.println(r);
		}
	}
}
