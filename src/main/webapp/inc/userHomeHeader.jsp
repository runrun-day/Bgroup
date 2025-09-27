<%@ page language="java" pageEncoding="UTF-8"%>
<!--動的インクルード-->
<header class = "header center">
<style>
.link{
	display:flex;
	justify-content: flex-end;
	align-items: center;
}
.link p{
	font-size: 1.5em;
}
.link form{
	display:flex;
	justify-content: flex-end;
}

input.nonbtn {
  background: none;        /* 背景なし */
  border: none;            /* 枠線なし */
  color: #000721;          
  padding: 0;              
  margin: 0 10px;          
  cursor: pointer;         /* カーソルをリンク風に */
  text-decoration: underline; /* 下線 */
  font-size: 1.5em;
}
/* ホバー時の色変化 */
input.nonbtn:hover {
  color: #005799;
  text-decoration: underline;
}
</style>
<div class="link">
    <p>ようこそ、${account.name}さん！</p>
    <form action="MenuNavigationServlet" method="post">
			<input class="nonbtn" type="submit"  name="next" value="カート">
			<input class="nonbtn" type="submit" name="next" value="注文履歴">
			<input class="nonbtn" type="submit" name="next" value="定期便">
			<input class="nonbtn" type="submit" name="next" value="ユーザー情報">
	</form>
</div>
</header>