package net.sinamegapolis.betternoteblocks.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButtonToggle;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;

public class GuiNoteLoverTabletScreen extends GuiScreen {

    public GuiNoteLoverTabletScreen(){
    }

    private GuiButtonToggle InstrumentNoteModeToggle;
    private TextureAtlasSprite meow;
    private Gui maw;
    private String error;

    @Override
    public void initGui() {
        super.initGui();
        this.drawString(this.fontRenderer, "hello there", 250, 250, 898989);
    }
}
