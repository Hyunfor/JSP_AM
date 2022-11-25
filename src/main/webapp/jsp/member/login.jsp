<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>

	<h1>로그인</h1>
	
	<script>
		function LoginForm_submit(form){
			form.loginId.value = form.loginId.value.trim(); // 공백
			
			// .value를 붙여야 꺼내온것임.
			if(form.loginId.value.length == 0){ // 입력안한 상태
				alert('아이디를 입력해주세요')
				form.loginId.focus();
				return;
			}
			
			form.loginPw.value = form.loginPw.value.trim(); 
			
			if(form.loginPw.value.length == 0){ 
				alert('비밀번호를 입력해주세요')
				form.loginPw.focus();
				return;
			}
			
			form.submit(); // 검증 후 통과
			
		}
	</script>
												<!-- script에서 검증 후 -->
	<form action="doLogin" method="POST" onsubmit="LoginForm_submit(this); return false;">
	
		<div>
			로그인 아이디 : <input name="loginId" placeholder="아이디를 입력해주세요."  type="text" />
		</div>
		<div>
			로그인 비밀번호 : <input name="loginPw" placeholder="비밀번호를 입력해주세요."  type="password" />
		</div>
		
		<div>
			<button type="submit">로그인</button>
			<a href="../home/main">취소</a>
		</div>
		
	</form>	
	
</body>
</html>