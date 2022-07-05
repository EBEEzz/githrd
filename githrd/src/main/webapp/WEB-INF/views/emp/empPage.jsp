<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 페이지</title>
<link rel="stylesheet" type="text/css" href="/www/css/w3.css" />
<link rel="stylesheet" type="text/css" href="/www/css/user.css" />
<script type="text/javascript" src="/www/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/www/js/emp.js"></script>
<style type="text/css">
</style>
</head>
<body>

	<div class="w3-content w3-center mw700">
		<h1 class="w3-blue w3-padding w3-card-4" style="margin-bottom: 5px;">EMP Page</h1>
		<div class="w3-col w3-margin-top" id="selbox">
			<select class="w3-quarter w3-select w3-border w3-center" name="sel" id="sel">
				<option disabled selected># 조회분류선택 #</option>
	<c:forEach var="data" items="${LIST}">
		<c:if test="${data eq '부 서'}">
				<option value="dept">${data}</option>
		</c:if>
		<c:if test="${data ne '부 서'}">
				<option value="job">${data}</option>
		</c:if>
	</c:forEach>
			</select>
			<select class="w3-half w3-select w3-border" name="selList" id="selList">
				<option disabled selected># 분류선택 #</option>
			</select>
			<select class="w3-quarter w3-select w3-border" name="eList" id="eList">
				<option disabled selected># 조회분류선택 #</option>
			</select>
		</div>
	</div>
</body>
</html>