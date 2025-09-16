<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー検索</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<header>
		<%@ include file="inc/adminHeader.jsp"%>
	</header>

	<main>
		<h2>ユーザー検索</h2>

		<form action="#" method="post">
			<label for="tel">電話番号</label><br> <input type="text" id="tel"
				name="tel"   required><br> <br>
			<button type="submit" action="#" class="return-button">戻る</button>
			<button type="submit" action="#" class="action-button">検索</button>
		</form>
	</main>
	</main>

	<footer>
		<%@ include file="inc/adminFooter.jsp"%>
	</footer>
</body>
</html>