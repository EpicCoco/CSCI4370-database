import React, { useEffect, useState } from 'react';
import { useParams, useNavigate, Link } from "react-router-dom";
import { Container, Row, Col, Card, Button } from 'react-bootstrap';
import axios from "axios";

const MovieDetail = ({ isLoggedIn }) => {

    const { id } = useParams();
    const navigate = useNavigate();
    //in case movie in different format
    const [movie, setMovie] = useState([]); //testing

    // temporary values for Actors, Awards, and Reviews
    const [actors, setActors] = useState(['John', 'Mike', 'Dave']);
    const [awards, setAwards] = useState(['Best Picture', 'Best Director', 'Best Actor']);
    const [reviews, setReviews] = useState(['I liked it']);
    const [avgReview, setAvgReview] = useState(0.0);

    const handleAddReview = () => {
        //go to review with the movie's info passed thru
        navigate("/review", {
            state: {
                movie: movie
            }
        })
    }

    const getMovieInfo = () => {
        axios.get(`http://localhost:8080/api/movie/info/${id}`)
        .then(res => {
            //console.log("movie data", res.data);
            setMovie(res.data);
        })
        .catch(err => {
            console.error(`Error with movie ${id}`, err);
        });
    }

    const getMovieAwards = () => {
        axios.get(`http://localhost:8080/api/award/movie/${id}`)
        .then(res => {
            //console.log("awards data", res.data);
            setAwards(res.data);
        })
        .catch(err => {
            console.error(`Error with movie ${id} awards`, err);
        });
    }

    const getMovieReviews = () => {
        axios.get(`http://localhost:8080/api/movie/reviews/${id}`)
        .then(res => {
            //console.log("reviews data", res.data);
            setReviews(res.data);
        })
        .catch(err => {
            console.error(`Error with movie ${id} reviews`, err);
        });
    }

    const getMovieActors = () => {
        axios.get(`http://localhost:8080/api/actor/${id}/actors`)
        .then(res => {
            //console.log("actors data", res.data);
            setActors(res.data);
        })
        .catch(err => {
            console.error(`Error with movie ${id} actors`, err);
        });
    }

    const getAverageReview = () => {
        axios.get(`http://localhost:8080/api/movie/avg/${id}`)
        .then(res => {
            //console.log("avg review data", res.data);
            setAvgReview(res.data);
        })
        .catch(err => {
            console.error(`Error with movie ${id} avg`, err);
        });
    }

    useEffect(() => {
        getMovieInfo();
        getMovieAwards();
        getMovieReviews();
        getMovieActors();
        getAverageReview();
      }, []); 

    return (
        <Container className="mt-4 justify-content-center">
            <h1 className="mb-4">{movie.title ? movie.title : "Movie Detail"}</h1>
            <Row className="mb-4">
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Movie Info</Card.Title>
                            <Card.Text>
                                <p><strong>Title:</strong> {movie.title}</p>
                                <p><strong>Genre:</strong> {movie.genre}</p>
                                <p><strong>Average Review:</strong> {avgReview === 0 ? "No reviews yet" : avgReview}</p>
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
                            <ul className="list-unstyled">
                                {actors.map((actor, index) => (
                                    <Link to={`/actor/${actor.actorId}`}><li key={index}>{actor.fname} {actor.lname}</li></Link> 
                                ))}
                            </ul>                 
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Awards</Card.Title>
                            <ul className="list-unstyled">
                                {awards.map((award, index) => (
                                    <Link to={`/award/${award.awardId}`}><li key={index}>{award.awardName}</li></Link> 
                                ))}
                            </ul>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card>
                        <Card.Body>
                            <div className="d-flex justify-content-between align-items-center">
                                <Card.Title>Reviews</Card.Title>
                                <Button variant="secondary" onClick={isLoggedIn ? handleAddReview : () => navigate("/sign-in")}>Add review</Button>
                            </div>
                            <Card.Subtitle>[Showing 20 most recent]</Card.Subtitle>
                            <ul className="list-unstyled">
                                {reviews.map((review, index) => (
                                    <li key={index}>{review.text} - {review.rating}</li>
                                ))}
                            </ul>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}

export default MovieDetail;
