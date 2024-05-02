import UserContext from "../context/UserContext";
import "../css/App.css";

//component imports
import React, { useContext, useState } from "react";
import { Button, Card, Col, Container, Form, InputGroup, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

// icon imports
import { faUser, faLock } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import axios from "axios";

const SignUp = () => {
  const [loggedIn, setLoggedIn, userData, setUserData] = useContext(UserContext);

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const handleSignUp = async (e) => {
    e.preventDefault();
    
    //on success, go to sign in page
    navigate("/sign-in");
  };

  return (
    <div>
      <Container fluid className="mt-2">
        <Row className="justify-content-md-center align-items-center">
          <Col md={4} className="d-flex justify-content-center">
            <Card style={{ width: "100%", border: "none" }}>
              <Card.Title className="text-center" style={{ fontSize: "36px" }}>
                Sign Up
              </Card.Title>
              <Card.Body>
                <Form onSubmit={handleSignUp}>
                  <Form.Group
                    controlId="formUsername"
                    className="vertical-spacing"
                  >
                    <Form.Label>Username</Form.Label>
                    <div className="custom-focus">
                      <InputGroup>
                        <InputGroup.Text>
                          <FontAwesomeIcon icon={faUser} />
                        </InputGroup.Text>
                        <Form.Control
                          type="text"
                          placeholder={"Username"}
                          value={username}
                          autoComplete="username"
                          onChange={(e) => setUsername(e.target.value)}
                          required
                        />
                      </InputGroup>
                    </div>
                  </Form.Group>

                  <Form.Group controlId="formPassword" className="vertical-spacing">
                    <Form.Label>Password</Form.Label>
                    <div className="custom-focus">
                      <InputGroup>
                        <InputGroup.Text>
                          <FontAwesomeIcon icon={faLock} />
                        </InputGroup.Text>
                        <Form.Control
                          type="password"
                          placeholder={"Password"}
                          value={password}
                          autoComplete="current-password"
                          onChange={(e) => setPassword(e.target.value)}
                          required
                        />
                      </InputGroup>
                    </div>
                    <div style={{padding: "8px"}} />
                  </Form.Group>
                  <Row xs={2} md={1} className="g-1">
                    <Button variant="primary" type="submit">
                      Sign up
                    </Button>
                  </Row>
                </Form>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default SignUp;