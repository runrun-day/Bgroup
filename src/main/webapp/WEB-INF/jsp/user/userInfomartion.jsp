<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ユーザー情報修正</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
	<header>
	  <jsp:include page="../../../inc/loginUserHeader.jsp"/>
	</header>
	
	<main>
	<!--エラー時の表示処理-->
		<c:if test="${not empty errorMsg}">
			<p style="color:red;"><c:out value="${errorMsg}" /></p>
		</c:if>
	
		<div class="form-buttons">

			<form action="UserEditServlet" method="post" name="next" value="check">
			 <input type="hidden" name="next" value="check">
				名前<br>
				<input type="text" name="name" value="${account.name}" required><br>
				メールアドレス<br>
				<input type="email" name="email" value="${account.email}" required><br>
				郵便番号  -(ハイフン)なし7桁<br>
				<input type="text" name="postcode" value="${account.postcode}" required><br>
				電話  -(ハイフン)なし<br>
				<input type="text" name="tel" value="${account.tel}" required><br>
				住所<br>
				<input type="text" name="address" value="${account.address}" required><br>
				パスワード<br>
				<input type="password" name="passward" value="" required><br>
				確認用パスワード<br>
				<input type="password" name="passward2" value="" required><br>
				<input type="submit" value="修正">
			</form>
			
			<form action="LoginServlet" method="post" name="next" value="back">
				<input type="hidden" name="next" value="back">
				<input type="submit" value="戻る">
			</form>
			
		</div>
		
	</main>
	
	<footer>
	  <%@ include file="../../../inc/userFooter.jsp" %>
	</footer>
	 
</body>
</html>