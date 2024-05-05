import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from "react-router-dom";
import { Container, Row, Col, Card, Button, Form } from 'react-bootstrap';
import axios from "axios";

const UserDetail = ({ userData, setLoggedIn }) => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [user, setUser] = useState({});
    const [reviews, setReviews] = useState([]);

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");

    useEffect(() => {
        axios.get(`http://localhost:8080/api/user/${id}`)
            .then(res => {
                //console.log(res.data);
                setUser(res.data);
                setFirstName(res.data.fname);
                setLastName(res.data.lname);
            })
            .catch(err => {
                console.error(`Error with user ${id}`, err);
            });

        axios.get(`http://localhost:8080/api/user/reviews/${id}`)
            .then(res => {
                //console.log("user's reviews:", res.data);
                setReviews(res.data);
            })
            .catch(err => {
                console.error(`Error with user ${id} reviews`, err);
            });
        
    }, [id]);

    const handleDeleteUser = () => {
        if (userData.userId === user.userId) {
            axios.delete(`http://localhost:8080/api/user/${id}`)
                .then((res) => {
                    console.log("user deleted", res);
                    setLoggedIn(false);
                    navigate("/");
                })
                .catch(err => {
                    console.error(`Error deleting user ${id}`, err);
                });
        } else {
            window.alert("Can't delete account right now.");
        }
    };

    const handleUpdateUser = () => {
        if (userData.userId === user.userId) {
            let values = {
                firstName: firstName,
                lastName: lastName
            };
            let config = {
                method: 'put',
                maxBodyLength: Infinity,
                url: `http://localhost:8080/api/user/update/${id}`,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                params: values
            };
            axios.request(config)
            .then((response) => {
                console.log("success");
            })
            .catch((error) => {
                console.log(error);
                window.alert("Something went wrong. Please try again later.")
            });
        } else {
            window.alert("Can't update account right now.");
        }
    }

    return (
        <Container className="mt-4 justify-content-center">
            <h1 className="mb-4">{user.username ? user.username : "User Detail"}</h1>
            <Row className="mb-4">
                <Col>
                    <Card>
                        <Card.Body className="d-flex justify-content-between align-items-center">
                            <div>
                                <Card.Title>User Info</Card.Title>
                                <p><strong>Full Name:</strong> {user.fname} {user.lname}</p>
                            </div>
                            {userData.userId === user.userId ? 
                            <Button variant="danger" onClick={handleDeleteUser}>Delete my account</Button>
                            : <></>}
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            {userData.userId === user.userId ?
            <Row className="mb-4">
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Change User Info</Card.Title>
                            <Form onSubmit={handleUpdateUser}>
                                <Form.Group controlId="formFirstName">
                                    <Form.Label>First Name</Form.Label>
                                    <Form.Control type="text" placeholder="First name" value={firstName} onChange={(e) => setFirstName(e.target.value)} />
                                </Form.Group>
                                <Form.Group controlId="formLastName">
                                    <Form.Label>Last Name</Form.Label>
                                    <Form.Control type="text" placeholder="Last name" value={lastName} onChange={(e) => setLastName(e.target.value)} />
                                </Form.Group>
                                <Button variant="primary" type="submit">
                                    Update Name
                                </Button>
                            </Form>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            : <></>}
            <Row>
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Reviews</Card.Title>
                            <Card.Subtitle>[Showing 20 most recent]</Card.Subtitle>
                            <ul className="list-unstyled">
                                {reviews.map((review, index) => (
                                    <li key={index}>{review.text}</li>
                                ))}
                            </ul>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}

export default UserDetail;
