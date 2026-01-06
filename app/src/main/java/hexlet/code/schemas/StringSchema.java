package hexlet.code.schemas;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public final class StringSchema extends BaseSchema<String> {

    private String substring;
    private int minLength;

    public StringSchema() {
        this.required = false;
        this.minLength = 0;
    }

    @Override
    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema contains(String s) {
        this.substring = s;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    @Override
    public boolean isValid(Object value) {

        if (value == null) {
            return !required;
        }

        if (!(value instanceof String)) {
            return false;
        }

        String s = (String) value;


        if (s.isEmpty() && required) {
            return false;
        }

        if (s.length() < minLength) {
            return false;
        }

        return substring == null || s.contains(substring);
    }

}
