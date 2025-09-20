<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登録完了</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	  <%@ include file="../../../inc/newSubmitHeader.jsp" %>
	<main>
		<h1>ユーザー登録しました！</h1>
		<p>ログイン画面より再度ログインをお願いします。</p>
		
		<form action="SignUpServlet" method="get" name="next" value="back">
			<input type="submit" value="ホームに戻る">
		</form>
		</main>
		
	  <%@ include file="../../../inc/userFooter.jsp" %>	   
</body>
</html>