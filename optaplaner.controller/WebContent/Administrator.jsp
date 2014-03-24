<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<f:view>
<h:commandButton value="Login"
action="#{AdministratorBean.logout}"/>

<h:dataTable value="#{todoController.todos}" var="todo"
      styleClass="todo" headerClass="todoheader"
      columnClasses="first, rest">
      <h:column>
        <%-- Via this facet we define the table header (column 1) --%>
        <f:facet name="header">
          <h:column>
            <h:outputText value="ID"></h:outputText>
          </h:column>
        </f:facet>
        <h:outputText value="#{todo.priority}"></h:outputText>
      </h:column>
      <h:column>
        <%-- Via this facet we define the table header (column 2) --%>
        <f:facet name="header">
          <h:column>
            <h:outputText value="Title"></h:outputText>
          </h:column>
        </f:facet>
        <h:outputText value="#{todo.title}"></h:outputText>

      </h:column>

      <h:column>
        <%-- Via this facet we define the table header (column 3) --%>
        <f:facet name="header">
          <h:column>
            <h:outputText value="ETA"></h:outputText>
          </h:column>
        </f:facet>
        <h:outputText value="#{todo.description}"></h:outputText>
      </h:column>
 <h:column>
        <%-- Via this facet we define the table header (column 3) --%>
        <f:facet name="header">
          <h:column>
            <h:outputText value="Progress bar"></h:outputText>
          </h:column>
        </f:facet>
        <h:outputText value="#{todo.description}"></h:outputText>
      </h:column>
      <h:column>
        <%-- Via this facet we define the table header (column 4) --%>
        <f:facet name="header">
          <h:column>
            <h:outputText value="Actions"></h:outputText>
          </h:column>
        </f:facet>
        <h:panelGrid columns="3">
         <h:commandButton value="edit" action="#{AdministratorBean.logout}"/>
          <h:commandButton value="stop task" action="#{AdministratorBean.logout}"/>
           <h:commandButton value="run task" action="#{AdministratorBean.logout}"/>
           <h:commandButton value="publish task" action="#{AdministratorBean.logout}"/>
        </h:panelGrid>
      </h:column>
      
    </h:dataTable>
   
</f:view>
</body>
</html>