import React, { useState } from 'react';
import Modal from './Modal';
import SeatTable from './SeatTable';
// import seatTableArr from '../data/seatTableArr';
import '../style/StudyRoom.css';

function Room() {
  const [tableData, setTableData] = useState({ seatNum: 0, active: false }); //
  const [showModal, setShowModal] = useState(false);
  const [seatTables, setSeatTables] = useState([
    { id: 1, active: true, gender: 'female' },
    { id: 2, active: true, gender: 'male' },
    { id: 3, active: false, gender: '' },
    { id: 4, active: false, gender: '' },
    { id: 5, active: false, gender: '' },
    { id: 6, active: true, gender: 'female' },
    { id: 7, active: false, gender: '' },
    { id: 8, active: true, gender: 'male' },
    { id: 9, active: true, gender: 'female' },
    { id: 10, active: false, gender: '' },
    { id: 11, active: false, gender: '' },
    { id: 12, active: false, gender: '' },
    { id: 13, active: false, gender: '' },
    { id: 14, active: false, gender: '' },
    { id: 15, active: false, gender: '' },
    { id: 16, active: false, gender: '' },
    { id: 17, active: false, gender: '' },
    { id: 18, active: false, gender: '' },
    { id: 19, active: false, gender: '' },
    { id: 20, active: false, gender: '' },
    { id: 21, active: false, gender: '' },
    { id: 22, active: false, gender: '' },
    { id: 23, active: false, gender: '' },
    { id: 24, active: false, gender: '' },
    { id: 25, active: false, gender: '' },
    { id: 26, active: false, gender: '' },
    { id: 27, active: false, gender: '' },
    { id: 28, active: false, gender: '' },
    { id: 29, active: false, gender: '' },
    { id: 30, active: false, gender: '' },
    { id: 31, active: false, gender: '' },
    { id: 32, active: false, gender: '' },
    { id: 33, active: false, gender: '' },
    { id: 34, active: false, gender: '' },
    { id: 35, active: false, gender: '' },
    { id: 36, active: false, gender: '' },
    { id: 37, active: false, gender: '' },
    { id: 38, active: false, gender: '' },
    { id: 39, active: false, gender: '' },
    { id: 40, active: false, gender: '' },
    { id: 41, active: false, gender: '' },
    { id: 42, active: false, gender: '' },
    { id: 43, active: false, gender: '' },
    { id: 44, active: false, gender: '' },
    { id: 45, active: false, gender: '' },
    { id: 46, active: false, gender: '' },
    { id: 47, active: false, gender: '' },
  ]);
  // console.log(seatTables);

  const onClick = (e) => {
    const {
      id,
      dataset: { active },
    } = e.target;
    // console.log(active);

    setTableData({
      ...tableData,
      seatNum: id,
      active,
    });
    setShowModal(true);
  };

  // const openModal = (e) => {
  //   setShowModal(true);
  // };

  const closeModal = () => {
    // console.log('close modal');
    setShowModal(false);
  };

  // 좌석예약 -> 이용권페이지
  const selectSeat = (seatNum) => {
    const id = parseInt(seatNum);
    setSeatTables(
      seatTables.map((table) => (table.id === id ? { ...table, active: !table.active } : table))
    );
    setShowModal(false);
  };

  return (
    <>
      <div className="header-container">
        <h1>좌석 현황</h1>
        <div className="status">
          <div className="status-box">예약가능</div>
          <div className="status-box">이용중</div>
        </div>
      </div>
      <div className="container">
        {/* grid */}
        <div className="table-container">
          {seatTables.map(({ id, active, gender }) => (
            <SeatTable key={id} id={id} active={active} gender={gender} onClick={onClick} />
          ))}
        </div>
        <div className="door-container">
          <span className="door">입구</span>
        </div>
      </div>

      <Modal
        tableData={tableData}
        open={showModal}
        closeModal={closeModal}
        selectSeat={selectSeat}
      />
    </>
  );
}

export default Room;
