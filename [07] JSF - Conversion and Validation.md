# JSF - Conversion and Validation

## Standard Converters

```text
    BigDecimalConverter     javax.faces.BigDecimal
                            - java.math.BigDecimal

    BigIntegerConverter     javax.faces.BigInteger
                            - java.math.BigInteger

    BooleanConverter        javax.faces.Boolean
                            - boolean/java.lang.Boolean

    ByteConverter           javax.faces.Byte
                            - byte/java.lang.Byte

    CharacterConverter      javax.faces.Character
                            - char/java.lang.Character

    DateTimeConverter       javax.faces.DateTime
                            - java.util.Date
                            - java.time.LocalDate         *
                            - java.time.LocalTime         *
                            - java.time.OffsetTime        *
                            - java.time.LocalDateTime     *
                            - java.time.OffsetDateTime    *
                            - java.time.ZonedDateTime     *

    DoubleConverter         javax.faces.Double
                            - double/java.lang.Double

    EnumConverter           javax.faces.Enum 
                            - enum/java.lang.Enum

    FloatConverter          javax.faces.Float
                            - float/java.lang.Float

    IntegerConverter        javax.faces.Integer
                            - int/java.lang.Integer

    LongConverter           javax.faces.Long
                            - long/java.lang.Long

    NumberConverter         javax.faces.Number 
                            - java.lang.Number

    ShortConverter          javax.faces.Short
                            - short/java.lang.Short
```

> **Ejemplo de uso**

```xml
<h:inputText value="#{bean.integers[index]}" converter="javax.faces.Integer">
</h:inputText>
```

## `<f:convertNumber>`

```text
    <f:convertNumber type="number" />
    <f:convertNumber type="currency" />
    <f:convertNumber type="percent" />
```

> **Ejemplo de uso**

```xml
<h:outputText value="#{product.price}">
    <f:convertNumber type="currency" locale="en_US" />
</h:outputText>
```

```text
    en_US   English
    es_ES   Spanish
    fr_FR   French
    de_DE   German
    pt_PT   Portuguese
    zh_CN   Chinese
    ja_JP   Japanese
    ar_AE   Arabic
    ru_RU   Russian
```

> **Otro ejemplo de uso**

```xml
<f:convertNumber type="currency" locale="pt_BR" 
        minFractionDigits="5" maxFractionDigits="5" />
```

## `<f:convertDateTime>`

```text
    <f:convertDateTime type>

    date (default)  java.util.Date
    time            java.util.Date
    both            java.util.Date
    localDate       java.time.LocalDate         *
    localTime       java.time.LocalTime         *
    localDateTime   java.time.LocalDateTime     *
    offsetTime      java.time.OffsetTime        *
    offsetDateTime  java.time.OffsetDateTime    *
    zonedDateTime   java.time.ZonedDateTime     *
```

> **Ejemplo de uso**

```xml
<h:inputText id="date" value="#{bean.date}">
    <f:convertDateTime type="date" pattern="yyyy-MM-dd" />
</h:inputText>

<h:inputText id="time" value="#{bean.time}">
    <f:convertDateTime type="time" pattern="HH:mm:ss" />
</h:inputText>

<h:inputText id="both" value="#{bean.both}">
    <f:convertDateTime type="both" pattern="yyyy-MM-dd HH:mm:ss" />
</h:inputText>
```

```text
    Date patterns

    Symbol  Meaning                     Presentation      Examples
    ------  -------                     ------------      -------
    u       year                        year              2004; 04
    y       year-of-era                 year              2004; 04
    M/L     month-of-year               number/text       7; 07; Jul; July; J
    d       day-of-month                number            10
    D       day-of-year                 number            (1-366)
    w       week-of-year                number            (1-53)
    W       week-of-month               number            (0-5)
    E       day-of-week                 text              Monday; Mon;
    F       day-of-week-in-month        text              1st Thursday of December

    Time patterns

    Symbol  Meaning                     Presentation      Examples
    ------  -------                     ------------      -------
    H       hour-of-day (0-23)          number            0
    h       hour-of-day (1-12)          number            11
    a       AM/PM                       text              PM
    m       minute-of-hour              number            30
    s       second-of-minute            number            55
    S       fraction-of-second          fraction          978
    n       nano-of-second              number            987654321

    EEEEE MMMMM yyyy HH:mm:ss.SSSZ -> Wednesday November 2017 18:47:42.787+0530
```

## Standard Validators

```text
    LongRangeValidator          javax.faces.LongRange 
        <f:validateLongRange>   - java.lang.Number

    DoubleRangeValidator        javax.faces.DoubleRange 
        <f:validateDoubleRange> - java.lang.Number

    LengthValidator             javax.faces.Length 
        <f:validateLength>      - java.lang.Object

    RegexValidator              javax.faces.RegularExpression
        <f:validateRegex>       - java.lang.String

    RequiredValidator           javax.faces.Required 
        <f:validateRequired>    - java.lang.Object

    BeanValidator               javax.faces.Bean 
        <f:validateBean>        - java.lang.Object

    n/a                         n/a
        <f:validateWholeBean>   - java.lang.Object *
```

## `<f:validateLongRange>/<f:validateDoubleRange>`

