package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public void findById(@PathVariable int id){
        if (movieService.getMovieById(id)!=null) {
            System.out.println("The movie with id= " + id);
            System.out.println(movieService.getMovieById(id));
        }else{
            System.out.println("Something went wrong");
        }
    }


    @GetMapping(value = "/movie")
    public void findAll(){
        System.out.println("All movies");
        movieService.findAll().forEach(System.out::println);
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
