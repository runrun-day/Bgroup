<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>管理者ログイン</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	  <%@ include file="../../../inc/adminHeader.jsp" %>
	  
	<main>
		<c:if test="${not empty requestScope.errorMsg}">
			<p style="color:red;"><c:out value="${requestScope.errorMsg}" /></p>
		</c:if>
		
		<form action="AdminLoginServlet" method="post" name="next" value="login">
			<input class="#" type="hidden" name="next" value="login">
			<div class="text-box">
			 	<div class="left"><p>パスワード</p></div><br> 
				<input class="passward-form"type="password" name="password" value="" required><br>
			</div>
			
			<div class="btn-one">
				<input class="login-button" type="submit" value="ログイン">
			</div>
		</form>
		<form class="userlogin-box" action="AdminLoginServlet" method="post" name="next" value="userlogin">
			<input type="hidden" name="next" value="userlogin">
			<input class="top-button" type="submit" value="ユーザーログイン">
		</form>
	</main>

	  <%@ include file="../../../inc/adminFooter.jsp" %>	   
</body>
</html>