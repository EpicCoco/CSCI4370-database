import React, { useEffect, useState } from 'react';
import Movie from '../components/Movie';
import axios from "axios";

const Home = () => {
  // temporary list of movies until get get proper routes
  /**  const movies = [
    { title: 'Fast and Furious', genre: 'Action', releaseDate: '2023-01-01' },
    { title: 'Ace Ventura', genre: 'Comedy', releaseDate: '2023-02-15' },
    { title: 'The Shawshank Redemption', genre: 'Drama', releaseDate: '2023-03-30' },
  ]; */

  const [movies, setMovies] = useState([]);

  
  useEffect(() => {
    axios.get('http://localhost:8080/api/movie/movies')
      .then((response) => {
        setMovies(response.data);
      })
      .catch((error) => {
        console.error(error);
        window.alert("Error loading movies - make sure database is running.")
      });
  }, []);
  

  return (
    <div>
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
      <div style={{padding: "20px"}} />
    </div>
  );
}

export default Home;
