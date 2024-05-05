import React, { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import { Container, Row, Col, Card } from 'react-bootstrap';
import axios from "axios";

const AwardDetail = () => {
    const { id } = useParams();
    const [award, setAward] = useState({});
    const [actor, setActor] = useState([]);
    const [movie, setMovie] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/award/info/${id}`)
            .then(res => {
                setAward(res.data);
                //console.log("award", res.data);

                axios.get(`http://localhost:8080/api/movie/info/${res.data.movieId}`)
                    .then(res => {
                        setMovie(res.data);
                        //console.log("movie", res.data);
                    })
                    .catch(err => {
                        console.error(`Error with movie ${id}`, err);
                    });

                axios.get(`http://localhost:8080/api/actor/${res.data.actorId}`)
                    .then(res => {
                        setActor(res.data);
                        //console.log("actor", res.data);
                    })
                    .catch(err => {
                        console.error(`Error with actor ${id}`, err);
                    });
            })
            .catch(err => {
                console.error(`Error with award ${id}`, err);
            });
    }, [id]);

    return (
        <Container className="mt-4 justify-content-center">
            <h1 className="mb-4">{award.awardName ? award.awardName : "Award Detail"}</h1>
            <Row className="mb-4">
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>Award Info</Card.Title>
                            <Card.Text>
                                <p><strong>Movie:</strong> {movie.title}</p>
                                {actor.fname ? <p><strong>Actor:</strong> {actor.fname} {actor.lname}</p> : <></>}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}

export default AwardDetail;
