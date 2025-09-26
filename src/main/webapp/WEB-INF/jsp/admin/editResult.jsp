<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>編集結果</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%@ include file="../../../inc/adminHeader.jsp"%>

	<main>
		<p class="result-message">商品を修正しました！</p><br> 

		
		<div class="btn-one">
			<a class="home-button" href="ProductListServlet?next=non">ホームに戻る</a>
		</div>
	

	</main>

	<%@ include file="../../../inc/adminFooter.jsp" %>	 
</body>
</html>