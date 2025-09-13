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
	
	<header>
	  <jsp:include page="/inc/userHeader.jsp"/>
	</header>
	
	<main>
	<!--エラー時の表示処理-->
		<c:if test="${not empty login.errorMsg}">
			<p style="color:red;"><c:out value="${login.errorMsg}" /></p>
		</c:if>
	
		<div class="form-buttons">
			<form action="LoginServlet" method="post" name="next" value="signup">
				<input type="hidden" name="next" value="signup">
				<input type="submit" value="新規登録">
			</form>
			<form action="LoginServlet" method="post" name="next" value="login">
			 <input type="hidden" name="next" value="login">
				メールアドレス<br>
				<input type="text" name="mail" value="" required><br>
				パスワード<br>
				<input type="password" name="password" value="" required><br>
				<input type="submit" value="ログイン">
			</form>
			<form action="SignUpServlet" method="get">
				<input type="submit" value="管理者ログイン">
			</form>
		</div>
	</main>
	<footer>
	  <%@ include file="inc/userFooter.jsp" %>	   
	</footer>
</body>
</html>