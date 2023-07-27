# JSF - Facelets Templating

## XHTML

    NOTA:

    Las plantillas y componentes base generalmente se colocan
    en META-INF para evitar que los usuarios las consulten directamente,
    es decir, META-INF es una carpeta protegida en las rutas de web.xml.

> `/WEB-INF/templates/base.xhtml`

```xml
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
	xmlns:jsf="http://xmlns.jcp.org/jsf"
	xmlns:h="http://xmlns.jcp.org/jsf/html"
	xmlns:f="http://xmlns.jcp.org/jsf/core">

<h:head>
	<title>#{title}</title>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
</h:head>

<h:body>
	<header>
		<ui:include src="/WEB-INF/includes/header.xhtml" />
	</header>
	<main>
		<ui:insert name="content" />
	</main>
	<footer>
		<ui:include src="/WEB-INF/includes/footer.xhtml"></ui:include>
	</footer>
</h:body>

</html>
```

> `/WEB-INF/includes/header.xhtml`

```xml
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://xmlns.jcp.org/jsf/html"
	xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
	xmlns:f="http://xmlns.jcp.org/jsf/core">
	<h1>Inicio de la página</h1>
</ui:composition>
```

> `/WEB-INF/includes/footer.xhtml`

```xml
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://xmlns.jcp.org/jsf/html"
	xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
	xmlns:f="http://xmlns.jcp.org/jsf/core">
	<h1>Fin de la página</h1>
</ui:composition>
```

> `index.xhtml`

```xml
<ui:composition template="/WEB-INF/templates/base.xhtml"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
	xmlns:jsf="http://xmlns.jcp.org/jsf"
	xmlns:h="http://xmlns.jcp.org/jsf/html"
	xmlns:f="http://xmlns.jcp.org/jsf/core"
	xmlns:c="http://xmlns.jcp.org/jsp/jstl/core">

	<ui:param name="title" value="My App :D" />

	<ui:define name="content">

		<h1>Hello world :D</h1>
        
	</ui:define>

</ui:composition>
```

## Template Compositions

> **ui:composition (single)**

```xml
<!-- Crea una plantilla para reutilizar la vista -->

<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://xmlns.jcp.org/jsf/html"
	xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
	xmlns:f="http://xmlns.jcp.org/jsf/core">
	
    <!-- (modifica la interfaz de la plantilla aquí) -->
    <!-- <html> -->

</ui:composition>
```

> **ui:include**

```xml
<!-- Inserta el contenido de algún *.xhtml -->

<ui:include src="/WEB-INF/includes/pages/#{bean.currentPage}.xhtml">
    
    <!-- (configura la inclusión) -->
    <!-- <ui:param> -->

</ui:include>
```

> **ui:param**

```xml
<!-- Configura un parámetro sobre la plantilla asociada -->
<ui:param name="..." value="..."></ui:param>
```

> **ui:composition (associate template)**

```xml
<!-- Utiliza una plantilla y reemplaza sus parámetros e inserciones -->

<ui:composition template="/WEB-INF/templates/base.xhtml" ... >

    <!-- (modifica la plantilla asociada aquí) -->
    <!-- <ui:param> -->
    <!-- <ui:define> | <ui:decorate> -->

</ui:composition>
```

> **ui:define**

```xml
<!-- Reemplaza el <ui:insert name="..." /> contenido en la plantilla arriba -->

<ui:define name="...">

    <!-- (modifica el contenido del <ui:insert>) -->
    <!-- <ui:param> -->
    <!-- <html> -->

</ui:define>
```

> **ui:decorate**

```xml
<!-- Reemplaza el único <ui:insert> sin nombre de la plantilla dada -->

<ui:decorate template="...">

    <!-- (modifica el contenido del <ui:insert>) -->
    <!-- <ui:param> -->
    <!-- <html> -->

</ui:decorate>
```

> **ui:insert**

```xml
<!-- Define un espacio a reemplazar sobre la plantilla actual -->

<ui:insert name="...">

    <!-- (establece una vista por defecto para esta región) -->

</ui:insert>
```

## Tag Files

Configuración: `/WEB-INF/example.taglib.xml`

