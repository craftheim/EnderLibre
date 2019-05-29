package craftheim.el.mod.tiles;

import li.cil.oc.api.API;
import li.cil.oc.api.network.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.common.Optional;

@Optional.Interface(iface = "li.cil.oc.api.network.Environment", modid = "opencomputers")
public class TileEntityOCComponent extends TileEntity implements Environment, ITickable {
    private Component _node = null;

    protected void createNode(String componentName) {
        if (this instanceof Environment) {
            _node = API.network.newNode(this, Visibility.Network).
                withComponent(componentName).
                create();
        }
    }

    @Override
    public void update() {
        if (node() != null && node().network() == null) {
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

    @Override
    public void onConnect(Node node) {

    }

    @Override
    public void onDisconnect(Node node) {

    }

    @Override
    public void onMessage(Message message) {

    }
}
