<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<header>
		<%@ include file="inc/loginUserHeader.jsp"%>
	</header>
	<main>
		<p class="result-message">ユーザー登録しました！</p>
		<br> <a class="home-botton" href="#">ホームに戻る</a>
	</main>
	<footer>
		<%@ include file="inc/userFooter.jsp"%>
	</footer>
</body>
</html>