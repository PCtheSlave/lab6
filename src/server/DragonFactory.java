package server;

import lib.dragon.Dragon;
import lib.utils.DragonFieldsReader;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DragonFactory implements Serializable {
    private int id = 1;
    DragonFieldsReader dragonFieldsReader = new DragonFieldsReader();

    public Dragon createDragon() {
        return new Dragon(getId(),
                dragonFieldsReader.readName(),
                dragonFieldsReader.readCoordinates(),
                LocalDateTime.now(),
                dragonFieldsReader.readAge(),
                dragonFieldsReader.readWeight(),
                dragonFieldsReader.readSpeaking(),
                dragonFieldsReader.readCharacter(),
                dragonFieldsReader.readKiller());
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id++;
    }
}