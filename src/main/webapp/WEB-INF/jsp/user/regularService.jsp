<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>定期便</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	  <jsp:include page="../../../inc/loginUserHeader.jsp"/>
	
	<main>
	・定期便注文一覧
	<br>
	<c:forEach var="entry" items="${regularServiceList}">
		<p>注文日：${entry.key}</p>
		<table>
			<tr>
				<td>商品名</td>
				<td>個数</td>
				<td>単価</td>
				<td>金額</td>
				<td>定期便</td>
			</tr>
			<c:forEach var="rs" items="${entry.value}">
				<tr>
					<td>・${rs.productName}</td>
					<td>${rs.num}個</td>
					<td>× @${rs.price}</td>
					<td>${rs.amount}円</td>
					<td>${rs.span}ヵ月</td>
				</tr>
			</c:forEach>
		</table>
		
		<p>合計金額：${dailyTotals[entry.key]}円</p>
		
		<!-- 注文日単位での解除ボタン -->
		<form action="SubscriptionOrderServlet" method="post">
			<input type="hidden" name="next" value="rescission">
			<input type="hidden" name="orderDate" value="${entry.key}">
			<!-- 複数商品の場合でもregularServiceIdは同じになるので、-->
			<!--	 最初の1つだけを取得-->
			<input type="hidden" name="regularServiceId" value="${entry.value[0].regularServiceId}">
			<input type="submit" value="解除">
		</form>
	</c:forEach>

	<form action="MenuNavigationServlet" method="get">
		<input type="hidden" name="next" value="back">
		<input type="submit" value="戻る">
	</form>
	</main>

	  <%@ include file="../../../inc/userFooter.jsp" %>	   
</body>
</html>