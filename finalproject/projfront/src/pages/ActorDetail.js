import React, { useEffect, useState } from 'react';
import { useParams, Link } from "react-router-dom";
import { Container, Row, Col, Card } from 'react-bootstrap';
import axios from "axios";

const ActorDetail = () => {
    const { id } = useParams();
    const [actor, setActor] = useState({});
    const [movies, setMovies] = useState([]);
    const [awards, setAwards] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/actor/${id}`)
            .then(res => {
                setActor(res.data);
            })
            .catch(err => {
                console.error(`Error with actor ${id}`, err);
            });
    }, [id]);

    return (
        <Container className="mt-4 justify-content-center">
            <h1 className="mb-4">{actor.fname ? `${actor.fname} ${actor.lname}` : "Actor Detail"}</h1>
            <Row className="mb-4">
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Actor Info</Card.Title>
                            <Card.Text>
                                <p><strong>Full Name:</strong> {actor.fname} {actor.lname}</p>
                                <p><strong>Age:</strong> {actor.age}</p>
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Movies</Card.Title>
                            <Card.Text>
                                <ul className="list-unstyled">
                                    {movies.map((movie, index) => (
                                        <Link to={`/movie/${movie.id}`} key={index}>
                                            <li>{movie.title}</li>
                                        </Link>
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
                                        <li key={index}>{award.title}</li>
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

export default ActorDetail;
