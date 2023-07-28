# JSF - JSTL Core Tags

Espacio de nombre: `xmlns:c="http://xmlns.jcp.org/jsp/jstl/core"`

Existen algunas implementaciones de las JSTL Core Tags de JSP migradas a JSF.

```text
    <c:set>

    <c:if>
    
    <c:choose>
        <c:when>
        <c:otherwise> 
        
    <c:forEach>
    
    <c:catch>
```

> **Ejemplo de `<c:set>`**

```xml
<c:set var="salary" scope="session" value="#{123 + 456}" />
```

> **Ejemplo de `<c:if>`**

```xml
 <c:if test="#{salary >= 1_000}">
    <p>My salary is:  #{salary}<p>
</c:if>
```

> **Ejemplo de `<c:choose>`, `<c:when>` y `<c:otherwise>`**

```xml
<c:choose>
  
    <c:when test="#{salary <= 0}">
        Salary is very low to survive.
    </c:when>
    
    <c:when test="#{salary > 1_000}">
        Salary is very good.
    </c:when>
    
    <c:otherwise>
        No comment sir...
    </c:otherwise>

</c:choose>
```

> **Ejemplo de `<c:forEach>`**

```xml
 <c:forEach var="i" begin="1" end="5">
    <p>Item #{i}</p>
</c:forEach>

<c:forEach items="#{bean.items}" var="item">
    <p>#{item.name}</p>
</c:forTokens>
```

> **Ejemplo de `<c:catch>`**

```xml
 <c:catch var="catchException">
   ...
</c:catch>

<c:if test="${catchException != null}">
    <p>
        The exception is : ${catchException} 
        <br />
        There is an exception: ${catchException.message}
    </p>
</c:if>
```
