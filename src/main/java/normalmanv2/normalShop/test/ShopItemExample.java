package normalmanv2.normalShop.test;

import normalmanv2.normalShop.api.Context;
import normalmanv2.normalShop.api.shop.Shop;
import normalmanv2.normalShop.api.shop.ShopHolder;
import normalmanv2.normalShop.common.AbstractShopItem;
import normalmanv2.normalShop.util.ColorUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public void click(InventoryClickEvent event, Context<?> context) {

        Optional<String> id = context.getId();
        Optional<Shop> shop = context.getShop();
        Optional<ShopHolder> holder = context.getHolder();

        if (id.isEmpty()) return;
        if (shop.isEmpty()) return;
        if (holder.isEmpty()) return;

        UUID uuid = UUID.fromString(id.get());
        String currencyType = shop.get().getCurrencyType();
        ShopHolder shopHolder = holder.get();
        String contextType = context.getType().type();
        event.setCancelled(true);

        if (!shopHolder.isPlayer()) return;

        if (contextType.equalsIgnoreCase("increase")) {
            Optional<Integer> amount = context.getData("amount", Integer.class);
            if (amount.isEmpty()) throw new RuntimeException("Amount is null!");

            this.increaseAmount(amount.get());
        } else if (contextType.equalsIgnoreCase("decrease")) {
            Optional<Integer> amount = context.getData("amount", Integer.class);
            if (amount.isEmpty()) throw new RuntimeException("Amount is null!");

            this.decreaseAmount(amount.get());
        } else if (contextType.equalsIgnoreCase("purchase")) {
            Player player = (Player) event.getWhoClicked();
            ItemStack item = this.build(null, null, null);
            item.setAmount(this.quantity);

            shop.get().openShop(player);
            player.getInventory().addItem(item);
            // NormalEconomy.getInstance().getBank(uuid).modifyBalance(currencyType, this.getPrice());
        } else if (contextType.equalsIgnoreCase("sell")) {
            Player player = (Player) event.getWhoClicked();
            ItemStack item = this.build(null, null, null);
            item.setAmount(this.quantity);

            for (ItemStack itemStack : player.getInventory().getContents()) {
                if (itemStack == null) continue;
                if (itemStack == item) {
                    player.getInventory().removeItem(item);
                    // NormalEconomy.getInstance().getBank(uuid).modifyBalance(currencyType, this.getPrice());
                }
            }


        }


    }
}
