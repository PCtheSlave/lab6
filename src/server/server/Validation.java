package server.server;

import lib.dragon.Color;
import lib.dragon.Country;
import lib.dragon.DragonCharacter;

import java.time.LocalDateTime;

public class Validation {
    public boolean validation(String[] line, int lineNumber) {
        try {
            if (line[0].trim().equals("") || Integer.parseInt(line[0]) <= 0) {
                System.err.println("Неверный формат id у " + lineNumber + " строки");
                return false;
            }
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат id у " + lineNumber + " строки");
            return false;
        }

        if (line[1].trim().equals("")) {
            System.err.println("Неверный формат name у " + lineNumber + " строки");
            return false;
        }

        try {
            Float.parseFloat(line[2]);
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат X у " + lineNumber + " строки");
            return false;
        }

        try {
            if(Integer.parseInt(line[3]) <= -790) {
                System.err.println("Неверный формат Y у " + lineNumber + " строки");
                return false;
            }
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат Y у " + lineNumber + " строки");
            return false;
        }

        try {
            LocalDateTime.parse(line[4]);
        } catch (Exception e) {
            System.err.println("Неверный формат creationDate у " + lineNumber + " строки");
            return false;
        }

        try {
            if(Long.parseLong(line[5]) <= 0) {
                System.err.println("Неверный формат age у " + lineNumber + " строки");
                return false;
            }
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат age у " + lineNumber + " строки");
            return false;
        }

        try {
            if(Long.parseLong(line[6]) <= 0) {
                System.err.println("Неверный формат weight у " + lineNumber + " строки");
                return false;
            }
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат weight у " + lineNumber + " строки");
            return false;
        }

        try {
            if (line[7].trim().equalsIgnoreCase("true") || line[7].trim().equalsIgnoreCase("false")) {
            } else {
                System.err.println("Неверный формат speaking у " + lineNumber + " строки");
                return false;
            }
        } catch (Exception ignored) {}

        try {
            DragonCharacter.valueOf(line[8]);
        } catch (IllegalArgumentException e) {
            System.err.println("Неверный формат character у " + lineNumber + " строки");
            return false;
        }

        if (line[9].trim().equals("")) {
            System.err.println("Неверный формат nameKiller у " + lineNumber + " строки");
            return false;
        }

        if (line[10].trim().equals("")) {
            System.err.println("Неверный формат passportId у " + lineNumber + " строки");
            return false;
        }

        try {
            Color.valueOf(line[11]);
        } catch (IllegalArgumentException e) {
            System.err.println("Неверный формат hairColor у " + lineNumber + " строки");
            return false;
        }

        try {
            Country.valueOf(line[12]);
        } catch (IllegalArgumentException e) {
            System.err.println("Неверный формат nationality у " + lineNumber + " строки");
            return false;
        }

        try {
            Double.parseDouble(line[13]);
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат xKiller у " + lineNumber + " строки");
            return false;
        }

        try {
            Integer.parseInt(line[14]);
        } catch (NumberFormatException e) {
            System.err.println("Неверный формат yKiller у " + lineNumber + " строки");
            return false;
        }

        if (line[15].trim().equals("")) {
            System.err.println("Неверный формат nameLocation у " + lineNumber + " строки");
            return false;
        }
        return true;
    }
}
