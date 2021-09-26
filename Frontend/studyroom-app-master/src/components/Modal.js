import React from 'react';
import '../style/Modal.css';
import axios from 'axios';

function Modal({ open, closeModal, seatNumber }) {
  const seatReservation = () => {
    let seat = {
      seatNumber,
    };
    axios({
      method: 'POST',
      url: 'http://3.38.17.21:8080/reservation',
      // url: 'http://52.79.80.209:8080/reservation',
      data: seat,
      withCredentials: true,
    })
      .then((res) => {
        console.log('orders res', res);
      })
      .catch((err) => console.log('orders err', err.response.data));
  };

  return (
    <div className={open ? 'open modal' : 'modal'}>
      {open && (
        <section>
          <header>
            header
            <button className="close" onClick={closeModal}>
              &times;
            </button>
          </header>

          <main>{seatNumber}번 좌석을 예약하시겠습니까?</main>

          <footer>
            <button className="ok" onClick={seatReservation}>
              확인
            </button>
            <button className="close" onClick={closeModal}>
              취소
            </button>
          </footer>
        </section>
      )}
    </div>
  );
}

export default Modal;
