package com.imshy.Discord;

import com.google.gson.*;
import com.imshy.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ChannelManager
{

    private final String FOLDERNAME = "Extra";

    public ChannelManager()
    {

    }

    private void setupFolder() throws IOException
    {
        // returns the path of the jar file
        String pathUrl = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        // adding the newly wanted folder name
        pathUrl += FOLDERNAME + '/';
        // creating path object
        Path path = Path.of(pathUrl);
        // creating the directory
        Files.createDirectories(path);
    }

    public JsonObject getData()
    {
        InputStream stream = Main.class.getResourceAsStream("Storage/channelData.json");
        StringBuilder sb = new StringBuilder();
        String input;
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(stream)));

        try
        {

            while ((input = br.readLine()) != null)
            {
                sb.append(input);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return new JsonParser().parse(sb.toString()).getAsJsonObject();

    }



    private void writeData(String data) throws IOException
    {
        FileWriter fw = new FileWriter("Storage/channelData.json");

    }
    public void writeToFile(String data)
    {
        try
        {
            writeData(data);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public  JsonObject createDefaultObject(String channelID, int minutes)
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

    public  void addToList(JsonObject channelObject)
    {
        JsonObject data = getData();
        data.add(channelObject.get("ChannelId").getAsString(), channelObject);


    }

    public  void removeFromList()
    {

    }

}
