package net.sinamegapolis.betternoteblocks.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.sinamegapolis.betternoteblocks.block.BlockBetterNote;
import net.sinamegapolis.betternoteblocks.block.BlockCompressedTrash;
import net.sinamegapolis.betternoteblocks.block.BlockNoteSolidifier;
import net.sinamegapolis.betternoteblocks.block.BlockTrash;
import net.sinamegapolis.betternoteblocks.item.ItemMagicalSolidNote;
import net.sinamegapolis.betternoteblocks.item.ItemNotalyzer;
import net.sinamegapolis.betternoteblocks.tileentity.TileEntityBetterNote;
import net.sinamegapolis.betternoteblocks.tileentity.TileEntityTrash;

import java.util.ArrayList;
import java.util.List;

public class ModRegistry {
    public static final List<Block> BLOCKS = new ArrayList<Block>();
    public static final List<Item> ITEMS = new ArrayList<Item>();
    public static final Block BetterNoteBlock = new BlockBetterNote("betternoteblock");
    public static final Item Notalyzer = new ItemNotalyzer("notalyzer");
    public static final Item MagicalSolidNote = new ItemMagicalSolidNote("magicalsolidnote");
    public static final Block NoteSolidifier = new BlockNoteSolidifier("notesolidifierblock");
    public static final Block TrashBlock = new BlockTrash("trashblock");
    public static final Block CompressedTrashBlock = new BlockCompressedTrash("compressedtrash");

    @SubscribeEvent
    public void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(BLOCKS.toArray(new Block[0]));
        GameRegistry.registerTileEntity(TileEntityBetterNote.class,BetterNoteBlock.getRegistryName().toString());
        GameRegistry.registerTileEntity(TileEntityTrash.class, TrashBlock.getRegistryName().toString());
    }


    @SubscribeEvent
    public void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ITEMS.toArray(new Item[0]));
    }

}
