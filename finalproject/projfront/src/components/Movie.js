import React from 'react';
import { Link } from 'react-router-dom';
import { Card } from 'react-bootstrap';

import "../css/Movie.css";

const Movie = ({ title, genre, releaseDate, index }) => {
  return (
    <div className="d-flex justify-content-center mt-4">
      <Card className="movie-card" style={{ width: '70%' }}>
        <Card.Body>
          <div className="d-flex flex-row">
            <div className="mr-3" style={{ minWidth: '30px' }}>
              <span>{index + 1}</span>
            </div>
            <div>
              <Card.Title>{title}</Card.Title>
              <Card.Text><strong>Genre:</strong> {genre}</Card.Text>
              <Card.Text><strong>Release Date:</strong> {releaseDate}</Card.Text>
            </div>
          </div>
        </Card.Body>
      </Card>
    </div>
  );
}

export default Movie;
