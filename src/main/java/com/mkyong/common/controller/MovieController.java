package com.mkyong.common.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mkyong.common.model.MovieDTO;

@Controller
public class MovieController {
    
	@RequestMapping(value = "/movies", method = RequestMethod.GET)
	@ResponseBody
	public List<MovieDTO> getMovie() {

		List<MovieDTO> listmovies=new ArrayList<MovieDTO>();
    	try{
    		
            Class.forName("com.mysql.jdbc.Driver");

            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/nilesh","root","root");
//here sonoo is the database name, root is the username and root is the password
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from movies");
            while(rs.next()){
            	MovieDTO m1 =new MovieDTO();
            	m1.setId(rs.getInt(1));
            	m1.setMovieName(rs.getString(2));
            	
            	listmovies.add(m1);
            }
            con.close(); 
        }catch(Exception e){ System.out.println(e);}
    	
    	return listmovies;
	}
	
	@RequestMapping(value = "/movies", method = RequestMethod.POST)
	@ResponseBody
	public void addMovie(@RequestBody MovieDTO movie)
	{
    	
        try{
    		
            Class.forName("com.mysql.jdbc.Driver");

            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/nilesh","root","root");
//here sonoo is the database name, root is the username and root is the password
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select max(id) from movies");
            rs.next();
            Integer ID = rs.getInt(1) + 1;
            String sql = "INSERT INTO movies (id,movieName)  VALUES ('" +ID + "','" + movie.getMovieName() + "')";
            stmt.executeUpdate(sql);
            con.close(); 
        }catch(Exception e){ System.out.println(e);}
		
    	
	}
	@RequestMapping(value = "/movies", method = RequestMethod.PUT)
	@ResponseBody
	public void updateMovie(@RequestBody MovieDTO movie)
	{
    	
    	try{
    		
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/nilesh","root","root");
//here sonoo is the database name, root is the username and root is the password
            Statement stmt=con.createStatement();

            stmt.executeUpdate("update movies set movieName =  '" + movie.getMovieName() + " '  where id =  '" + movie.getId() + " ' ");
            con.close(); 
        }catch(Exception e){ System.out.println(e);}
    	
    	
		
	}
	@RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteMovie(@PathVariable("id") int id)
	{
    	
    	try{
    		
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/nilesh","root","root");
//here sonoo is the database name, root is the username and root is the password
            Statement stmt=con.createStatement();

            stmt.executeUpdate("delete from movies where id =  '" + id + " ' ");
            con.close(); 
        }catch(Exception e){ System.out.println(e);}
    	
    			
	}

}