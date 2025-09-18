<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>定期便</title>
</head>
<body>
    <p>ようこそ、${account.name}さん！</p>
	・定期便注文一覧
	<br>
	<table>
	<tr>
		
		<c:set var="prevDate" value="" scope="page" />

		<c:forEach var="rs" items="${rsList}">
			<c:if test="${rs.date ne prevDate}">
				<p>注文日：${rs.date}</p>
				<c:set var="prevDate" value="${rs.date}" scope="page" />
			</c:if>
			<tr>
			<tr>
                <td>
                <table>
                <tr>
                <th>商品名</th>  
                <th>個数</th>  
                <th>単価</th>  
                <th>金額</th>  
                <th>定期便</th>
		        </tr>  

				<tr>
                <td>・${rs.productName}</td>
				<td>${rs.num}個</td>
				<td>× @${rs.price}</td>
				<td>${rs.amount}円</td>
				<td>${rs.span}ヵ月</td>
                </tr>
                </table>
                </td>
				<td>
					<form action="SubscriptionOrderServlet" method="post" name="next" value="rescission">
						<input type="hidden" name="next"value="rescission"> 
						<input type="hidden" name="regularServiceDetailId" value="${rs.regularServiceDetailId}">
						<input type="submit" value="解除">
					</form>
				</td>
			</tr>
		</c:forEach>
		</table>
	</table>
	合計 ${total}円
	<form action="MenuNavigationServlet" method="post" name="next" value="back">
		<input type="hidden" name="next" value="back">
		<input type="submit" value="戻る">
	</form>
</body>
</html>