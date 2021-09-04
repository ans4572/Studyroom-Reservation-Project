import React from 'react';
import '../style/Modal.css';

function Modal({ tableData, open, closeModal, selectSeat }) {
  // console.log('Modal', tableData);
  const { seatNum, active } = tableData;

  return (
    <div className={open ? 'openModal modal' : 'modal'}>
      {open && (
        <section>
          <header>
            header
            <button className="close" onClick={closeModal}>
              x
            </button>
          </header>

          <main>
            {active === 'false' ? `${seatNum}번 좌석 예약하시겠습니까?` : '예약된 좌석입니다'}
          </main>

          <footer>
            <button className="close" onClick={() => selectSeat(seatNum)}>
              확인
            </button>
          </footer>
        </section>
      )}
    </div>
  );
}

export default Modal;
