<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文完了画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<header>
		<jsp:include page="../../../inc/loginUserHeader.jsp"/>
	</header>
	<main>
		<p class="result-message">注文が完了しました！</p><br>
		 <a class="home-botton" href="MenuNavigationServlet">ホームに戻る</a>
	</main>
	<footer>
		<%@ include file="../../../inc/userFooter.jsp" %>
	</footer>
</body>
</html>