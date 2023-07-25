# JSF - Phase Events

Los eventos de fase son:

    • PhaseId.ANY_PHASE            (0)
    • PhaseId.RESTORE_VIEW         (1)
    • PhaseId.APPLY_REQUEST_VALUES (2)
    • PhaseId.PROCESS_VALIDATIONS  (3)
    • PhaseId.UPDATE_MODEL_VALUES  (4)
    • PhaseId.INVOKE_APPLICATION   (5)
    • PhaseId.RENDER_RESPONSE      (6)

## Escuchar un evento de fase

Interfaz: `javax.faces.event.PhaseListener`

> Vía `faces-config.xml`

```xml
<lifecycle>
    <phase-listener>com.example.project.MyListener</phase-listener>
</lifecycle>
```

> Vía `.xhtml`

```xml
<f:view>
    <f:phaseListener type="com.example.project.MyListener" />
    ...
</f:view>
```

## Component System Events

Interfaz: `javax.faces.event.ComponentSystemEvent`
Interfaz: `javax.faces.event.ComponentSystemEventListener`

    • PreRemoveFromViewEvent

    Fired when a component is about to be 
    removed from the component tree.
    
    • PostAddToViewEvent
    
    Fired when a component has been added 
    to the component tree.
    
    • PostRestoreStateEvent 
    (read: "PostRestoreViewEvent")
    
    Fired for each component when the 
    restore view phase ends. Note that 
    this event is only fired for UIViewRoot 
    when the view build time hasn’t yet
    taken place during this phase. 
    If the view build time has taken place
    during this phase, then this event 
    is fired for any component in the tree.

    • PreValidateEvent
    
    Fired when a component is about to 
    process its converter and validators, 
    and also when there are actually none.
    
    • PostValidateEvent
    
    Fired when a component is finished 
    processing its converter and validators, 
    and also when there are actually none.
    
    • PreRenderViewEvent
    
    Fired when the UIViewRoot is about 
    to write HTML output to the HTTP response.
    Note that this is the latest possible 
    safe moment to change the destination 
    of the HTTP response, or to programmatically 
    manipulate the component tree.
    When doing so after this moment, 
    there’s no guarantee that any programmatic changes 
    to the response or the component tree will 
    take place as intended, because by then 
    the response may already be committed, 
    or the view state may already be saved.
    
    • PreRenderComponentEvent
    
    Fired when a component is about to write
    its HTML output to the HTTP response.
    
    • PostRenderViewEvent
    
    Fired when the UIViewRoot is finished writing
    the HTML output to the HTTP response. 
    Note that this event is new since JSF 2.3. 
    All others are from JSF 2.0.
    
    • PostConstructViewMapEvent 
    
    Fired when the UIViewRoot has just 
    started the view scope.
    
    • PreDestroyViewMapEvent
    
    Fired when the UIViewRoot is about to
    destroy the view scope.

> **Recibir el evento en un bean** 

```xml
<f:metadata>
    <f:viewParam name="id" value="#{bean.id}" />
    <f:event type="preRenderView" listener="#{bean.onload()}" />
</f:metadata>
```

> **Controlar el evento en un bean**

```java
public void onload() {
    FacesContext context = FacesContext.getCurrentInstance();
    if (!context.isPostback() && !context.isValidationFailed()) {
        // ...
    }
}
```

**Nota:** Se espera un `CustomSystemEvent` como argumento del bean en `listener="#{bean.onload}"` si no se usan paréntesis.

> **Recibir el evento alternativamente en el bean**
>
> **Nota:** Desde *JSF 2.2*

```xml
<f:metadata>
    <f:viewParam name="id" value="#{bean.id}" />
    <f:viewAction action="#{bean.onload}" />
</f:metadata>
```

## Custom Component System Events

Podemos controlar un evento antes de entrar a una fase:

> **Definimos el evento que interceptará un componente antes de entrar a una fase**

```java
@NamedEvent(shortName="preInvokeApplication")
public class PreInvokeApplicationEvent extends ComponentSystemEvent {

    public PreInvokeApplicationEvent(UIComponent component) {
        super(component);
    }

}
```

> **Definimos el *listener* de la fase**
>
> **Nota:** Registramos el `listener` en `faces-config.xml`

```java
public class PreInvokeApplicationListener implements PhaseListener {

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.INVOKE_APPLICATION;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().publishEvent(context,
            PreInvokeApplicationEvent.class, context.getViewRoot());
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        // NOOP.
    }
}
```

> **Escuchar el evento sobre un componente**

```xml
<f:metadata>
    ...
    <f:event type="preInvokeApplication"
        listener="#{bean.prepareInvokeApplication}" />
    ...
</f:metadata>
```