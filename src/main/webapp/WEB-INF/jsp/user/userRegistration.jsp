<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>新規登録</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%@ include file="../../../inc/newSubmitHeader.jsp"%>

	<main>
		<!--エラー時の表示処理-->
		<c:if test="${not empty errorMsg}">
			<p style="color: red;">
				<c:out value="${errorMsg}" />
			</p>
		</c:if>

			<form action="SignUpServlet" method="post" name="next" value="check">
				<input type="hidden" name="next" value="check"> <br>
				<div class="text-box"><p>名前</p></div>
				<input class="form-wide" type="text" name="name"	value="${form.name}" required> <br>
				
				<div class="text-box"><p>メールアドレス</p></div> 
				<input class="form-wide" type="email" name="email" value="${form.email}"	required><br>
				
				<div class="text-box"><p>郵便番号 -(ハイフン)なし7桁</p></div>  
				<input class="form-wide" type="text" name="postcode"	value="${form.postcode}" required> <br>
				
				<div class="text-box"><p>電話 -(ハイフン)なし</p></div>
				<input class="form-wide" type="text" name="tel" value="${form.tel}" required><br>
				
				<div class="text-box"><p>住所</p></div> 
				<input class="form-wide" type="text" name="address" value="${form.address}" required><br>
				
				<div class="text-box"><p>パスワード</p></div>  
				<input class="form-wide" type="password"	name="passward" value="" required><br>
				
				<div class="text-box"><p>確認用パスワード</p></div> 
					<input class="form-wide" type="password" name="passward2" value="" required><br>
				<div class="btn-two reverse">
					<input class="action-button" type="submit" value="登録">
			</form>

					<form action="LoginServlet" method="post" name="next" value="back">
						<input type="hidden" name="next" value="back">
						<input class="return-button" type="submit" value="戻る"><br>
					</form>
				</div>
	</main>
	<%@ include file="../../../inc/userFooter.jsp"%>
</body>
</html>