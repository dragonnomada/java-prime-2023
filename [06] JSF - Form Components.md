# JSF - Form Components

## Text-Based Input Components

```xml
<h:form>
    <h:inputText value="#{bean.text}" />
    <h:inputSecret value="#{bean.password}" />
    <h:inputTextarea value="#{bean.message}" />
    <h:inputHidden value="#{bean.hidden}" />
    <h:commandButton value="Submit" action="#{bean.submit}" />
</h:form>
```

> **Usando IDs**

```xml
<h:form id="form">
    <h:inputText id="text" value="#{bean.text}" />
    <h:inputSecret id="password" value="#{bean.password}" />
    <h:inputTextarea id="message" value="#{bean.message}" />
    <h:inputHidden id="hidden" value="#{bean.hidden}" />
    <h:commandButton id="submit" value="Submit" action="#{bean.submit}" />
</h:form>
```

**Ejercicio:** Compara la diferencia en el código de salida de HTML.

## File-Based Input Component

```xml
<h:form id="form" enctype="multipart/form-data">
    <h:inputFile id="file" value="#{bean.file}" />
    <h:commandButton id="submit" value="Submit" action="#{bean.submit}" />
</h:form>
```

> **Bean**

```java
private Part file;

public void submit() throws IOException {
    logger.info("Form has been submitted!");
    logger.info("file: " + file);

    if (file != null) {
        logger.info("name: " + file.getSubmittedFileName());
        logger.info("type: " + file.getContentType());
        logger.info("size: " + file.getSize());

        InputStream content = file.getInputStream();
        
        // Write content to disk or DB.
    }
}
```

> **Ejercicio:** Muestra el contenido de un archivo de texto en el `Logger`.

## Selection Components

```xml
<h:form id="form">
    <h:selectBooleanCheckbox id="checked" value="#{bean.checked}" />
    <h:selectOneMenu id="oneMenu" value="#{bean.oneMenu}">
        <f:selectItems value="#{bean.availableItems}" />
    </h:selectOneMenu>
    <h:selectOneRadio id="oneRadio" value="#{bean.oneRadio}">
        <f:selectItems value="#{bean.availableItems}" />
    </h:selectOneRadio>
    <h:selectManyListbox id="manyListbox" value="#{bean.manyListbox}">
        <f:selectItems value="#{bean.availableItems}" />
    </h:selectManyListbox>
    <h:selectManyCheckbox id="manyCheckbox" value="#{bean.manyCheckbox}">
        <f:selectItems value="#{bean.availableItems}" />
    </h:selectManyCheckbox>
    <h:commandButton id="submit" value="Submit" action="#{bean.submit}" />
</h:form>
```

> **Bean**

```java
private boolean checked;
private String oneMenu;
private String oneRadio;
private List<String> manyListbox;
private List<String> manyCheckbox;
private List<String> availableItems;

@PostConstruct
public void init() {
    availableItems = Arrays.asList("one", "two", "three");
}

public void submit() {
    logger.info("Form has been submitted!");
    logger.info("checked: " + checked);
    logger.info("oneMenu: " + oneMenu);
    logger.info("oneRadio: " + oneRadio);
    logger.info("manyListbox: " + manyListbox);
    logger.info("manyCheckbox: " + manyCheckbox);
}
```

> **Ejercicio:** Crea los selectores necesarios para configurar un platillo de comida rápida, considera tipo de salsa, si lleva ensalada, tipo de agua de sabor, ingredientes extras, etc.

## SelectItem Tags

```xml
<h:selectOneMenu id="selectedItem" value="#{bean.selectedItem}">
    <f:selectItem itemValue="#{null}" itemLabel="-- select one --" />
    <f:selectItem itemValue="one" itemLabel="First item" />
    <f:selectItem itemValue="two" itemLabel="Second item" />
    <f:selectItem itemValue="three" itemLabel="Third item" />
</h:selectOneMenu>
```

> **Condensado**

