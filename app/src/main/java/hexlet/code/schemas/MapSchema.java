package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>>{

    private Integer sizeOfMap = null;


    public MapSchema sizeof(int size) {
        this.sizeOfMap = size;
        return this;
    }

    @Override
    public boolean isValid(Map<?, ?> value) {
        if (value == null) {
            return !required;
        }

        if ((sizeOfMap != null) && (sizeOfMap != value.size()) ) {
            return false;
        }
        return true;
    }
}
