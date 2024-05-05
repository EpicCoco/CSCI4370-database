import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from "react-router-dom";
import { Container, Row, Col, Card, Button } from 'react-bootstrap';
import axios from "axios";

const UserDetail = ({ userData }) => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [user, setUser] = useState({});
    const [reviews, setReviews] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/user/${id}`)
            .then(res => {
                //console.log(res.data);
                setUser(res.data);
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
                    navigate("/");
                })
                .catch(err => {
                    console.error(`Error deleting user ${id}`, err);
                });
        } else {
            window.alert("Can't delete account right now.");
        }
    };

    return (
        <Container className="mt-4 justify-content-center">
            <h1 className="mb-4">{user.username ? user.username : "User Detail"}</h1>
            <Row className="mb-4">
                <Col>
                    <Card>
                        <Card.Body className="d-flex justify-content-between align-items-center">
                            <div>
                                <Card.Title>User Info</Card.Title>
                                <Card.Text>
                                    <p><strong>Full Name:</strong> {user.fname} {user.lname}</p>
                                </Card.Text>
                            </div>
                            {userData.userId === user.userId ? 
                            <Button variant="danger" onClick={handleDeleteUser}>Delete my account</Button>
                            : <></>}
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Reviews</Card.Title>
                            <Card.Text>
                                <ul className="list-unstyled">
                                    {reviews.map((review, index) => (
                                        <li key={index}>{review.text}</li>
                                    ))}
                                </ul>
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}

export default UserDetail;
