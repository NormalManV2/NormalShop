package normalmanv2.normalShop.util;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ColorUtil {

    private static final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer
            .builder()
            .hexCharacter('#')
            .hexColors()
            .useUnusualXRepeatedCharacterHexFormat()
            .build();
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    public static String translate(String text) {
        return LEGACY_SERIALIZER.serialize(MINI_MESSAGE.deserialize(text));
    }

}
