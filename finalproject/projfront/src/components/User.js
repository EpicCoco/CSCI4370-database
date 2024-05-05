import React from 'react';
import { Link } from 'react-router-dom';
import { Card } from 'react-bootstrap';

import "../css/User.css";

const User = ({ id, username, firstName, lastName, index }) => {
  return (
    <div className="d-flex justify-content-center mt-4">
      <Link to={`/user/${id}`} className="user-link">
        <Card className="user-card">
          <Card.Body>
            <div className="d-flex flex-row">
              <div className="mr-3" style={{ minWidth: '30px' }}>
                <span>{index + 1}</span>
              </div>
              <div>
                <Card.Title>{username}</Card.Title>
                <Card.Text><strong>Name:</strong> {firstName} {lastName}</Card.Text>
              </div>
            </div>
          </Card.Body>
        </Card>
      </Link>
    </div>
  );
}

export default User;
