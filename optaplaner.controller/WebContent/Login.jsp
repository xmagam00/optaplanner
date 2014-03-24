<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="bootstrap/js/bootstrap.min.js"></script>
   <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
  <script src="http://code.jquery.com/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LoginBean</title>
</head>
<body>
<f:view>
<h:form>
<h:panelGroup rendered="#{LoginBeanBean.validationComplete and (!LoginBean.isUsernameValid or !LoginBean.isPasswordValid)}">
<h:outputText value="Username is a Required Field" rendered="#{!LoginBean.isUsernameValid}" style="color:red"></h:outputText>
<br>
<h:outputText value="Password is a Required Field" rendered="#{!LoginBean.isPasswordValid}" style="color:red"></h:outputText>
</h:panelGroup>
<h:panelGroup rendered="#{LoginBean.validationComplete and (LoginBean.isUsernameValid and LoginBean.isPasswordValid)}">
<h:outputText value="Username & Password are valid" style="color:green"></h:outputText>
</h:panelGroup>
<table style="margin:0px auto;">
<tr>
<td><h:outputText value="Enter Username: " /></td>
<td><h:inputText id="username"
value="#{LoginBean.username}" />
</td>
</tr>
<tr>
<td><h:outputText value="Enter Password: " /></td>
<td><h:inputSecret id="password"
value="#{LoginBean.password}" />
</td>
</tr>
<tr>
<td></td>
<td><h:commandButton value="Login"
action="#{LoginBean.checkValidity}"/>
</td>
</tr>
</table>
</h:form>

</f:view>
</body>
</html>