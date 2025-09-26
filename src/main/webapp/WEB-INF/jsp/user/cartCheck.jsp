<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>注文確認画面</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Dela+Gothic+One&family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="../../../inc/loginUserHeader.jsp" />
	<main>
		<c:if test="${not empty msg}">
			<p style="color:red;"><c:out value="${msg}" /></p>
		</c:if>
	
		<form action="SubscriptionOrderServlet" method="post">
			<input type="hidden" name="next" value="orderCommit"> 
			<p>注文確認</p>
			${account.name}さん<br> 
			〒${account.postcode}<br>
			${account.address}<br> 
			${account.tel}<br>
	
			<table class="table_non">
				<tr>
					<td>商品</td>
					<td>個数</td>
					<td>単価</td>
					<td>金額</td>
					<td>定期便</td>
				</tr>
				<c:forEach var="item" items="${cart}">
					<tr>
						<td>・${item.productName}</td>
						<td>${item.num}個</td>
						<td>${item.price}円</td>
						<td>${item.amount}円</td>
						<td><c:choose>
								<c:when test="${item.regularService}">
	      						${item.span} ヶ月
	    						</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose></td>
	
					</tr>
				</c:forEach>
			</table>
			<p>合計 ${totalAmount} 円</p>
			<div class="btn-two" reverse>
			<input class="action-button" type="submit" value="確定">
		</form>
		
		<form action="ConfirmContentServlet" method="get">
			<input class="return-button" type="submit" value="戻る">
		</form>
		</div>
		
	</main>
	<%@ include file="../../../inc/userFooter.jsp"%>
</body>
</html>