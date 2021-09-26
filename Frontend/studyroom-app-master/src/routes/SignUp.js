import axios from 'axios';
import React, { useState, useEffect } from 'react';
import '../style/SignUp.css';

function Signup({ history }) {
  useEffect(() => {
    console.log('signup effect');
    console.log('history', history);
  }, [history]);

  const [signupInputs, setSignupInputs] = useState({
    name: '',
    age: '',
    loginId: '',
    gender: '',
    password: '',
    checkPassword: '',
  });
  const { name, loginId, age, gender, password, checkPassword } = signupInputs;

  const onChange = (e) => {
    const { name, value } = e.target;
    setSignupInputs({
      ...signupInputs,
      [name]: value,
    });
  };

  function fetchSignUp() {
    let body = {
      name,
      age,
      gender,
      loginId,
      password,
    };

    axios
      .post('http://3.38.17.21:8080/users', body)
      // .post('http://52.79.80.209:8080/users', body)
      .then((response) => {
        console.log('signup response', response);
        alert('가입 완료됐습니다.');
      })
      .catch((error) => console.log('error', error));
  }

  const checkUsername = () => {
    if (!name) return;
    if (name === '') return;
    if (name.length < 2 || name.length > 20) {
      return '이름은 2자 이상, 20자 이하로 입력하세요.';
    }
    return true;
  };

  const checkUserid = () => {
    // const pattern_num = /[0-9]/; // 숫자
    // const pattern_eng = /[a-zA-Z]/; // 문자
    const pattern_spc = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
    const pattern_chr = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
    if (!loginId) return;
    if (loginId === '') return;
    if (
      loginId.length < 4 ||
      pattern_chr.test(loginId) ||
      pattern_spc.test(loginId)
    ) {
      return '아이디는 4-12자의 영문과 숫자만 사용 가능합니다.';
    }
    return true;
  };

  const checkAge = () => {
    if (age === '') return;
    return true;
  };

  const checkGender = () => {
    if (gender === '') return;
    return true;
  };

  const checkPW = () => {
    if (!password) return;
    if (password === '') return;
    if (password.length < 6 || password.length > 20) {
      return '비밀번호는 6자 이상, 20자 이하로 입력하세요.';
    }
    if (password !== checkPassword) return '비밀번호가 일치하지 않습니다.';
    return true;
  };

  const onSignupSubmit = () => {
    console.log('name', name, '/ 유효성검사', checkUsername());
    console.log('id', loginId, '/ 유효성검사', checkUserid());
    console.log('age', age, '/ 유효성검사', checkAge());
    console.log('gender', gender, '/ 유효성검사', checkGender());
    console.log('password', password, '/ 유효성검사', checkPW());
    console.log('checkPassword', checkPassword, '/ 유효성검사', checkPW());

    if (
      checkUsername() === true &&
      checkUserid() === true &&
      checkAge() === true &&
      checkGender() === true &&
      checkPW() === true
    ) {
      console.log('가입완료');
      fetchSignUp();
      // 로그인페이지로 이동
      history.push('/login');
    } else {
      console.log('빈문자열or조건불만족');
    }
  };

  return (
    <div className="signup-container">
      <h1>회원가입</h1>
      <h4>이름</h4>
      <input
        type="text"
        name="name"
        value={name}
        minLength="2"
        maxLength="20"
        onChange={onChange}
        placeholder="이름을 입력해주세요."
      />
      <p className="check">{checkUsername()}</p>
      <h4>아이디</h4>
      <input
        type="text"
        name="loginId"
        value={loginId}
        maxLength="12"
        onChange={onChange}
        placeholder="아이디를 입력해주세요."
      />
      <p className="check">{checkUserid()}</p>
      <h4>나이</h4>
      <input
        type="number"
        name="age"
        value={age}
        min="0"
        max="100"
        onChange={onChange}
        placeholder="나이를 입력해주세요."
      />
      <h4>성별</h4>
      <select value={gender} name="gender" onChange={onChange}>
        <option value="">성별을 선택해주세요.</option>
        <option value="MALE">남성</option>
        <option value="FEMALE">여성</option>
      </select>
      <h4>비밀번호</h4>
      <input
        type="password"
        name="password"
        value={password}
        minLength="6"
        maxLength="20"
        onChange={onChange}
        placeholder="비밀번호를 입력해주세요."
      />
      <p className="check">{checkPW()}</p>
      <input
        type="password"
        name="checkPassword"
        value={checkPassword}
        minLength="6"
        maxLength="20"
        onChange={onChange}
        placeholder="비밀번호를 확인합니다."
      />
      <p className="check">{checkPW()}</p>
      <button className="signup-btn" type="submit" onClick={onSignupSubmit}>
        가입하기
      </button>
    </div>
  );
}

export default Signup;
