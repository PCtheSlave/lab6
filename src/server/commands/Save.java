package server.commands;

import lib.Pack;
import lib.dragon.Dragon;
import server.CollectionManager;
import server.interfaces.Command;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Save implements Command {

    private CollectionManager collectionManager;

    public Save(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        File file = new File(System.getenv("FILE_PATH"));
        try (FileOutputStream outputStream = new FileOutputStream(file)){
            for (Dragon vals : collectionManager.getDragons()) {
                outputStream.write(vals.toString().getBytes(StandardCharsets.UTF_8));
                outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        pack.pack("Коллекция сохранена в файл\n");
        return pack;
    }

    @Override
    public String getDescription() {
        return "Сохранить коллекцию в файл";
    }
}
