/*
 * Movie Theater Seating
 * 
 * SeatAssigner.java
 * 
 * Michael Bennett <mbennett20001@gmail.com>
 */
package org.mb.movies;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * SeatAssigner - main entry point
 *
 */
public class SeatAssigner {
	public static int numRows = 10;
	public static int numSeats = 20;

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("usage: SeatAssigner <path to input file>");
			System.exit(1);
		}
		
		// Open input file
		File inputFile = new File(args[0]);
		if (!inputFile.exists() || !inputFile.canRead()) {
			System.err.println("cant read file: " + inputFile);
			System.exit(1);
		}
		
		// Read input file into list of Reservation
		ArrayList<Reservation> reservations = null;
		try {
			reservations = ReservationInputReader.processInput(inputFile);
		}
		catch (Exception e) {
			System.err.println("problem reading input:" + e.getMessage());
			System.exit(1);
		}

		// Create our theater and assign seats
		Theater theater = new Theater(numRows, numSeats);
		theater.assignSeats(reservations);
		
		// Write assigned seats to the output file, over write if file exists.
		File outFile = new File("output.txt");
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(outFile));
			for (Reservation r : reservations) {
				pw.println(r);
			}
			pw.flush();
			pw.close();
			
			// Full path to the output
			System.out.println(outFile.getCanonicalPath());
		} catch (IOException e) {
			System.err.println("problem writing output to output.txt:" + e.getMessage());
			System.exit(1);
		}

	}

}
