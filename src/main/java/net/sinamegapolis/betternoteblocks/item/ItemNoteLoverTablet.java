package net.sinamegapolis.betternoteblocks.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.sinamegapolis.betternoteblocks.gui.GuiNoteLoverTabletScreen;
import net.sinamegapolis.betternoteblocks.init.IHasModel;
import net.sinamegapolis.betternoteblocks.init.ModRegistry;
import net.sinamegapolis.betternoteblocks.tileentity.TileEntityBetterNote;

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

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        if(worldIn.isRemote){
            return EnumActionResult.PASS;
        }
        if(worldIn.getTileEntity(pos) instanceof TileEntityBetterNote){
            player.sendStatusMessage(new TextComponentString("The Current note of this BetterNoteBlock is "+ ((TileEntityBetterNote) worldIn.getTileEntity(pos)).note),false );
            return EnumActionResult.SUCCESS;
        }
        if(worldIn.getTileEntity(pos) instanceof TileEntityNote){
            player.sendStatusMessage(new TextComponentString("The Current note of this NoteBlock is "+ ((TileEntityNote) worldIn.getTileEntity(pos)).note),false );
            return EnumActionResult.SUCCESS;
        }
       return EnumActionResult.PASS;
    }
}
