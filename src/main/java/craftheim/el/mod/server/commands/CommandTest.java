package craftheim.el.mod.server.commands;

import craftheim.el.common.capabilities.CapabilityBank;
import craftheim.el.common.capabilities.ICapabilityBank;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandTest extends CommandBase {

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/test";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer player = (EntityPlayer)sender;
        if(player == null)
            return;

        if(args.length > 1) {
            player.sendMessage(new TextComponentString("Cualca la cantidad de argumentos"));
            return;
        }

        ICapabilityBank bank = player.getEntityWorld().getCapability(CapabilityBank.DefaultProvider.CAP, null);
        if(bank == null)
            return;

        if(args.length == 0) {
            player.sendMessage(new TextComponentString("Re bien anduvo. Tu platita: " + bank.get() + " weeblons"));
            return;
        }

        int amount;
        try {
            amount = parseInt(args[0]);
        } catch (Exception e) {
            player.sendMessage(new TextComponentString("Cualca el valor del argumento"));
            return;
        }

        if(amount > 0)
            bank.give(amount);
        else if(amount < 0)
            bank.take(-amount);

        player.sendMessage(new TextComponentString("Pronto"));
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }
}
