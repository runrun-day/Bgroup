<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ログインTOP</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	  <%@ include file="inc/userHeader.jsp" %>	 
	<main id="container" >
	<!--エラー時の表示処理-->
		<c:if test="${not empty errorMsg}">
			<p style="color:red;"><c:out value="${errorMsg}" /></p>
		</c:if>
		<form action="LoginServlet" method="post">
			メールアドレス<br>
			<input type="text" name="mail" value=""  required><br>
			パスワード<br>
			<input type="password" name="password" value="" required><br>
		 	<input type="hidden" name="next" value="login">
			<div class="btn-two" reverse>
				<input class="login-button" type="submit" value="ログイン">
		</form>
	
			<form action="LoginServlet" method="post" >
				<input type="hidden" name="next" value="signup">
				<input class="signup-button" type="submit" value="新規登録">
			</form>
		</div>

<!--		<div class="adminlogin">-->
			<form class="login-box" action="AdminLoginServlet" method="get">
				<input class="top-button" type="submit" value="管理者ログイン">
			</form>
<!--		</div>	-->
		
	</main>

	  <%@ include file="inc/userFooter.jsp" %>	   
</body>
</html>