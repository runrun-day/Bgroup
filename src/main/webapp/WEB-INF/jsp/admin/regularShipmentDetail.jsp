<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>定期便詳細</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%@ include file="../../../inc/adminHeader.jsp" %>	

	<main>
		<h1 class="title left">注文詳細</h1>
		
		<p>ユーザー名:${orderList.get(0).userName}</p>
		<p>注文日時：${orderList.get(0).orderDate}</p>
		<table class="table_non">
		<tr>
		  <th>商品名</th>
		  <th>個数</th>
		  <th>単価</th>
		  <th>小計</th>
		  <th>定期便</th>
		</tr>
		<c:forEach var="order" items="${orderList}">
		<tr>
		<td>${order.productName}</td>
		<td>${order.num}</td>
		<td>${order.price}</td>
		<td>${order.amount}</td>
		<td>
		<c:if test="${order.span > 0}">
		${order.span}ヵ月
		</c:if>
		</td>
		</tr>
		</c:forEach>
		</table>
		
		<h3>総合計：${total}円</h3>
		
		<form action="OrderListServlet" method="get" name="next"value="back">
			<input type="hidden" name="next"value="back">
			<input type="submit" value="ホームへ戻る" class="home-button">
		</form>
	</main>

	  <%@ include file="../../../inc/adminFooter.jsp" %> 
</body>
</html>