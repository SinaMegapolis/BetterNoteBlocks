package net.sinamegapolis.betternoteblocks.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.sinamegapolis.betternoteblocks.block.BlockBetterNote;
import net.sinamegapolis.betternoteblocks.item.ItemNoteLoverTablet;
import net.sinamegapolis.betternoteblocks.tileentity.TileEntityBetterNote;

import java.util.ArrayList;
import java.util.List;

public class ModRegistry {
    public static final List<Block> BLOCKS = new ArrayList<Block>();
    public static final List<Item> ITEMS = new ArrayList<Item>();
    public static final Block BetterNoteBlock = new BlockBetterNote("betternoteblock");
    public static final Item NoteLoverTablet = new ItemNoteLoverTablet("notelover_tablet");

    @SubscribeEvent
    public void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(BLOCKS.toArray(new Block[0]));
        GameRegistry.registerTileEntity(TileEntityBetterNote.class,BetterNoteBlock.getRegistryName().toString());
    }


    @SubscribeEvent
    public void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ITEMS.toArray(new Item[0]));
    }
}
