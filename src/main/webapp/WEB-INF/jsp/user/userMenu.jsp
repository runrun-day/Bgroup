<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザーホーム</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Dela+Gothic+One&family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<style>
.item-img {
	width: 200px;
	height: 200px;
}
</style>

	<header>
		<%@ include file="../../../inc/userHeader.jsp"%>
	</header>
	<main>
		<div class="container-wide">

			<div class="border-container">
				<c:forEach var = "product" items ="${products}">
					<tr>
						<div class="item">
							<td>${product.name}</td>
							<td>${product.price}円</td>
						</div>
					<div class="img">
						<td>
							<img src="${pageContext.request.contextPath}/image?name=${product.imageRename}" width="200">
						</td>
					</div>
						<td>
						<form action="" method="post" name="next" value="cart">
							<input type="hidden" name="orderId" value="${product.productId}">
							<input type="submit" value="カートに追加" class="cart-button">
						</form>
					</td>
					</div>
				</c:forEach>
	</main>

	<footer>
		<%@ include file="../../../inc/userFooter.jsp"%>
	</footer>
</body>
</html>