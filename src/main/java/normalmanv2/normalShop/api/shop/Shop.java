package normalmanv2.normalShop.api.shop;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public interface Shop {

    /**
     * The id of the shop.
     * @return The id of the shop.
     */
    String id();

    /**
     * The name of the shop (used internally for ui creation)
     * @return The name of the shop.
     */
    String name();

    /**
     * Get the holder of the shop, e.g., a player or NPC.
     * @return The holder of the shop.
     */
    ShopHolder getHolder();

    /**
     * Get all items available in the shop.
     * @return A map of ui slots to shop items.
     */
    Map<Integer, ShopItem> getItems();

    /**
     * Adds an item to the shop.
     * @param slot The slot to add the item in.
     * @param item The shop item to add.
     */
    void addItem(Integer slot, ShopItem item);

    /**
     * Removes an item from the shop.
     * @param slot The slot of the item to remove.
     * @return The removed item, or null if the item wasn't found.
     */
    ShopItem removeItem(Integer slot);

    /**
     * Removes an item from the shop.
     * @param item The item to remove from the shop.
     * @return False if the item does not exist in the shop.
     */
    boolean removeItem(ShopItem item);

    /**
     * Purchase an item from the shop.
     * @param item The item of which to purchase.
     * @param buyer The entity making the purchase.
     * @return True if the purchase was successful, false otherwise.
     */
    boolean purchaseItem(ShopItem item, ShopHolder buyer);

    /**
     * Get the currency type used by the shop (e.g., gold, points, coins).
     * @return The type of currency used.
     */
    String getCurrencyType();

    /**
     * Open the shop GUI for the specified player.
     * @param player The player to open the GUI for.
     * @return Returns the opened GUI.
     */
    Inventory openShop(Player player);
}
