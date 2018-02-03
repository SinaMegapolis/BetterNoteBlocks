package net.sinamegapolis.betternoteblocks.block;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockNote;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.*;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sinamegapolis.betternoteblocks.init.IHasModel;
import net.sinamegapolis.betternoteblocks.init.ModRegistry;
import net.sinamegapolis.betternoteblocks.item.ItemNotalyzer;
import net.sinamegapolis.betternoteblocks.tileentity.TileEntityBetterNote;


import java.util.Iterator;
import java.util.List;

public class BlockBetterNote extends BlockNote implements IHasModel{
    private static final List<SoundEvent> INSTRUMENTS = Lists.newArrayList(SoundEvents.BLOCK_NOTE_HARP, SoundEvents.BLOCK_NOTE_BASEDRUM, SoundEvents.BLOCK_NOTE_SNARE, SoundEvents.BLOCK_NOTE_HAT, SoundEvents.BLOCK_NOTE_BASS, SoundEvents.BLOCK_NOTE_FLUTE, SoundEvents.BLOCK_NOTE_BELL, SoundEvents.BLOCK_NOTE_GUITAR, SoundEvents.BLOCK_NOTE_CHIME, SoundEvents.BLOCK_NOTE_XYLOPHONE);
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static EntityPlayer p = Minecraft.getMinecraft().player;

    public BlockBetterNote(String name)
    {
        setCreativeTab(CreativeTabs.REDSTONE);
        setHardness(0.8f);
        setRegistryName(name);
        setUnlocalizedName(net.sinamegapolis.betternoteblocks.BetterNoteBlocks.MODID + "." + name);
        ModRegistry.BLOCKS.add(this);
        ModRegistry.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        boolean flag = worldIn.isBlockPowered(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileEntityNote)
        {
            TileEntityNote tileentitynote = (TileEntityNote)tileentity;

            if (tileentitynote.previousRedstoneState != flag)
            {
                if (flag)
                {
                    tileentitynote.triggerNote(worldIn, pos);
                }

                tileentitynote.previousRedstoneState = flag;
            }
        }
    }

    /**
     * Called when the block is right clicked by a player.
     */
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            if(!(playerIn.getHeldItem(hand).getItem() instanceof ItemNotalyzer)){
            TileEntity tileentity = worldIn.getTileEntity(pos);
                    if(tileentity instanceof TileEntityBetterNote){
                TileEntityBetterNote tileentitynote = (TileEntityBetterNote) tileentity;
                int old = tileentitynote.note;
                tileentitynote.changePitch();
                if (old == tileentitynote.note) return false;
                tileentitynote.triggerNote(worldIn, pos, playerIn);
                playerIn.addStat(StatList.NOTEBLOCK_TUNED);
            }}

            return true;
        }




    }



    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityBetterNote();
    }

    private SoundEvent getInstrument(int eventId)
    {
        if (eventId < 0 || eventId >= INSTRUMENTS.size())
        {
            eventId = 0;
        }

        return INSTRUMENTS.get(eventId);
    }

    /**
     * Called on server when World#addBlockEvent is called. If server returns true, then also called on the client. On
     * the Server, this may perform additional changes to the world, like pistons replacing the block with an extended
     * base. On the client, the update may involve replacing tile entities or effects such as sounds or particles
     */
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param)
    {
        net.minecraftforge.event.world.NoteBlockEvent.Play e = new net.minecraftforge.event.world.NoteBlockEvent.Play(worldIn, pos, state, param, id);
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(e)) return false;
        id = e.getInstrument().ordinal();
        param = e.getVanillaNoteId();
        float f = (float)Math.pow(2.0D, (double)(param - 12) / 12.0D);
        worldIn.playSound((EntityPlayer)null, pos, this.getInstrument(id), SoundCategory.RECORDS, 3.0F, f);
        worldIn.spawnParticle(EnumParticleTypes.NOTE, (double)pos.getX() + 0.5D, (double)pos.getY() + 1.2D, (double)pos.getZ() + 0.5D, (double)param / 24.0D, 0.0D, 0.0D);
        return true;
    }

    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     */
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public net.minecraft.block.state.BlockStateContainer createBlockState()
    {
        return new net.minecraft.block.state.BlockStateContainer(this, new net.minecraft.block.properties.IProperty[] {FACING});
    }


    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void registerModels() {

        net.minecraftforge.client.model.ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this),
                0, new net.minecraft.client.renderer.block.model.ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
