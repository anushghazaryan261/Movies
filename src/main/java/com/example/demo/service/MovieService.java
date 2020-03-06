package com.example.demo.service;

import com.example.demo.constants.MariadbConstants;
import com.example.demo.model.Movie;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    public void createTable(){
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL, MariadbConstants.User, MariadbConstants.PASS)) {
            String query="CREATE TABLE IF NOT EXISTS movies(\n" +
                    "    id int NOT NULL AUTO_INCREMENT,\n" +
                    "    name varchar(255),\n" +
                    "    duration varchar(255),\n" +
                    "    PRIMARY KEY (id)\n" +
                    ")";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public int createMovie(Movie movie){
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="INSERT INTO movies (name,duration) VALUES (?,?)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,movie.getName());
            preparedStatement.setString(2,movie.getDuration());
            return preparedStatement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public int updateMovie(Movie movie,int id){
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="UPDATE movies SET name=(?),duration=(?) WHERE id=(?)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,movie.getName());
            preparedStatement.setString(2,movie.getDuration());
            preparedStatement.setInt(3,id);
            return preparedStatement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public int deleteMovie(int id){
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="DELETE FROM movies WHERE id=(?)";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public Movie getMovieById(int id){
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            List<Movie> all = findAll();
            for (Movie movie : all) {
                if (movie.getId() == id) {
                    return movie;
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public List<Movie> findAll(){
        List<Movie> movies=new ArrayList<>();
        try(Connection con= DriverManager.getConnection(MariadbConstants.URL,MariadbConstants.User,MariadbConstants.PASS)){
            String query="SELECT * FROM movies";
            PreparedStatement preparedStatement=con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String duration=resultSet.getString("duration");
                Movie movie=new Movie(id,name,duration);
                movies.add(movie);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return movies;
    }
}
