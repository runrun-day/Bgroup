<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>カート画面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="../../../inc/loginUserHeader.jsp" />
	<main>
		<c:forEach var="item" items="${cart}">
		<table class="table_non">
			<tr>
				<td>
					<form class="cart-form"	action="MenuNavigationServlet" method="post" style="display: inline;">
						<input type="hidden" name="next" value="updateCart">
						<input type="hidden" name="productName" value="${item.productName}">
						<input type="hidden" name="productId" value="${item.productId}">
						${item.productName} 数量
						<input class="form-narrow" type="number"name="num_${item.productId}" value="${item.num}" min="1">
						定期便 
						<input type="checkbox" name="regular_${item.productId}"value="true" ${item.regularService ? "checked" : ""}> 
						定期期間
						<select class="form-narrow" name="span_${item.productId}">
							<option value="1" ${item.span == 1 ? "selected" : ""}>1</option>
							<option value="2" ${item.span == 2 ? "selected" : ""}>2</option>
							<option value="3" ${item.span == 3 ? "selected" : ""}>3</option>
						</select> ヶ月
					</form>
				</td>
				<td>
					<!-- 削除専用フォーム -->
					<form action="${pageContext.request.contextPath}/MenuNavigationServlet" method="post" style="display: inline;">
						<input type="hidden" name="next" value="deleteCart"> 
						<input type="hidden" name="productName" value="${item.productName}">
						<input type="hidden" name="productId" value="${item.productId}">
						<input class="table-button right" type="submit" value="削除">
					</form>
				</td>
			</tr>
		</table>
		</c:forEach>
		
		<script>
  // changeイベントで自動submit
  document.querySelectorAll(".cart-form input, .cart-form select").forEach(el => {
    el.addEventListener("change", function() {
      this.form.submit();
    });
  });
</script>

		<form action="ConfirmContentServlet" method="post">
			<input type="hidden" name="next" value="order">

			<c:forEach var="item" items="${cart}">
				<input type="hidden" name="num_${item.productId}"
					value="${item.num}">
				<input type="hidden" name="regular_${item.productId}"
					value="${item.regularService}">
				<input type="hidden" name="span_${item.productId}"
					value="${item.span}">
			</c:forEach>
			<div class="btn-two" reverse>
			<input class="action-button" type="submit" value="注文する">
		</form>
		
		<form action="MenuNavigationServlet" method="get">
			<input class="return-button" type="submit" value="戻る">
		</form>
		</div>

	</main>

	<%@ include file="../../../inc/userFooter.jsp"%>
</body>
</html>