```xml
<h:selectOneMenu id="selectedItem" value="#{bean.selectedItem}">
    <f:selectItem itemValue="#{null}" itemLabel="-- select one --" />
    <f:selectItems value="#{bean.availableItems}" />
</h:selectOneMenu>
```

> **Bean**

```java
private Map<String, String> availableItems;

@PostConstruct
public void init() {
    availableItems = new LinkedHashMap<>();
    availableItems.put("First item", "one");
    availableItems.put("Second item", "two");
    availableItems.put("Third item", "three");
}
```

> **Usando `<f:selectItems>`**

```xml
<f:selectItems value="#{bean.availableItems.entrySet()}" var="entry"
    itemValue="#{entry.key}" itemLabel="#{entry.value}">
</f:selectItems>
```

> **Ejercicio:** Crea una lista con nombres de paises y el código del país.

## SelectItemGroup

```java
private List<SelectItem> availableItems;

@PostConstruct
public void init() {
    SelectItemGroup group1 = new SelectItemGroup("Group 1");
    
    group1.setSelectItems(new SelectItem[] {
        new SelectItem("Group 1 Value 1", "Group 1 Label 1"),
        new SelectItem("Group 1 Value 2", "Group 1 Label 2"),
        new SelectItem("Group 1 Value 3", "Group 1 Label 3")
    });

    SelectItemGroup group2 = new SelectItemGroup("Group 2");

    group2.setSelectItems(new SelectItem[] {
        new SelectItem("Group 2 Value 1", "Group 2 Label 1"),
        new SelectItem("Group 2 Value 2", "Group 2 Label 2"),
        new SelectItem("Group 2 Value 3", "Group 2 Label 3")
    });

    availableItems = Arrays.asList(group1, group2);
}
```

> **Usando `<h:selectOneMenu>`**

```xml
<h:selectOneMenu id="oneMenu" value="#{bean.oneMenu}">
    <f:selectItems value="#{bean.availableItems}" />
</h:selectOneMenu>
```

> **Ejercicio:** Prueba los grupos con `<h:selectOneRadio layout="pageDirection">`, `<h:selectManyListbox>` y `<h:selectManyCheckbox layout="pageDirection">`, luego cambia el `layout="pageDirection"` por `layout="lineDirection"`.

## Label and Message Components

```xml
<h:form id="login">
    <fieldset>
        <legend>Login</legend>
        <section>
            <h:outputLabel for="email" value="Email address" />
            <h:inputText id="email" value="#{login.email}" required="true" />
            <h:message id="m_email" for="email" />
        </section>
        <section>
            <h:outputLabel for="password" value="Password" />
            <h:inputSecret id="password" value="#{login.password}" required="true" />
            <h:message id="m_password" for="password" />
        </section>
        <footer>
            <h:commandButton id="submit" value="Login" action="#{login.submit}" />
            <h:message id="m_submit" for="submit" />
        </footer>
    </fieldset>
</h:form>
```

> **Ver todos los mensajes**

```xml
<h:messages id="messages" globalOnly="true" />
```

* **Nota:** Para poner un mensaje global usamos `context.addMessage(null, message);`

> **Bean**

```java
FacesContext context = FacesContext.getCurrentInstance();

FacesMessage message = new FacesMessage("Authentication failed");

context.addMessage("login:submit", message);
```

> **Ejercicio:** Simula un inicio de sesión que devuelva los mensajes necesarios para algunas validaciones simples sobre correo y la contraseña del usuario, así como si pudo o no iniciar sesión.

## Command Components

```xml
<h:commandButton ...>
    <f:actionListener type="com.example.project.SomeActionListener" />
    <f:actionListener binding="#{beanImplementingActionListener}" />
    <f:actionListener binding="#{bean.someActionListenerMethod()}" />
</h:commandButton>
```

> **Usando `ActionListener`**

```java
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class SomeActionListener implements ActionListener {

    @Override
    public void processAction(ActionEvent event) {
        // ...
    }
  
}
```

