package normalmanv2.normalShop.common;

import normalmanv2.normalShop.api.shop.Shop;
import normalmanv2.normalShop.api.shop.ShopHolder;
import normalmanv2.normalShop.api.shop.ShopItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractShop implements Shop {

    private final String id;
    private final String name;
    private final ShopHolder holder;
    private final Map<Integer, ShopItem> items;
    private final String currencyType;

    public AbstractShop(String id, String name, ShopHolder holder, String currencyType) {
        this.id = id;
        this.name = name;
        this.holder = holder;
        this.items = new HashMap<>();
        this.currencyType = currencyType;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public ShopHolder getHolder() {
        return this.holder;
    }

    @Override
    public Map<Integer, ShopItem> getItems() {
        return Collections.unmodifiableMap(this.items);
    }

    @Override
    public void addItem(Integer slot, ShopItem item) {
        this.items.put(slot, item);
    }

    @Override
    public ShopItem removeItem(Integer slot) {
        return this.items.remove(slot);
    }

    @Override
    public boolean removeItem(ShopItem item) {
        return this.items.entrySet().removeIf(entry -> entry.getValue().equals(item));
    }

    @Override
    public boolean purchaseItem(ShopItem item, ShopHolder buyer) {
        return false;
    }

    @Override
    public String getCurrencyType() {
        return this.currencyType;
    }

    @Override
    public abstract Inventory openShop(Player player);
}
