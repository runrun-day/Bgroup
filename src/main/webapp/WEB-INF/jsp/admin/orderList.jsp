<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>注文一覧</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	  <%@ include file="../../../inc/adminHeader.jsp" %>

	<main>
		<h1 class="title left">注文一覧</h1>
			<div class="admin-container">
			<table>
				<tr class="tr-under">
					<th>注文者</th>
					<th>注文日</th>
					<th></th>
				</tr>
				<c:forEach var = "order" items ="${orderList}"> 
				<tr>
					<td>${order.userName}</td>
					<td>${order.date}</td>
					<td class="td-detail">
						<form action="OrderListServlet" method="post">
							<!--注文IDから詳細データ引っ張ってくるYO-->
							<input type="hidden" name="orderId" value="${order.orderId}">
							<input type="submit" class="table-button right" value="詳細">
						</form>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		
		<div class="btn-one">
			<form action="OrderListServlet" method="get" name="next"value="back">
				<input type="hidden" name="next"value="back">
				<input type="submit" class="return-button" value="戻る">
			</form>
		</div>

	</main>

	  <%@ include file="../../../inc/adminFooter.jsp" %>  
</body>
</html>