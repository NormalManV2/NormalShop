package normalmanv2.normalShop.api.shop;

import normalmanv2.normalShop.api.Context;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public interface ShopItem {
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
     * @param material The material of which the item should resemble.
     * @return The built ItemStack object.
     */
    ItemStack build(Material material);

    /**
     * Builds the itemStack from given parameters.
     * @param material The material of which the item should resemble.
     * @param displayName The display name of the item.
     * @param lore The lore of the item.
     * @return Returns the built ItemStack object.
     */
    ItemStack build(Material material, String displayName, String... lore);


    /**
     * This method is used to handle ui clicks. Context must not be null. It is used for (mostly) redundant
     * internal click handling. (IE: Regular shop clicks, increase / decreasing amount, as well as a CUSTOM
     * for any type that may be necessary).
     * @param event The click event, used for more specific information.
     * @param context The context of the click.
     */
    void click(InventoryClickEvent event, Context<?> context);

}
