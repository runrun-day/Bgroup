<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品一覧画面</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<style>
	.header.admin,
	.footer.admin{
		background-color: #ccc;
	}
	
	
	.table-button{
background-color: #007bff;
      color: white;
      border: none;
      padding: 5px 10px;
      cursor: pointer;
      border-radius: 4px;
	}
	
	.table-button:hover {
      background-color: #0056b3;
    }
	
	.return-button{
	background-color: blue;
    color: white;
    border: none;
    padding: 5px 10px;
    cursor: pointer;
    border-radius: 4px;


    }
     table {
      border-collapse: collapse;
      border: 2px solid #333;
      margin-top: 20px;
      width: 300px;
    }

    td {
      border: 1px solid #999;
      padding: 10px;
      text-align: center;
    }
    
    
</style>
	
	
</head>
<body>
	  <%@ include file="../../../inc/adminHeader.jsp" %>
	
	<main>
		<h1 class="title left">商品一覧</h1>	
		<c:if test="${not empty errorMsg}">
			<p style="color:red;"><c:out value="${errorMsg}" /></p>
		</c:if>
		
		<c:forEach var = "product" items ="${products}">
			<table>
				<tr>
					<td>${product.name}</td>
					<td>
						<form action="ProductListServlet" method="post" name="next" value="edit">
							<input type="hidden" name="next" value="edit">
							<input type="hidden" name="productId" value="${product.productId}">
							<input class=table-button type="submit" value="編集">
						</form>
					</td>
				</tr>
			</table>
		</c:forEach>
					
		<form action="AdminMenuRoutingServlet" method="get">
			<input class=return-button type="submit" value="戻る">
		</form>
	</main>

	  <%@ include file="../../../inc/adminFooter.jsp" %>
</body>
</html>