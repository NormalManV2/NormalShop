package normalmanv2.normalShop.api;

import normalmanv2.normalShop.api.shop.Shop;
import normalmanv2.normalShop.api.shop.ShopHolder;
import normalmanv2.normalShop.api.shop.ShopItem;

import java.util.Optional;
import java.util.UUID;

public interface Context<T extends ContextType> {

    T getType();

    <Z> Optional<Z> getData(String key, Class<Z> type);

    default Optional<ShopHolder> getHolder() {
        return getData("holder", ShopHolder.class);
    }

    default Optional<Shop> getShop() {
        return getData("shop", Shop.class);
    }

    default Optional<ShopItem> getShopItem() {
        return getData("shopItem", ShopItem.class);
    }

    default Optional<String> getId() {
        return getData("id", String.class);
    }

    void putData(String key, Object value);

}
