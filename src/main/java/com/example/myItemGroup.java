package com.example;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class myItemGroup {

    public myItemGroup() {
    }

    public static ArrayList<Item> items = new ArrayList<>();

    public static ItemGroup TEST_GROUP;

    public static void addToGroup(Item item) {
        items.add(item);
    }

    public static void initialize() {

        TEST_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier("firstmod", "test_group"),
                FabricItemGroup.builder()
                        .icon(() -> new ItemStack(myItem.CUSTOM_ITEM))
                        .displayName(Text.translatable("itemGroup.firstmod.test_group"))
                        .entries((context, entries) -> {
                            for (Item item : items) {
                                entries.add(item);
                            }
                        })
                        .build());

    }
}