> **Implementando `ActionListener` sobre un Bean**

```java
@Named
@RequestScoped
public class BeanImplementingActionListener implements ActionListener {

    @Override
    public void processAction(ActionEvent event) {
        // ...
    }

}
```

> **Llamando directamente el método de un Bean**

```java
@Named
@RequestScoped
public class Bean {

    public void someActionListenerMethod() {
        // ...
    }
  
}
```

> **Enviar un parámetro asociado**

```xml
<h:commandButton id="submit" value="Submit" action="#{bean.submit}">
    <f:param name="id" value="#{otherBean.id}" />
</h:commandButton>
```

> **Recibiendo el parámetro asociado en el Bean**

```java
@Inject 
@ManagedProperty("#{param.id}")
private Integer id;

public void submit() {
    logger.info("Submitted ID: " + id);
}
```

> **Alternativa para recibir un valor en el Bean**

```xml
<h:commandButton id="submit" value="Submit" action="#{bean.submit(otherBean.id)}">
</h:commandButton>
```

* **Nota:** El valor será recibido directamente sobre el método `public void submit(Integer id)`.

> **Generar un comando reutilizable en JavaScript**
>
> **NOTA:** Solo disponible para *JSF 2.3*

```xml
<h:form id="form">
    <h:commandScript id="submit" name="invokeBeanSubmit" action="#{bean.submit}">
    </h:commandScript>
</h:form>
```

* **Nota:** Desde JavaScript podremos llamar a `invokeBeanSubmit(params)`, donde `params` puede no ser pasado o ser un objeto con los parámetros equivalentes a `<f:param>`.

> **Ejercicio:** Crea un comando que envie el valor de dos beans diferentes.

## Navigation

```java
public String someActionMethod() {
    // ...
    return "someOutcome";
}
```

> **Configuramos la regla de navegación en `faces-config.xml`**

```xml
<navigation-rule>
    <navigation-case>
        <from-outcome>someOutcome</from-outcome>
        <to-view-id>/otherview.xhtml</to-view-id>
    </navigation-case>
</navigation-rule>
```

> **Alternativamente**

```java
public String someActionMethod() {
    // ...
    return "/otherview.xhtml";
    // return "/otherview";
    // return "/otherview?faces-redirect=true";
}
```

> **Ejercicio:** Define una navegación de una página a otra y viceversa.

## Ajaxifying Components

Etiqueta: `<f:ajax>`

Se logra con la interfaz `ClientBehaviorHolder`.

    Componentes que implementan `ClientBehaviorHolder`

    HtmlBody, HtmlCommandButton, HtmlCommandLink, HtmlDataTable, HtmlForm,
    HtmlGraphicImage, HtmlInputFile, HtmlInputSecret, HtmlInputText,
    HtmlInputTextarea, HtmlOutcomeTargetButton, HtmlOutcomeTargetLink,
    HtmlOutputLabel, HtmlOutputLink, HtmlPanelGrid, HtmlPanelGroup,
    HtmlSelectBooleanCheckbox, HtmlSelectManyCheckbox,
    HtmlSelectManyListbox, HtmlSelectManyMenu, HtmlSelectOneListbox,
    HtmlSelectOneMenu, HtmlSelectOneRadio, UIWebsocket

    ---

    <h:body>, <h:commandButton>, <h:commandLink>,
    <h:dataTable>, <h:form>, <h:graphicImage>, <h:inputFile>, <h:inputSecret>,
    <h:inputText>, <h:inputTextarea>, <h:button>, <h:link>, <h:outputLabel>,
    <h:outputLink>, <h:panelGrid>, <h:panelGroup>, <h:selectBooleanCheckbox>,
    <h:selectManyCheckbox>, <h:selectManyListbox>, <h:selectManyMenu>,
    <h:selectOneListbox>, <h:selectOneMenu>, <h:selectOneRadio> and <f:websocket>

