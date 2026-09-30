package com.example.onigokko.item;

import com.example.onigokko.OnigokkoMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    /** MODアイテム登録*/
    public static final DeferredRegister<Item> ITEMS=
            DeferredRegister.create(ForgeRegistries.ITEMS, OnigokkoMod.MODID);

    /** 役職選択の杖 */
    public static final RegistryObject<Item> ROLE_WAND =
            ITEMS.register("role_wand",
                    () -> new Item(new Item.Properties().stacksTo(1)));

    /** 魔法の時計*/
    public static final RegistryObject<Item> MAGIC_CLOCK =
            ITEMS.register("magic_clock",
                    () -> new Item(new Item.Properties().stacksTo(1)));

}
