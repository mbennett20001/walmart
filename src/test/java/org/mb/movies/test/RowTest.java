package org.mb.movies.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mb.movies.Reservation;
import org.mb.movies.Row;

public class RowTest {

	@Test
	public void testGetRowName() {
		Row r = new Row(9, 10);
		
		assertTrue(r.getRowName().charValue() == 'J');
	}

	@Test
	public void testAssignSeats() {
		Row r = new Row(0, 20);
		Reservation r1 = new Reservation("R001", 4);
		Reservation r2 = new Reservation("R002", 8);
		Reservation r3 = new Reservation("R003", 5);
		Reservation r4 = new Reservation("R004", 3);
		
		r.assignReservationBlock(r1);
		r.assignReservationBlock(r2);
		r.assignReservationBlock(r3);
		r.assignReservationBlock(r4);
		
		assertTrue(r1.getCompleted());
		assertTrue(r2.getCompleted());
		assertTrue(r3.getCompleted());
		assertTrue(r4.getCompleted());
		
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		System.out.println(r4);
	}
	
	@Test
	public void testOverAssign() {
		Row r = new Row(1, 20);
		Reservation r1 = new Reservation("R001", 8);
		Reservation r2 = new Reservation("R002", 8);
		
		r.assignReservationBlock(r1);
		try {
			r.assignReservationBlock(r2);
			fail("should have thrown error for over booking row");
		}
		catch (IllegalStateException ill) {
			System.out.println("got expected error: " + ill.getMessage());
		}
		
	}

}
