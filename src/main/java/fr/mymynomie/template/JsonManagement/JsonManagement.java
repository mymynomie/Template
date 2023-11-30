package fr.mymynomie.template.JsonManagement;


import net.minecraft.entity.player.EntityPlayer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonManagement {
    public static String ReturnPath() {
        return "world/Template/Object/";
    }

    public static JSONObject loadJson(EntityPlayer player) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(ReturnPath() + player.getUniqueID() + ".json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        assert jsonObject != null;
        return jsonObject;
    }

    public static void overwriteJson(JSONObject jsonObject, EntityPlayer player) {
        try (FileWriter file = new FileWriter(ReturnPath() + player.getUniqueID() + ".json")) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            createjson(player);
            e.printStackTrace();
        }
    }

    public static void createjson(EntityPlayer player) {
        JSONObject obj = new JSONObject();
        obj.put("PosX", 0);
        obj.put("PosY", 0);
        obj.put("PosZ", 0);


        try (FileWriter file = new FileWriter(ReturnPath() + player.getUniqueID() + ".json")) {
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            System.err.println("TEMPLATE : Emplacement du fichier invalide !");
        }
    }

    //SET POSX
    public static void setPosX(EntityPlayer player ,int state) {
        JSONObject jsonObject = loadJson(player);
        jsonObject.replace("PosX", state);
        overwriteJson(jsonObject, player);
    }

    //GET POSX
    public static int getPosX(EntityPlayer player) {
        JSONObject jsonObject = loadJson(player);
        return Integer.parseInt(jsonObject.get("PosX").toString());
    }

    //SET POSY
    public static void setPosY(EntityPlayer player, int state) {
        JSONObject jsonObject = loadJson(player);
        jsonObject.replace("PosY", state);
        overwriteJson(jsonObject, player);
    }

    //GET POSY
    public static int getPosY(EntityPlayer player) {
        JSONObject jsonObject = loadJson(player);
        return Integer.parseInt(jsonObject.get("PosY").toString());
    }

    //SET POSZ
    public static void setPosZ(EntityPlayer player, int state) {
        JSONObject jsonObject = loadJson(player);
        jsonObject.replace("PosZ", state);
        overwriteJson(jsonObject, player);
    }

    //GET POSZ
    public static int getPosZ(EntityPlayer player) {
        JSONObject jsonObject = loadJson(player);
        return Integer.parseInt(jsonObject.get("PosZ").toString());
    }

}
