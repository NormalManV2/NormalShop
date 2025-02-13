package normalmanv2.normalShop.common;

import normalmanv2.normalShop.api.shop.ShopHolder;
import org.bukkit.Bukkit;

import java.util.UUID;

public record ShopHolderImpl(String id) implements ShopHolder {

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean isPlayer() {
        return Bukkit.getPlayer(UUID.fromString(this.id)) != null;
    }
}
