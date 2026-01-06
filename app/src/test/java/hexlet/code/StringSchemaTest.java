package hexlet.code;

import hexlet.code.schemas.StringSchema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringSchemaTest {

    private StringSchema schema;

    @BeforeEach
    void setSchema() {
        Validator v = new Validator();
        schema = v.string();
    }

    @Test
    void testDefaultValidator() throws Exception {
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testRequired() throws Exception {
        schema.required();

        assertFalse(schema.isValid(null));// false
        assertFalse(schema.isValid(""));// false
        assertTrue(schema.isValid("what does the fox say"));// true
        assertTrue(schema.isValid("hexlet")); // true
    }


    @Test
    void testContains() throws Exception {
        assertTrue(schema.contains("wh").isValid("what does the fox say"));// true
        assertTrue(schema.contains("what").isValid("what does the fox say"));// true
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));// false
        assertFalse(schema.isValid("what does the fox say")); // false
    }

    @Test
    void testMethodChaining() throws Exception {
        assertTrue(schema.minLength(10).minLength(4).isValid("Hexlet")); // true
    }
}
