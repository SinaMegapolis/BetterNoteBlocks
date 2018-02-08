package net.sinamegapolis.betternoteblocks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.sinamegapolis.betternoteblocks.init.IHasModel;
import net.sinamegapolis.betternoteblocks.init.ModRegistry;

public class BlockNoteSolidifier extends Block implements IHasModel {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public BlockNoteSolidifier(String name)
    {
        super(Material.WOOD);
        setCreativeTab(CreativeTabs.REDSTONE);
        setHardness(0.8f);
        setRegistryName(name);
        setUnlocalizedName(net.sinamegapolis.betternoteblocks.BetterNoteBlocks.MODID + "." + name);
        ModRegistry.BLOCKS.add(this);
        ModRegistry.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this),
                0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        return false;
    }

}
