<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ユーザー修正確認画面</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	  <jsp:include page="../../../inc/loginUserHeader.jsp"/>

<<main>
	<!--エラー時の表示処理-->
		<c:if test="${not empty errorMsg}">
			<p style="color:red;"><c:out value="${errorMsg}" /></p>
		</c:if>

		<div class="">
			<h1>ユーザー情報確認</h1>
			<table>
				<tr>
					<th>氏名</label></th>
					<td>${form.name}</td>
				</tr>
				<tr>
					<th>メールアドレス</th>
					<td>${form.email}</td>
				</tr>
				<tr>
					<th>電話番号</th>
					<td>${form.tel}</td>
				</tr>
				<tr>
					<th><label for="">郵便番号</label></th>
					<td>${form.postcode}</td>
				</tr>
				<tr>
					<th>住所</th>
					<td>${form.address}</td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td>●●●●●●●●●●●●</td>
				</tr>
				<td colspan="2">
					<form action="SubscriptionOrderServlet" method="post">
						<input type="hidden" name="next" value="update">
						<input type="submit" value="確定">
					</form>
					<form action="UserEditServlet" method="post">
						<input type="hidden" name="next" value="back_2">
						<input type="submit" value="戻る">
					</form>
				</td>
			</table>
		</div>
	</main>

	  <%@ include file="../../../inc/userFooter.jsp" %>
</body>
</html>