<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ログインTOP</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	
	<header>
	  <%--  <%@ include file="inc/adminHeader.jsp" %> --%>

	  <%--<%@ include file="inc/userHeader.jsp" %>--%>
	  <%--<%@ include file="inc/loginUserHeader.jsp" %>--%>
	  <%--<%@ include file="inc/newSubmitHeader.jsp" %>--%>
	  
	  <jsp:include page="inc/userHeader.jsp"/>
	</header>
	
	
	
	<main>
	<!--エラー時の表示処理-->
		<c:if test="${not empty errorMsg}">
			<p style="color:red;"><c:out value="${errorMsg}" /></p>
		</c:if>
	
		<div class="form-buttons">
			<form action="" method="post" name="action" value="signup">
				<input type="submit" value="新規登録">
			</form>
			<form action="" method="post" name="action" value="login">
				メールアドレス<br>
				<input type="text" name="mail" value=""><br>
				パスワード<br>
				<input type="password" name="password" value=""><br>
				<input type="submit" value="ログイン">
			</form>
		</div>
	</main>
	<footer>
	  <%@ include file="inc/footer.jsp" %>	   
	</footer>
</body>
</html>