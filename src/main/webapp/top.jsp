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
	
	<main id="container">
	<!--エラー時の表示処理-->
		<c:if test="${not empty errorMsg}">
			<p style="color:red;"><c:out value="${errorMsg}" /></p>
		</c:if>
		<form action="LoginServlet" method="post" name="next" value="login">
			メールアドレス<br>
			<input type="text" name="mail" value="" required><br>
			パスワード<br>
			<input type="password" name="password" value="" required><br>
		 	<input type="hidden" name="next" value="login">
			<div class="login">
				<input class="btn-two" type="submit" class="login-button" value="ログイン">
			</div>
		</form>
	
		<div class="login">
			<form action="LoginServlet" method="post" name="next" value="signup">
				<input type="hidden" name="next" value="signup">
				<input class="btn-two" type="submit" class="signup-button" value="新規登録">
			</form>
		</div>

		<div class="adminlogin">
			<form action="AdminLoginServlet" method="get">
				<input type="submit" class="login-button" value="管理者ログイン">
			</form>
		</div>	
		
	</main>

	  <%@ include file="inc/userFooter.jsp" %>	   
</body>
</html>