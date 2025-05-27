package com.ictdemy;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalSeconds = 0;

        System.out.println("🕒 Kalkulačka času");
        System.out.println("Zadaj počiatočný čas vo formáte hh:mm:ss:");
        System.out.print("> ");

        String input = scanner.nextLine().trim();
        try {
            totalSeconds = parseToSeconds(input);
        } catch (Exception e) {
            System.out.println("❌ Neplatný formát času. Očakáva sa hh:mm:ss.");
            return;
        }

        System.out.println("Zadávaj ďalšie časy vo formáte +hh:mm:ss alebo -hh:mm:ss.");
        System.out.println("Stlač ENTER bez zadania na ukončenie a výpočet výsledku.\n");

        while (true) {
            System.out.print("> ");
            input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                break;
            }

            try {
                char operator = input.charAt(0);
                if (operator != '+' && operator != '-') {
                    throw new IllegalArgumentException("Zadanie musí začínať + alebo -");
                }

                String timePart = input.substring(1);
                int seconds = parseToSeconds(timePart);

                totalSeconds += operator == '+' ? seconds : -seconds;

            } catch (Exception e) {
                System.out.println("⚠️ Chybný vstup. Zadaj čas ako +hh:mm:ss alebo -hh:mm:ss.");
            }
        }

        // Výpočet výsledku
        int absSeconds = Math.abs(totalSeconds);
        int hours = absSeconds / 3600;
        int minutes = (absSeconds % 3600) / 60;
        int seconds = absSeconds % 60;

        String sign = totalSeconds < 0 ? "-" : "";

        System.out.printf("\n✅ Výsledný čas: %s%02d:%02d:%02d\n", sign, hours, minutes, seconds);
    }

    /**
     * Parsuje časový reťazec vo formáte hh:mm:ss na sekundy.
     */
    private static int parseToSeconds(String time) {
        String[] parts = time.split(":");
        if (parts.length != 3) throw new IllegalArgumentException("Zlý formát času");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        if (hours < 0 || minutes < 0 || seconds < 0 || minutes >= 60 || seconds >= 60) {
            throw new IllegalArgumentException("Neplatné hodnoty času");
        }

        return hours * 3600 + minutes * 60 + seconds;
    }
}