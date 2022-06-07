package client.command;

import client.client.Request;
import client.client.Response;
import lib.Pack;
import client.interfaces.CommandWithArgument;
import lib.dragon.Color;
import lib.dragon.Country;
import lib.dragon.Dragon;
import lib.dragon.DragonCharacter;
import lib.utils.IOUtils;

import java.io.IOException;
import java.net.Socket;

public class Update implements CommandWithArgument {
    private String[] arg;
    private Request request = new Request();
    private Response response = new Response();
    private Pack pack1 = new Pack();
    private Socket socket = new Socket();

    Update(Socket socket) {
        this.socket = socket;
    }

    @Override
    public Pack execute(String nameCommand, Pack pack) {
        try {
            pack1.pack(nameCommand, arg);
            request.request(pack1, socket);
            pack1 = response.responseUpdate(socket);
            if (pack1.getDragon() != null) {
                IOUtils.println("Введите stop, когда захотите прервать изменение элемента коллекции!");
                IOUtils.println("Введите значения полей для элемента коллекции: ");
                String[] commandWords = new String[]{""};
                while (!commandWords[0].equals("stop")) {
                    try {
                        String str = IOUtils.input();
                        if (str.equals(""))
                            continue;
                        commandWords = new String[0];
                        commandWords = str.trim().split(" ");
                        if (commandWords.length == 2) {
                            updateById(commandWords[0], pack1.getDragon(), commandWords[1]);
                        } else if (commandWords[0].equals("stop")) {
                            IOUtils.println("Дракон был изменён!");
                        } else {
                            IOUtils.printErr("Не указан аргумент или было указано более двух аргументов!");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("Не указано поле или значение!");
                    }
                }
                pack1.pack("get_dragon", pack1.getDragon());
                request.request(pack1, socket);
                return pack;
            } else {
                pack.pack(pack1.getCommandName());
                return pack;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateById(String command, Dragon dragon, String arg) {
        if (command.equals("name")) dragon.setName(arg);
        else if (command.equals("coordinate_x")) dragon.getCoordinates().setX(Float.parseFloat(arg));
        else if (command.equals("coordinate_y")) dragon.getCoordinates().setY(Integer.parseInt(arg));
        else if (command.equals("age")) dragon.setAge(Long.valueOf(arg));
        else if (command.equals("weight")) dragon.setWeight(Long.parseLong(arg));
        else if (command.equals("speaking")) dragon.setSpeaking(Boolean.parseBoolean(arg));
        else if (command.equals("character")) dragon.setCharacter(DragonCharacter.valueOf(arg));
        else if (command.equals("killer")) dragon.getKiller().setName(arg);
        else if (command.equals("passport")) dragon.getKiller().setPassportID(arg);
        else if (command.equals("color_heir")) dragon.getKiller().setHairColor(Color.valueOf(arg));
        else if (command.equals("nationality")) dragon.getKiller().setNationality(Country.valueOf(arg));
        else if (command.equals("location_X")) dragon.getKiller().getLocation().setX(Double.valueOf(arg));
        else if (command.equals("location_Y")) dragon.getKiller().getLocation().setY(Integer.parseInt(arg));
        else if (command.equals("location")) dragon.getKiller().getLocation().setName(arg);
        else IOUtils.println("Нет такого поля!");
    }

    @Override
    public void arg(String[] arg) {
        this.arg = arg;
    }
}
