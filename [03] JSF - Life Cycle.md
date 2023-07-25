# JSF - Life Cycle

    - Restore View Phase (First Phase)
    - Apply Request Values Phase (Second Phase)
    - Process Validations Phase (Third Phase)
    - Update Model Values Phase (Fourth Phase)
    - Invoke Application Phase (Fifth Phase)
    - Render Response Phase (Sixth Phase)

## Restore View Phase (First Phase)

El `UIViewRoot` está vacío.

Una subclase de `UIComponent` es ajustada con `UIComponent#setParent()`

Si `UIComponent` tienía un padre ejecuta `PreRemoveFromViewEvent` en el padre anterior.

El `UIComponent` se vuelve parte del árbol.

El `UIComponent` dispara `PostAddToViewEvent`.

Si la petición es `postback` se restaurará el *view state* identificado por el parámetro de la petición `javax.faces.ViewState`.

Si la petición es `postback` se lanzará el evento `PostRestoreStateEvent`.

Al final de la fase si el árbol completo aún no ha sido construido, entonces salta a la **sexta fase** (*Render Response Phase*). 

## Apply Request Values Phase (Second Phase)

Se invocará `UIComponent#processDecodes()` desde el `UIViewRoot`.

Se aplica `processDecodes()` primero sobre cada hijo y *facet*.

Finalmente se aplica `decode()` sobre el componente.

El `decode()` usa por defecto `Render#decode()`.

El `decode()` tiene la oportunidad de recuperar parámetro `value` enviado desde la petición.

En los componentes HTML solo los derivados de `UIForm`, `UIInput` y `UICommand` pueden.

El componente `UIForm` invocará `UIForm#setSubmitted()` con `true`.

El componente `UIInput` invocará `UIInput#setSubmittedValue()` con el parámetro `value`.

El componente `UICommand` encolará el `ActionEvent` para invocarse en la **quinta fase** (*Invoke Application Phase*).

## Process Validations Phase (Third Phase)

Se invocará `UIComponent#processValidators()` desde el `UIViewRoot`.

Se disparará `PreValidateEvent` sobre el componente.

Se invocará `processValidators()` sobre cada hijo y *facet*.

Los hijos y *facet* invocarán `PostValidateEvent` sobre el componente.

Finalmente se invocará `UIViewRoot#broadCastEvents()` para disparar cualquier `FacesEvent` encolado para la fase actual (generalmente una instancia de `ValueChangeEvent`).

En los componentes HTML, los `UIInput` se comportan diferente aquí.

> Antes de llamar al `processValidators()` en cada hijo y *facet*, se invocará `UIInput#validate()` en el componente.

Si hay un valor enviado durante la **segunda fase** (*Apply Request Values Phase*), entonces se invocará primero a `Converter#getAsObject()` en cualquier `Conveter` asociado.

Si el `Converter` no arroja `ConverterException` se invocará `Validator#validate()` en todas las instancias de `Validator` asociadas.

Cuando no se arrojan `ConverterException` o `ValidatorException`, se invocará `UIInput#setValue()` con el valor convertido y validado.

> `UIInput#isLocalValueSet()` regresará `true`.

> `UIInput#setSubmittedValue()` será invocado con `null`.

Cuando se arroja `ConverterException` o `ValidatorException`, se invocará `UIInput#setValid()` con `false`.

> El mensaje de la excepción serpa agregado al *faces context* mediante `FacesContext#addMessage()`.

> Se invocará `FacesContext#setValidationFailed` con `true`.

Si `FacesContext#isValidationFaild()` es `true` se avanzará inmediatamente a la **sexta fase** (*Render Response Phase*).


## Update Model Values Phase (Fourth Phase)

Se invocará `UIComponent#processUpdates()` desde el `UIViewRoot`.

Se invocará `processUpdates()` en cada hijo y *facet*.

Finalmente se invocará `UIViewRoot#broadCastEvents()` para disparar cada `FacesEvent` encolado para la fase actual (JSF no propone ningún evento, pero se puede personalizar alguno).

En los componentes HTML, los `UIInput` tienen un comportamiento aquí.

> Después de llamar al `processValidators()` en cada hijo y *facet*, se invocará `UIInput#updateModel()` en el componente.

> Cuando `UIInput#isValid()` y `UIInput#isLocalValueSet()` son `true`, se invocará el `setter` asociado al atributo `value` con `getLocalValue()` como argumento.

> Imediatamente después se invocará `UIInput#setValue()` con `null`

