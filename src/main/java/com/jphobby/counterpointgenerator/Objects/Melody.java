package com.jphobby.counterpointgenerator.Objects;

/**
 * A uniquely identifiable melody of a specified length which starts in a certain octave and which contains notes.
 */

public class Melody {

    private int melodyIdentifier;
    private int startingOctave;
    private Note[] notes;

    public Melody(int melodyIdentifier, int melodyOctave, int melodyLength) {
        this.melodyIdentifier = melodyIdentifier;
        this.startingOctave = melodyOctave;
        notes = new Note[melodyLength];
    }

    public Note[] getNotes() {
        return notes;
    }

//    public Note getNoteAtIndex(int index) {
//
//        while (notes[index] == null) {
//            index--;
//        }
//
//        return notes[index];
//    }

    public void addNote(Note newNote, int index) {
        notes[index] = newNote;
    }

    public int getMelodyIdentifier() {
        return melodyIdentifier;
    }

    public int getStartingOctave() {
        return startingOctave;
    }
}
