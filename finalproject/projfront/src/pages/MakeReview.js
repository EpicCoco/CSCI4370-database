import React, { useState } from 'react';
import { Container, Form, Button, Row, Col, Card, InputGroup } from 'react-bootstrap';
import { useNavigate, useLocation } from "react-router-dom";
import axios from 'axios';

const MakeReview = ({ userData }) => {
    const navigate = useNavigate();
    const location = useLocation();
    // Take in movie from prev screen - need id to make post request
    const { movie } = location.state || {};

    const [content, setContent] = useState('');
    const [rating, setRating] = useState(1);

    const handleSubmit = async (event) => {
        event.preventDefault();

        let values = {
            userID: userData.userId,
            movieID: movie.movieId,
            rating: rating, 
            text: content
        };

        let config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: 'http://localhost:8080/api/review',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            params: values
        };

        axios.request(config)
            .then((response) => {
                console.log(response.data);
                navigate(`/movie/${movie.movieId}`)
            })
            .catch((error) => {
                console.error("failed to post review", error);
            })

    };

    return (
        <div>
            <Container fluid className="mt-2">
                <Row className="justify-content-md-center align-items-center">
                    <Col md={4} className="d-flex justify-content-center">
                        <Card style={{ width: "100%", border: "none" }}>
                            <Card.Title className="text-center" style={{ fontSize: "36px" }}>
                                Make a Review
                            </Card.Title>
                            <Card.Text className="text-center">
                                {`What are your thoughts on ${movie.title}?`}
                            </Card.Text>
                            <Card.Body>
                                <Form onSubmit={handleSubmit}>
                                    <Form.Group controlId="formContent" className="vertical-spacing">
                                        <div className="custom-focus">
                                            <InputGroup>
                                                <Form.Control
                                                    as="textarea"
                                                    rows={2}
                                                    placeholder="Enter your review"
                                                    value={content}
                                                    onChange={(e) => setContent(e.target.value)}
                                                    required
                                                />
                                            </InputGroup>
                                        </div>
                                    </Form.Group>
                                    <div style={{ padding: "8px" }} />
                                    <Form.Group controlId="formRating" className="vertical-spacing">
                                        <Form.Label>Rating: {rating}</Form.Label>
                                        <input
                                            type="range"
                                            className="form-range"
                                            min={1}
                                            max={5}
                                            value={rating}
                                            onChange={(e) => setRating(parseInt(e.target.value))}
                                        />
                                    </Form.Group>
                                    <div style={{ padding: "8px" }} />
                                    <Button variant="primary" type="submit" style={{ width: "100%" }}>
                                        Submit
                                    </Button>
                                </Form>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
            </Container>
        </div>
    );
}

export default MakeReview;
