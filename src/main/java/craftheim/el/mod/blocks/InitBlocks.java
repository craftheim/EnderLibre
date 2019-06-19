package craftheim.el.mod.blocks;

import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class InitBlocks
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block blockPos = new BlockPos("block_pos");
    public static final Block blockMarket = new BlockMarket("block_market");
}
