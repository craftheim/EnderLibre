package craftheim.el.mod.gui;

import java.util.List;

import craftheim.el.mod.gui.container.MarketContainer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.util.ITooltipFlag.TooltipFlags;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class MarketGUI extends GuiContainer {
    public static final int ID = 1;
    
    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");

    public MarketGUI(MarketContainer container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.mc.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, 1 * 18 + 17);
        this.drawTexturedModalRect(i, j + 1 * 18 + 17, 0, 126, this.xSize, 13);
        this.drawTexturedModalRect(i, j + 1 * 18 + 30, 0, 126, this.xSize, 13);
        this.drawTexturedModalRect(i, j + 1 * 18 + 43, 0, 126, this.xSize, 13);
        this.drawTexturedModalRect(i, j + 1 * 18 + 56, 0, 126, this.xSize, 13);
        this.drawTexturedModalRect(i, j + 1 * 18 + 69, 0, 215, this.xSize, 13);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        //this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void renderToolTip(ItemStack stack, int x, int y) {
        List<String> tooltip = stack.getTooltip(this.mc.player, TooltipFlags.NORMAL);
        NBTTagCompound comp = stack.getTagCompound();
        if(comp != null && comp.hasKey("price")) {
            tooltip.add(" ");
            tooltip.add(TextFormatting.GOLD + "Price: "+ comp.getInteger("price"));
        }

        FontRenderer font = stack.getItem().getFontRenderer(stack);
        net.minecraftforge.fml.client.config.GuiUtils.preItemToolTip(stack);
        this.drawHoveringText(tooltip, x, y, (font == null ? fontRenderer : font));
        net.minecraftforge.fml.client.config.GuiUtils.postItemToolTip();
    }
   
    @Override
    public void initGui() {
        super.initGui();
        //Add buttons here
    }
}