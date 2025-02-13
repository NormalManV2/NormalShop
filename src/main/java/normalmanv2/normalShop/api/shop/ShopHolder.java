package normalmanv2.normalShop.api.shop;

public interface ShopHolder {
    /**
     * Get the unique identifier of the shop holder.
     * Can represent a player UUID, NPC ID, or other unique reference.
     * @return The unique identifier.
     */
    String getId();

    /**
     * Convenience method considering not all ShopHolders are players.
     * @return True if the player object exists.
     */
    boolean isPlayer();
}