> Finalmente se borrará la bandera `UIInput#isLocalValueSet()`.

Si surge un `RuntimeExcepción` aquí (generalmente causado por el `setter`), se invocará `UIInput#setIsValid()` con `false`.

> Se encolará `UpdateModelException` y se avanzará a la **sexta fase** (*Render Response Phase*).

## Invoke Application Phase (Fifth Phase)

Se invocará `UIViewRoot#processApplication()`.

Se invocará `UIViewRoot#broadCastEvents()` para disparar cualquier `FacesEvent` encolado para esta fase (usualmente instancias de `AjaxBehaviorEvent` o `ActionEvent`).

El `processApplication()` solo pertence a la clase `UIViewRoot`.

## Render Response Phase (Sixth Phase)

Si el árbol continua vacío en esta fase (la petición no es `postback`).

Si la vista no tienen `<f:metadata>` con hijos.

Si se invoca explícitamente a `FacesContext#setViewRoot` con una instancia nueva.

Entonces se construirá el árbol completo basado en la definición de vista.

Cuando el árbol está presente se dispara `PreRenderViewEvent` en el `UIViewRoot`.

> Este invoca `UIComponent#encodeAll()` desde el `UIViewRoot`.

> Después se invoca `PostRenderViewEvent` en el `UIViewRoot`.

El `UIComponent#encodeAll()` invocará primero `encodeBegin()` en el componente.

> Si `UIComponent#rendersChildren()` regresa `true`, se invocará `encodeChildren()` sobre el componente.

> Sino se invocará `UIComponent#encodeAll()` en cada hijo.

Finalmente se invocará `encodeEnd()` sobre el componente.

Esto ocurrirá solo si `UIComponent#isRendered()` devuelve `true` (cuando el atributo `rendered` no devuelve `false`).

La implementación por defecto de `encodeBegin()` dispara primero `PreRenderComponentEvent` para el componente actual.

> Entonces es delegado a `Renderer#encodeBegin()`.

La implementación por defecto de `encodeChildren()` es delegada a `Rendered#encodeChildren()`.

La implementación por defecto de `encodeEnd()` es delegada a `Rendered#encodeEnd()`.

Si los componentes no tienen un *renderer* asociado (cuando `UIComponent#getRendererType()` devuelve `null`), entonces no se producirá una salida HTML.

En el `encodeBegin()` se tiene la oportunidad de abrir un elemento HTML con sus atributos para la respuesta.

En el `encodeChildren()` se tiene la oportunidad de decorar o sobreescribir a todos los hijos de ser necesario.

En el `encodeEnd()` se tiene la oportunidad de cerrar la etiqueta HTML.

Se utiliza `FacesContext#getResponseWriter()` para escribir la respuesta.

En cualquier método y fase si se arroja `javax.faces.event.AbortProcessingException` se avanzará directamente a la **sexta fase** (*Render Response Phase*).

## AJAX Life Cycle

Es bastante similar al *JSF Life Cycle* con algunas diferencias:

* El `processDecodes()`, `processValidators()` y `processUpdates()` será invocado solo sobre `UIViewRoot` y los componentes marcados con `<f:ajax execute>`.
* El `encodeAll()` será invocado solo en `UIViewRoot` y los componentes marcados con `f:ajaz render>`.

Los ciclos será iguales usando `@all` (no hay casos reales donde usar `<f:ajax execute="@all">`).

Del lado del HTMl, no es posible lanzar varios formularios a la vez (el mayor valor es `<f:ajax execute="@form">`).

Un posible caso real para usar `<f:ajax render="@all">` es cuando ocurre una excepción en la petición *Ajax*.

Podemos invocar `PartialViewContext#setRenderAll()` para lograr esto programaticamente.

## Esquemas

