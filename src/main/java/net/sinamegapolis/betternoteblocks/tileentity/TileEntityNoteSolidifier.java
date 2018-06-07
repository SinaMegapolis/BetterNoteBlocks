package net.sinamegapolis.betternoteblocks.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.sinamegapolis.betternoteblocks.init.ModRegistry;
import net.sinamegapolis.betternoteblocks.item.ItemMagicalSolidNote;
import net.sinamegapolis.betternoteblocks.utill.MagicalSolidNoteCreationHandler;

import java.util.ArrayList;

public class TileEntityNoteSolidifier extends TileEntity implements ITickable{

    private static int BNBPCCount = 0;
    private int tickCount = 0;

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
    }

    public boolean createMagicalSolidNote(World worldIn, EntityPlayer playerIn, TileEntityNoteSolidifier te){
        if(checkForMSNCreationStructure(worldIn, te.getPos())){
            BlockPos msnDroppingCoords = te.getPos().up();
            int amount = worldIn.rand.nextInt(10 - 5 + 1)+ 5;
            for(int i = 1; i < amount; i++){
                worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, te.getPos().up().getX(), te.getPos().up().getY(), te.getPos().up().getZ(), 3.0F, 3.0F, 3.0F);
            }
            worldIn.playSound(playerIn, te.getPos().up(),  SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, 1.0F);
            if(!worldIn.isRemote) {
                worldIn.spawnEntity(new EntityItem(worldIn, msnDroppingCoords.getX(), msnDroppingCoords.getY(), msnDroppingCoords.getZ(), new ItemStack(ModRegistry.MagicalSolidNote)));
            }
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

        if((worldIn.getBlockState(pos.up())).getBlock() != Blocks.GLASS)return false;
        return true;
    }

    @Override
    public void update() {

        if (checkForMSNCreationStructure(this.getWorld(), this.getPos())) {
            if(tickCount != 648){
                TileEntityBetterNote[] tileEntityBNote = {
                        (TileEntityBetterNote)this.getWorld().getTileEntity(this.getPos().south().east()),
                        (TileEntityBetterNote)this.getWorld().getTileEntity(this.getPos().south()),
                        (TileEntityBetterNote)this.getWorld().getTileEntity(this.getPos().south().west()),
                        (TileEntityBetterNote)this.getWorld().getTileEntity(this.getPos().west()),
                        (TileEntityBetterNote)this.getWorld().getTileEntity(this.getPos().north().west()),
                        (TileEntityBetterNote)this.getWorld().getTileEntity(this.getPos().north()),
                        (TileEntityBetterNote)this.getWorld().getTileEntity(this.getPos().north().east()),
                        (TileEntityBetterNote)this.getWorld().getTileEntity(this.getPos().east())
                };
                if(tickCount<=320) {
                    MagicalSolidNoteCreationHandler.runCycle(tileEntityBNote, tickCount, 40, this.getWorld());
                }
                if(tickCount>320){
                    MagicalSolidNoteCreationHandler.runCycle(tileEntityBNote, tickCount - 320, 20, this.getWorld());
                }
                if(tickCount>=480){
                    MagicalSolidNoteCreationHandler.runCycle(tileEntityBNote, tickCount - 480, 10, this.getWorld());
                }
                if(tickCount>=560){
                    MagicalSolidNoteCreationHandler.runCycle(tileEntityBNote, tickCount - 560, 5, this.getWorld());
                }
                if(tickCount>=600){
                    MagicalSolidNoteCreationHandler.runCycle(tileEntityBNote, tickCount - 600, 3, this.getWorld());
                }
                if(tickCount>=624){
                    MagicalSolidNoteCreationHandler.runCycle(tileEntityBNote, tickCount - 624, 2, this.getWorld());
                }
                if(tickCount>=640){
                    MagicalSolidNoteCreationHandler.runCycle(tileEntityBNote, tickCount - 640, 1, this.getWorld());
                }
                BNBPCCount = BNBPCCount +1;
            }else{

                    createMagicalSolidNote(getWorld(), null, this);
                    getWorld().setBlockToAir(getPos().up());

                BNBPCCount = 0;
            }
        }
        if(tickCount != 648){
            tickCount = tickCount + 1;
        }else{tickCount = 0;}

    }
}
