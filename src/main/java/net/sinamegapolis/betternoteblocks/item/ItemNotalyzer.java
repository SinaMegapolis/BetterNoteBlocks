package net.sinamegapolis.betternoteblocks.item;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.sinamegapolis.betternoteblocks.gui.GuiNoteLoverTabletScreen;
import net.sinamegapolis.betternoteblocks.init.IHasModel;
import net.sinamegapolis.betternoteblocks.init.ModRegistry;
import net.sinamegapolis.betternoteblocks.tileentity.TileEntityBetterNote;

import javax.annotation.Nullable;
import java.util.List;

public class ItemNotalyzer extends Item implements IHasModel{

    public ItemNotalyzer(String name)
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
        boolean isMSNStructurePresent = false;
        TileEntity te = worldIn.getTileEntity(pos);
        if(!isMSNStructurePresent){
           if(te instanceof TileEntityBetterNote){
            player.sendStatusMessage(new TextComponentString(new TextComponentTranslation("notalyzer.results.currentpitch").getUnformattedComponentText() +" " + ((TileEntityBetterNote) te).note),false );
            player.sendStatusMessage(new TextComponentString(getInstrument(worldIn, pos)),false );
            return EnumActionResult.SUCCESS;
           }
           if(te instanceof TileEntityNote){
            player.sendStatusMessage(new TextComponentTranslation("notalyzer.results.badblock"), false);
            player.setHeldItem(hand, ItemStack.EMPTY);
            worldIn.createExplosion(player, pos.getX(), pos.getY(), pos.getZ(), 4.0F, true);
            }
        }
       return EnumActionResult.PASS;
    }

    private String getInstrument(World worldIn, BlockPos posIn){
        IBlockState iblockstate = worldIn.getBlockState(posIn.down());
        Material material = iblockstate.getMaterial();
        TextComponentTranslation Instrument = new TextComponentTranslation("notalyzer.results.instrument"),
        InstrumentType = new TextComponentTranslation("notalyzer.results.instrument.otherblocks");
        if (material == Material.ROCK)
        {
            InstrumentType = new TextComponentTranslation("notalyzer.results.instrument.rockmaterial");
        }

        if (material == Material.SAND)
        {
            InstrumentType = new TextComponentTranslation("notalyzer.results.instrument.sandmaterial");
        }

        if (material == Material.GLASS)
        {
            InstrumentType = new TextComponentTranslation("notalyzer.results.instrument.glassmaterial");
        }

        if (material == Material.WOOD)
        {
            InstrumentType = new TextComponentTranslation("notalyzer.results.instrument.woodmaterial");
        }

        Block block = iblockstate.getBlock();

        if (block == Blocks.CLAY)
        {
            InstrumentType = new TextComponentTranslation("notalyzer.results.instrument.clayblock");
        }

        if (block == Blocks.GOLD_BLOCK)
        {
            InstrumentType = new TextComponentTranslation("notalyzer.results.instrument.goldblock");
        }

        if (block == Blocks.WOOL)
        {
            InstrumentType = new TextComponentTranslation("notalyzer.results.instrument.woolblock");
        }

        if (block == Blocks.PACKED_ICE)
        {
            InstrumentType = new TextComponentTranslation("notalyzer.results.instrument.packediceblock");
        }

        if (block == Blocks.BONE_BLOCK)
        {
            InstrumentType = new TextComponentTranslation("notalyzer.results.instrument.boneblock");
        }
        return Instrument.getUnformattedComponentText() + " " + InstrumentType.getUnformattedComponentText();
    }
}
