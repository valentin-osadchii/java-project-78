package hexlet.code.schemas;

/**
 * Abstract base class for all validation schemas.
 * Provides common functionality such as the 'required' flag.
 *
 * @param <T> the type of value this schema validates
 */
public abstract class BaseSchema<T> {
    protected boolean required = false;

    /**
     * Marks the schema as required (null values are not allowed).
     * Returns this schema for method chaining.
     *
     * @return this instance
     */
    public BaseSchema<T> required() {
        this.required = true;
        return this;
    }

    /**
     * Validates the given value against the schema rules.
     *
     * @param value the value to validate (may be null)
     * @return true if valid, false otherwise
     */
    public abstract boolean isValid(Object value);
}
