package normalmanv2.normalShop;

import normalmanv2.normalShop.api.Context;
import normalmanv2.normalShop.api.ContextType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ShopContext implements Context<ContextType> {
    private final ContextType contextType;
    private final Map<String, Object> data;

    public ShopContext(ContextType contextType) {
        this.contextType = contextType;
        this.data = new HashMap<>();
    }

    @Override
    public ContextType getType() {
        return this.contextType;
    }

    @Override
    public <Z> Optional<Z> getData(String key, Class<Z> type) {
        Object value = this.data.get(key);
        if (type.isInstance(value)) {
            return Optional.of(type.cast(value));
        }
        return Optional.empty();
    }

    public <Z> void putData(String key, Z value) {

        this.data.put(key, value);
    }
}
