package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        movieService.createTable();
        this.movieService = movieService;
    }

    @PostMapping(value = "/movie")
    public void createMovie(@RequestBody Movie movie){
        if (movieService.createMovie(movie)==1) {
            System.out.println("Creating Movie");
        }else{
            System.out.println("Something went wrong");
        }
    }

    @PutMapping(value = "/movie/{id}")
    public void updateMovie(@RequestBody Movie movie,@PathVariable int id){
        if (movieService.updateMovie(movie,id)==1) {
            System.out.println("Updating movie");
        }else{
            System.out.println("Something went wrong");
        }
    }

    @GetMapping(value = "/movie/{id}")
    public ResponseEntity<Movie> findById(@PathVariable int id){
        Movie movie=movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }


    @GetMapping(value = "/movie")
    public ResponseEntity<List<Movie>> findAll(){
        List<Movie> movies=movieService.findAll();
        return ResponseEntity.ok(movies);
    }


    @DeleteMapping(value = "/movie/{id}")
    public void deleteMovie(@PathVariable int id){
        if(movieService.deleteMovie(id)==1){
            System.out.println("Deleting the movie");
        }else{
            System.out.println("Something went wrong");
        }
    }



}
