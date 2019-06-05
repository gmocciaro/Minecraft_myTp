package me.myTp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class goTabComplete implements TabCompleter {

    public List<String> onTabComplete(CommandSender sender, Command cmd, String cmdlabel, String[] args){
        Player p = (Player)sender;

        // Taking list
        File file = new File("plugins//myTp");
        String[] fileList = file.list();
        List<String> list = new ArrayList<>();
        int numberOfItems = fileList.length;
        if (numberOfItems > 0) {
            for (int i = 0; i < numberOfItems; i++) {
                String name = fileList[i];

                String[] nameSplit = name.split("\\.");
                if(nameSplit.length > 0) {
                    list.add(nameSplit[0]);
                }
            }

            return list;
        }

        if(cmd.getName().equalsIgnoreCase("go")) {

        }

        return null;
    }

}
