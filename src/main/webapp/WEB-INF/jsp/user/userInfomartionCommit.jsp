<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>情報修正完了</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="../../../inc/loginUserHeader.jsp"/>
		
	<main>
		<p class="result-message">ユーザー情報修正しました！</p><br>
		 <a class="home-button" href="UserEditServlet">ホームに戻る</a>
	</main>

	<%@ include file="../../../inc/userFooter.jsp"%>
</body>
</html>