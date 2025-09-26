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
	<%@ include file="../../../inc/adminHeader.jsp"%>

	<main>
		<p class="result-message">商品を削除しました！</p>
		
		<div class="btn-one">
		<a class="home-button" href="ProductListServlet">ホームに戻る</a>
		</div> 
	</main>

	<%@ include file="../../../inc/adminFooter.jsp"%>
</body>
</html>