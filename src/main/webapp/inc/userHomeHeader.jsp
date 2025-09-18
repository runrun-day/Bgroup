<%@ page language="java" pageEncoding="UTF-8"%>
<!--動的インクルード-->
<header class = "header center">
<div class="link">
    <p>ようこそ、${account.name}さん！
    <form action="MenuNavigationServlet" method="post" name="next"value="cart">
			<input type="hidden" name="next"value="cart">
			<input type="submit" value="カート">
	</form>
	<form action="MenuNavigationServlet" method="post" name="next"value="orders">
			<input type="hidden" name="next"value="orders">
			<input type="submit" value="注文履歴">
	</form>
	<form action="MenuNavigationServlet" method="post" name="next"value="regular">
			<input type="hidden" name="next"value="regular">
			<input type="submit" value="定期便">
	</form>
	<form action="MenuNavigationServlet" method="post" name="next"value="user">
			<input type="hidden" name="next"value="cart">
			<input type="submit" value="ユーザー情報">
	</form>
	</p>
</div>
</header>