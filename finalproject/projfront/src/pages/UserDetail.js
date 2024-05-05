import React, { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import { Container, Row, Col, Card } from 'react-bootstrap';
import axios from "axios";

const UserDetail = () => {
    const { id } = useParams();
    const [user, setUser] = useState({});
    const [reviews, setReviews] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/user/${id}`)
            .then(res => {
                console.log(res.data);
                setUser(res.data);
            })
            .catch(err => {
                console.error(`Error with user ${id}`, err);
            });
        
    }, [id]);

    return (
        <Container className="mt-4 justify-content-center">
            <h1 className="mb-4">{user.username ? user.username : "User Detail"}</h1>
            <Row className="mb-4">
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>User Info</Card.Title>
                            <Card.Text>
                                <p><strong>Full Name:</strong> {user.fname} {user.lname}</p>
                            </Card.Text>
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
                                        <li key={index}>hi</li>
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
