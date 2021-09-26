import React, { useEffect, useState } from 'react';
// import Modal from './Modal';
import SeatTable from './SeatTable';
import '../style/StudyRoom.css';
import axios from 'axios';
import { useHistory } from 'react-router';

function Room() {
  // const [showModal, setShowModal] = useState(false);
  const [seats, setSeats] = useState([
    { num: 1, name: '', gender: '' },
    { num: 2, name: '', gender: '' },
    { num: 3, name: '', gender: '' },
    { num: 4, name: '', gender: '' },
    { num: 5, name: '', gender: '' },
    { num: 6, name: '', gender: '' },
    { num: 7, name: '', gender: '' },
    { num: 8, name: '', gender: '' },
    { num: 9, name: '', gender: '' },
    { num: 10, name: '', gender: '' },
    { num: 11, name: '', gender: '' },
    { num: 12, name: '', gender: '' },
    { num: 13, name: '', gender: '' },
    { num: 14, name: '', gender: '' },
    { num: 15, name: '', gender: '' },
    { num: 16, name: '', gender: '' },
    { num: 17, name: '', gender: '' },
    { num: 18, name: '', gender: '' },
    { num: 19, name: '', gender: '' },
    { num: 20, name: '', gender: '' },
    { num: 21, name: '', gender: '' },
    { num: 22, name: '', gender: '' },
    { num: 23, name: '', gender: '' },
    { num: 24, name: '', gender: '' },
    { num: 25, name: '', gender: '' },
    { num: 26, name: '', gender: '' },
    { num: 27, name: '', gender: '' },
    { num: 28, name: '', gender: '' },
    { num: 29, name: '', gender: '' },
    { num: 30, name: '', gender: '' },
    { num: 31, name: '', gender: '' },
    { num: 32, name: '', gender: '' },
    { num: 33, name: '', gender: '' },
    { num: 34, name: '', gender: '' },
    { num: 35, name: '', gender: '' },
    { num: 36, name: '', gender: '' },
    { num: 37, name: '', gender: '' },
    { num: 38, name: '', gender: '' },
    { num: 39, name: '', gender: '' },
    { num: 40, name: '', gender: '' },
    { num: 41, name: '', gender: '' },
    { num: 42, name: '', gender: '' },
    { num: 43, name: '', gender: '' },
    { num: 44, name: '', gender: '' },
    { num: 45, name: '', gender: '' },
    { num: 46, name: '', gender: '' },
    { num: 47, name: '', gender: '' },
  ]);
  const history = useHistory();

  useEffect(() => {
    axios
      .get('http://3.38.17.21:8080/seats', { withCredentials: true })
      .then((res) => {
        console.log(res.data);
        const newData = res.data;
        const newSeatArr = newData.map((t) => ({
          num: t.seatNumber,
          name: t.name,
          gender: t.gender,
        }));
        const result = newSeatArr.concat(
          seats.filter(({ num }) => !newSeatArr.find((f) => f.num === num))
        );
        const sortResult = result.sort((a, b) => a.num - b.num);
        // console.log('sort result', sortResult);
        setSeats(sortResult);
      })
      .catch((err) => console.log(err.response.data));
  }, []);

  // console.log('rendering');

  // const openModal = (num) => {
  //   setSeatNumber(num);
  //   setShowModal(true);
  // };

  // const closeModal = () => {
  //   setShowModal(false);
  // };

  const seatReservation = (seatNumber) => {
    window.confirm(
      `${seatNumber}좌석을 예약하시겠습니까? 자동 로그아웃 됩니다.`
    ) &&
      axios({
        method: 'POST',
        url: 'http://3.38.17.21:8080/reservation',
        // url: 'http://52.79.80.209:8080/reservation',
        data: { seatNumber },
        withCredentials: true,
      })
        .then((res) => {
          console.log('orders res', res);
          sessionStorage.removeItem('isAuthorized');
          alert('좌석 예약 완료');
          history.push('/login');
        })
        .catch((err) => {
          console.log('orders err', err.response.data);
          const errMsg1 = '아직 퇴실하지 않는 정보가 있습니다.';
          const errMsg2 = '이용권이 없습니다.';
          if (err.response.data.message === errMsg1) alert(errMsg1);
          if (err.response.data.message === errMsg2) alert(errMsg2);
        });
  };

  return (
    <>
      <div className="studyroom-container">
        <div className="header-container">
          <h1>좌석 현황</h1>
          <div className="status">
            <div className="status-box">예약가능</div>
            <div className="status-box">이용중</div>
          </div>
        </div>
        <div className="container">
          <div className="table-container">
            {seats.map((seat) => (
              <SeatTable
                key={seat.num}
                num={seat.num}
                gender={seat.gender}
                onClick={() => {
                  seatReservation(seat.num);
                }}
              />
            ))}
          </div>
          <div className="door-container">
            <span className="door">입구</span>
          </div>
        </div>
      </div>

      {/* <Modal open={showModal} closeModal={closeModal} seatNumber={seatNumber} /> */}
    </>
  );
}

export default Room;
