<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品登録確定</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

	<header>
		<%@ include file="../../../inc/adminHeader.jsp"%>
	</header>

	<main>
	
		<p class="result-message">ユーザーを削除しました！</p>
		<a class="btn" href="UserSearchServlet">ホームに戻る</a>
		 
	</main>
	<footer>
		<%@ include file="../../../inc/adminFooter.jsp"%>
	</footer>
</body>
</html>