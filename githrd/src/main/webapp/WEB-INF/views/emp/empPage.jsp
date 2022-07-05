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
	.contentForm {
		width: 200px;
		opacity: 0.8;
	}
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

		<!-- 사원 정보 표시창 -->
		<div class="w3-col w3-margin-top w3-padding w3-card-4" id="infoBox">
			<h3 class="w3-container w3-left-align"><span class="ename edata"></span> 사원 정보</h3>
			<div class="w3-col w3-border w3-border-grey">
				<div class="w3-col w3-margin-top">
					<h4 class="w3-col w3-right-align w3-text-dark-grey contentForm">사원번호 : </h4>
					<div class="w3-rest w3-left-align" style="padding-left: 30px;">
						<h4 class="w3-col w3-text-blue edata" id="eno"></h4>
					</div>
				</div>
				<div class="w3-col">
					<h4 class="w3-col w3-right-align w3-text-dark-grey contentForm">사원이름 : </h4>
					<div class="w3-rest w3-left-align" style="padding-left: 30px;">
						<h4 class="w3-col w3-text-blue ename edata"></h4>
					</div>
				</div>
				<div class="w3-col">
					<h4 class="w3-col w3-right-align w3-text-dark-grey contentForm">사원직급 : </h4>
					<div class="w3-rest w3-left-align" style="padding-left: 30px;">
						<h4 class="w3-col w3-text-blue edata" id="job"></h4>
					</div>
				</div>
				<div class="w3-col">
					<h4 class="w3-col w3-right-align w3-text-dark-grey contentForm">사원급여 : </h4>
					<div class="w3-rest w3-left-align" style="padding-left: 30px;">
						<h4 class="w3-col w3-text-blue edata" id="sal"></h4>
					</div>
				</div>
				<div class="w3-col">
					<h4 class="w3-col w3-right-align w3-text-dark-grey contentForm">커 미 션 : </h4>
					<div class="w3-rest w3-left-align" style="padding-left: 30px;">
						<h4 class="w3-col w3-text-blue edata" id="comm"></h4>
					</div>
				</div>
				<div class="w3-col">
					<h4 class="w3-col w3-right-align w3-text-dark-grey contentForm">급여등급 : </h4>
					<div class="w3-rest w3-left-align" style="padding-left: 30px;">
						<h4 class="w3-col w3-text-blue edata" id="grade"></h4>
					</div>
				</div>
				<div class="w3-col">
					<h4 class="w3-col w3-right-align w3-text-dark-grey contentForm">부서이름 : </h4>
					<div class="w3-rest w3-left-align" style="padding-left: 30px;">
						<h4 class="w3-col w3-text-blue edata" id="dname"></h4>
					</div>
				</div>
				<div class="w3-col">
					<h4 class="w3-col w3-right-align w3-text-dark-grey contentForm">부서위치 : </h4>
					<div class="w3-rest w3-left-align" style="padding-left: 30px;">
						<h4 class="w3-col w3-text-blue edata" id="loc"></h4>
					</div>
				</div>
				<div class="w3-col">
					<h4 class="w3-col w3-right-align w3-text-dark-grey contentForm">상사이름 : </h4>
					<div class="w3-rest w3-left-align" style="padding-left: 30px;">
						<h4 class="w3-col w3-text-blue edata" id="mgr"></h4>
					</div>
				</div>
				<div class="w3-col w3-margin-bottom">
					<h4 class="w3-col w3-right-align w3-text-dark-grey contentForm">입 사 일 : </h4>
					<div class="w3-rest w3-left-align" style="padding-left: 30px;">
						<h4 class="w3-col w3-text-blue edata" id="hdate"></h4>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>