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

<main>
<h1 class="title">ユーザー情報確認</h1>
名前：<br>
メールアドレス：<br>
電話番号：<br>
郵便番号：<br>
住所：<br>
パスワード：<br>

<form action="#" method="post">
	<input type="hidden" name="next" value="back">
	<input type="submit" value="戻る">
	
</form>

<form action="#" method="post">
<input type="submit" value="確定">
</form>

</main>

	  <%@ include file="../../../inc/userFooter.jsp" %>
</body>
</html>