import React from "react";
import UserContext from "./context/UserContext";

//CSS
import "bootstrap/dist/css/bootstrap.min.css";
import "./css/App.css";

import TopNavbar from "./components/TopNavbar";

import { useState } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import Home from "./pages/Home";
import SignIn from "./pages/SignIn";
import SignUp from "./pages/SignUp";
import MovieDetail from "./pages/MovieDetail";
import ActorDetail from "./pages/ActorDetail";
import AwardDetail from "./pages/AwardDetail";
import UserDetail from "./pages/UserDetail";
import MakeReview from "./pages/MakeReview";
import NotFound from "./pages/NotFound";


function App() {
  const [loggedIn, setLoggedIn] = useState(false);
  const [userData, setUserData] = useState({});

  return (
    <UserContext.Provider value={[loggedIn, setLoggedIn, userData, setUserData]}>
      <BrowserRouter>
        <TopNavbar isLoggedIn={loggedIn} userData={userData} />
        <Routes>
          <Route index element={<Home />} />
          
          <Route path="/sign-in" element={<SignIn />} />
          <Route path="/sign-up" element={<SignUp />} />
          <Route path="/movie/:id" element={<MovieDetail isLoggedIn={loggedIn}/>} />
          <Route path="/actor/:id" element={<ActorDetail />} />
          <Route path="/award/:id" element={<AwardDetail />} />
          <Route path="/user/:id" element={<UserDetail userData={userData} setLoggedIn={setLoggedIn}/>} />

          {/** <Route path="/movie/:id" element={<MovieDetail isLoggedIn={loggedIn} />} /> */}
          <Route path="*" element={<NotFound />} />
          {loggedIn && 
          <>
          <Route path="/review/" element={<MakeReview userData={userData} />} />
          </>}
        </Routes>
      </BrowserRouter>
    </UserContext.Provider>
  );
}

export default App;