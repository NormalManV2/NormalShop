package normalmanv2.normalShop.test;

import normalmanv2.normalShop.api.shop.ShopHolder;
import normalmanv2.normalShop.common.AbstractShop;
import normalmanv2.normalShop.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopExample extends AbstractShop {

    public ShopExample(String id, String name, ShopHolder holder, String currencyType) {
        super(id, name, holder, currencyType);
    }

    @Override
    public Inventory openShop(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 36, ColorUtil.translate(this.name()));
        return this.decorate(inventory);
    }

    @Override
    public Inventory decorate(Inventory inventory) {

        for (Integer integer : this.getItems().keySet()) {
            ItemStack item = this.getItems().get(integer).build();
            inventory.setItem(integer, );

        }

        return inventory;
    }
}
