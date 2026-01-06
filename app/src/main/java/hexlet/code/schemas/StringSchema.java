package hexlet.code.schemas;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class StringSchema {

    private boolean required;
    private String substring;
    private int minLength;

    public StringSchema() {
        this.required = false;
        this.minLength = 0;
    }

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


    public boolean isValid(String s) {

        if (s == null) {
            return !required; // если required = true → false, иначе → true
        }

        if (s.isEmpty() && required) {
            return false;
        }

        if (s.length() < minLength) {
            return false;
        }

        if (substring != null && !s.contains(substring)) {
            return false;
        }

        return true;
    }

}
