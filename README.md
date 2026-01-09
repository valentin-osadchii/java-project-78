### Hexlet tests and linter status:
[![Actions Status](https://github.com/valentin-osadchii/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/valentin-osadchii/java-project-78/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=valentin-osadchii_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=valentin-osadchii_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=valentin-osadchii_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=valentin-osadchii_java-project-78)


# Java Validator

Учебный проект: гибкий валидатор данных для строк, чисел и объектов Map с поддержкой цепочки вызовов (fluent interface).

## Функциональность

### 1. Валидация строк (StringSchema)
- required() — запрещает null и пустые строки.
- minLength(int length) — задает минимальную длину строки.
- contains(String substring) — проверяет наличие подстроки.

Пример:
```
var v = new Validator();
var schema = v.string().required().minLength(5).contains("hex");
schema.isValid("hexlet"); // true
schema.isValid("java");   // false (меньше 5 символов)
```

---

### 2. Валидация чисел (NumberSchema)
- required() — запрещает null.
- positive() — разрешает только положительные числа (0 не входит).
- range(int min, int max) — ограничивает диапазон значений (включительно).

Пример:
```
var schema = v.number().required().positive().range(5, 10);
schema.isValid(7);   // true
schema.isValid(0);   // false (не положительное)
schema.isValid(12);  // false (вне диапазона)
```

---

### 3. Валидация объектов Map (MapSchema)
- required() — запрещает null, требует тип Map.
- sizeof(int size) — проверяет точное количество пар ключ-значение.
- shape(Map<String, BaseSchema<?>> schemas) — вложенная валидация значений по ключам.

Пример:
```
var schemas = new HashMap<>();
schemas.put("name", v.string().required().minLength(4));
schemas.put("age", v.number().positive());

var schema = v.map().required().shape(schemas);
var data = Map.of("name", "Anna", "age", 30);
schema.isValid(data); // true
```

---

## Особенности
- Цепочка вызовов: Методы настройки схем можно комбинировать:  
  v.string().required().minLength(3).contains("a").
- Приоритет методов: Последующие вызовы одного метода перезаписывают предыдущие:  
  schema.minLength(10).minLength(4) → действует ограничение 4.
- Валидация по умолчанию: Без required() null и пустые значения считаются валидными.

## Использование
1. Создайте валидатор:  
   `Validator v = new Validator();`
2. Настройте схему:  
   `StringSchema schema = v.string().required().minLength(5);`
3. Проверьте данные:  
   `boolean isValid = schema.isValid("hello");`