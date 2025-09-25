<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新規登録</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%@ include file="../../../inc/adminHeader.jsp"%>

	<main class="main container">
		<h1 class="title left heading">メニュー</h1>
		<div class="admin-container">
			<form action="AdminMenuRoutingServlet" method="post">
				<input type="hidden" name="next"value="prodacts">
				<input class="action-button menu" type="submit" value="商品一覧">
			</form>
			<form action="AdminMenuRoutingServlet" method="post">
			 	<input type="hidden" name="next"value="registration">
				<input class="action-button menu" type="submit" value="商品登録">
			</form>
			<form action="AdminMenuRoutingServlet" method="post">
				<input type="hidden" name="next"value="orders">
				<input class="action-button menu" type="submit" value="注文一覧">
			</form>
			<form action="AdminMenuRoutingServlet" method="post">
			 	<input type="hidden" name="next"value="regular">
				<input class="action-button menu" type="submit" value="定期便一覧">
			</form>
			<form action="AdminMenuRoutingServlet" method="post">
				<input type="hidden" name="next"value="users">
				<input class="action-button menu" type="submit" value="ユーザー検索">
			</form>
			<form action="AdminMenuRoutingServlet" method="post">
			 	<input type="hidden" name="next" value="dashboard">
				<input class="action-button menu" type="submit" value="ダッシュボード">
			</form>
		</div>
		<form class="logout-form" action="AdminLoginServlet" method="post">
			<input type="hidden" name="next" value="logout">
			<input class="logout-button" type="submit" value="ログアウト">
		</form>
	</main>

	  <%@ include file="../../../inc/adminFooter.jsp" %>	   
</body>
</html>