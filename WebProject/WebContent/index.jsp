<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width">
		<title>SearchEngine Form</title>
    	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
		<link rel="stylesheet" href="style.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"></script>
		<script src="js/index.js"></script>
	</head>
	<body background ="img/night.jpg">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <a class="navbar-brand" href=""><b>Image Search Engine</b></a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		    <ul class="navbar-nav mr-auto">
		    </ul>
		    <form class="form-inline my-2 my-lg-0" id="search">
		      <input class="form-control mr-sm-2" type="search" id="searchField" autocomplete="on" placeholder="Search Tag" aria-label="Search">
		      <button class="btn btn-outline-success my-2 my-sm-0" id="search" type="submit">Search</button>
		    </form>
		  </div>
		</nav>
		
		<div class="resultList">
		  <h2>Here are your results:</h2>
		  <ul id="result-list" class="list-group">
		    
		  </ul>
		</div>
	</body>
</html>