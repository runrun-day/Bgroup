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
<style>
input.nonbtn {

  background: none;        /* 背景なし */
  border: none;            /* 枠線なし */
  color: #0070c9;          
  padding: 0;              
  margin: 0 10px;          
  cursor: pointer;         /* カーソルをリンク風に */
  text-decoration: underline; /* 下線 */
  font-size: 2em;

}

/* ホバー時の色変化 */
input.nonbtn:hover {
  color: #005799;
  text-decoration: underline;
}

</style>
</head>
<body>

	<jsp:include page="../../../inc/userHomeHeader.jsp"/>
	
	<main class="main container">
		<table>
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
					<form class="cart-button" action="MenuNavigationServlet" method="post" name="next" value="cart">
						<input type="hidden" name="next" value="cartIn">
						<input type="hidden" name="productId" value="${product.productId}">
						<input type="hidden" name="productName" value="${product.name}">
						<input type="hidden" name="price" value="${product.price}">
						<input type="submit" value="カートに追加" class="cart-button">
					</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="login-box">	
			<form action="LoginServlet" method="post">
				<input type="hidden" name="next" value="logout">
				<input class="logout-button" type="submit" class="logout-button" value="ログアウト">
			</form>	
		</div>
	</main>

		<%@ include file="../../../inc/userFooter.jsp"%>
</body>
</html>