```xml
<h:inputText value="#{bean.quantity}">
    <f:validateLongRange minimum="1" maximum="10" />
</h:inputText>

<h:inputText value="#{bean.quantity}" a:type="number" a:min="1" a:max="10">
    <f:validateLongRange minimum="1" maximum="10" />
</h:inputText>

<h:inputText value="#{bean.volume}" a:type="range" a:min="1" a:max="10" a:step="0.1">
    <f:validateLongRange minimum="1" maximum="10" />
</h:inputText>

<h:inputText value="#{bean.volume}" a:type="range" a:min="0.1" a:max="10.0" a:step="0.1">
    <f:validateDoubleRange minimum="0.1" maximum="10.0" />
</h:inputText>
```

## `<f:validateLength>/<f:validateRegex>`

```xml
<!-- Object#toString() | String#length() / String#matches() -->

<h:inputText value="#{bean.someStringOrInteger}" maxlength="3">
    <f:validateLength minimum="3" maximum="3" />
</h:inputText>

<h:inputText value="#{bean.someString}" maxlength="3">
    <f:validateRegex pattern="[0-9]{3}" />
</h:inputText>

<h:inputText value="#{bean.someInteger}" maxlength="3">
    <f:validateLongRange minimum="100" maximum="999" />
</h:inputText>

<h:inputText value="#{bean.someString}" maxlength="3">
    <f:validateRegex pattern="\\d{3}" />
</h:inputText>
```

```text
    Regex Character classes

    No. Character Class     Description
    1   [abc]               a, b, or c (simple class)
    2   [^abc]              Any character except a, b, or c (negation)
    3   [a-zA-Z]            a through z or A through Z, inclusive (range)
    4   [a-d[m-p]]          a through d, or m through p: [a-dm-p] (union)
    5   [a-z&&[def]]        d, e, or f (intersection)
    6   [a-z&&[^bc]]        a through z, except for b and c: [ad-z] (subtraction)
    7   [a-z&&[^m-p]]       a through z, and not m through p: [a-lq-z](subtraction)

    Regex   Description
    X?      X occurs once or not at all
    X+      X occurs once or more times
    X*      X occurs zero or more times
    X{n}    X occurs n times only
    X{n,}   X occurs n or more times
    X{y,z}  X occurs at least y times but less than z times

    Regex Metacharacters

    Regex   Description
    .       Any character (may or may not match terminator)
    \\d     Any digits, short of [0-9]
    \\D     Any non-digit, short for [^0-9]
    \\s     Any whitespace character, short for [\\t\\n\\x0B\\f\\r]
    \\S     Any non-whitespace character, short for [^\\s]
    \\w     Any word character, short for [a-zA-Z_0-9]
    \\W     Any non-word character, short for [^\\w]
    \\b     A word boundary
    \\B     A non word boundary
```

## Custom Converters

Anotación:  `@FacesConverter(forClass=Product.class, managed=true)`

Interfaz:   `Converter<Product>` (`javax.faces.convert.Converter`)

Excepción:  `throw new ConverterException(new FacesMessage("Invalid product ID"), e)`

Implementaciones:

```text
    @Override
    public String getAsString(FacesContext context, 
                              UIComponent component, 
                              Product product) { ... }

    @Override
    public Product getAsObject(FacesContext context, 
                               UIComponent component, 
                               String id) { ... }
```

> **Ejemplos de uso**

```xml
<f:viewParam name="id" value="#{bean.product}"
             converter="ProductConverter"
             required="true" 
             requiredMessage="Bad request">
</f:viewParam>
```

## Custom Validators

Anotación: `@FacesValidator(value="UniqueEmailValidator", managed=true)`

Interfaz:  `Validator<String>` (`javax.faces.validator.Validator`)

Excepción: `throw new ValidatorException(new FacesMessage("Email already in use"))`

Implementaciones:

```text
    @Override
    public void validate(FacesContext context, 
                         UIComponent component, 
                         String email) throws ValidatorException { ... }
```

Nota: `String oldEmail = (String) ((UIInput) component).getValue();`

Pista: `if (!email.equals(oldEmail) && userService.exist(email)) { throw ... }`

> **Ejemplo de uso**

```xml
<h:inputText value="#{bean.email}" validator="UniqueEmailValidator">
</h:inputText>
```

## Custom Constraints

> **@Phone**

```java
@Constraint(validatedBy=PhoneValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface Phone {
    String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
```

> **PhoneValidator**

```java
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final Pattern SPECIAL_CHARS = Pattern.compile(
        "[\\s().+-]|ext",
        Pattern.CASE_INSENSITIVE
    );

    private static final Pattern DIGITS = Pattern.compile("[0-9]{7,15}");

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null || phone.isEmpty()) {
            return true; // Let @NotNull/@NotEmpty handle this.
        }
        return isValid(phone);
    }

    public static boolean isValid(String phone) {
        String digits = SPECIAL_CHARS.matcher(phone).replaceAll("");
        return DIGITS.matcher(digits).matches();
    }

}
```

> **Ejemplo de uso**

```java
@Phone
private String phone;
```

## Custom Messages

```xml
<h:inputText id="field" label="First input" value="#{bean.field}" 
             required="true"></h:inputText>
<h:message for="field" />

<h:inputText id="notNullField" label="Second input"
             value="#{bean.notNullField}"></h:inputText>
<h:message for="notNullField" />
```

```java
FacesContext context = FacesContext.getCurrentInstance();
FacesMessage message = new FacesMessage("Not null field error");
context.addMessage("myForm:notNullField", message);
```
