import React from 'react';
import '../style/NavBar.css';
import { NavLink } from 'react-router-dom';

function NavBar() {
  return (
    <nav>
      <div className="nav-container">
        <div className="home">
          <NavLink to="/" activeClassName="active" style={{ color: 'black' }}>
            Home
          </NavLink>
        </div>
        <ul>
          <li>
            <NavLink to="/seat" activeClassName="active">
              좌석현황
            </NavLink>
          </li>
          <li>
            <NavLink to="/login" activeClassName="active">
              로그인
            </NavLink>
          </li>
        </ul>
      </div>
    </nav>
  );
}

export default NavBar;