![Schema.1](https://cdn.educba.com/academy/wp-content/uploads/2019/04/JSF-life-cycle-1.png)

![Schema.2](https://i.stack.imgur.com/kNMHT.png)

![Schema.3](https://th.bing.com/th/id/R.cda9be98342239fe91e1783fa9c13c20?rik=lsloGjDVIBYYNw&riu=http%3a%2f%2fdocs.oracle.com%2fmiddleware%2f1212%2fadf%2fADFFD%2fimg%2fgs_adf_jsf_lifecycle.png&ehk=UNk29ZBUOeIi2tBN6e%2bY2tbU%2fjtU5DbAajbDBpr8JFA%3d&risl=&pid=ImgRaw&r=0)

## Tiempo de Construcción de la Vista (View Build Time)

Se considera el tiempo de construcción de la vista cuando `UIViewRoot` está a punto de ser propagado con todos sus hijos basado en la definición de la vista.

Cuando *JSF* está a punto de crear una instancia basada en la defición de la vista:

* Recupera la instancia del atributo `binding` de la representación del componente (un `UIComponete`).
* Sino crea una instancia basada en el *component type* asociado e invoca al `setter` contenido en el atributo `binding`.
* El atributo `id` es usado para invocar `UICompnent#setId()`.
* Se invoca `UIComponent#setParent()` con el padre del componente.
* El árbol existirá hasta el final de la respuesta `render` de la **sexta fase** (*Render Response Phase*) y luego es elegible para el recolector de basura con las instancias liberadas del *faces context*.

Todos los atributos `binding` deberían ser `RequestScoped`, se debe cuidar el uso de otros tipos de `scope` para evitar fugas de memoria (evita guardar el árbol completo en el `HTTP session`).

No se debería reconstruir la vista invocando `ViewDeclarationLanguage#buildView()` o implicitamente con `ViewHandler#createView()` (de `context.getApplication().getViewHandler()`), por ejemplo:

```java
public void rebuildCurrentView() {
    FacesContext context = FacesContext.getCurrentInstance();
    UIViewRoot currentView = context.getViewRoot();
    String viewId = currentView.getViewId();
    ViewHandler viewHandler = context.getApplication.getViewHandler();
    UIViewRoot newView = viewHandler.createView(context, viewId);
    context.setViewRoot(newView);
}
```

## Tiempo de Pintado de la Vista (View Render Time)

El tiempo de pintado de la vista no está sujeto a alguna fase en el ciclo de vida del JSF, este se genera con `UIComponent#encodeAll()` y permite obtener en alguna fase, como en la **quinta fase** (*Invoke Application Phase*) la salida HTML del componente como un valor `String`, esto se hace por defecto en la **sexta fase** (*Render Response Phase*).

## Estado de la Vista (View State)

Los componentes `UIComponent` son re-ensamblados como `RequestScoped`, por lo que son creados durante el **Tiempo de Construcción de la Vista** (*View Build Time*) y desehados en la **sexta fase** (*Render Response Phase*).

Cualquier cambio a las propiedades en las instancias de `UIComponent` que no sean referenciados por expresiones **EL (*Expression Language*)** y sean diferentes a los valores por defecto, serán guardadas en el **View State** (*Estado de la vista*).

Guardar el estado de la vista (*view state*) ocurre en el **Tiempo de Pintado de la Vista** (*View Render Time*).

El estado de la vista será escrito a un campo *input hidden* de `javax.faces.ViewState` en la representación HTML generada.

El método de guardado en JSF tiene, ajustado por defecto en el servidor (`server`), tiene un identificador único referenciando el objeto *view state* serializado y almacenado en el `HTTP session`.

Podemos cambiar a `client` para almacenar el objeto del estado de la vista (*view state*) del lado del cliente, es decir, se guardará todo el objeto serializado y encriptado, si cambiamos la configuración en el `web.xml`.

```xml
<!-- web.xml -->
<context-param>
    <param-name>javax.faces.STATE_SAVING_METHOD</param-name>
    <param-value>client</param-value>
</context-param>

<env-entry>
    <env-entry-name>jsf/ClientSideSecretKey</env-entry-name>
    <env-entry-type>java.lang.String</env-entry-type>
    <env-entry-value>[AES key in Base64 format]</env-entry-value>
</env-entry>
```

Podemos generar una clave de encriptación con:

```java
KeyGenerator keyGen = KeyGenerator.getInstance("AES");
keyGen.init(256); // Or 128 in case you don't have JCE.
byte[] rawKey = keyGen.generateKey().getEncoded();
String key = Base64.getEncoder().encodeToString(rawKey);
System.out.println(key); // Prints AES key in Base64 format.
```

## Alcance /tipo/ Vista (View Scope)

El alcance tipo vista se diferencia de los otros tipos de alcances:

* `RequestScope` - Guarda valores sobre `HttpServletRequest`
* `SessionScope` - Guarda valores sobre `HttpSession`
* `ApplicationScope` - Guarda valores sobre `ServletContext`

El `ViewScope` guarda valores sobre `UIViewRoot#getViewMap()` y almacenado de forma independiente sobre `HTTP session`, no sobre el `view state`.