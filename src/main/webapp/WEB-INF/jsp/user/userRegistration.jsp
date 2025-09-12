<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ログインTOP</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<header>
	  <jsp:include page="../../../inc/userHeader.jsp"/>
	</header>
	<main>
	<!--エラー時の表示処理-->
		<c:if test="${not empty errorMsg1}">
			<p style="color:red;"><c:out value="${errorMsg1}" /></p>
		</c:if>
	
		<div class="form-buttons">
<!--			戻るはページ移動 -->

			<form action="SignUpServlet" method="post" name="next" value="check">
			 <input type="hidden" name="next" value="check">
				名前<br>
				<input type="text" name="name" value="${form.name}" required><br>
				メールアドレス<br>
				<input type="email" name="email" value="${form.email}" required><br>
				郵便番号<br>
				<input type="text" name="postcode" value="${form.postcode}" required><br>
				電話<br>
				<input type="text" name="tel" value="${form.tel}" required><br>
				住所<br>
				<input type="text" name="address" value="${form.address}" required><br>
				パスワード<br>
				<input type="password" name="passward" value="" required><br>
				確認用パスワード<br>
				<input type="password" name="passward2" value="" required><br>
				<input type="submit" value="登録">
			</form>
		</div>
	</main>
	<footer>
	  <%@ include file="../../../inc/userFooter.jsp" %>	   
	</footer> 
</body>
</html>