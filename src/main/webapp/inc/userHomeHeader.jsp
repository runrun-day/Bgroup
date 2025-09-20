<%@ page language="java" pageEncoding="UTF-8"%>
<!--動的インクルード-->
<header class = "header center">
<div class="link">
    <p>ようこそ、${account.name}さん！</p>
    <form action="MenuNavigationServlet" method="post">
			<input type="submit" class="nonbtn" name="next" value="カート">
			<input type="submit" class="nonbtn" name="next" value="注文履歴">
			<input type="submit" class="nonbtn" name="next" value="定期便">
			<input type="submit" class="nonbtn" name="next" value="ユーザー情報">
	</form>
</div>
</header>