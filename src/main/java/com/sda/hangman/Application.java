package com.sda.hangman;

import com.sda.hangman.domain.HangmanGameService;
import com.sda.hangman.domain.model.GameStatus;
import com.sda.hangman.domain.port.PhraseRepository;
import com.sda.hangman.infrastructure.memory.InMemoryPhraseRepository;

import java.util.Scanner;

public class Application {

    private static Scanner scanner;
    private static PhraseRepository phraseRepository;
    private static HangmanGameService hangmanGameService;

    public static void main(String[] args) {
        hangmanGameService = new HangmanGameService();
        phraseRepository = new InMemoryPhraseRepository();
        scanner = new Scanner(System.in);
        boolean menuFlag = true;
        do {
            System.out.println("1. Start");
            System.out.println("2. Wyniki");
            System.out.println("Inne. Koniec");
            int decision = scanner.nextInt();

            switch (decision) {
                case 1:
                    System.out.println("Tutaj bedzie logika do start");
                    break;
                case 2:
                    System.out.println("Tutaj bedzie logika do wynikow");
                    break;
                default:
                    menuFlag = false;
            }
        } while (menuFlag);
    }

    public static void startGame() {
        System.out.println("Podaj swoje imie");
        String name = scanner.nextLine();
        System.out.println("Kliknij enter zeby zaczac gre");
        String phrase = phraseRepository.getPhrase();
        GameStatus gameStatus = hangmanGameService.createGameStatus(name, phrase);

        while (true) {
            System.out.println("tutaj bedzie fraza, (pozostało 5 prób)");
            System.out.println("Podaj kolejna litere: ");
            char letter = scanner.nextLine().charAt(0);
            hangmanGameService.processNextLetter(letter, gameStatus);
        }
    }
}
