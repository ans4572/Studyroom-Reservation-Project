import React from 'react';

function SeatTable({ id, active, gender, onClick }) {
  return (
    <>
      <div
        id={id}
        data-active={active}
        className="table"
        // className={`table${id}`}
        style={{
          display: 'flex',
          fontSize: 14,
          fontWeight: 700,
          padding: '2px 0 0 4px',
          boxSizing: 'border-box',
          backgroundColor: active ? 'lightblue' : 'beige',
          cursor: 'pointer',
          borderRadius: 6,
        }}
        onClick={onClick}
      >
        {id}{' '}
        <span
          style={{
            fontSize: 20,
            padding: '0 0 0 5px',
            color: gender === 'male' ? 'blue' : 'red',
          }}
        >
          {active ? (gender === 'male' ? '♂' : '♀') : null}
        </span>
      </div>
    </>
  );
}

export default SeatTable;
