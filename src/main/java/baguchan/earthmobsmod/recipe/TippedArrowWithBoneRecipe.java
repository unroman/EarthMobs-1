package baguchan.earthmobsmod.recipe;

import baguchan.earthmobsmod.registry.ModItems;
import baguchan.earthmobsmod.registry.ModRecipes;
import com.google.common.collect.Lists;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;

import java.util.List;

public class TippedArrowWithBoneRecipe extends CustomRecipe {
    public TippedArrowWithBoneRecipe(CraftingBookCategory p_251985_) {
        super(p_251985_);
	}

	public boolean matches(CraftingContainer p_44515_, Level p_44516_) {
		List<Item> list = Lists.newArrayList();
		List<Item> list2 = Lists.newArrayList();
		List<Item> list3 = Lists.newArrayList();

		int slot = 0;

		for (int i = 0; i < p_44515_.getWidth(); ++i) {
			for (int j = 0; j < p_44515_.getHeight(); ++j) {
				ItemStack itemstack = p_44515_.getItem(i + j * p_44515_.getWidth());

				if (j == 0) {
					if (itemstack.is(ModItems.BONE_SHARD.get())) {
						slot = i;
						list.add(itemstack.getItem());
					} else if (!itemstack.isEmpty()) {
						return false;
					}
				} else if (j == 2) {
					if (itemstack.is(Tags.Items.FEATHERS)) {
						if (slot == i) {
							list3.add(itemstack.getItem());
						} else {
							return false;
						}
					} else if (!itemstack.isEmpty()) {
						return false;
					}
				} else if (j == 1) {
					if (itemstack.is(Tags.Items.RODS_WOODEN)) {
						if (slot == i) {
							list2.add(itemstack.getItem());
						} else {
							return false;
						}
					} else if (!itemstack.isEmpty()) {
						return false;
					}
				}
			}
		}

		return list3.size() == 1 && list.size() == 1 && list2.size() == 1;
	}

	@Override
	public ItemStack assemble(CraftingContainer p_44001_, HolderLookup.Provider p_267165_) {
		for (int i = 0; i < p_44001_.getContainerSize(); ++i) {
			ItemStack itemstack = p_44001_.getItem(i);
			if (itemstack.is(ModItems.BONE_SHARD.get())) {
				PotionContents optional = itemstack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
				if (optional.potion().isPresent()) {
					return PotionContents.createItemStack(Items.TIPPED_ARROW, optional.potion().get()).copyWithCount(2);
				} else {
					ItemStack itemstack1 = new ItemStack(Items.ARROW, 2);
					return itemstack1;
				}
			}
		}
		return ItemStack.EMPTY;
	}

	public boolean canCraftInDimensions(int p_44505_, int p_44506_) {
		return true;
	}

	public RecipeSerializer<?> getSerializer() {
		return ModRecipes.RECIPE_TIPPED_ARROW_WITH_BONE.get();
	}
}