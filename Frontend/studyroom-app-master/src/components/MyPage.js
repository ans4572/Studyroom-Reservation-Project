import React from 'react';
import '../style/MyPage.css';
import { BsPerson } from 'react-icons/bs';
import axios from 'axios';
import { useHistory } from 'react-router';

function MyPage() {
  const history = useHistory();

  const exit = () => {
    window.confirm('퇴실하시겠습니까? 자동 로그아웃됩니다') &&
      axios
        .delete('http://3.38.17.21:8080/reservation', { withCredentials: true })
        // .delete('http://52.79.80.209:8080/reservation')
        .then((res) => {
          console.log('exit res', res);
          sessionStorage.removeItem('isAuthorized');
          alert('로그아웃이 완료됐습니다');
          history.push('/login');
        })
        .catch((err) => console.log('exit err', err.response.data));
  };

  const askSeatNumber = () => {
    let test = true;
    while (test) {
      const seatNumber = window.prompt(
        '몇 번 좌석으로 이동하시겠습니까? (1-47번 중 숫자만 입력해 주세요)'
      );
      if (!isNaN(seatNumber) && seatNumber < 48 && seatNumber !== '') {
        console.log('not NaN /', seatNumber, '/', typeof seatNumber);
        test = false;
        return seatNumber;
      }
    }
  };

  const changeSeat = () => {
    const seatNumber = askSeatNumber();
    axios({
      method: 'PUT',
      url: 'http://3.38.17.21:8080/reservation',
      data: { seatNumber },
      withCredentials: true,
    })
      .then((res) => {
        console.log('exit res', res);
        alert(`${seatNumber}번으로 좌석 이동 완료. 자동 로그아웃됩니다`);
        sessionStorage.removeItem('isAuthorized');
        history.push('/login');
      })
      .catch((err) => console.log('exit err', err.response.data));
  };

  const logout = () => {
    window.confirm('로그아웃하시겠습니까?') &&
      axios
        .delete('http://3.38.17.21:8080/users/logout')
        // .delete('http://52.79.80.209:8080/users/logout')
        .then((res) => {
          console.log('logout', res);
          sessionStorage.removeItem('isAuthorized');
          alert('로그아웃이 완료됐습니다');
          history.push('/login');
        })
        .catch((err) => console.log('orders err', err.response.data));
  };

  return (
    <div className="mypage-container">
      <div>
        <div className="user">
          <div className="img">
            <BsPerson size="60" />
          </div>
          <div className="info">
            <span>{'홍길동'}님</span>
            <span>{'id'}</span>
          </div>
        </div>
        <div className="mpbtn-container">
          <button onClick={exit}>퇴실</button>
          <button onClick={changeSeat}>자리이동</button>
          <button onClick={logout}>로그아웃</button>
        </div>
      </div>

      <div className="userVoucher">이용 가능한 시간: {1}시간</div>
    </div>
  );
}

export default MyPage;
