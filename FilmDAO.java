/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataModel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Justin
 */

// Getters and setters for each part of the film. Name, description, rating, price
public class FilmDAO
{
    private String name;
    private String rating;
    private String description;
    private Double rentalPrice;
    private static final Logger log = Logger.getLogger(FilmDAO.class.getName());

    /**
     * No argument constructor for the class FilmDAO
     */
    public FilmDAO()
    {
    
    }
    
    /**
     *
     * @param filmName name of the film
     * @param filmRating rating of the film
     * @param des description of the film
     * @param price price to rent the film
     */
    public FilmDAO(String filmName, String filmRating, String des, Double price)
    {
        name = filmName;
        rating = filmRating;
        description = des;
        rentalPrice = price;
    }
    
    /**
     *
     * @return name of the film
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name name of the film
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return rating of the film
     */
    public String getRating() {
        return rating;
    }

    /**
     *
     * @param rating rating of the film
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     *
     * @return description of the film
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description description of the film
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return price to rent the film
     */
    public Double getRentalPrice() {
        return rentalPrice;
    }

    /**
     *
     * @param rentalPrice price to rent the film
     */
    public void setRentalPrice(Double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
    
}
