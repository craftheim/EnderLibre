package craftheim.el.mod.gui;

import craftheim.el.mod.gui.container.MarketContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == MarketGUI.ID) {
            BlockPos pos = new BlockPos(x, y, z);
            return new MarketContainer(world, pos);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == MarketGUI.ID) {
            BlockPos pos = new BlockPos(x, y, z);
            return new MarketGUI(new MarketContainer(world, pos));
        }
        return null;
    }

}