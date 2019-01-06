package com.jphobby.counterpointgenerator.Generators;

import com.jphobby.counterpointgenerator.Objects.Note;
import com.jphobby.counterpointgenerator.Objects.ProbabilityDensityFunctions;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Generates a melody.
 */

public class MusicGenerator {

    private final ProbabilityDensityFunctions probabilityDensityFunctions = new ProbabilityDensityFunctions();
    MusicGeneratorUtils generatorUtils = new MusicGeneratorUtils();

    /**
     * Fill the sheetMusic by generating a note for every open slot for the whole length of the array.
     * The starting notes are fixed, the rest are randomly generated
	 *
     * @param  sheetMusic  a 2D array which will contain all notes. rows represent melodies, columns represent notes
     * @return   		   the sheetmusic array filled with notes
     */

    public Note[][] fillSheetMusic(Note[][] sheetMusic) {

        generatorUtils.setStartingAndEndingNotes(sheetMusic);

        int secondNotePosition = 2;
        int penultimateNotePosition = sheetMusic[0].length - 1;
        
        IntStream.range(secondNotePosition, penultimateNotePosition)
                 .forEach(notePosition -> generateNotesForAllMelodies(sheetMusic, notePosition));

        return sheetMusic;
    }

    /**
     * Fill the sheetMusic with music by generating a note for every open slot for the whole length of the array.
     * The method iterates through all melodies and sequentially generates notes for all of them, starting from the bass.
     * The starting notes are fixed, the rest are randomly generated through a probability density function.
     * 
     * @param  sheetMusic  	 a 2D array which will contain all notes. rows represent melodies, columns represent notes
     * 					  	 NOTE: sheetMusic should already contain the starting and ending notes
     * @param  notePosition  current note index in array
     * @return   		  	 the sheetmusic array filled with notes, except for the starting and ending notes
     */

    private final int bassMelodyNumber = 0;
    
    private Note[][] generateNotesForAllMelodies(Note[][] sheetMusic, int notePosition) {
    	int numberOfMelodies = sheetMusic.length;
    	int previousNoteLocation = notePosition - 1;
    	// Check whether previous note exists
    	if (sheetMusic[bassMelodyNumber][previousNoteLocation] == null) {
    		throw new IllegalStateException("Previous note is null, was starting note set?");
    	}
        
        Note previousBassNote = sheetMusic[bassMelodyNumber][previousNoteLocation];

        Note newBassNote = generateBassNote(sheetMusic, notePosition, bassMelodyNumber, previousBassNote);

        // Generate notes for remaining melodies
        IntStream.range(1, numberOfMelodies).forEach(melody -> { generateNote(sheetMusic, notePosition, newBassNote, melody); });

        return sheetMusic;
    }

    private void generateNote(Note[][] sheetMusic, int notePosition, Note currentBassNote, int melodyNumber) {
        int intervalToNewNote = probabilityDensityFunctions.getIntervalFromConsonantsDistribution();
        // Set note
        int newPitch = currentBassNote.getPitch() + intervalToNewNote;
        int newOctave = currentBassNote.getOctave() + 1 + melodyNumber / 2;
        sheetMusic[melodyNumber][notePosition] = new Note(newPitch, newOctave);
    }

    private Note generateBassNote(Note[][] sheetMusic, int note, int bassMelody, Note previousBassNote) {
        int intervalToNewBassNote = probabilityDensityFunctions.getIntervalFromDefaultDistribution();
        intervalToNewBassNote = generatorUtils.intervalUpOrDown(intervalToNewBassNote, previousBassNote);
        // Set note
        int newBassPitch = previousBassNote.getPitch() + intervalToNewBassNote;
        int newBassOctave = previousBassNote.getOctave();
        sheetMusic[bassMelody][note] = new Note(newBassPitch, newBassOctave);
        return sheetMusic[bassMelody][note];
    }
}
