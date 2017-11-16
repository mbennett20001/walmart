Movie Theater Seating

Michael Bennett <mbennett20001@gmail.com>

Assumptions made:
  * Reservations made early (those at the beginning of the file) should get 
    priority over over those made late (at the end of the file).
  * Higher priority Reservations should be kept together (seats should be 
    contiguous).
  * Most desirable seats are center seats within rows and center  rows within 
    theater.
  * Non contiguous seating is only done once all contiguous blocks are 
    exhausted.
    
Building and running:

(Prerequisite: Java 8 installed and path, or JAVA_HOME set)

To build on *Nix/MacOS, execute "./gradlew build install"
To build on Win, execute "gradle build install"

This will build and run all tests, and create a directory containing the 
program:
  build/install/SeatAssigner
  
Test output report will be created in:
  build/reports/tests/test/index.html
  
To execute the program, change directories and execute:
./SeatAssigner /path/to/input/file.

Example: (MacOS)
----
mcmicbe01:walmart micbe$ pwd
/Users/micbe/Development/walmart

mcmicbe01:walmart micbe$ ./gradlew build install

BUILD SUCCESSFUL in 1s
9 actionable tasks: 9 executed

mcmicbe01:walmart micbe$ cd build/install/SeatAssigner/bin/

mcmicbe01:bin micbe$ ./SeatAssigner /Users/micbe/Development/walmart/src/test/resources/TestInput3.txt
/Users/micbe/Development/walmart/build/install/SeatAssigner/bin/output.txt

mcmicbe01:bin micbe$ more output.txt
R001 E10,E11
R002 E12,E13,E14,E15
R003 E5,E6,E7,E8,E9
R004 E16,E17,E18,E19
R005 E3,E4
R006 E1,E2
R007 F9,F10,F11,F12
R008 F13,F14,F15,F16,F17
R009 F5,F6,F7,F8
R010 F3,F4
R011 F18,F19
R012 D9,D10,D11,D12
R013 D13,D14,D15,D16,D17
R014 D5,D6,D7,D8
R015 F1,F2
R016 G8,G9,G10,G11,G12,G13,G14
R017 G1,G2,G3,G4,G5,G6,G7
R018 C8,C9,C10,C11,C12,C13,C14
