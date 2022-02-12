<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des tâches</title>
<style>
body {
	background-size:cover;
	background-repeat:no-repeat;
	background-position:center; 
	background-attachment: fixed;
	height:100vh; 
	overflow: scroll;
}

.card{
	margin: auto;
}

.nav_special{
	background-color:rgba(225,225,225,0.9)!important;
}

.table {
	background-color:#eeeeee;
}

label {
   display:block;
   width:210px;
   float:left;
   font-family:"Arial";
}

.table > tbody > tr > td, .table-bordered > thead > tr > th {
	border: 0.5px solid white !important;
}

.erreur {
	color: red;
}
</style>
</head>
<body>
<h1>Liste des tâches</h1>
<ul>
<c:forEach items="${pageDeTaches.content}" var="tache">
<li>${tache.intitule} (${tache.nbHeuresPrevues} heures)<a href="tache?ID=${tache.id}">Modifier</a></li>
</c:forEach>
</ul>
<c:if test="${!pageDeTaches.first}">
	<a href="taches?page=0">&#x23EE;</a>
    <a href="taches?page=${pageDeTaches.number-1}&sort=${pageDeTaches.sort.iterator().next().property}">&#x23EA;</a>
</c:if>
<c:if test="${!pageDeTaches.last}">
	<a href="taches?page=${pageDeTaches.number+1}&sort=${pageDeTaches.sort.iterator().next().property}">&#x23E9;</a>
    <a href="taches?page=${pageDeTaches.totalPages-1}&sort=${pageDeTaches.sort.iterator().next().property}">&#x23ED;</a>
</c:if>
<br>
Page actuelle : ${pageDeTaches.number+1}<br>
Nombre total de pages : ${pageDeTaches.totalPages}
<br>
<a href="tache">Ajouter une tâche</a>
<br>
<a href="index">Retour à l'accueil</a>
</body>
</html>