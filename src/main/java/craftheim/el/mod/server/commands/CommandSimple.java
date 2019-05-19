package craftheim.el.mod.server.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandSimple extends CommandBase {
    private String _name;
    private String _usage;
    private ICommandSimpleExecution _exec;

    public CommandSimple(String name, String usage, ICommandSimpleExecution exec) {
        _name = name;
        _usage = usage;
        _exec = exec;
    }

    public interface ICommandSimpleExecution {
        void execute(MinecraftServer server, ICommandSender sender, String[] args);
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public String getUsage(ICommandSender sender) { return _usage; }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        _exec.execute(server, sender, args);
    }
}
