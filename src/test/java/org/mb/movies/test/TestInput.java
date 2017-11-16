package org.mb.movies.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.Test;
import org.mb.movies.Reservation;
import org.mb.movies.ReservationInputReader;

public class TestInput {

	@Test
	public void testProcessInput() {
		File f= new File(TestInput.class.getClassLoader().getResource("TestInput1.txt").getFile());
		try {
			ArrayList<Reservation> reservations = ReservationInputReader.processInput(f);
			assertNotNull(reservations);
			assertTrue("size is 4", reservations.size() == 4);
			Reservation r = reservations.get(2);
			assertTrue("third reservation id is R003", r.getReservationID().equals("R003"));
			assertTrue("third reservation has 4 seats", r.getNumSeats() == 4);
			int totalSeats = reservations.stream().collect(Collectors.summingInt(Reservation::getNumSeats));
			assertTrue("total seats: 13", totalSeats == 13);

			
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testProcessBadInput() {
		
		File f2 = new File(TestInput.class.getClassLoader().getResource("BadInput.txt").getFile());
		try {
			ReservationInputReader.processInput(f2);
			fail("should have thrown exception for bad input");
		}
		catch(IOException e) {
			assertNotNull(e.getMessage());
		}

	}
}
