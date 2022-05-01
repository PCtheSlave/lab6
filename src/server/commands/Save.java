package server.commands;

import lib.collection.Dragon;
import server.CollectionManager;
import server.interfaces.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class Save implements Command {

    private CollectionManager collectionManager;

    public Save(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        File file = new File(System.getenv("FILE_PATH"));
        try (FileOutputStream outputStream = new FileOutputStream(file)){
            for (Dragon vals : collectionManager.getDragons()) {
                outputStream.write(vals.toString().getBytes(StandardCharsets.UTF_8));
                outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "Сохранить коллекцию в файл";
    }
}
