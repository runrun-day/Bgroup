<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>定期便一覧</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%@ include file="../../../inc/adminHeader.jsp" %>

	<main>
		<p>定期便一覧</p>
		<table>
			<tr>
				<th>注文者</th>
				<th>注文日</th>
				<th></th>
				
			</tr>
			<c:forEach var = "order" items ="${orderList}"> 
			<tr>
				<td>${order.userName}</td>
				<td>${order.date}</td>
				<td>
					<form action="SubscriptionListServlet" method="post">
						<!--注文IDから詳細データ引っ張ってくるYO-->
						<input type="hidden" name="rsorderId" value="${order.regularServiceId}">
						<input type="submit" value="詳細">
					</form>
					</td>
			</tr>
			</c:forEach>
			
		</table>
		<form action="OrderListServlet" method="get" name="next"value="back">
			<input type="hidden" name="next"value="back">
			<input type="submit" value="戻る">
		</form>

	</main>

	<%@ include file="../../../inc/adminFooter.jsp" %> 

</body>
</html>