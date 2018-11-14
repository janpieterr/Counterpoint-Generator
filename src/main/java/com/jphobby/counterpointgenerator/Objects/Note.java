package com.jphobby.counterpointgenerator.Objects;

/**
 * Every note has a pitch and is in a specific octave.
 */

public class Note {

    private int pitch;
    private int octave;

    private String duration = "q";

    public Note(int pitch, int octave) {
        this.octave = limitOctaveRange(octave);
        this.pitch = setCorrectOctave(pitch);
    }

    public int getPitch() {
        return pitch;
    }

    public int getOctave() {
        return octave;
    }

    public String getDuration() { return duration; }

    public void setDuration(String duration) { this.duration = duration; }

    private int setCorrectOctave(int pitch) {
        if (pitch > 7) {
            pitch = pitch - 7;
            this.octave++;
        } else if (pitch < 1) {
            pitch = pitch + 7;
            this.octave--;
        }
        return pitch;
    }

    private int limitOctaveRange(int octave) {
        if (octave < 3) {
            return ++octave;
        } else if (octave > 7) {
            return --octave;
        }
        return octave;
    }
}

