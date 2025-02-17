package normalmanv2.normalShop.api.shop;

import normalmanv2.normalShop.api.Context;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.io.Serializable;

public interface ShopItem extends Serializable {
    /**
     * Get the name of the item.
     * @return The item name.
     */
    String getName();

    /**
     * Get the price of the item.
     * @return The price of the item.
     */
    double getPrice();

    /**
     * Get the item's description.
     * @return The description of the item.
     */
    String getDescription();

    /**
     * Increase the amount of this item.
     * @param amount The amount to increase by.
     */
    void increaseAmount(int amount);

    /**
     * Decrease the amount of this item.
     * @param amount The amount to decrease by.
     * @return False if amount is below zero.
     */
    boolean decreaseAmount(int amount);

    /**
     * Builds the ItemStack object from relevant information within the ShopItem object.
     * @param material The material of which the item should resemble. The material **MUST NOT BE** null.
     * @return The built ItemStack object.
     */
    ItemStack build(Material material);

    /**
     * Builds the itemStack from given parameters.
     * @param material The material of which the item should resemble. This can be null, defaults to
     *                 Material.EMERALD.
     * @param displayName The display name of the item. This can be null, defaults to internal name
     *                    field.
     * @param lore The lore of the item. This can be null, defaults to internal description field.
     * @return Returns the built ItemStack object.
     */
    ItemStack build(@Nullable Material material, @Nullable String displayName, @Nullable String... lore);

    /**
     * This method is used to handle ui clicks. Context must not be null. It is used for (mostly) redundant
     * internal click handling.
     * @param event The click event, used for more specific information.
     * @param context The context of the click.
     */
    void click(InventoryClickEvent event, Context<?> context);

}
