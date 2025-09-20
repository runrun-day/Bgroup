<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>カート画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	  <jsp:include page="../../../inc/loginUserHeader.jsp"/>

<main>
	<c:forEach var="product" items="${cart}" > 
		<p>商品名
			数量<input type="number" name="quantity" value="1" min="1">
			定期便<input type="checkbox">する
			<select name="span">
				<option>1</option>
				<option>2</option>
				<option>3</option>
			</select>ヶ月 
			<form action="#" method="post">
				<input type="submit" value="削除">
			</form>
		</p>
	</c:forEach>
	<form action="MenuNavigationServlet" method="get">
		<input type="submit" value="戻る">
	</form>
	<form action="ConfirmContentServlet" method="post">
		<input type="submit" value="注文する">
	</form>
</main>

	  <%@ include file="../../../inc/userFooter.jsp" %> 
</body>
</html>