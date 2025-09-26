<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>注文履歴</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<style>
.underline {
      border-bottom: 2px solid #b3b1b1;
      padding:10px;
	  width:700px;
	  }
</style>
</head>
<body>
	  <jsp:include page="../../../inc/loginUserHeader.jsp"/>
	
	<main>
		
		<h1 class="title left">注文履歴一覧</h1>
		<c:forEach var="entry" items="${orderHistoryMap}">
	    <p class="title left">注文日：${entry.key}</p>
	    <table> 
	        <tr>
	            <td>商品名</td>
	            <td>個数</td>
	            <td>単価</td>
	            <td>金額</td>
	            <td>定期便</td>
	        </tr>
	        <c:forEach var="item" items="${entry.value}">
	            <tr>
	                <td>${item.productName}</td>
	                <td>${item.num}個</td>
	                <td>@${item.price}</td>
	                <td>${item.amount}円</td>
	                <td>
					  <c:if test="${item.span > 0}">
					    ${item.span}ヵ月
					  </c:if>
	                </td>
	            </tr>
	            
	       
	        </c:forEach>
	    </table>
	    <div class="underline"></div>
	</c:forEach>
	
		<form action="MenuNavigationServlet" method="get">
			<input type="hidden" name="next" value="home">
			<input type="submit" value="ホームに戻る" class="home-button">
		</form>
	</main>

	  <%@ include file="../../../inc/userFooter.jsp" %>	   
</body>
</html>