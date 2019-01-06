package com.jphobby.counterpointgenerator;

import com.jphobby.counterpointgenerator.Generators.MusicGenerator;
import com.jphobby.counterpointgenerator.Objects.Note;
import org.jfugue.Player;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {

    private static final MusicGenerator musicGenerator = new MusicGenerator();
    private static final Player player = new Player();

    // Main settings
    static final int numberOfMelodies = 4;
    static final int lengthInNotes = 17;
    static final int tempo = 80;
    
    static Note[][] sheetMusic = new Note[numberOfMelodies][lengthInNotes];

    public static void main( String[] args ) {
    	
        sheetMusic = musicGenerator.fillSheetMusic(sheetMusic);

        String playerString = PlayerUtils.convertToPlayerFormat(sheetMusic);
        PlayerUtils.printPlayerString(playerString);
        
        player.play(playerString);
    }
}