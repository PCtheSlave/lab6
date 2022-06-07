package client.util;

import client.validation.DragonValidation;
import lib.dragon.*;
import lib.utils.IOUtils;

import java.time.LocalDateTime;
import java.util.Scanner;

public class DragonReader {
    DragonValidation dragonValidation = new DragonValidation();

    public Dragon createDragon() {
        return new Dragon(1, readNameDragon(), readCoordinates(), LocalDateTime.now(), readAge(), readWeight(), readSpeaking(), readCharacter(), readKiller());
    }

    public static void setScanner(Scanner scanner) {
        IOUtils.setScanner(scanner);
    }

    public String readNameDragon() {
        String nameDragon;
        while (true) {
            IOUtils.print("Введите имя дракона: ");
            nameDragon = IOUtils.input();
            if (dragonValidation.nameDragonValid(nameDragon))
                return nameDragon;
        }
    }

    public Coordinates readCoordinates() {
        return new Coordinates(readCoordinateX(), readCoordinateY());
    }

    public float readCoordinateX() {
        float x;
        while (true) {
            try {
                IOUtils.print("Введите координату дракона Х: ");
                String str = IOUtils.input();
                x = Float.parseFloat(str.trim());
                return x;
            } catch (NumberFormatException e) {
                IOUtils.printErr("Координата Х не может быть не числом!");
            }
        }
    }

    public int readCoordinateY() {
        int y;
        while (true) {
            try {
                IOUtils.print("Введите координату дракона Y: ");
                String str = IOUtils.input();
                y = Integer.parseInt(str.trim());
                if (dragonValidation.CoordinateYValid(y))
                    return y;
            } catch (NumberFormatException e) {
                IOUtils.printErr("Введите верный формат для координаты Y!");
            }
        }
    }

    public long readAge() {
        long age;
        while (true) {
            try {
                IOUtils.print("Введите возраст дракона: ");
                String str = IOUtils.input();
                age = Long.parseLong(str.trim());
                if (dragonValidation.AgeValid(age))
                    return age;
            } catch (NumberFormatException e) {
                IOUtils.printErr("Введите верный формат для возраста дракона!");
            }
        }
    }

    public long readWeight() {
        long weight;
        while (true) {
            try {
                IOUtils.print("Введите вес дракона: ");
                String str = IOUtils.input();
                weight = Long.parseLong(str.trim());
                if (dragonValidation.WeightValid(weight))
                    return weight;
            } catch (NumberFormatException e) {
                IOUtils.printErr("Вес не может быть не числом!");
            }
        }
    }

    public boolean readSpeaking() {
        while (true) {
            IOUtils.print("Говорит?(");
            IOUtils.printList(" TRUE FALSE ");
            IOUtils.print("): ");
            String str = IOUtils.input();
            if (str.trim().equalsIgnoreCase("true")) {
                return true;
            } else if (str.trim().equalsIgnoreCase("false")) {
                return false;
            }
            IOUtils.printErr("Введите true либо false!");
        }
    }

    public DragonCharacter readCharacter() {
        DragonCharacter character;
        while (true) {
            try {
                IOUtils.print("Введите характер дракона из списка( ");
                for (DragonCharacter val : DragonCharacter.values()) {
                    IOUtils.printList(val.name() + " ");
                }
                IOUtils.print("): ");
                String str = IOUtils.input();
                if (str.equals(""))
                    return null;
                character = DragonCharacter.valueOf(str.trim().toUpperCase());
                return character;
            } catch (IllegalArgumentException e) {
                IOUtils.printErr("Введите значение из списка!");
            }
        }
    }

    public Person readKiller() {
        return new Person(readNameKiller(), readPassportId(), readHairColor(), readNationality(), readLocation());
    }

    public String readNameKiller() {
        String nameKiller;
        while (true) {
            IOUtils.print("Введите имя killer: ");
            nameKiller = IOUtils.input();
            if (dragonValidation.nameDragonValid(nameKiller))
                return nameKiller;
        }
    }

    public String readPassportId() {
        String passport;
        while (true) {
            IOUtils.print("Введите паспорт killer: ");
            passport = IOUtils.input();
            if (dragonValidation.nameDragonValid(passport))
                return passport;
        }
    }

    public Color readHairColor() {
        Color hairColor;
        while (true) {
            try {
                IOUtils.print("Введите цвет волос killer из списка( ");
                for (Color val : Color.values()) {
                    IOUtils.printList(val.name() + " ");
                }
                IOUtils.print("): ");
                String str = IOUtils.input();
                if (str.equals(""))
                    return null;
                hairColor = Color.valueOf(str.trim().toUpperCase());
                return hairColor;
            } catch (IllegalArgumentException e) {
                IOUtils.printErr("Введите значение из списка!");
            }
        }
    }

    public Country readNationality() {
        Country nationality;
        while (true) {
            try {
                IOUtils.print("Введите страну killer из списка( ");
                for (Country val : Country.values()) {
                    IOUtils.printList(val.name() + " ");
                }
                IOUtils.print("): ");
                String str = IOUtils.input();
                if (str.equals(""))
                    return null;
                nationality = Country.valueOf(str.trim().toUpperCase());
                return nationality;
            } catch (IllegalArgumentException e) {
                IOUtils.printErr("Введите значение из списка!");
            }
        }
    }

    Location readLocation() {
        return new Location(readLocationX(), readLocationY(), readLocationName());
    }

    public double readLocationX() {
        double x;
        while (true) {
            try {
                IOUtils.print("Введите координату killer Х: ");
                String str = IOUtils.input();
                x = Double.parseDouble(str.trim());
                return x;
            } catch (NumberFormatException e) {
                IOUtils.printErr("Координата killer Х не может быть не числом!");
            }
        }
    }

    public int readLocationY() {
        int y;
        while (true) {
            try {
                IOUtils.print("Введите координату killer Y: ");
                String str = IOUtils.input();
                y = Integer.parseInt(str.trim());
                return y;
            } catch (NumberFormatException e) {
                IOUtils.printErr("Координата killer Y не может быть не числом!");
            }
        }
    }

    public String readLocationName() {
        String locationName;
        while (true) {
            IOUtils.print("Введите название места killer: ");
            locationName = IOUtils.input();
            if (dragonValidation.nameDragonValid(locationName))
                return locationName;
        }
    }

}
