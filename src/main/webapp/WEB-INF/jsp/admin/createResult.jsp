<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品削除確定画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%@ include file="../../../inc/adminHeader.jsp"%>

	<main>
		<p class="result-message">商品を登録しました！</p>
		
		<div class="btn-one">
		<a class="home-button" href="ProductRegistrationServlet">ホームに戻る</a>
	</div> 
	</main>

	<%@ include file="../../../inc/adminFooter.jsp"%>
</body>
</html>