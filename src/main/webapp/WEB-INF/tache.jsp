<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tache</title>
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
	<div class="container">

<form:form modelAttribute="tache" action="tache" method="post">
<form:label path="projet.nom">Projet</form:label>${tache.projet.nom}<br>
<form:label path="colonneActuelle.nom">Colonne</form:label>${tache.colonneActuelle.nom}<br>
<form:label path="typeTache">Type de tâche</form:label>
<form:select path="typeTache">
<form:option value="">Merci de choisir un type de tâche</form:option>
<form:options items="${typesTache}" itemLabel="nom" itemValue="id"/>
</form:select>
<form:errors path="typeTache" cssClass="erreur"></form:errors>
<br>
<form:label path="intitule">Intitulé</form:label><form:textarea path="intitule"/>
<form:errors path="intitule" cssClass="erreur"></form:errors>
<br>
<form:label path="developpeur">Développeur</form:label>
<form:select path="developpeur">
<form:options items="${developpeurs}" itemLabel="prenom" itemValue="id"/>
</form:select>
<form:errors path="developpeur" cssClass="erreur"></form:errors>
<br>
<form:label path="nbHeuresPrevues">Nombre d'heures prévues</form:label>
<form:input path="nbHeuresPrevues"/>
<form:errors path="nbHeuresPrevues" cssClass="erreur"></form:errors>
<br>
<form:button>
<c:if test="${tache.id eq null }">Ajouter</c:if>
<c:if test="${tache.id ne null }">Enregistrer</c:if>
</form:button>
</form:form>
</div>
</body>
</html>