<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ユーザー情報修正</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
	<jsp:include page="../../../inc/loginUserHeader.jsp" />

	<main>
		<!--エラー時の表示処理-->
		<c:if test="${not empty errorMsg}">
			<p style="color: red;">
				<c:out value="${errorMsg}" />
			</p>
		</c:if>

		<form action="UserEditServlet" method="post">
			<input type="hidden" name="next" value="check"> 
			<div class="text-box">名前</div>
			<input class="form-wide" type="text" name="name" value="${account.name}" required><br> 
			
			<div class="text-box">メールアドレス</div>
			<input class="form-wide" type="email" name="email" value="${account.email}" required><br>
			
			<div class="text-box">郵便番号 -(ハイフン)なし7桁</div> 
			<input class="form-wide" type="text" name="postcode" value="${account.postcode}" required><br>
			
			<div class="text-box">電話 -(ハイフン)なし</div>
			<input class="form-wide" type="text" name="tel" value="${account.tel}" required><br> 
			
			<div class="text-box">住所</div>
			<input class="form-wide" type="text" name="address" value="${account.address}" required><br> 
			
			<div class="text-box">パスワード</div>
			<input class="form-wide" type="password" name="passward" value=""required><br> 
			
			<div class="text-box">確認用パスワード</div> 
			<input class="form-wide" type="password" name="passward2" value="" required><br>
			
			<div class="btn-two reverse">
				<input type="submit" value="修正" class="action-button">
		</form>

			<form action="UserEditServlet" method="post">
				<input type="hidden" name="next" value="back_1"> 
				<input class="return-button" type="submit" value="戻る" >
			</form>
		</div>

	</main>

	<%@ include file="../../../inc/userFooter.jsp"%>
</body>
</html>