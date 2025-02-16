package normalmanv2.normalShop.common;

import normalmanv2.normalShop.api.shop.ShopHolder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record ShopHolderImpl(String id, Inventory inventory) implements ShopHolder {

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean isPlayer() {
        return Bukkit.getPlayer(UUID.fromString(this.id)) != null;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
