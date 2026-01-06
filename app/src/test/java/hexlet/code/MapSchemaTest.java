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
    public void testDefaultSchema() throws Exception {
        assertTrue(schema.isValid(null));
    }

    @Test
    public void testRequired() throws Exception {
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    public void testSimpleData() throws Exception {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data)); // true
    }

    @Test
    public void testMapSize() throws Exception {
        schema.sizeof(2);

        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        assertFalse(schema.isValid(data)); // false

        data.put("key2", "value2");
        assertTrue(schema.isValid(data)); // true
    }


}
