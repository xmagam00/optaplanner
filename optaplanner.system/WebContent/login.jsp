<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ page contentType="text/html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<f:view>
<h:form>
<h:panelGroup rendered="#{login.validationComplete and (!login.isUsernameValid or !login.isPasswordValid)}">
<h:outputText value="Username is a Required Field" rendered="#{!login.isUsernameValid}" style="color:red"></h:outputText>
<br>
<h:outputText value="Password is a Required Field" rendered="#{!login.isPasswordValid}" style="color:red"></h:outputText>
</h:panelGroup>
<h:panelGroup rendered="#{login.validationComplete and (login.isUsernameValid and login.isPasswordValid)}">
<h:outputText value="Username & Password are valid" style="color:green"></h:outputText>
</h:panelGroup>
<table>
<tr>
<td><h:outputText value="Enter Username: " /></td>
<td><h:inputText id="username"
value="#{login.username}" />
</td>
</tr>
<tr>
<td><h:outputText value="Enter Password: " /></td>
<td><h:inputSecret id="password"
value="#{login.password}" />
</td>
</tr>
<tr>
<td></td>
<td><h:commandButton value="Login"
action="#{login.checkValidity}"/>
</td>
</tr>
</table>
</h:form>

</f:view>
</body>
</html>