package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    private Integer sizeOfMap = null;
    private Map<String, BaseSchema<?>> shapeSchemas = new HashMap<>();

    public MapSchema sizeof(int size) {
        this.sizeOfMap = size;
        return this;
    }

    @Override
    public boolean isValid(Object value) {

        if (value == null) {
            return !required;
        }

        if (!(value instanceof Map)) {
            return false;
        }

        Map<?, ?> data = (Map<?, ?>) value;

        if ((sizeOfMap != null) && (sizeOfMap != data.size())) {
            return false;
        }

        // считаем, что схема валидации является источником истины.
        // итерируемся по ключам схемы
        for (Map.Entry<String, BaseSchema<?>> entry : shapeSchemas.entrySet()) {
            String key = entry.getKey();
            BaseSchema<?> schema = entry.getValue();

            // если ключа нет в проверямой мапе - это ошибка входных данных
            // => данные невалдины
            if (!data.containsKey(key)) {
                return false;
            }

            //
            Object actualValue = data.get(key);
            if (!schema.isValid(actualValue)) {
                return false;
            }
        }

        return true;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        this.shapeSchemas = new HashMap<>(schemas); // делаем копию
        return this;
    }

}
