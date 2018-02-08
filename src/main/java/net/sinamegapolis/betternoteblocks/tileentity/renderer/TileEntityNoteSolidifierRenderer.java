package net.sinamegapolis.betternoteblocks.tileentity.renderer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

import net.sinamegapolis.betternoteblocks.tileentity.TileEntityNoteSolidifier;
import org.lwjgl.opengl.GL11;

public class TileEntityNoteSolidifierRenderer extends TileEntitySpecialRenderer<TileEntityNoteSolidifier> {
    @Override
    public void render(TileEntityNoteSolidifier te, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
        GlStateManager.pushMatrix();
        GlStateManager.translate(x,y,z);

        GlStateManager.popMatrix();
    }
}
