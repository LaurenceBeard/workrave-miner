package cz.razor.workraveminer;

import java.util.Arrays;

import org.joda.time.DateTime;

/**
 * Activity record from Workrave.
 * 
 * @author kedaj
 *
 */
public class DayActivity {
	DateTime startDateTime;
	DateTime endDateTime;
	int microBreaksNaturallyTaken;
	int restBreaksNatuarallyTaken;
	int activeSeconds;
	int mouseMovement;
	int clickMovement;
	int movementTime;
	int clicks;
	int keyStrokes;
	
	public static final String SEPARATOR = " ";
	
	
	public String toString(){
		return startDateTime.toString() + "\n"
		+ endDateTime.toString()+ " ~~ " + clicks+ "clicks, "+keyStrokes+" keystrokes";
	}
	
	public static String getMetadata(){
		String[] chunks = {
				"startDateTime", 
				"endDateTime",
				"microBreaksNaturallyTaken", 
		"restBreaksNatuarallyTaken", 		
		"activeSeconds", 		
		"mouseMovement", 		
		"clickMovement", 		
		"movementTime", 		
		"clicks", 		
		"keyStrokes", 		
		};
		
		String s="";
		for (int i = 0; i < chunks.length; i++) {
			s += chunks[i];
			if(i != chunks.length-1){
				s+= SEPARATOR;
			}
		}
		return s;
	}
	
	public String toDataString(){
		
		String[] chunks = {
				startDateTime.toString(), 
				endDateTime.toString(),
				Integer.toString(microBreaksNaturallyTaken), 
		Integer.toString(restBreaksNatuarallyTaken), 		
		Integer.toString(activeSeconds), 		
		Integer.toString(mouseMovement), 		
		Integer.toString(clickMovement), 		
		Integer.toString(movementTime), 		
		Integer.toString(clicks), 		
		Integer.toString(keyStrokes), 		
		};
		
		String s="";
		for (int i = 0; i < chunks.length; i++) {
			s += chunks[i];
			if(i != chunks.length-1){
				s+= SEPARATOR;
			}
		}
		return s;
		
	}
}

