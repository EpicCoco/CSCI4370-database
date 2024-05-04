import React, { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import { Container, Row, Col, Card } from 'react-bootstrap';
import axios from "axios";

const AwardDetail = () => {
    const { id } = useParams();
    const [award, setAward] = useState({});
    const [actors, setActors] = useState([]);
    const [movies, setMovies] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/award/${id}`)
            .then(res => {
                setAward(res.data);
                setActors(res.data.actors);
                setMovies(res.data.movies);
            })
            .catch(err => {
                console.error(`Error with award ${id}`, err);
            });
    }, [id]);

    return (
        <Container className="mt-4 justify-content-center">
            <h1 className="mb-4">{award.name ? award.name : "Award Detail"}</h1>
            <Row className="mb-4">
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Award Info</Card.Title>
                            <Card.Text>
                                <p><strong>Description:</strong> {award.description}</p>
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
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
                            <Card.Title>Movies</Card.Title>
                            <Card.Text>
                                <ul className="list-unstyled">
                                    {movies.map((movie, index) => (
                                        <li key={index}>{movie}</li>
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

export default AwardDetail;
