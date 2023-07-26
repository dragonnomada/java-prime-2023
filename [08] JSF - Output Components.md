# JSF - Output Components

## Document-Based Output Components

```xml
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
	  xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
	  xmlns:h="http://xmlns.jcp.org/jsf/html"
	  xmlns:f="http://xmlns.jcp.org/jsf/core"
	  xmlns:c="http://xmlns.jcp.org/jsp/jstl/core">

<h:head>
    <title>Title</title>
</h:head>

<h:body>
    ...
</h:body>

</html>
```

## Text-Based Output Components

```xml
<p>Welcome, <h:outputText value="#{user.name}" />!</p>

<p>Welcome, #{user.name}!</p>

<h:outputText value="#{message.html}" escape="false" />

<h:outputText value="#{product.price}">
    <f:convertNumber type="currency" locale="en_US" />
</h:outputText>

<h:outputLink value="http://google.com">Google</h:outputLink>
```

## Navigation-Based Output Components

```xml
<h:link outcome="page2" value="link1" />
<!-- <a href="#{request.contextPath}/folder1/page2.xhtml">link1</a> -->

<h:link outcome="/folder2/page1" value="link2" />
<!-- <a href="#{request.contextPath}/folder2/page1.xhtml">link2</a> -->

<h:link outcome="/folder2/page2" value="link3" />
<!-- <a href="#{request.contextPath}/folder2/page2.xhtml">link3</a> -->

<h:link outcome="/page1" value="link4" />
<!-- <a href="#{request.contextPath}/page1.xhtml">link4</a> -->

<h:link outcome="/page2" value="link5" />
<!-- <a href="#{request.contextPath}/page2.xhtml">link5</a> -->
```

## Panel-Based Output Components

    <h:panelGroup>                          <span>
    <h:panelGroup layout="block">           <div>
    <h:panelGrid><h:panelGroup>             <table>...<td>

```xml
<h:panelGrid columns="3">
    <h:panelGroup>A.1</h:panelGroup>
    <h:panelGroup>A.2</h:panelGroup>
    <h:panelGroup>A.3</h:panelGroup>
    <h:panelGroup>B.1</h:panelGroup>
    <h:panelGroup>B.2</h:panelGroup>
    <h:panelGroup>B.3</h:panelGroup>
    <h:panelGroup>c.1</h:panelGroup>
    <h:panelGroup>c.2</h:panelGroup>
    <h:panelGroup>c.3</h:panelGroup>
</h:panelGrid>

<!-- 

<table>
    <tbody>
        <tr>
            <td>A.1</td>
            <td>A.2</td>
            <td>A.3</td>
        </tr>
        <tr>
            <td>B.1</td>
            <td>B.2</td>
            <td>B.3</td>
        </tr>
        <tr>
            <td>C.1</td>
            <td>C.2</td>
            <td>C.3</td>
        </tr>
    </tbody>
</table>

-->
```

## Data Iteration Component

```xml
<h:dataTable id="strings" value="#{bean.strings}" var="string">
    <h:column>
        <f:facet name="header">COLUMN_NAME</f:facet>
        #{string}
    </h:column>
</h:dataTable>

<!--

<table>
    <thead>
        <tr>
            <th scope="col">COLUMN_NAME</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>one</td>
        </tr>
        <tr>
            <td>two</td>
        </tr>
        <tr>
            <td>three</td>
        </tr>
    </tbody>
</table>

-->
```

> **NOTA: El servicio debe ser transaccional**

```java
@Stateless
public class ProductService {
    @PersistenceContext
    private EntityManager entityManager;

    @TransactionAttribute(SUPPORTS)
    public List<Product> list() {
        return entityManager
                .createQuery("FROM Product ORDER BY id DESC", Product.class)
                .getResultList();
    }
}
```

## Editable `<h:dataTable>`