* **Importante:** Se requiere estar contenido en `<h:form>` y `<h:head>` debe estar presente ya que incluye a `jsf.js` que contiene la función principal `jsf.
ajax.request()`.

> **Ejemplo de uso:**

```xml
<h:head id="head">
    <title>f:ajax demo</title>
</h:head>

<h:body>
    <h:form id="form">
        <h:inputText id="text" value="#{bean.text}">
            <f:ajax />
        </h:inputText>
        <h:commandButton id="submit" value="Submit" action="#{bean.submit}">
            <f:ajax execute="@form" />
        </h:commandButton>
    </h:form>
</h:body>
```

En `<f:ajax>` el `execute` será el componente que se actualizará en la **segunda fase** (*Apply Request Values Phase*) y encolará el `AjaxBehaviorEvent` en la **quinta fase** (*Invoke Application Phase*).

* **Nota**: Los alcances son: `@all`, `@this`, `@form` y `@none`, donde `@form` es el `<h:form>` más cercano.

> **Interceptar un evento con Ajax**

```xml
<h:inputText ...>
    <f:ajax event="blur" />
</h:inputText>
```

* **Nota:** Algunos eventos de `<h:inputText>` (`<input>`) son *blur*, *change*, *click*, *dblclick*, *focus*, *keydown*, *keypress*, *keyup*, *mousedown*, *mousemove*, *mouseout*, *mouseover*, *mouseup*, *select*.

> **Asociar varios `listeners` a los Beans**

```xml
<h:inputText id="foo" ...>
    <f:ajax listener="#{bean.onchangeFoo}" />
    <f:ajax listener="#{otherBean.onchangeFoo}" />
</h:inputText>
```

> **Manejo del evento en el Bean**

```java
public void onchangeFoo(AjaxBehaviorEvent event) {
    // ...
}
```

> **Actualizar un objetivo**

```xml
<f:ajax execute="@form" render="m_text" />
```

> **Ejercicio:** Intercepta el evento cuando un selector cambia su valor.

## Navigation in Ajax

```java
public void ajaxListener(AjaxBehaviorEvent event) {
    // ...
    String outcome = "/otherview?faces-redirect=true";

    FacesContext context = FacesContext.getCurrentInstance();
    
    Application application = context.getApplication();
    
    NavigationHandler handler = application.getNavigationHandler();
    
    handler.handleNavigation(context, null, outcome);
}
```

> **Alternativamente**

```java
public void ajaxListener(AjaxBehaviorEvent event) throws IOException {
    // ...
    
    String path = "/otherview.xhtml";
    
    FacesContext context = FacesContext.getCurrentInstance();
    
    ExternalContext externalContext = context.getExternalContext();
    
    String uri = externalContext.getRequestContextPath() + path;
    
    externalContext.redirect(uri);
}
```

> **Ejercicio:** Crea una navegación de una página a otra via Ajax.

## GET forms

```xml
<f:metadata>
    <f:viewParam id="query" name="query" value="#{search.query}" />
    <f:viewAction action="#{search.onload}" />
</f:metadata>

<h:body>
    <form>
        <label for="query">Query</label>
        <input type="text" name="query" value="#{empty search.query ? param.query : search.query}">
        </input>
        <input type="submit" value="Search" />
        <h:message for="query" />
    </form>
    <h:dataTable id="results" rendered="#{not empty search.results}" value="#{search.results}" var="result">
        <h:column>#{result.name}</h:column>
        <h:column>#{result.description}</h:column>
    </h:dataTable>
</h:body>
```

> **Bean**

```java
@Named 
@RequestScoped
public class Search {
    
    private String query;
    private List<Result> results;
    
    @Inject
    private SearchService searchService;
    
    public void onload() {
        results = searchService.getResults(query);
    }
    
}
```

> **Ejercicio:** Implementa un servicio ficticio de búsqueda de frutas.

## Stateless Forms

```xml
<f:view transient="true">
    <h:form id="login">
        ...
    </h:form>
</f:view>
```