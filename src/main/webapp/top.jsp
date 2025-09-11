<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
	    <h1>はたらく文具屋さん(仮)</h1>
	</header>
	<main>
	<!--エラー時の表示処理-->
		<c:if test="${not empty errorMsg}">
			<p style="color:red;"><c:out value="${errorMsg}" /></p>
		</c:if>
	
		<div class="form-buttons">
			<form action="LoginServlet" method="post" name="action" value="signup">
				<input type="submit" value="新規登録">
			</form>
			<form action="LoginServlet" method="post" name="action" value="login">
				メールアドレス<br>
				<input type="text" name="mail" value=""><br>
				パスワード<br>
				<input type="password" name="password" value=""><br>
				<input type="submit" value="ログイン">
			</form>
		</div>
	</main>
	<footer>
	    <p>&copy; はたらく文具屋さん(仮)</p>
	</footer>
</body>
</html>