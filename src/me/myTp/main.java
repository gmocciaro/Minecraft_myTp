package me.myTp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class main extends JavaPlugin {

    private String pr = "§7[§4Server§7]§9 ";
    public void onEnable() {

    }

    public void onDisable() {

    }

    public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {

        Player p = (Player)sender;
        if(cmd.getName().equalsIgnoreCase("go")) {
            if(args.length > 0) {
                String locationName = args[0];
                File file = new File("plugins//myTp//" + locationName + ".yml");
                if(!file.exists()) {
                    sender.sendMessage(this.pr + "Luogo non trovato :(, usa /goList per la lista dei luoghi disponibili");
                    return true;
                }

                YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                Location loc = p.getLocation();
                double yaw = cfg.getDouble("Spawn.YAW");
                double pitch = cfg.getDouble("Spawn.PITCH");
                loc.setX(cfg.getDouble("Spawn.X"));
                loc.setY(cfg.getDouble("Spawn.Y"));
                loc.setZ(cfg.getDouble("Spawn.Z"));
                loc.setYaw((float)yaw);
                loc.setPitch((float)pitch);
                World world = Bukkit.getWorld(cfg.getString("Spawn.WORLD"));
                loc.setWorld(world);
                p.teleport(loc);
                p.sendMessage(this.pr + "Benvenuto a " + locationName + "!");
                return true;
            }
        }

        if(cmd.getName().equalsIgnoreCase("goList")) {
            File file = new File("plugins//myTp");
            String[] list = file.list();
            String locations = "";
            if (list != null) {
                int numberOfItems = list.length;
                if (numberOfItems > 0) {
                    for (int i = 0; i < numberOfItems; i++) {
                        String name = list[i];
                        if (i > 0) {
                            locations += " §7| §9";
                        }

                        String[] nameSplit = name.split("\\.");
                        locations += nameSplit[0];
                    }

                    p.sendMessage(this.pr + locations);
                    return true;
                } else {
                    p.sendMessage(this.pr + "Non esistono luoghi ancora...");
                    return true;
                }
            } else {
                p.sendMessage(this.pr + "Non esistono luoghi ancora...");
                return true;
            }
        }

        if(cmd.getName().equalsIgnoreCase("delGo")) {
            if(p.getPlayer().isOp() && args.length > 0) {
                // location name
                String locationName = args[0];

                // Working with file
                File file = new File("plugins//myTp//" + locationName + ".yml");
                if(!file.exists()) {
                    p.sendMessage(this.pr + "Questo luogo non esiste!");
                    return true;
                }

                file.delete();
                p.sendMessage(this.pr + "Luogo " + locationName + " eliminato con successo");

                return true;
            }
        }

        if(cmd.getName().equalsIgnoreCase("setGo")) {
            if(p.getPlayer().isOp() && args.length > 0) {
                // location name
                String locationName = args[0];

                // Working with file
                File file = new File("plugins//myTp//" + locationName + ".yml");
                if(file.exists()) {
                    // Deleting old file :(
                    file.delete();
                }

                // Creating file
                try {
                    file.createNewFile();
                } catch (IOException var20) {
                    var20.printStackTrace();
                }

                Location loc = p.getLocation();
                double x = loc.getX();
                double y = loc.getY();
                double z = loc.getZ();
                float yaw = loc.getYaw();
                float pitch = loc.getPitch();
                String world = loc.getWorld().getName();
                YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

                cfg.set("Spawn.X", x);
                cfg.set("Spawn.Y", y);
                cfg.set("Spawn.Z", z);
                cfg.set("Spawn.YAW", yaw);
                cfg.set("Spawn.PiTCH", pitch);
                cfg.set("Spawn.WORLD", world);

                try {
                    cfg.save(file);
                } catch (IOException var19) {
                    var19.printStackTrace();
                }

                p.sendMessage(this.pr + "Luogo salvato come " + locationName);

                return true;
            }
        }

        if(cmd.getName().equalsIgnoreCase("goto")) {
            if(args.length > 0) {
                String playerName = args[0];

                for(Player on:Bukkit.getServer().getOnlinePlayers()){
                    if(playerName.equalsIgnoreCase(on.getName())){
                        Location onLocation = on.getLocation();
                        Location pLocation = p.getLocation();

                        pLocation.setX(onLocation.getX());
                        pLocation.setY(onLocation.getY());
                        pLocation.setZ(onLocation.getZ());
                        pLocation.setYaw(onLocation.getYaw());
                        pLocation.setPitch(onLocation.getPitch());
                        pLocation.setWorld(onLocation.getWorld());
                        p.teleport(pLocation);
                        return true;
                    }
                }

                p.sendMessage(this.pr + "Utente " + playerName + " non trovato");
                return true;
            }
        }

        return false;
    }
}
