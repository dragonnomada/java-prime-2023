# JSF - Websocket Push

## Configuration

```xml
<!-- src/main/webapp/WEB-INF/web.xml -->

<context-param>
    <param-name>javax.faces.ENABLE_WEBSOCKET_ENDPOINT</param-name>
    <param-value>true</param-value>
</context-param>
```

> **Usar un puerto específico**

```xml
<param-value>8000</param-value>
```

## Usage

```xml
<f:websocket channel="test" onmessage="logMessage" />
```

> **Controlar el mensaje en Javascript**

```html
<script>
    function logMessage(message, channel, event) {
        alert(message);
    }
</script>
```

> **Enviar una notificación al cliente**

```java
@Named 
@RequestScoped
public class Bean {

    @Inject 
    @Push
    private PushContext test;
    
    public void submit() {
        test.send("Hello World!");
    }

}
```

> **Enviar la notificación sobre un canal específico

```java
@Push(channel="test")
```

## Scopes and Users

```xml
<!-- Persistir la conexión durante la sesión -->
<f:websocket channel="progress" scope="session" />

<!-- Restringir por usuario -->
<f:websocket channel="chat" user="#{loginBean.userId}" />
```

> **Enviar la notificación a un usuario específico**

```java
test.send(message, userId);
```

> **Enviar la notificación a múltiples usuarios específicos**

```java
chat.send(message, recipientIds); // Set<Long> recipientIds
```

## Channel Design Hints

```java
Map<String, Object> message = new HashMap<>();
```

> **Establecer un mensaje complejo (objeto)**

```java
message.put("functionName", "someFunction");
message.put("data", data); // Puede ser un objeto o bean
```

> **Recibir un mensaje complejo (objeto)**

```html
<script>
    function someSocketListener(message) {
        window[message.functionName](message.data);
    }

    function someFunction(data) {
        // TODO: Procesar los datos
    }

    function otherFunction(data) {
        // TODO: Procesar los datos
    }
</script>
```

## One-Time Push

```xml
<!-- Evita que el websocket se conecte automáticamente -->

<f:websocket ... connected="false" />
```

> **Conectamos el websocket manualmente desde Javascript**

```html
<script>
    function startLongRunningProcess() {
        jsf.push.open("push");
        document.getElementById("status").innerHTML = "Ejecutando...";
        // TODO: Ejecuta un proceso largo
    }

    function endLongRunningProcess(result) {
        document.getElementById("status").innerHTML = "Finalizado";
        // TODO: Haz algo con el resultado
        jsf.push.close("push");
    }
</script>
```

> **Ejemplo de un resultado asíncrono**

```java
@Stateless
public class LongRunningProcessService {
   
    @Asynchronous
    public void asyncSubmit(Consumer<String> callback) {
        String result = someLongRunningProcess();
        callback.accept(result);
    }

}
```

## Stateful UI Updates

```xml
<f:websocket channel="push" scope="view">
    <f:ajax event="loaded" render=":results" />
</f:websocket>
```
