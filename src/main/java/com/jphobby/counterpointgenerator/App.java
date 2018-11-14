package com.jphobby.counterpointgenerator;

import com.jphobby.counterpointgenerator.Generators.MusicGenerator;
import com.jphobby.counterpointgenerator.Objects.Note;
import org.jfugue.Player;

import java.util.stream.IntStream;

public class App {

    private static final MusicGenerator musicGenerator = new MusicGenerator();
    private static final Player player = new Player();
    private static final String notes = "cdefgab";

    // Main settings
    private static final int numberOfMelodies = 4;
    private static final int lengthInNotes = 17;
    private static final int tempo = 80;

    public static void main( String[] args ) {

        Note[][] sheetMusic = new Note[numberOfMelodies][lengthInNotes];

        sheetMusic = musicGenerator.generateMusic(sheetMusic);

        player.play(convertToPlayerFormat(sheetMusic));
    }

    private static String convertToPlayerFormat(Note[][] sheetMusic) {
        StringBuilder playerString = new StringBuilder();

        playerString.append("T")
                    .append(tempo)
                    .append(" ");

        IntStream.range(0, sheetMusic.length)
                .forEach(melodyNumber -> {
                    // For each melody, add identifier
                    playerString.append("V")
                                .append(melodyNumber)
                                .append(" ");

                    StringBuilder currentVoice = new StringBuilder();
                    // Add pitch and octave for all notes of this melody
                    IntStream.range(1, sheetMusic[melodyNumber].length)
                            .forEach( currentNote -> {
                                Note currentNoteObject = sheetMusic[melodyNumber][currentNote];
                                char currentNoteLetter = notes.charAt(currentNoteObject.getPitch() - 1);
                                currentVoice.append(currentNoteLetter)
                                            .append(currentNoteObject.getOctave())
                                            .append(currentNoteObject.getDuration())
                                            .append(" ");
                            });
                    System.out.println(currentVoice);
                    playerString.append(currentVoice);
                });

        return playerString.toString();
    }
}