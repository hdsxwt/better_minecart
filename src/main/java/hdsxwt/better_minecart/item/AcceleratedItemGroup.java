package hdsxwt.better_minecart.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class AcceleratedItemGroup {
	private static final List<ItemStack> GROUP_ENTRIES = new ArrayList<>();

	public static void addToGroup(Item item) {
		GROUP_ENTRIES.add(new ItemStack(item));
	}

	public static List<ItemStack> getGroupEntries() {
		return GROUP_ENTRIES;
	}

	private AcceleratedItemGroup() {
		// Private constructor to prevent instantiation
	}
}

