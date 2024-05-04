import React from 'react';
import { Link } from 'react-router-dom';
import { Card } from 'react-bootstrap';

import "../css/Movie.css";

const Movie = ({ id, title, genre, releaseDate, index }) => {
  return (
    <div className="d-flex justify-content-center mt-4">
      <Link to={`/movie/${id}`} className="movie-link">
        <Card className="movie-card">
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
      </Link>
    </div>
  );
}

export default Movie;
