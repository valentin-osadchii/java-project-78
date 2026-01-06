package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    private boolean positive;
    private Integer min = null;
    private Integer max = null;


    public NumberSchema positive() {
        this.positive = true;
        return this;
    }


    public NumberSchema range(int minValue, int maxValue) {
        this.min = minValue;
        this.max = maxValue;
        return this;
    }

    @Override
    public boolean isValid(Object objectValue) {

        if (objectValue == null) {
            return !required;
        }

        if (!(objectValue instanceof Integer)) {
            return false;
        }

        Integer value = (Integer) objectValue;


        if (positive && value <= 0) {
            return false;
        }

        if (min != null && value < min) {
            return false;
        }

        return max == null || value <= max;
    }
}
