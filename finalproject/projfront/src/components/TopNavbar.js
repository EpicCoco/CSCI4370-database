import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Navbar, Nav, Button } from 'react-bootstrap';

const TopNavbar = ({ isLoggedIn, userData }) => {
  const navigate = useNavigate();

  const handleSignIn = () => {
    navigate("/sign-in")
  }

  return (
    <Navbar expand="lg" className="navbar fixed-top bg-primary" sticky="top" bg="light" data-bs-theme="light" style={{ padding: "10px"}}>
      <Navbar.Brand as={Link} to="/">Home</Navbar.Brand>
      {isLoggedIn ?
      <Navbar.Brand as={Link} to={`/user/${userData.userId}`}>Profile</Navbar.Brand>
      :<></>}

      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="mx-auto">        
        </Nav>

        <Nav className="ml-auto">
          {isLoggedIn ? 
          (<Button variant="outline-primary" href="/">Sign Out</Button>)
          : (<Button variant="outline-primary" onClick={handleSignIn}>Sign In</Button>)
          }
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
}

export default TopNavbar;
