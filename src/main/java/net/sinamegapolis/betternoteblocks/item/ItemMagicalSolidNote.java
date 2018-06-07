package net.sinamegapolis.betternoteblocks.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sinamegapolis.betternoteblocks.init.IHasModel;
import net.sinamegapolis.betternoteblocks.init.ModRegistry;
import net.sinamegapolis.betternoteblocks.utill.MagicalSolidNoteUsageHandler;

public class ItemMagicalSolidNote extends Item implements IHasModel{
    public ItemMagicalSolidNote(String name)
    {
        this.setCreativeTab(CreativeTabs.REDSTONE);
        setRegistryName(name);
        setUnlocalizedName(net.sinamegapolis.betternoteblocks.BetterNoteBlocks.MODID + "." + name);
        ModRegistry.ITEMS.add(this);
    }

    public ItemMagicalSolidNote(){
    }

    @Override
    public void registerModels() {

        net.minecraftforge.client.model.ModelLoader.setCustomModelResourceLocation(this,
                0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        MagicalSolidNoteUsageHandler.makeCreepersConfused(playerIn, worldIn, 0, 6.0F, 1.0D, 1.2D);
        MagicalSolidNoteUsageHandler.makeSkeletonsConfused(playerIn, worldIn, 0, 6.0F, 1.0D, 1.2D);
        return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

}
