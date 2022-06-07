package lib.utils;

import lib.Pack;
import lib.dragon.Color;
import lib.dragon.Country;
import lib.dragon.Dragon;
import lib.dragon.DragonCharacter;
import server.CollectionManager;
import server.server.Response;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class CollectionInfo {
    Response response = new Response();
    ByteBuffer buffer = ByteBuffer.allocate(32768);
    public String getInfoAboutCollection(CollectionManager collectionManager) {
        String str = "";
        str += "Дата создания коллекции " + collectionManager.getCreationCollectionDate() + "\n";
        str += "Количество элементов в коллеции " + collectionManager.getDragons().size();
        return str;
    }

    public String show(CollectionManager collectionManager, SocketChannel client){

        String str = "";
        Pack pack = new Pack();
        if (collectionManager.getDragons().size() == 0) {
            str += "Коллекция пуста!\n";
        } else {
            for (Dragon vals : collectionManager.getDragons()) {
                str = "";
                str += "Dragon(id=" + vals.getId() + "): ";
                str += "dragon name=" + vals.getName() + ", ";
                str += "dragon coordinate X=" + vals.getCoordinates().getX() + ", ";
                str += "dragon coordinate Y=" + vals.getCoordinates().getY() + ", ";
                str += "creation date=" + vals.getCreationDate() + ", ";
                str += "dragon age=" + vals.getAge() + ", ";
                str += "dragon weight=" + vals.getWeight() + ", ";
                str += "speakable=" + vals.getSpeaking() + ", ";
                str += "character=" + vals.getCharacter() + ", ";
                str += "killer name=" + vals.getKiller().getName() + ", ";
                str += "killer passport=" + vals.getKiller().getPassportID() + ", ";
                str += "killer hair color=" + vals.getKiller().getHairColor() + ", ";
                str += "killer country=" + vals.getKiller().getNationality() + ", ";
                str += "killer coordinate X=" + vals.getKiller().getLocation().getX() + ", ";
                str += "killer coordinate Y=" + vals.getKiller().getLocation().getY() + ", ";
                str += "killer place name=" + vals.getKiller().getLocation().getName() + "\n";
                pack.pack(str);
                try {
                    Response response = new Response();
                    buffer.clear();
                    buffer.put(response.serialize(pack));
                    buffer.flip();
                    client.write(buffer);
                    buffer.clear();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            str = "";
        }
        return str;
    }

    public String getFieldsName() {
        return "Список всех полей:\nname(String)\ncoordinate_x(Integer)\ncoordinate_y(Double)\nage(Integer)\nweight\nspeaking\ncharacter: " +
                Arrays.toString(DragonCharacter.values()) + "killer\npassport\ncolor_heir: " + Arrays.toString(Color.values()) +
                "nationality: " + Arrays.toString(Country.values()) + "location_X\nlocation_Y\nlocation";
    }
}
