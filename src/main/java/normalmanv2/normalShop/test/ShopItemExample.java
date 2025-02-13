package normalmanv2.normalShop.test;

import normalmanv2.normalShop.api.Context;
import normalmanv2.normalShop.common.AbstractShopItem;
import normalmanv2.normalShop.util.ColorUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.security.InvalidParameterException;
import java.util.List;

public class ShopItemExample extends AbstractShopItem {

    public ShopItemExample(String name, double price, String description, NamespacedKey key) {
        super(name, price, description, key);
    }

    @Override
    public ItemStack build(Material material) {

        if (material == Material.AIR) throw new InvalidParameterException("The material cannot be air!");

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta == null) throw new RuntimeException("ItemMeta is null!");

        meta.setDisplayName(ColorUtil.translate(this.getName()));
        List<String> lore = List.of(ColorUtil.translate(this.getDescription()));
        meta.setLore(lore);
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(this.key, PersistentDataType.STRING, this.getName());
        container.set(this.key, PersistentDataType.DOUBLE, this.getPrice());
        container.set(this.key, PersistentDataType.STRING, this.getDescription());
        container.set(this.key, PersistentDataType.INTEGER, this.quantity);
        item.setItemMeta(meta);

        return item;
    }

    @Override
    public void click(InventoryClickEvent event, Context context) {

    }
}
