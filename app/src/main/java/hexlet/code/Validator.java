package hexlet.code;

import hexlet.code.schemas.StringSchema;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class Validator {

    public StringSchema string() {
        return new StringSchema();
    }
}
