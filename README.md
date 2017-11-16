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
```
mcmicbe01:walmart micbe$ pwd
/Users/micbe/Development/walmart

mcmicbe01:walmart micbe$ ./gradlew build install

BUILD SUCCESSFUL in 1s
9 actionable tasks: 9 executed

mcmicbe01:walmart micbe$ cd build/install/SeatAssigner/bin/

mcmicbe01:bin micbe$ ./SeatAssigner /Users/micbe/Development/walmart/src/test/resources/TestInput3.txt
/Users/micbe/Development/walmart/build/install/SeatAssigner/bin/output.txt
```