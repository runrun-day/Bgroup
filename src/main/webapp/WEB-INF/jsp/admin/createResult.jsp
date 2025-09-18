<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
<meta charset="UTF-8">
<title>商品削除確定画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

	<header>
		<%@ include file="../../../inc/adminHeader.jsp"%>
	</header>

	<main>
		<p class="result-message">商品を登録しました！</p>
		 <form action="ProductRegistrationServlet" method="get">
			<input type="submit" value="ホームに戻る">
		</form>
	</main>
	<footer>
		<%@ include file="../../../inc/adminFooter.jsp"%>
	</footer>
</body>
</html>