package com.example;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class MyItemGroup {

    public static ItemGroup TEST_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(ExampleMod.MOD_ID, "test_group"),
                FabricItemGroup.builder()
                        .icon(() -> new ItemStack(MyItem.CUSTOM_ITEM))
            .displayName(Text.translatable("MyItemGroup.my_mod.test_group"))
            .build());


    public static final RegistryKey<ItemGroup> MY_ITEM_GROUP_KEY = register("test_group");

    private static RegistryKey<ItemGroup> register(String id) {
        return RegistryKey.of(Registries.ITEM_GROUP.getKey(), new Identifier(ExampleMod.MOD_ID, id));
    }

    public static void addToGroup(Item item){
        ItemGroupEvents.modifyEntriesEvent(MY_ITEM_GROUP_KEY).register(content -> content.add(item));
    }

    public static void initialize() {

    }
}
