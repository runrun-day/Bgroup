<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品一覧画面</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<style>
	.header.admin,
	.footer.admin{
		background-color: #ccc;
	}

</style>
</head>
<body>
 	<%@ include file="../../../inc/adminHeader.jsp" %>	

	
	<main>
		<h1 class="title left">商品編集</h1>	
		
		<form action="ProductListServlet" method="post">
			<div class="text-box"><p>商品名</p></div>
			<input class="form-wide" type="text" name="name" value="${product.name}" required><br>
		
			<div class="text-box"><p>価格</p></div>
			<input class="form-narrow" type="number" min="1" max="10000" name="price" value="${product.price}" required>円<br>
		
			<div class="text-box"><p>画像</p></div>
			<img src="${pageContext.request.contextPath}/image?name=${product.imageRename}" width="200"><br>
			<input type="hidden" name="productId" value="${product.productId}">
			<input type="hidden" name="next" value="editCommit">
		
		<div class="btn-two">	
			<input class="action-button" type="submit" value="修正">
	
		</form>
		
		<form action="ProductListServlet" method="post">
			<input type="hidden" name="productId" value="${product.productId}">
			<input type="hidden" name="next" value="deleteCommit">
		<input class="action-button" type="submit" value="削除">
		</form>
		
		</div>

		<form class="login-box" action="ProductListServlet" method="get">
		<input class="return-button"type="submit" value="戻る">
		</form>
		
	</main>

 	<%@ include file="../../../inc/adminFooter.jsp" %>

</body>
</html>