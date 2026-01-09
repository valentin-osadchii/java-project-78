package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> value.size() == size);
        return this;
    }


    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {

        Map<String, BaseSchema<?>> copiedSchemas = new HashMap<>(schemas);

        addCheck("shape", map -> {
            if (map == null) {
                return true;
            }
            for (Map.Entry<String, BaseSchema<?>> entry : copiedSchemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();

                if (!map.containsKey(key)) {
                    return false;
                }

                Object value = map.get(key);
                BaseSchema<Object> objectSchema = (BaseSchema<Object>) schema;

                if (!objectSchema.isValid(value)) {
                    return false;
                }

            }
            return true;
        });
        return this;
    }

}
