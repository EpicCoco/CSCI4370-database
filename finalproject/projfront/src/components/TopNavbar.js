import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Navbar, Nav, Form, Button, InputGroup } from 'react-bootstrap';

const TopNavbar = ({ isLoggedIn, onSearch }) => {
  const navigate = useNavigate();

  const [searchQuery, setSearchQuery] = useState('');

  const handleSearchClick = () => {
    onSearch(searchQuery);
  }

  const handleSignIn = () => {
    navigate("/sign-in")
  }

  return (
    <Navbar expand="lg" className="navbar fixed-top bg-primary" sticky="top" bg="light" data-bs-theme="light" style={{ padding: "10px"}}>
      <Navbar.Brand as={Link} to="/">Home</Navbar.Brand>

      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="mx-auto">
        {/** can put search bar back in if decide to fully implement
        <InputGroup>
          <Form.Control
            placeholder="Search movies..."
            aria-label="Search"
            aria-describedby="basic-addon1"
            onChange={(e) => setSearchQuery(e.target.value)}
          />
          <Button variant="outline-primary" onClick={handleSearchClick}>Search</Button>
        </InputGroup> 
        */}
        
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
