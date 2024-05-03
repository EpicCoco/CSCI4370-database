import React from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap';

const MovieDetail = () => {
    // Arrays for Actors, Awards, and Reviews
    const actors = ['John', 'Mike', 'Dave'];
    const awards = ['Best Picture', 'Best Director', 'Best Actor'];
    const reviews = ['I liked it', 'Mid', 'womp womp'];

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
                                    {actors.map((actor, index) => (
                                        <li key={index}>{actor}</li>
                                    ))}
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
                                    {awards.map((award, index) => (
                                        <li key={index}>{award}</li>
                                    ))}
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
                                    {reviews.map((review, index) => (
                                        <li key={index}>{review}</li>
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

export default MovieDetail;
