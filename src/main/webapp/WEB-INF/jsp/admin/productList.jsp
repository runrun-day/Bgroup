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
</head>
<body>
	  <%@ include file="../../../inc/adminHeader.jsp" %>
	
	<main class="main container">
		<h1 class="title left">商品一覧</h1>	
		<c:if test="${not empty errorMsg}">
			<p style="color:red;"><c:out value="${errorMsg}" /></p>
		</c:if>
		<div class="admin-container">
			<c:forEach var = "product" items ="${products}">
				<table>
					<tr>
						<td class="name">${product.name}</td>
						<td>
							<form action="ProductListServlet" method="post" name="next" value="edit">
								<input type="hidden" name="next" value="edit">
								<input type="hidden" name="productId" value="${product.productId}">
								<input class="table-button right" type="submit" value="編集">
							</form>
						</td>
					</tr>
				</table>
			</c:forEach>
		</div>
		<div class="btn-one">			
			<form action="AdminMenuRoutingServlet" method="get">
				<input class="return-button" type="submit" value="戻る">
			</form>
		</div>
	</main>

	  <%@ include file="../../../inc/adminFooter.jsp" %>
</body>
</html>