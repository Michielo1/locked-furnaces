package com.michielo.bukkit.command;

import com.michielo.LockManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command c, String label, String[] args) {

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                sendHelp(commandSender);
                return true;
            }

            commandSender.sendMessage(ChatColor.RED + "Unknown command!");
            sendHelp(commandSender);
            return true;
        }

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("unlock")) {
                if (commandSender instanceof Player p) {
                    LockManager.getInstance().unlock(p, args[1]);
                } else {
                    commandSender.sendMessage(ChatColor.RED + "This command is only for players!");
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("lock")) {
                if (commandSender instanceof Player p) {
                    LockManager.getInstance().lock(p, args[1]);
                } else {
                    commandSender.sendMessage(ChatColor.RED + "This command is only for players!");
                }
                return true;
            }

            commandSender.sendMessage(ChatColor.RED + "Unknown command!");
            sendHelp(commandSender);
            return true;
        }

        sendHelp(commandSender);
        return true;
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "={ LockedFurnaces }=");
        sender.sendMessage(ChatColor.AQUA + "- /lockedfurnances help | This message");
        sender.sendMessage(ChatColor.AQUA + "- /lockedfurnances unlock <ore> | Allows smelting of the given item");
        sender.sendMessage(ChatColor.AQUA + "- /lockedfurnances lock <ore> | Prevents smelting of the given item");
    }

}
