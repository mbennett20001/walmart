/*
 * Movie Theater Seating
 * 
 * ReservationInputReader.java
 * 
 * Michael Bennett <mbennett20001@gmail.com>
 */

package org.mb.movies;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReservationInputReader {

	/**
	 * Reads reservation input.  One reservation per line in the format:
	 *   Rxxx y
	 *   
	 *   where 'xxx' is the reservation id number with leading zeros, 
	 *   and y is an integer with the number of seats
	 *   There must be a newline or EOF after the number of seats
	 *   
	 *   For example:
	 *   R001 5
	 *   
	 *   
	 * @param inputFile
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<Reservation> processInput(File inputFile) throws IOException {
		ArrayList<Reservation> reservations = null;
		List<String> allLines = Files.readAllLines(inputFile.toPath());
		if ((allLines != null) && !allLines.isEmpty()) {
			reservations =  new ArrayList<Reservation>(allLines.size());
			
			Pattern p = Pattern.compile("(R\\d+)\\s+(\\d+)");
			
			for (String r : allLines) {
				Matcher m = p.matcher(r);
				if (m.matches()) {
					String res = m.group(1);
					String num = m.group(2);
					reservations.add(new Reservation(res, Integer.parseInt(num)));
				}
				else {
					throw new IOException("bad input line: " + r);
				}
			}
			
		}

		return reservations;
	}
}
