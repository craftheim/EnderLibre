package craftheim.el.mod.server.commands;

import com.mojang.authlib.GameProfile;
import craftheim.el.mod.server.data.Bank;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.server.command.CommandTreeBase;

public class CommandEnderLibre extends CommandTreeBase {

    public CommandEnderLibre() {
        addSubcommand(new CommandSimple("show", "/enderlibre show username", new CommandSimple.ICommandSimpleExecution() {
            @Override
            public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
                if(args.length != 1) {
                    sender.sendMessage(new TextComponentString("Cualca la cantidad de argumentos"));
                    return;
                }

                GameProfile gp = server.getPlayerProfileCache().getGameProfileForUsername(args[0]);
                if(gp == null) {
                    sender.sendMessage(new TextComponentString("Algo salio mal, no me hackees"));
                    return;
                }

                Bank bank = Bank.getInstance(server.getEntityWorld());
                sender.sendMessage(new TextComponentString("Re bien anduvo. Tu platita: " + bank.get(gp.getId()) + " weeblons"));
            }
        }));

        addSubcommand(new CommandSimple("give", "/enderlibre give username amount", new CommandSimple.ICommandSimpleExecution() {
            @Override
            public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
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
                if(gp == null) {
                    sender.sendMessage(new TextComponentString("Algo salio mal, no me hackees"));
                    return;
                }

                Bank bank = Bank.getInstance(server.getEntityWorld());
                bank.give(gp.getId(), amount);

                sender.sendMessage(new TextComponentString("Pronto"));
            }
        }));

        addSubcommand(new CommandSimple("take", "/enderlibre take username amount", new CommandSimple.ICommandSimpleExecution() {
            @Override
            public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
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
                if(gp == null) {
                    sender.sendMessage(new TextComponentString("Algo salio mal, no me hackees"));
                    return;
                }

                Bank bank = Bank.getInstance(server.getEntityWorld());
                bank.take(gp.getId(), amount);

                sender.sendMessage(new TextComponentString("Pronto"));
            }
        }));

        addSubcommand(new CommandSimple("set", "/enderlibre set username amount", new CommandSimple.ICommandSimpleExecution() {
            @Override
            public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
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
                if(gp == null) {
                    sender.sendMessage(new TextComponentString("Algo salio mal, no me hackees"));
                    return;
                }

                Bank bank = Bank.getInstance(server.getEntityWorld());
                bank.set(gp.getId(), amount);

                sender.sendMessage(new TextComponentString("Pronto"));
            }
        }));

        addSubcommand(new CommandSimple("setAll", "/enderlibre setAll amount", new CommandSimple.ICommandSimpleExecution() {
            @Override
            public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
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

                Bank bank = Bank.getInstance(server.getEntityWorld());
                bank.setAll(amount);

                sender.sendMessage(new TextComponentString("Pronto"));
            }
        }));
    }

    @Override
    public String getName() {
        return "enderlibre";
    }

    @Override
    public String getUsage(ICommandSender sender) { return "/enderlibre"; }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) { return true; }
}
