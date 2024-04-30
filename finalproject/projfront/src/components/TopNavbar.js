import React from 'react';
import { Link } from 'react-router-dom';
import { Navbar, Nav, Form, FormControl, Button, InputGroup } from 'react-bootstrap';

const TopNavbar = ({ isLoggedIn }) => {
  return (
    <Navbar expand="lg" className="navbar fixed-top bg-primary" sticky="top" bg="light" data-bs-theme="light">
      <Navbar.Brand as={Link} to="/">Home</Navbar.Brand>

      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="mx-auto">
        <InputGroup>
          <Form.Control
            placeholder="Search movies..."
            aria-label="Search"
            aria-describedby="basic-addon1"
          />
          <Button variant="outline-primary">Search</Button>
        </InputGroup>
        </Nav>

        <Nav className="ml-auto">
          <Button variant="outline-primary">Sign In</Button>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
}

export default TopNavbar;
