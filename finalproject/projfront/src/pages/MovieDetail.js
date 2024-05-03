import React from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap';

const MovieDetail = () => {
    return (
        <Container className="mt-4">
            <h1 className="mb-4">Movie Detail</h1>
            <Row>
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Actors</Card.Title>
                            <Card.Text>
                                <ul className="list-unstyled">
                                    <li>Actor 1</li>
                                    <li>Actor 2</li>
                                    <li>Actor 3</li>
                                    {/* Add more actors here */}
                                </ul>
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Awards</Card.Title>
                            <Card.Text>
                                <ul className="list-unstyled">
                                    <li>Best Picture</li>
                                    <li>Best Director</li>
                                    <li>Best Actor</li>
                                    {/* Add more awards here */}
                                </ul>
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Reviews</Card.Title>
                            <Card.Text>
                                <ul className="list-unstyled">
                                    <li>"A masterpiece!" - New York Times</li>
                                    <li>"Absolutely stunning!" - The Guardian</li>
                                    <li>"Must-watch!" - Variety</li>
                                    {/* Add more reviews here */}
                                </ul>
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}

export default MovieDetail;
