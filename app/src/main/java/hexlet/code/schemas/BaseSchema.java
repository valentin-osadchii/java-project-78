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
     * Adds custom check to an ordered list of cheks (as Predicates)
     *
     * @param name
     * @param predicate
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

    public BaseSchema<T> required() {
        addCheck("required", value -> value != null);
        return this;
    }

}
