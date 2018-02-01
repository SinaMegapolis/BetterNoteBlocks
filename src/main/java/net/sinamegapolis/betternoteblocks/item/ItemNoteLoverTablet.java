package net.sinamegapolis.betternoteblocks.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.sinamegapolis.betternoteblocks.init.IHasModel;
import net.sinamegapolis.betternoteblocks.init.ModRegistry;

public class ItemNoteLoverTablet extends Item implements IHasModel{
    public ItemNoteLoverTablet(String name)
    {
        this.setCreativeTab(CreativeTabs.REDSTONE);
        setRegistryName(name);
        setUnlocalizedName(net.sinamegapolis.betternoteblocks.BetterNoteBlocks.MODID + "." + name);
        ModRegistry.ITEMS.add(this);
    }

    @Override
    public void registerModels() {

        net.minecraftforge.client.model.ModelLoader.setCustomModelResourceLocation(this,
                0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
