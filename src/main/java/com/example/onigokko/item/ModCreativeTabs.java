package com.example.onigokko.item;

import com.example.onigokko.OnigokkoMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OnigokkoMod.MODID);

    public static final RegistryObject<CreativeModeTab> ONIGOKKO_TAB =
            TABS.register("onigokko_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.onigokko"))
                    .icon(() -> new ItemStack(ModItems.ROLE_WAND.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.ROLE_WAND.get());
                        output.accept(ModItems.MAGIC_CLOCK.get());
                    })
                    .build());
}
