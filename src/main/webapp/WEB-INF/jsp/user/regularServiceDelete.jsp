<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>定期便解除</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	  <jsp:include page="../../../inc/loginUserHeader.jsp"/>

	<main>
	<p class="result-message">登録を解除しました！</p>
	<form action="SubscriptionOrderServlet" method="get">
		<input type="submit" value="ホームに戻る" class="home-botton">
	</form>
		</main>

	  <%@ include file="../../../inc/userFooter.jsp" %>	   
</body>
</html>