package normalmanv2.normalShop.common;

import normalmanv2.normalShop.api.shop.ShopItem;
import normalmanv2.normalShop.util.ColorUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractShopItem implements ShopItem {

    private final String name;
    private final double price;
    private final String description;
    protected int quantity;
    protected final NamespacedKey key;

    public AbstractShopItem(String name, double price, String description, NamespacedKey key) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = 0;
        this.key = key;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.quantity * this.price;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void increaseAmount(int amount) {
        this.quantity += amount;
    }

    @Override
    public boolean decreaseAmount(int amount) {
        return (this.quantity -= amount) >= 0;
    }

    @Override
    public abstract ItemStack build(Material material);

    @Override
    public ItemStack build(Material material, String displayName, String... lore) {

        if (material == Material.AIR) throw new InvalidParameterException("The material cannot be air!");

        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();

        if (meta == null) throw new RuntimeException("ItemMeta is null!");

        meta.setDisplayName(ColorUtil.translate(displayName));
        List<String> loreList = List.of(ColorUtil.translate(Arrays.toString(lore)));
        meta.setLore(loreList);

        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(this.key, PersistentDataType.DOUBLE, this.price);
        container.set(this.key, PersistentDataType.INTEGER, this.quantity);
        container.set(this.key, PersistentDataType.STRING, this.description);
        container.set(this.key, PersistentDataType.STRING, this.name);

        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
