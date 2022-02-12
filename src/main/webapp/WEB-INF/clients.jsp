<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des clients</title>
</head>
<body>
<h1>Liste des clients</h1>
<ul>
<c:forEach items="${clients}" var="client">
<li>${client.nom}</li>
</c:forEach>
</ul>
<a href="client">Ajouter un client</a>
<br>
<a href="index">Retour à l'accueil</a>
</body>
</html>