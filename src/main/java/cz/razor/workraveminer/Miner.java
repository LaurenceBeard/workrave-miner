package cz.razor.workraveminer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.joda.time.DateTime;

public class Miner {

	public static final String LINE_DATE = "D";
	public static final String LINE_MICRO_BREAK = "B 0";
	public static final String LINE_REST_BREAK = "B 1";
	public static final String LINE_DAY_BREAK = "B 2";
	public static final String LINE_ACTIVITY = "m";

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		String fileName = null;
		int numberOfRecords = 0;
		try {
			if (args.length == 0) {
				System.out.println("run the program with filename parameter");
			} else {
				fileName = args[0];
			}

			String line;
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			DayActivity dayActivity = null;
			final int N = 5;
			DateTime startDateTime;
			DateTime endDateTime;
			String[] parts;
			

			in.readLine(); // skip the first comment line

			while ((line = in.readLine()) != null) {
				parts = line.split(" ");

				if (line.startsWith(LINE_DATE)) {
					startDateTime = new DateTime(Integer.parseInt(parts[3]),
							Integer.parseInt(parts[2]) + 1, Integer
									.parseInt(parts[1]), Integer
									.parseInt(parts[4]), Integer
									.parseInt(parts[5]), 0, 0);

					endDateTime = new DateTime(Integer.parseInt(parts[3 + N]),
							Integer.parseInt(parts[2 + N]) + 1, Integer
									.parseInt(parts[1 + N]), Integer
									.parseInt(parts[4 + N]), Integer
									.parseInt(parts[5 + N]), 0, 0);
					dayActivity = new DayActivity();
					dayActivity.startDateTime = startDateTime;
					dayActivity.endDateTime = endDateTime;
					//System.out.println(dayActivity.toString());
					//System.out.println(line);
				} else if (line.startsWith(LINE_MICRO_BREAK)) {
					dayActivity.microBreaksNaturallyTaken = Integer
							.parseInt(parts[5]);

				} else if (line.startsWith(LINE_REST_BREAK)) {
					dayActivity.restBreaksNatuarallyTaken = Integer
							.parseInt(parts[5]);

				} else if (line.startsWith(LINE_DAY_BREAK)) {

				} else if (line.startsWith(LINE_ACTIVITY)) {
					dayActivity.activeSeconds = Integer.parseInt(parts[2]);
					dayActivity.mouseMovement = Integer.parseInt(parts[3]);
					dayActivity.clickMovement = Integer.parseInt(parts[4]);
					dayActivity.movementTime = Integer.parseInt(parts[5]);
					dayActivity.clicks = Integer.parseInt(parts[6]);
					dayActivity.keyStrokes = Integer.parseInt(parts[7]);
					System.out.println(dayActivity.toDataString());
					
					numberOfRecords++;
				} else
					continue;

			}
			in.close();
		} catch (IOException e) {
			System.out.println("Exception reading file " + fileName + " : "
					+ e.toString());
		}
		
		System.out.println(DayActivity.getMetadata());
		System.out.println("\n"+numberOfRecords+" records");

		// # WorkRaveStats 4
		// # D 16 5 104 14 51 17 5 104 3 59
		// # B 0 7 9 8 40 0 0 9 386
		// # B 1 7 4 1 0 1 3 4 362
		// # B 2 7 0 0 0 0 0 0 0
		// # m 6 6049 234742 113560 266 417 16617
		// # D 17 5 104 16 49 18 5 104 3 59
		// # B 0 7 1 0 3 0 0 1 526
		// # B 1 7 3 0 0 0 2 2 1768
		// # B 2 7 0 0 0 0 0 0 872
		// # m 6 15272 39275 11937 36 30 1899
		// #
		// # D(ate): [ tm_mday tm_mon tm_year tm_hour tm_min ] {2}
		// #D 20 10 109 10 5 20 10 109 16 39
		// # B(reak): <t> <nn> <x0> <x1> <x2> <x3> <x4> <x5> <x6>
		// #
		// # <t> = break type
		// # X 0 = microbreak
		// # X 1 = rest break
		// # 2 = daily limit
		// #
		// # <nn> STATS_BREAKVALUE_SIZEOF - number of following numbers
		// #
		// # <x0> STATS_BREAKVALUE_PROMPTED - break prompts<x6> + repeated
		// prompts
		// # <x1> STATS_BREAKVALUE_TAKEN - prompted breaks taken
		// # X <x2> STATS_BREAKVALUE_NATURAL_TAKEN - natural breaks taken
		// # <x3> STATS_BREAKVALUE_SKIPPED - breaks skipped
		// # <x4> STATS_BREAKVALUE_POSTPONED - breaks postponed
		// # <x5> STATS_BREAKVALUE_UNIQUE_BREAKS - break prompts
		// # <x6> STATS_BREAKVALUE_TOTAL_OVERDUE - overdue time (seconds)
		// #
		// # m(isc): <nn> <x0> <x1> <x2> <x3> <x4> <x5>
		// #
		// # <nn> STATS_VALUE_SIZEOF
		// # X <x0> STATS_VALUE_TOTAL_ACTIVE_TIME (seconds)
		// # X <x1> STATS_VALUE_TOTAL_MOUSE_MOVEMENT
		// # X <x2> STATS_VALUE_TOTAL_CLICK_MOVEMENT - ask what this means
		// # X <x3> STATS_VALUE_TOTAL_MOVEMENT_TIME (seconds)
		// # X <x4> STATS_VALUE_TOTAL_CLICKS
		// # X <x5> STATS_VALUE_TOTAL_KEYSTROKES

	}

}
