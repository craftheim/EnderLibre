package craftheim.el.mod.tiles;

import craftheim.el.mod.EnderLibre;
import li.cil.oc.api.API;
import li.cil.oc.api.network.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "li.cil.oc.api.network.Environment", modid = "opencomputers")
public class TileEntityOCComponent extends TileEntity implements Environment, ITickable {
    private Component _node = null;

    public TileEntityOCComponent(String componentName) {
        if(Loader.isModLoaded("opencomputers")) {
            EnderLibre.LOGGER.warn(API.network);
            _node = API.network.newNode(this, Visibility.Network).
                withComponent(componentName).
                create();
        }
    }

    @Override
    public void update() {
        if (_node != null && _node.network() == null) {
            API.network.joinOrCreateNetwork(this);
        }
    }

    @Override
    public void onChunkUnload() {
        super.onChunkUnload();
        if (_node != null)
            _node.remove();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        if (_node != null)
            _node.remove();
    }

    @Override
    public Node node() {
        return _node;
    }

    @Optional.Method(modid = "opencomputers")
    @Override
    public void onConnect(Node node) {

    }

    @Optional.Method(modid = "opencomputers")
    @Override
    public void onDisconnect(Node node) {

    }

    @Optional.Method(modid = "opencomputers")
    @Override
    public void onMessage(Message message) {

    }
}
