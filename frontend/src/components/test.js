// 회원가입 상태

import React, { useState } from 'react';
import './App.css';
// import HeaderBar from './components/HeaderBar.js';
// import Room from './components/Room.js';
// import Login from './components/Login';
import SignUp from './components/SignUp';

const App = () => {
  const [signupInputs, setSignupInputs] = useState({
    username: '',
    userid: '',
    gender: '',
    password: '',
    checkPassword: '',
  });
  const { username, userid, gender, password, checkPassword } = signupInputs;

  const onChange = (e) => {
    const { name, value } = e.target;
    setSignupInputs({
      ...signupInputs,
      [name]: value,
    });
  };

  // input값 return값 체크
  // 공백제거 & 아이디: 4-12자의 영문 대소문자와 숫자로만 입력
  const validate = {
    checkUsername: () => {
      return username
        ? username.length < 2 || username.length > 20
          ? '이름은 2자 이상, 20자 이하로 입력하세요.'
          : null
        : null;
    },
    checkUserid: () => {
      // 첫번째자리숫자인지확인
      // const intId = parseInt(userid[0]);
      // if (isNaN(intId)) return 'NaN!';
      // if (typeof intId === 'number') return 'number!';
      //
      // 자리수 특수기호 (중복검사?)
      const pattern_num = /[0-9]/; // 숫자
      const pattern_eng = /[a-zA-Z]/; // 문자
      const pattern_spc = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
      return userid
        ? userid.length >= 6 && pattern_eng.test(userid) && !pattern_spc.test(userid)
          ? null
          : '아이디는 6-12자의 영문과 숫자만 사용 가능합니다.'
        : null;
    },
    checkGender: () => gender !== '' || null,
    checkPasswordLength: () => {
      return password
        ? password.length <= 6 || password.length > 20
          ? '비밀번호는 6자 이상, 20자 이하로 입력하세요.'
          : null
        : null;
    },
    // 중요하진 않은데 길이조건만족시 비밀번호확인 둘 다? 아니면 따로
    checkPassword: () => {
      return checkPassword
        ? password !== checkPassword
          ? '비밀번호가 일치하지 않습니다.'
          : null
        : null;
    },
  };

  // input 모두 입력, 조건 만족
  const onSignupSubmit = () => {
    // const date = new Date(); 가입일?
    console.log('name', username, validate.checkUsername());
    console.log('userid', userid, validate.checkUserid());
    console.log('gender', gender, validate.checkGender());
    console.log('pw', password, validate.checkPasswordLength());
    console.log('checkPw', checkPassword, validate.checkPassword());
    if (
      username !== '' &&
      userid !== '' &&
      gender !== '' &&
      password !== '' &&
      checkPassword !== ''
    ) {
      console.log('all not null', signupInputs);
    } else {
      console.log('null', signupInputs);
    }
  };

  return (
    <>
      <SignUp
        signupInputs={signupInputs}
        onChange={onChange}
        validate={validate}
        onSignupSubmit={onSignupSubmit}
      />
    </>
  );
};

export default App;
