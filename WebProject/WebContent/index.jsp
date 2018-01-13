<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="provaServlet.SearchServlet"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>SearchEngine Form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
		<link rel="stylesheet" href="style.css">
	</head>
	<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="userProfile.jsp"><b>Image Search Engine</b></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" id="searchField" autocomplete="on" placeholder="Search Tag" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" id="search" type="submit" onclick="ricerca()">Search</button>
    </form>
  </div>
</nav>
	<img src="C:\Users\Marco\Desktop\Uninsubria\terra.jpg" alt="Planet Earth" width="100%" height="auto">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script type="text/javascript">
		function ricerca(){
			window.alert("coggiuda");
			var x = getElementById("searchField");
			x.text = "success";
		}
			$("#search").on("submit", function(e){
				e.preventDefault();
				if($("#searchField").val().length > 0){
					var request = $.ajax({
		                url: "SearchServlet",
		                type: "POST",
		                data: {
		                	document.getElementById("searchField").value;
		                }
					});
					window.alert("coggiuda");
					request.done(function(response) {
		                /*if(success){
		                	console.log("bravo");
		                }
		                else{
		                	console.log("stronzo");
		                }*/
		                alert(response);
		            });
		            request.fail(function() {
		           		console.log("fail");
					});
		            
				}
	         });
		</script>
	    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-route.js"></script>
	</body>
</html>