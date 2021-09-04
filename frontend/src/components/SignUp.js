import React from 'react';
import '../style/SignUp.css';

function Signup({ signupInputs, onChange, validate, onSignupSubmit }) {
  const { username, userid, gender, password, checkPassword } = signupInputs;

  return (
    <div className="signup-container">
      <h1>회원가입</h1>
      <h4>이름</h4>
      <input
        type="text"
        name="username"
        value={username}
        minLength="2"
        maxLength="20"
        onChange={onChange}
        placeholder="이름을 입력해주세요."
      />
      <p>{validate.checkUsername()}</p>
      <h4>아이디</h4>
      <input
        type="text"
        name="userid"
        value={userid}
        minLength="6"
        maxLength="12"
        onChange={onChange}
        placeholder="아이디를 입력해주세요."
      />
      <p>{validate.checkUserid()}</p>
      <h4>성별</h4>
      <select value={gender} name="gender" onChange={onChange}>
        <option value="">성별을 선택해주세요.</option>
        <option value="male">남성</option>
        <option value="female">여성</option>
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
      <p>{validate.checkPasswordLength()}</p>
      <input
        type="password"
        name="checkPassword"
        value={checkPassword}
        minLength="6"
        maxLength="20"
        onChange={onChange}
        placeholder="비밀번호를 확인합니다."
      />
      <p>{validate.checkPassword()}</p>
      <button type="submit" onClick={onSignupSubmit}>
        가입하기
      </button>
    </div>
  );
}

export default Signup;
