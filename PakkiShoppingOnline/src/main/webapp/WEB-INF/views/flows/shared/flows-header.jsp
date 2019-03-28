<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- this is ho we going to add sprin tag library -->

<spring:url var="css" value="/resources/css/" />
<!-- this resource is coming from dispatcher servlet mapping tag -->
<spring:url var="js" value="/resources/js/" />
<spring:url var="images" value="/resources/images/" />


<!-- ${contextRoot} say - ${greeting} -->
<!-- this is known as el expression -->

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />




<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>Pakki Shopping - ${title}</title>

<script>
	window.menu = '${title}'; // this title is coming from the page controller

	window.contextRoot = '${contextRoot}' // to make it dynamic we use context root
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.css" rel="stylesheet">

<!-- Bootstrap Readable Theme  -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Bootstrap DataTables Theme  -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

<!-- BootStrap Icons -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

</head>

<body>

	<div class="wrapper">
		<!-- here we wrap the whole body under the wrapper class -->
		<!-- Navigation -->
	<%@include file="flows-navbar.jsp" %>

		<!-- Page Content -->

		<div class="content">
