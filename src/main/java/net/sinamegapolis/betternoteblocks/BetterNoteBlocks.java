package net.sinamegapolis.betternoteblocks;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.sinamegapolis.betternoteblocks.proxy.CommonProxy;

@Mod(modid = BetterNoteBlocks.MODID, name = BetterNoteBlocks.NAME, version = BetterNoteBlocks.VERSION)
public class BetterNoteBlocks {
    public static final String MODID = "betternoteblocks";
    public static final String NAME = "Better Note Blocks";
    public static final String VERSION = "alpha0.1";

    @Mod.Instance
    public static BetterNoteBlocks INSTANCE;

    @SidedProxy(clientSide = "net.sinamegapolis.betternoteblocks.proxy.ClientProxy", serverSide = "net.sinamegapolis.betternoteblocks.proxy.CommonProxy")
    public static CommonProxy PROXY;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e){
        PROXY.preInit(e);
        PROXY.registerRenderers();
    }
    @EventHandler
    public void init(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(this);
        PROXY.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        PROXY.postInit(e);
    }
}
