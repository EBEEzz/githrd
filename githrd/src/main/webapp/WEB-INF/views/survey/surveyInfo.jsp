<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 정보</title>
<link rel="stylesheet" type="text/css" href="/www/css/w3.css">
<link rel="stylesheet" type="text/css" href="/www/css/base.css">
<script type="text/javascript" src="/www/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/www/js/survey/survey.js"></script>
<style type="text/css">
	label {
		font-size: 16pt;
	}
	
	#msg {
		font-size: 20pt;
		font-weight: bold;
		color: indigo;
	}
</style>
</head>
<body>
	<div class="w3-content w3-center mx650">
		<h1 class="w3-blue w3-padding w3-card-4">Survey Information</h1>
		
<!-- 진행중인 설문 리스트 -->
<c:if test="${not empty ING }">
		<div class="w3-col w3-left-align w3-card-4">
			<h3 class="w3-blue">진행중인 설문</h3>
			<div class="w3-col">
	<c:forEach var="data" items="${ING}" varStatus="st">
		<c:if test="${data.cnt eq 0}">
				<h3 class="w3-text-blue" id="${data.sino}">${st.count}. ${data.title}</h3>
		</c:if>
		<c:if test="${data.cnt eq 1}">
				<h3 id="${data.sino}">${st.count}. ${data.title}</h3>
		</c:if>
	</c:forEach>
			</div>
		</div>
</c:if>

<!-- 종료된 설문 리스트 -->
<c:if test="${not empty OLD}">
		<div class="w3-col w3-left-align w3-card-4">
			<h3 class="w3-amber">종료된 설문</h3>
			<div class="w3-col">
	<c:forEach var="data" items="${OLD}" varStatus="st">
				<h3 id="${data.sino}">${st.count}. ${data.title}</h3>
	</c:forEach>
			</div>
		</div>
</c:if>
		
	</div>
</body>
</html>