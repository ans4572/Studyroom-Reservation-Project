import React from 'react';
import { Route, Switch } from 'react-router';
import Home from '../components/Home';
import BuyVoucher from '../components/BuyVoucher';
import MyPage from '../components/MyPage';
import NavBar from '../components/NavBar';
import StudyRoom from '../components/StudyRoom';

function Main() {
  return (
    <>
      <NavBar />
      <Switch>
        <Route exact path="/" component={Home} />
        <Route exact path="/seats" component={StudyRoom} />
        <Route exact path="/buy" component={BuyVoucher} />
        <Route exact path="/mypage" component={MyPage} />
      </Switch>
    </>
  );
}

export default Main;
