package com.example.onigokko;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import com.example.onigokko.item.ModCreativeTabs;
import com.example.onigokko.item.ModItems;

@Mod(OnigokkoMod.MODID)
public class OnigokkoMod {

    /** MOD ID アイテム名やテクスチャのパスに使用 */
    public static final String MODID = "onigokko";

    /** ログ出力用 System.out.printlnの代わりに使う */
    public static final Logger LOGGER = LogUtils.getLogger();

    public OnigokkoMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(modEventBus);
        ModCreativeTabs.TABS.register(modEventBus);

        LOGGER.info("[onigokko] Base TagGameSystem Loading start...");
    }
}
