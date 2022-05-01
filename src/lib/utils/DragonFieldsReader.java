package lib.utils;

import lib.collection.*;
import lib.collection.Color;

import java.util.Scanner;

public class DragonFieldsReader {
    Scanner in = new Scanner(System.in);

    public String readName() {
        String str;
        while (true) {
            System.out.print("Введите имя дракона: \n");
            str = in.nextLine();
            if (str.trim().equals("")) {
                System.err.print("Имя не может быть пустой строкой\n");
                continue;

            }
            if (str.trim().equals("null")) {
                System.err.print("Имя не может быть null\n");
                continue;
            }
            return str;
        }
    }

    public Coordinates readCoordinates() {
        return new Coordinates(readCoordinateX(), readCoordinateY());
    }

    public Float readCoordinateX() {
        float x;
        while (true) {
            try {
                System.out.print("Введите координату дракона Х: \n");
                String str = in.nextLine();
                x = Float.parseFloat(str.trim());
                return x;
            } catch (NumberFormatException e) {
                System.err.print("Координата Х не может быть не числом\n");
            }
        }
    }

    public int readCoordinateY() {
        int y;
        while (true) {
            try {
                System.out.print("Введите координату дракона Y: \n");
                String str = in.nextLine();
                y = Integer.parseInt(str.trim());
                if (y <= -790) {
                    throw new IllegalArgumentException();
                }
                return y;
            } catch (NumberFormatException e) {
                System.err.print("Введите верный формат для координаты Y(int)\n");
            } catch (IllegalArgumentException e) {
                System.err.print("Координата Y должна быть больше числа -790\n");
            }
        }
    }

    public Long readAge() {
        long age;
        while (true) {
            try {
                System.out.print("Введите возраст дракона: \n");
                String str = in.nextLine();
                age = Long.parseLong(str.trim());
                if (age <= 0) {
                    throw new IllegalArgumentException();
                }
                return age;
            } catch (NumberFormatException e) {
                System.err.print("Возраст не может быть не числом\n");
            } catch (IllegalArgumentException e) {
                System.err.print("Возраст должен быть больше нуля\n");
            }
        }
    }

    public Long readWeight() {
        long weight;
        while (true) {
            try {
                System.out.print("Введите вес дракона: \n");
                String str = in.nextLine();
                weight = Long.parseLong(str.trim());
                if (weight <= 0) {
                    throw new IllegalArgumentException();
                }
                return weight;
            } catch (NumberFormatException e) {
                System.err.print("Вес не может быть не числом\n");
            } catch (IllegalArgumentException e) {
                System.err.print("Вес должен быть больше нуля\n");
            }
        }
    }

    public Boolean readSpeaking() {
        while (true) {
            try {
                System.out.print("Говорит?: \n");
                String str = in.nextLine();
                if (str.trim().equalsIgnoreCase("true")) {
                    return true;
                } else if (str.trim().equalsIgnoreCase("false")) {
                    return false;
                }
            } catch (Exception ignored) {}
            System.err.println("Введите true либо false.");
        }
    }

    public DragonCharacter readCharacter() {
        DragonCharacter character;
        while (true) {
            try {
                System.out.print("Выберите характер дракона: \n");
                for (DragonCharacter val : DragonCharacter.values()) {
                    System.out.println(val.name());
                }
                String val = in.nextLine();
                if (val.equals("null"))
                    return null;
                character = DragonCharacter.valueOf(val.trim().toUpperCase());
                return character;
            } catch (IllegalArgumentException e) {
                System.err.print("Выберите значение из списка\n");
            }
        }
    }

    public Person readKiller() {
        return new Person(readNameKiller(), readPassportId(), readHairColor(), readNationality(), readLocation());
    }

    public String readNameKiller() {
        String killer;
        while (true) {
            System.out.print("Введите имя killer: \n");
            killer = in.nextLine();
            if (killer.trim().equals("")) {
                System.err.print("Имя не может быть пустой строкой\n");
                continue;

            }
            if (killer.trim().equals("null")) {
                System.err.print("Имя не может быть null\n");
                continue;
            }
            return killer;
        }
    }

    public String readPassportId() {
        String passportId;
        while (true) {
            System.out.print("Введите паспорт killer: \n");
            passportId = in.nextLine();
            if (passportId.trim().equals("")) {
                System.err.print("Значение паспорта не может быть пустой строкой\n");
                continue;

            }
            if (passportId.trim().equals("null")) {
                System.err.print("Значение паспорта не может быть null\n");
                continue;
            }
            return passportId;
        }
    }

    public Color readHairColor() {
        Color hairColor;
        while (true) {
            try {
                System.out.print("Выберите цвет волос killer: \n");
                for (Color val : Color.values()) {
                    System.out.println(val.name());
                }
                String val = in.nextLine();
                if (val.equals("null"))
                    return null;
                hairColor = Color.valueOf(val.trim().toUpperCase());
                return hairColor;
            } catch (IllegalArgumentException e) {
                System.err.print("Выберите значение из списка\n");
            }
        }
    }

    public Country readNationality() {
        Country nationality;
        while (true) {
            try {
                System.out.print("Выберите страну killer: \n");
                for (Country val : Country.values()) {
                    System.out.println(val.name());
                }
                String val = in.nextLine();
                if (val.equals("null"))
                    return null;
                nationality = Country.valueOf(val.trim().toUpperCase());
                return nationality;
            } catch (IllegalArgumentException e) {
                System.err.print("Выберите значение из списка\n");
            }
        }
    }

    Location readLocation() {
        return new Location(readLocationX(), readLocationY(), readLocationName());
    }

    public Double readLocationX() {
        double x;
        while (true) {
            try {
                System.out.print("Введите координату killer Х: \n");
                String str = in.nextLine();
                x = Double.parseDouble(str.trim());
                return x;
            } catch (NumberFormatException e) {
                System.err.print("Координата killer Х не может быть не числом\n");
            }
        }
    }

    public Integer readLocationY() {
        int y;
        while (true) {
            try {
                System.out.print("Введите координату killer Y: \n");
                String str = in.nextLine();
                y = Integer.parseInt(str.trim());
                return y;
            } catch (NumberFormatException e) {
                System.err.print("Координата killer Y не может быть не числом\n");
            }
        }
    }

    public String readLocationName() {
        String locationName;
        while (true) {
            System.out.print("Введите имя места killer: \n");
            locationName = in.nextLine();
            if (locationName.trim().equals("")) {
                System.err.print("Имя места killer не может быть пустой строкой\n");
                continue;
            }
            if (locationName.trim().equals("null")) {
                System.err.print("Имя места killer не может быть null\n");
                continue;
            }
            return locationName;
        }
    }
}
