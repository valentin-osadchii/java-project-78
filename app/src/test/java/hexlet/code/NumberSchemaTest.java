package hexlet.code;

import hexlet.code.schemas.NumberSchema;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    private NumberSchema schema;

    @BeforeEach
    void setSchema() {
        Validator v = new Validator();
        schema = v.number();
    }

    @Test
    void testDefaultValidator() throws Exception {
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));
    }


    @Test
    void testRequired() throws Exception {
        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
    }

    @Test
    void testPositive() throws Exception {
        schema.positive();

        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    void testRange() throws Exception {
        schema.range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }


    @Test
    void testPositiveAndRangeCombined() {
        schema.positive().range(1, 100);

        assertTrue(schema.isValid(50));
        assertFalse(schema.isValid(0));     // не положительное
        assertFalse(schema.isValid(101));   // вне диапазона
        assertFalse(schema.isValid(-5));    // ни то, ни другое
    }

}
