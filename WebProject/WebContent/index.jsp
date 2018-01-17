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
    <form class="form-inline my-2 my-lg-0" id="search">
      <input class="form-control mr-sm-2" type="search" id="searchField" autocomplete="on" placeholder="Search Tag" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" id="search" type="submit">Search</button>
    </form>
  </div>
</nav>
	<img src="C:\Users\Marco\Desktop\Uninsubria\terra.jpg" alt="Planet Earth" width="100%" height="auto">
	<div id="ajaxGetUserServletResponse"></div>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"></script>
		<script src="/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
		<script src="/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
				
		<script type="text/javascript">

			$("#search").on("submit", function(e){
				e.preventDefault();
				if($("#searchField").val().length > 0){
					var request = $.ajax({
		                url: "SearchServlet",
		                type: "GET",
		                data: {
		                	searchField: $("#searchField").val()
		                }
					});
					request.done(function(data, textStatus, jqXHR) {
						//$('#ajaxGetUserServletResponse').text(responseText);
						window.alert(data);
		            });
		            request.fail(function() {
		           		window.alert("fail");
					});
		            
				}
	         });
		</script>
		
	</body>
</html>