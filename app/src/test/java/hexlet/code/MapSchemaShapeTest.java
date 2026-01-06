package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaShapeTest {

    private Validator v;
    private MapSchema schema;

    @BeforeEach
    void setUp() {
        v = new Validator();
        schema = v.map();
    }

    @Test
    void testValidObjectPassesShapeValidation() {
        Map<String, BaseSchema<String>> shape = new HashMap<>();
        shape.put("firstName", v.string().required());
        shape.put("lastName", v.string().required().minLength(2));

        schema.shape(shape);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", "Smith");

        assertTrue(schema.isValid(human));
    }

    @Test
    void testNullValueInRequiredFieldFails() {
        Map<String, hexlet.code.schemas.BaseSchema<String>> shape = new HashMap<>();
        shape.put("firstName", v.string().required());
        shape.put("lastName", v.string().required().minLength(2));

        schema.shape(shape);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", null);

        assertFalse(schema.isValid(human));
    }

    @Test
    void testValueFailsMinLengthConstraint() {
        Map<String, hexlet.code.schemas.BaseSchema<String>> shape = new HashMap<>();
        shape.put("firstName", v.string().required());
        shape.put("lastName", v.string().required().minLength(2));

        schema.shape(shape);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "Anna");
        human.put("lastName", "B"); // длина = 1

        assertFalse(schema.isValid(human));
    }

    @Test
    void testMissingRequiredKeyFails() {
        Map<String, hexlet.code.schemas.BaseSchema<String>> shape = new HashMap<>();
        shape.put("firstName", v.string().required());
        shape.put("lastName", v.string().required());

        schema.shape(shape);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        // ключ "lastName" отсутствует

        assertFalse(schema.isValid(human));
    }

    @Test
    void testExtraKeysAreIgnored() {
        Map<String, hexlet.code.schemas.BaseSchema<String>> shape = new HashMap<>();
        shape.put("name", v.string().required());

        schema.shape(shape);

        Map<String, String> data = new HashMap<>();
        data.put("name", "Alice");
        data.put("age", "30"); // лишний ключ — игнорируется

        assertTrue(schema.isValid(data));
    }
}
