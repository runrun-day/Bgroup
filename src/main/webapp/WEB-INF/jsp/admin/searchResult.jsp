<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登録内容確認</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<header>
	  <jsp:include page="../../../inc/userHeader.jsp"/>
	</header>
	<main>
	<!--エラー時の表示処理-->
		<c:if test="${not empty errorMsg}">
			<p style="color:red;"><c:out value="${errorMsg}" /></p>
		</c:if>

		<div class="">
			<h1>ユーザー情報確認</h1>
			<table>
				<tr>
					<th>氏名</label></th>
					<td>${userInfo.name}</td>
				</tr>
				<tr>
					<th>メールアドレス</th>
					<td>${userInfo.email}</td>
				</tr>
				<tr>
					<th>電話番号</th>
					<td>${userInfo.tel}</td>
				</tr>
				<tr>
					<th><label for="">郵便番号</label></th>
					<td>${userInfo.postcode}</td>
				</tr>
				<tr>
					<th>住所</th>
					<td>${userInfo.address}</td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td>●●●●●●●●●●●●</td>
				</tr>
				<td colspan="2">
					<form action="UserSearchServlet" method="post" name="next" value="deletCheck">
							<input type="hidden" name="next" value="deletCheck">
							<input type="hidden" name="userId" value="${userInfo.userId}">
							<input type="submit" value="削除">
					</form>
					
					<form action="UserSearchServlet" method="get">
						<input type="hidden" name="next" value="back_1">
						<input type="submit" value="戻る">
					</form>
				</td>
			</table>
		</div>
	</main>
	<footer>
	  <%@ include file="../../../inc/userFooter.jsp" %>	   
	</footer> 
</body>
</html>