<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ユーザーの削除確認</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%@ include file="../../../inc/adminHeader.jsp" %>
	
	<main>
	<p>該当ユーザーの削除確認</p>
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
							<input type="submit" value="確定">
					</form>
					
					<form action="UserSearchServlet" method="get">
						<input type="hidden" name="next" value="back_2">
						<input type="submit" value="戻る">
					</form>
				</td>
			</table>
	</main>

	<%@ include file="../../../inc/adminFooter.jsp" %>  
</body>
</html>