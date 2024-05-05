import React, { useEffect, useState } from 'react';
import Movie from '../components/Movie';
import axios from "axios";
import User from '../components/User';

const Home = () => {
  const [movies, setMovies] = useState([]);
  const [users, setUsers] = useState([]);

  useEffect(() => {
    // Fetch movies
    axios.get('http://localhost:8080/api/movie')
      .then((response) => {
        setMovies(response.data);
      })
      .catch((error) => {
        console.error(error);
        window.alert("Error loading movies - make sure database is running.")
      });

    // Fetch users
    axios.get('http://localhost:8080/api/user')
      .then((response) => {
        //console.log(response.data);
        setUsers(response.data);
      })
      .catch((error) => {
        console.error(error);
        window.alert("Error loading users - make sure database is running.")
      });
  }, []);

  return (
    <div className="d-flex">
      <div className="flex-grow-1">
        <h1 className='d-flex justify-content-center mt-4'>Movie List</h1>
        <div>
          {movies.map((movie, index) => (
            <Movie
              id={movie.movieId}
              key={index}
              title={movie.title}
              genre={movie.genre}
              releaseDate={movie.releaseDate}
              index={index}
            />
          ))}
        </div>
      </div>
      <div className="flex-grow-1">
        <h1 className='d-flex justify-content-center mt-4'>User List</h1>
        <div>
          {users.map((user, index) => (
            <User
              id={user.userId}
              username={user.username}
              firstName={user.fname}
              lastName={user.lname}
              index={index}
              key={index}
            />
          ))}
        </div>
      </div>
    </div>
  );
}

export default Home;
