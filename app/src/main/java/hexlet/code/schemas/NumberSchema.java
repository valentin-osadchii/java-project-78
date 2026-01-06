package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    private boolean positive;
    private Integer min = null;
    private Integer max = null;


    public NumberSchema positive() {
        this.positive = true;
        return this;
    }


    public NumberSchema range(int min, int max) {
        this.min = min;
        this.max = max;
        return this;
    }

    @Override
    public boolean isValid(Integer value) {
        if (value == null) {
            return !required;
        }

        if (positive && value <= 0) {
            return false;
        }

        if (min != null && value < min) {
            return false;
        }

        if (max != null && value > max) {
            return false;
        }

        return true;
    }
}
