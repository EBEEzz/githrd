<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Top Five</title>
<link rel="stylesheet" type="text/css" href="/www/css/w3.css">
<link rel="stylesheet" type="text/css" href="/www/css/user.css">

</head>
<body>
	<div class="w3-content mxw650">
		<h1 class="w3-padding w3-blue w3-center w3-card-4">최근 측정 거리 5개</h1>
		<div class="w3-col w3-padding">
<c:forEach var="data" items="${LIST}" varStatus="st">
			<div class="w3-card-4 w3-col w3-padding w3-margin-bottom">
				<h3 class="w3-border-bottom">${st.count} :</h3>
				<h3> 거리 : ${data.ddata}</h3>
				<h3> 측정시간 : ${data.sdate}</h3>
			</div>
</c:forEach>
		</div>
	</div>
</body>
</html>