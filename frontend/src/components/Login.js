import React from 'react';
import '../style/SignUp.css';

function Login() {
  return (
    <div className="login-container">
      <h1>로그인</h1>
      <input name="id" type="text" placeholder="아이디 입력" />
      <input name="password" type="password" placeholder="비밀번호 입력" />
      <button type="submit">로그인</button>
      <h5>
        아직 계정이 없으신가요? <a href="#!">가입하기</a>
        {/* 아직 계정이 없으신가요? <a href="() => false">가입하기</a> */}
      </h5>
      <hr />
    </div>
  );
}

export default Login;
