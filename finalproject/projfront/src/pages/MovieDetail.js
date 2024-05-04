import React, { useEffect, useState } from 'react';
import { useParams, Link } from "react-router-dom";
import { Container, Row, Col, Card } from 'react-bootstrap';
import axios from "axios";

const MovieDetail = () => {

    const { id } = useParams();
    const [movie, setMovie] = useState([]); //in case movie in different format

    // temporary values for Actors, Awards, and Reviews
    const [actors, setActors] = useState(['John', 'Mike', 'Dave']);
    const [awards, setAwards] = useState(['Best Picture', 'Best Director', 'Best Actor']);
    const [reviews, setReviews] = useState(['I liked it', 'Mid', 'womp womp']);

    useEffect(() => {
        //want to get movie detail on page load
        axios.get(`http://localhost:8080/api/movie/${id}`)
        .then(res => {
            setMovie(res.data);
            setActors(res.data.actors);
            setAwards(res.data.awards);
            setReviews(res.data.reviews);
        })
        .catch(err => {
            console.error(`Error with movie ${id}`, err);
        });
      }); //may need to re-add dependency array

    return (
        <Container className="mt-4 justify-content-center">
            <h1 className="mb-4">{movie.title ? movie.title : "Movie Detail"}</h1>
            <Row>
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Actors</Card.Title>
                            <Card.Text>
                                <ul className="list-unstyled">
                                    {/** How I think I wanna do linking to actor page */}
                                    {actors.map((actor, index) => (
                                        <Link to="/"><li key={index}>{actor}</li></Link> 
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
