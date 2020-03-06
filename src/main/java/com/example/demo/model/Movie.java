package com.example.demo.model;

import java.util.Objects;

public class Movie {
    private int id=0;
    private String name;
    private String duration;


    public Movie(int id, String name, String duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public Movie(String name, String duration) {
        this.name = name;
        this.duration = duration;
        id++;
    }

    public Movie(String name) {
        this.name = name;
        id++;
    }

    public Movie() {
        id++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                Objects.equals(name, movie.name) &&
                Objects.equals(duration, movie.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
