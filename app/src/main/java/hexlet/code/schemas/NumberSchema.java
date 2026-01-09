package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        addCheck("positive", value -> (value == null || value > 0));
        return this;
    }

    public NumberSchema range(int minValue, int maxValue) {
        addCheck("range", value -> (value >= minValue && value <= maxValue));
        return this;
    }

}
