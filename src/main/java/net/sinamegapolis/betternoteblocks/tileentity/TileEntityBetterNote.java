package net.sinamegapolis.betternoteblocks.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.sinamegapolis.betternoteblocks.block.BlockBetterNote;
import net.sinamegapolis.betternoteblocks.init.ModRegistry;
import net.minecraft.tileentity.TileEntityNote;

public class TileEntityBetterNote extends TileEntityNote
{
    /** Note to play */
    public byte note;
    /** stores the latest redstone state */
    public boolean previousRedstoneState;
    /**this is used in making MSN */
    public int triggeredByRedstoneCount = 0;

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setByte("note", this.note);
        compound.setBoolean("powered", this.previousRedstoneState);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.note = compound.getByte("note");
        this.note = (byte)MathHelper.clamp(this.note, 0, 24);
        this.previousRedstoneState = compound.getBoolean("powered");
    }

    /**
     * change pitch by -> (currentPitch + 1) % 25
     */
    public void changePitch()
    {
        byte old = note;
        this.note = (byte)((this.note + 1) % 25);
        if (!net.minecraftforge.common.ForgeHooks.onNoteChange(this, old)) return;
        this.markDirty();
    }

    public void triggerBetterNote(World worldIn, BlockPos posIn)
    {
        if (worldIn.getBlockState(posIn.up()).getMaterial() == Material.AIR)
        {
            IBlockState iblockstate = worldIn.getBlockState(posIn.down());
            Material material = iblockstate.getMaterial();
            int i = 0;

            if (material == Material.ROCK){
                i = 1;
            }

            if (material == Material.SAND)
            {
                i = 2;
            }

            if (material == Material.GLASS)
            {
                i = 3;
            }

            if (material == Material.WOOD)
            {
                i = 4;
            }

            Block block = iblockstate.getBlock();

            if (block == Blocks.CLAY)
            {
                i = 5;
            }

            if (block == Blocks.GOLD_BLOCK)
            {
                i = 6;
            }

            if (block == Blocks.WOOL)
            {
                i = 7;
            }

            if (block == Blocks.PACKED_ICE)
            {
                i = 8;
            }

            if (block == Blocks.BONE_BLOCK)
            {
                i = 9;
            }
            BlockBetterNote bbn = (BlockBetterNote)worldIn.getBlockState(posIn).getBlock();
            worldIn.addBlockEvent(posIn, bbn, i, this.note);
        }
    }

    public boolean createMagicalSolidNote(World worldIn, EntityPlayer playerIn, TileEntityBetterNote te){
        if(checkForMSNCreationStructure(worldIn, te.getPos())){
            int amount = worldIn.rand.nextInt(10 - 5 + 1)+ 5;
            for(int i = 0; i < amount; i++){
                worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, te.getPos().up().getX(), te.getPos().up().getY(), te.getPos().up().getZ(), 3.0F, 3.0F, 3.0F);
            }
            worldIn.playSound(playerIn, te.getPos().up(),  SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, 1.0F);
            return true;
        }
        return false;
    }

    public boolean checkForMSNCreationStructure(World worldIn, BlockPos pos){
        if(!(worldIn.getTileEntity(pos.east()) instanceof TileEntityBetterNote)) return false;
        if(!(worldIn.getTileEntity(pos.east().north()) instanceof TileEntityBetterNote)) return false;
        if(!(worldIn.getTileEntity(pos.east().south()) instanceof TileEntityBetterNote)) return false;

        if(!(worldIn.getTileEntity(pos.south()) instanceof TileEntityBetterNote)) return false;
        if(!(worldIn.getTileEntity(pos.north()) instanceof TileEntityBetterNote)) return false;

        if(!(worldIn.getTileEntity(pos.west()) instanceof TileEntityBetterNote)) return false;
        if(!(worldIn.getTileEntity(pos.west().north()) instanceof TileEntityBetterNote)) return false;
        if(!(worldIn.getTileEntity(pos.west().south()) instanceof TileEntityBetterNote)) return false;
        return true;
    }
}