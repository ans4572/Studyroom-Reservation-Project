import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../style/SignUp.css';
import axios from 'axios';

function Login() {
  const [loginInputs, setLoginInputs] = useState({
    loginId: '',
    password: '',
  });
  const { loginId, password } = loginInputs;

  const onChange = (e) => {
    const { name, value } = e.target;
    setLoginInputs({
      ...loginInputs,
      [name]: value,
    });
  };

  const fetch = () => {
    let body = {
      loginId,
      password,
    };

    // .post('http://52.79.80.209:8080/login', body, { withCredentials: true })
    axios({
      method: 'POST',
      url: 'http://52.79.80.209:8080/login',
      data: body,
      withCredentials: true,
    })
      .then((response) => console.log(response))
      .catch((error) => console.log('error', error));
  };

  const login = () => {
    console.log(loginId, password);
    if (loginId !== '' && password !== '') {
      fetch();
    } else {
      console.log('모두 입력해주세요.');
    }
  };

  return (
    <div className="login-container">
      <h1>로그인</h1>
      <input
        name="loginId"
        type="text"
        placeholder="아이디 입력"
        value={loginId}
        onChange={onChange}
      />
      <input
        name="password"
        type="password"
        placeholder="비밀번호 입력"
        value={password}
        onChange={onChange}
      />
      <button type="submit" onClick={login}>
        로그인
      </button>
      <h5>
        아직 계정이 없으신가요?
        {/* link or history */}
        <Link to="/signup" className="signup">
          가입하기
        </Link>
        {/* 아직 계정이 없으신가요? <a href="() => false">가입하기</a> */}
      </h5>
      <hr />
    </div>
  );
}

export default Login;
