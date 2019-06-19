package craftheim.el.mod.commands;

import com.mojang.authlib.GameProfile;
import craftheim.el.mod.data.Bank;
import craftheim.el.mod.data.GlobalData;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.server.command.CommandTreeBase;

public class CommandEnderLibre extends CommandTreeBase {

    public CommandEnderLibre() {
        addSubcommand(new CommandSimple("show", "/enderlibre show username", (server, sender, args) -> {
            if(args.length != 1) {
                sender.sendMessage(new TextComponentString("Cualca la cantidad de argumentos"));
                return;
            }

            GameProfile gp = server.getPlayerProfileCache().getGameProfileForUsername(args[0]);

            Bank bank = GlobalData.getInstance(server.getEntityWorld()).getBank();

            sender.sendMessage(new TextComponentString("Re bien anduvo. La platita de "+gp.getName()+": " + bank.get(gp.getId()) + " weeblons"));
        }));

        addSubcommand(new CommandSimple("give", "/enderlibre give username amount", (server, sender, args) -> {
            if(args.length != 2) {
                sender.sendMessage(new TextComponentString("Cualca la cantidad de argumentos"));
                return;
            }

            int amount;
            try {
                amount = parseInt(args[1]);
            } catch (Exception e) {
                sender.sendMessage(new TextComponentString("Cualca el valor del argumento"));
                return;
            }

            if(amount < 0) {
                sender.sendMessage(new TextComponentString("No le puedo dar plata negativa, derp"));
                return;
            }

            GameProfile gp = server.getPlayerProfileCache().getGameProfileForUsername(args[0]);

            Bank bank = GlobalData.getInstance(server.getEntityWorld()).getBank();
            bank.give(gp.getId(), amount);

            sender.sendMessage(new TextComponentString("Pronto"));
        }));

        addSubcommand(new CommandSimple("take", "/enderlibre take username amount", (server, sender, args) -> {
            if(args.length != 2) {
                sender.sendMessage(new TextComponentString("Cualca la cantidad de argumentos"));
                return;
            }

            int amount;
            try {
                amount = parseInt(args[1]);
            } catch (Exception e) {
                sender.sendMessage(new TextComponentString("Cualca el valor del argumento"));
                return;
            }

            if(amount < 0) {
                sender.sendMessage(new TextComponentString("No le puedo dar plata negativa, derp"));
                return;
            }

            GameProfile gp = server.getPlayerProfileCache().getGameProfileForUsername(args[0]);

            Bank bank = GlobalData.getInstance(server.getEntityWorld()).getBank();
            bank.take(gp.getId(), amount);

            sender.sendMessage(new TextComponentString("Pronto"));
        }));

        addSubcommand(new CommandSimple("set", "/enderlibre set username amount", (server, sender, args) -> {
            if(args.length != 2) {
                sender.sendMessage(new TextComponentString("Cualca la cantidad de argumentos"));
                return;
            }

            int amount;
            try {
                amount = parseInt(args[1]);
            } catch (Exception e) {
                sender.sendMessage(new TextComponentString("Cualca el valor del argumento"));
                return;
            }

            GameProfile gp = server.getPlayerProfileCache().getGameProfileForUsername(args[0]);

            Bank bank = GlobalData.getInstance(server.getEntityWorld()).getBank();
            bank.set(gp.getId(), amount);

            sender.sendMessage(new TextComponentString("Pronto"));
        }));

        addSubcommand(new CommandSimple("setAll", "/enderlibre setAll amount", (server, sender, args) -> {
            if(args.length != 1) {
                sender.sendMessage(new TextComponentString("Cualca la cantidad de argumentos"));
                return;
            }

            int amount;
            try {
                amount = parseInt(args[0]);
            } catch (Exception e) {
                sender.sendMessage(new TextComponentString("Cualca el valor del argumento"));
                return;
            }

            Bank bank = GlobalData.getInstance(server.getEntityWorld()).getBank();
            bank.setAll(amount);

            sender.sendMessage(new TextComponentString("Pronto"));
        }));

        addSubcommand(new CommandSimple("test", "/enderlibre test", (server, sender, args) -> {
            GlobalData.getInstance(server.getEntityWorld()).getMarket().clear();
            GlobalData.getInstance(server.getEntityWorld()).getMarket().addSale(((EntityPlayer)sender).getPersistentID(), new ItemStack(Item.getByNameOrId("minecraft:chest"), 3), 10);
            GlobalData.getInstance(server.getEntityWorld()).getMarket().addSale(((EntityPlayer)sender).getPersistentID(), new ItemStack(Item.getByNameOrId("minecraft:chest"), 15), 10);
            GlobalData.getInstance(server.getEntityWorld()).getMarket().addSale(((EntityPlayer)sender).getPersistentID(), new ItemStack(Item.getByNameOrId("minecraft:iron_pickaxe"), 25), 10);

            sender.sendMessage(new TextComponentString("Pronto"));
        }));
    }

    @Override
    public String getName() {
        return "enderlibre";
    }

    @Override
    public String getUsage(ICommandSender sender) { return "/enderlibre"; }
}
