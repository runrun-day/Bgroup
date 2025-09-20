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
		
		<p>管理者ログイン</p>
		<form action="AdminLoginServlet" method="post" name="next" value="login">
			<input type="hidden" name="next" value="login"> パスワード<br>
			<input type="password" name="password" value="" required><br>
			<input type="submit" value="ログイン">
		</form>
		<form action="AdminLoginServlet" method="post" name="next" value="userlogin">
			<input type="hidden" name="next" value="userlogin">
			<input type="submit" value="ユーザーログイン">
		</form>
	</main>

	  <%@ include file="../../../inc/adminFooter.jsp" %>	   
</body>
</html>