package com.jphobby.counterpointgenerator;

import java.util.stream.IntStream;

import com.jphobby.counterpointgenerator.Objects.Note;

public class PlayerUtils {

	static String convertToPlayerFormat(Note[][] sheetMusic) {
        StringBuilder playerString = new StringBuilder();
        // Code basic settings such as tempo
        playerString.append("T").append(App.tempo).append(" ");
        // Loop through all melodies and notes and add them to the player string
        IntStream.range(0, App.numberOfMelodies)
        	.filter(melody -> sheetMusic[melody] != null)
            .forEach(melodyNumber -> { 
        		// For each melody, add identifier
            	playerString.append("V").append(melodyNumber).append(" ");

                // Add pitch and octave for all notes of this melody
                playerString.append(
                		IntStream.range(1, App.lengthInNotes)
                		.filter(note -> sheetMusic[melodyNumber][note] != null)
                		.collect(StringBuilder::new,
                        (sb, noteNumber) -> sb.append(sheetMusic[melodyNumber][noteNumber].getPitchChar())
	                        				  .append(sheetMusic[melodyNumber][noteNumber].getOctave())
	                        	     	      .append(sheetMusic[melodyNumber][noteNumber].getDuration())
	                        	     	      .append(" "),
                        (sb1, sb2) -> sb1.append(sb2.toString()))
                );
            });

        return playerString.toString();
    }
	
	static void printPlayerString(String playerString) {
		String[] parts = playerString.split("V");
		
		for(String part : parts) {
            System.out.println(part);
		}
	}
}