```xml
<h:form id="list">
    <h:dataTable id="products" value="#{products.list}" var="product">
        <h:column>
            <f:facet name="header">ID</f:facet>
            #{product.id}
        </h:column>
        <h:column>
            <f:facet name="header">Name</f:facet>
            <h:inputText id="name" value="#{product.name}" />
            <h:message for="name" />
        </h:column>
        <h:column>
            <f:facet name="header">Description</f:facet>
            <h:inputTextarea id="description" value="#{product.description}">
            </h:inputTextarea>
            <h:message for="description" />
        </h:column>
    </h:dataTable>
    <h:commandButton id="save" value="Save" action="#{products.save}">
        <f:ajax execute="@form" render="@form" />
    </h:commandButton>
</h:form>
```

> **Bean**

```java
public void save() {
    productService.update(products);
}
```

> **Service**

```java
@TransactionAttribute(REQUIRED)
public void update(Iterable<Product> products) {
    products.forEach(entityManager::merge);
}
```

> **Ejemplo directo**

```xml
<h:form>
    <ui:repeat value="#{bean.strings}" var="string" varStatus="loop">
        <h:inputText value="#{bean.strings[loop.index]}" /><br />
    </ui:repeat>
    <h:commandButton value="Save" action="#{bean.save}">
        <f:ajax execute="@form" />
    </h:commandButton>
</h:form>
```

## Resource Components

```xml
<h:graphicImage name="images/some.svg" />
<h:outputScript name="scripts/some.js" />
<h:outputStylesheet name="styles/some.css" />

<h:graphicImage library="images" name="some.svg" />
<h:outputScript library="scripts" name="some.js" />
<h:outputStylesheet library="styles" name="some.css" />
```

## Pass-Through Elements

    xmlns:jsf="http://xmlns.jcp.org/jsf"

    <main>, <article>, <section>, <aside>, 
    <nav>, <header>, <footer>, <div>, ...

```xml
<main id="main">...</main>

<!-- NO FUNCIONA -->
<h:commandButton id="submit" ...>
    <f:ajax render=":main" />
</h:commandButton>

<!-- Los componentes simples no están registrados en UIViewRoot -->
<!-- jsf:id reconvierte los elementos -->

<main jsf:id="main">...</main>

<!-- FUNCIONA -->
<h:commandButton id="submit" ...>
    <f:ajax render=":main" />
</h:commandButton>
```

    Passthrough Elements Recognized by JSF

    Passthrough HTML element            Implied JSF component
    ----------------------------------  -------------------------------
    <a jsf:action="…">                  <h:commandLink>
    <a jsf:actionListener="…">          <h:commandLink>
    <a jsf:value="…">                   <h:outputLink>
    <a jsf:outcome="…">                 <h:link>
    <body jsf:id="…">                   <h:body>
    <button jsf:id="…">                 <h:commandButton type="button">
    <button jsf:outcome="…">            <h:button>
    <form jsf:id="…">                   <h:form>
    <head jsf:id="…">                   <h:head>
    <img jsf:id="…">                    <h:graphicImage>
    <input jsf:id="…" type="button">    <h:commandButton type="button">
    <input jsf:id="…" type="checkbox">  <h:selectBooleanCheckbox>
    <input jsf:id="…" type="file">      <h:inputFile>
    <input jsf:id="…" type="hidden">    <h:inputHidden>
    <input jsf:id="…" type="password">  <h:inputSecret>
    <input jsf:id="…" type="reset">     <h:commandButton type="reset">
    <input jsf:id="…" type="submit">    <h:commandButton type="submit">
    <input jsf:id="…" type="*">         <h:inputText>
    <label jsf:id="…">                  <h:outputLabel>
    <link jsf:id="…">                   <h:outputStylesheet>
    <script jsf:id="…">                 <h:outputScript>
    <select jsf:id="…">                 <h:selectOneListbox>
    <select jsf:id="…" multiple="*">    <h:selectManyListbox>
    <* jsf:id="…">                      <h:panelGroup>

> **Ejemplo de equivalencias**

```xml
<img jsf:library="common" name="some.svg" />
<!-- <h:graphicImage library="common" name="some.svg" /> -->

<input type="text" jsf:value="#{bean.name}" />
<!-- <h:inputText value="#{bean.name}" /> -->

<input type="email" jsf:value="#{bean.email}" />
<!-- <h:inputText a:type="email" value="#{bean.email}" /> -->

<a jsf:outcome="contact">Contact</a>
<!-- <h:link outcome="contact" value="Contact" /> -->
```

