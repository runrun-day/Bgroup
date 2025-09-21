<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザー検索</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%@ include file="../../../inc/adminHeader.jsp"%>

    <main>
        <h1>ユーザー検索</h1>
		<!--ユーザーが見つからない場合のエラー表示-->
		<c:if test="${not empty errorMsg}">
			<p style="color:red;"><c:out value="${errorMsg}" /></p>
		</c:if>

        <form action="UserSearchServlet" method="post">
        	<input type="hidden" name="next"value="search">
            <label for="tel">電話番号</label><br>
            <input type="text" id="tel" name="tel" required><br><br>
   			<input  class="action-button" type="submit" value="検索">
        </form>
        
        <form action="UserSearchServlet" method="get">
        	<input type="hidden" name="next"value="">
			<input type="submit" value="戻る">
		</form>
    </main>

	<%@ include file="../../../inc/adminFooter.jsp"%>
</body>
</html>