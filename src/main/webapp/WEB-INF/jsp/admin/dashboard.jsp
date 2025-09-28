<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ダッシュボード</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%@ include file="../../../inc/adminHeader.jsp" %>
	<h1 class="title">ダッシュボード</h1>	
	<main>
	<div class="admin-container">
		<form class="center"action="#" method="">
			<input type="hidden" name="next"value="">
			<input class="menu-button" type="submit" value="購入額ランキング">
		</form>
		<form class="center" action="#" method="">
			<input type="hidden" name="next"value="">
			<input class="menu-button" type="submit" value="取引数推移">
		</form>
		</div>
		<form class="login-box" action="AdminMenuRoutingServlet" method="get">
			<input type="hidden" name="next"value="">
			<input class="home-button " type="submit" value="ホームへ戻る" value="home-button">
		</form>
	</main>
	<%@ include file="../../../inc/adminFooter.jsp" %>
</body> 
</html>