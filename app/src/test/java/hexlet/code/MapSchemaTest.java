package hexlet.code;

import hexlet.code.schemas.MapSchema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MapSchemaTest {

    private MapSchema schema;

    @BeforeEach
    void setSchema() {
        Validator v = new Validator();
        schema = v.map();
    }

    @Test
    void testDefaultSchema() {
        assertTrue(schema.isValid(null));
    }

    @Test
    void testRequired() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void testSimpleData() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data)); // true
    }

    @Test
    void testMapSize() {
        schema.sizeof(2);

        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        assertFalse(schema.isValid(data)); // false

        data.put("key2", "value2");
        assertTrue(schema.isValid(data)); // true
    }


}
