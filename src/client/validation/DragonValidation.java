package client.validation;

import lib.utils.IOUtils;

public class DragonValidation {
    public boolean nameDragonValid(String nameDragon) {
        if (nameDragon.equals("")) {
            IOUtils.printErr("Имя не может быть пустой строкой!");
            return false;
        }
        return true;
    }

    public boolean CoordinateYValid(int y) {
        if (y <= -790) {
            IOUtils.printErr("Координата Y должна быть больше числа -790!");
            return false;
        }
        return true;
    }

    public boolean AgeValid(long age) {
        if (age <= 0) {
            IOUtils.printErr("Возраст должен быть больше нуля!");
            return false;
        }
        return true;
    }

    public boolean WeightValid(long weight) {
        if (weight <= 0) {
            IOUtils.printErr("Вес должен быть больше нуля!");
            return false;
        }
        return true;
    }
}
