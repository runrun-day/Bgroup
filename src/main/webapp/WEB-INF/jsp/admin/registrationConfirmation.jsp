<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品登録画面</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<header>
	  <jsp:include page="../../../inc/adminHeader.jsp"/>
	</header>
	
	<main>
		<h1 class="title">商品情報確認</h1>	
		<div class="container-wide">
		
			名前：${product.name}
			<br>
			<img src="temp/${product.imageRename}" width="200">
			<br>
			価格：${product.price} 円
			<br>
				
			<form action="ProductRegistrationServlet" method="get">
			<input type="submit" value="戻る">
			</form>
		
			<form action="ProductRegistrationServlet" method="post">
			<input type="hidden" name="next" value="commit">
			<input type="hidden" name="name" value="${product.name}">
			<input type="hidden" name="price" value="${product.price}">
			<input type="hidden" name="imageRename" value="${product.imageRename}">
			<input type="submit" value="確定">
			</form>
		
		</div>
	</main>

	<footer>
	  <%@ include file="../../../inc/adminFooter.jsp" %>
	</footer>
</body>
</html>