package com.jphobby.counterpointgenerator.Generators;

import com.jphobby.counterpointgenerator.Objects.Note;

import java.util.Random;
import java.util.stream.IntStream;

public class MusicGeneratorUtils {

    private final Random r = new Random();


    /**
     * Is used to keep the melody within certain octave bounds, for added listening pleasure
     */

    int intervalUpOrDown(int pitch, Note previousNote) {

        if (previousNote.getOctave() < 3) {
            return pitch;
        } else if (previousNote.getOctave() > 5 || r.nextBoolean() ) {
            return -pitch;
        } else {
            return pitch;
        }
    }

    void setStartingAndEndingNotes(Note[][] sheetMusic) {
        IntStream.range(0, sheetMusic.length).forEach(melodyNumber -> {
            // Starting pitches are 1 and 5, alternating
            int startingPitch = (melodyNumber % 2 == 0) ? 1 : 5;
            // Octave starts at 3 and increases every two melodies
            int octave = 3 + melodyNumber / 2;

            // Set starting note
            sheetMusic[melodyNumber][1] = new Note(startingPitch, octave);
            // Set ending note, with twice the duration
            int melodyLength = sheetMusic[melodyNumber].length - 1;
            Note endingNote = new Note(startingPitch, octave);
            endingNote.setDuration("w");
            sheetMusic[melodyNumber][melodyLength] = endingNote;
        });
    }

}
