package com.imshy.Discord;

import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ChannelManager
{

    private final String FOLDERNAME = "Extras";
    // returns the path of the jar file
    // data final name
    private final String DATANAME = "data.json";

    private final String currentPathUrl = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();

    // combining the current execution path with the name of the folder
    private final String extraPathUrl = currentPathUrl + FOLDERNAME + '/';
    // Data url
    private final String DataUrl = extraPathUrl + DATANAME;

    public ChannelManager()
    {
        System.out.println("initialized");
        try
        {
            setupFolder(); // 1


            setupData();// 2
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private void setupFolder() throws IOException
    {
        // creating path object
        Path path = Path.of(extraPathUrl);
        // creating the directory
        Files.createDirectories(path);
    }

    private void setupData() throws IOException
    {
        File file = new File(DataUrl);
        if (!file.exists())
        {
            file.createNewFile();
            writeToData("{}");
        }
    }


    private String readData() throws FileNotFoundException
    {
        File file = new File(DataUrl);
        Scanner sc = new Scanner(file);
        StringBuilder sb = new StringBuilder();

        while (sc.hasNextLine())
            sb.append(sc.nextLine());

        return sb.toString();
    }

    public JsonObject getChannelData()
    {
        try
        {
            return new JsonParser().parse(readData()).getAsJsonObject();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    private void writeToData(String data) throws IOException
    {
        FileWriter fw = new FileWriter(DataUrl);
        fw.write(data);
        fw.close();

    }

    public void writeToFile(String data)
    {
        try
        {
            writeToData(data);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public JsonObject createDefaultObject(String channelID, int minutes)
    {
        JsonArray arr = new JsonArray();
        JsonElement a = new JsonPrimitive(minutes);
        JsonElement b = new JsonPrimitive(channelID);

        JsonObject obj = new JsonObject();
        obj.add("StockList", arr);
        obj.add("Time", a);
        obj.add("ChannelId", b);

        return obj;
    }

    public void addToList(JsonObject channelObject)
    {
        System.out.println(channelObject.toString());
        JsonObject data = getChannelData();
        data.add(channelObject.get("ChannelId").getAsString(), channelObject);

        writeToFile(data.toString());

    }

    public void removeFromList()
    {

    }

}
