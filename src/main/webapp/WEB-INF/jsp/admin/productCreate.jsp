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
 	<%@ include file="../../../inc/adminHeader.jsp" %>	

	
	<main>
		<h1 class="title left">商品登録</h1>	
		<c:if test="${not empty errorMsg}">
			<p style="color:red;"><c:out value="${errorMsg}" /></p>
		</c:if>
		
		<form action="ProductRegistrationServlet" method="post" name="next"value="check" enctype="multipart/form-data">
			<input type="hidden" name="next"value="check">
		
			<div class="text-box"><p>名前</p></div>
			<input class="form-wide" type="text" name="name" value="" required><br>
				
			<div class="text-box"><p>画像</p></div>
			<input type="file" name="image" value="" required><br>
		
			<div class="text-box"><p>価格</p></div>
			<input class="form-narrow" type="number" min="1" max="10000" name="price" required> 円<br>
			
		<div class="btn-two reverse">
			<input class="action-button" type="submit" value="登録">
		</form>
			
			<form action="ProductListServlet" method="get">
			<input class="return-button" type="submit" value="戻る">
			</form>
		</div>	
	</main>

 	<%@ include file="../../../inc/adminFooter.jsp" %>a
</body>
</html>