package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Abstract base class for all validation schemas.
 * Provides common functionality such as the 'required' flag.
 *
 * @param <T> the type of value this schema validates
 */
public abstract class BaseSchema<T> {

    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();


    /**
     * Adds custom check to an ordered list of cheks (as Predicates).
     *
     * @param name name of check
     * @param predicate a predicate that checks is provided value is valid
     */
    protected void addCheck(String name, Predicate<T> predicate) {
        checks.put(name, predicate);
    }

    public final boolean isValid(T value) {
        for (Predicate<T> check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a check that a value is not null (default behaviour).
     * Can be overridden to check if a value is not empty or
     * implement other type-specific checks
     *
     * @return validation schema instance (self)
     */
    public BaseSchema<T> required() {
        addCheck("required", value -> value != null);
        return this;
    }

}
