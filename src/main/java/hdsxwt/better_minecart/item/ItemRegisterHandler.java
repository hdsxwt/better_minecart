package hdsxwt.better_minecart.item;

import hdsxwt.better_minecart.BetterMinecartMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public abstract class ItemRegisterHandler {

	public static void register() {
		BetterMinecartMod.LOGGER.info(BetterMinecartMod.MESSAGE_HEAD_STRING + "registering mod items");
		
		registerAcceleratedMinecartItem();
		registerAcceleratedMinecartItemGroup();
		
		BetterMinecartMod.LOGGER.info(BetterMinecartMod.MESSAGE_HEAD_STRING + "registering mod items complete");
	}

	public static final RegistryKey<Item> ACCELERATED_MINECART_KEY = RegistryKey.of(
				Registries.ITEM.getKey(),
				Identifier.of(BetterMinecartMod.MOD_ID, "accelerated_minecart")
	);

	public static final Item.Settings ACCELERATED_MINECART_SETTINGS = new Item
				.Settings()
				.maxCount(1)
				.registryKey(ACCELERATED_MINECART_KEY);

	public static final Item ACCELERATED_MINECART = Registry.register(Registries.ITEM,
				Identifier.of(BetterMinecartMod.MOD_ID, "accelerated_minecart"),
				new AcceleratedMinecartItem(ACCELERATED_MINECART_SETTINGS)
	);

	public static final RegistryKey<ItemGroup> BETTER_MINECART_GROUP = RegistryKey.of(
		RegistryKeys.ITEM_GROUP,
		Identifier.of(BetterMinecartMod.MOD_ID, "better_minecart_group")
	);

	private static void registerAcceleratedMinecartItem() {
		return; // The item is registered inline with its declaration, so this method can be left empty or removed.
	}

	private static void registerAcceleratedMinecartItemGroup() {
		Registry.register(Registries.ITEM_GROUP, BETTER_MINECART_GROUP,
            FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.better_minecart.better_minecart_group"))
                .icon(() -> new ItemStack(ACCELERATED_MINECART))
                .entries((context, entries) -> {
                    entries.add(ACCELERATED_MINECART);
                })
                .build()
        );
	}
}