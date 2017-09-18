/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasejavafx;

import dataModel.FilmDAO;
import inputOutput.ConnectionData;
import inputOutput.PostgreSQLConnect;
import inputOutput.XmlParser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn; 
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Justin
 */

public class DatabaseJavaFx extends Application
{
    private static final Logger logger = Logger.getLogger(DatabaseJavaFx.class.getName());
    private ObservableList list = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage primaryStage)
    {
        logger.log(Level.INFO, "Setting up table.");
        
        // Setting up GUI
        TableView tableView = new TableView();
        tableView.setEditable(false);
        
        final Label label = new Label("Films");
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
        
        TableColumn title = new TableColumn("Title");
            title.setMinWidth(200);
            title.setCellValueFactory(new PropertyValueFactory<FilmDAO, String>("name"));
        
        TableColumn description = new TableColumn("Description");
            description.setMinWidth(700);
            description.setCellValueFactory(new PropertyValueFactory<FilmDAO, String>("description"));

                
        TableColumn rating = new TableColumn("Rating");
            rating.setMinWidth(100);
            rating.setCellValueFactory(new PropertyValueFactory<FilmDAO, String>("rating"));

                        
        TableColumn rentalRate = new TableColumn("Rental Rate");
            rentalRate.setMinWidth(100);
            rentalRate.setCellValueFactory(new PropertyValueFactory<FilmDAO, Double>("rentalPrice"));
            
            // Add columns to the table view
            tableView.getColumns().addAll(title, description, rating, rentalRate);
            
            final Button fetch = new Button("Fetch films from database");
            fetch.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    // Fetching data from database
                    fetchData(tableView);
                    logger.log(Level.SEVERE, "Finished fetching films from DB");
                }
            });
            
        Scene scene = new Scene(new Group());
        
        VBox box = new VBox();
            
        box.setPrefHeight(800);
        box.setStyle("-fx-background-color:cornsilk; -fx-padding: 25;");
        box.getChildren().addAll(label, tableView);
        ((Group) scene.getRoot()).getChildren().addAll(box, fetch);
        
        primaryStage.setTitle("Films for Rent");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     *
     * @param tableView is the table of the GUI to access the database
     */
    public void fetchData(TableView tableView)
     {
         // Call method getConnection in class to connect to DB
         try
         {
             Connection connect = getConnection();
             logger.log(Level.SEVERE, "Fetching data from database");
             // Populate the UI with data from the DB by calling method fetchFilms
             tableView.setItems(fetchFilms(connect));
         }

         catch(SQLException | ClassNotFoundException e)
         {
             logger.log(Level.SEVERE, null, e);
         }
     }
    
    /**
     *
     * @return the connection to the database
     * @throws SQLException
     * @throws ClassNotFoundException if a class is not found
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException
     {
         logger.log(Level.SEVERE, "Parsing XML document");
         
         // Location of the XML document
         XmlParser parse = new XmlParser("inputOutput/properties.xml");
         
         ConnectionData data = parse.getConnectionData();
     
         // Connecting to the database
         PostgreSQLConnect connect = new PostgreSQLConnect(data);
         
         return connect.getConnection();
     }
     
    /**
     *
     * @param connection is the connection to the database
     * @return the list with the films stored in the database
     * @throws SQLException if the database can not be 
     */
    public ObservableList<FilmDAO> fetchFilms(Connection connection) throws SQLException
     {
         logger.log(Level.SEVERE, "Removing individual film data from DB");
         
         // List to store the films retrieved from the database
         ObservableList<FilmDAO> filmList = FXCollections.observableArrayList();
         
         String query = new String("select title, rental_rate, rating, description " +
                        "from film " +
                        "order by title;");
        
         Statement statement = connection.createStatement();
         
         ResultSet result = statement.executeQuery(query);
         
         FilmDAO film;
         
         // Adding each piece of info about the film to the list
         while(result.next() == true)
         {
             film = new FilmDAO();
             
             film.setName(result.getString("title"));
             film.setDescription(result.getString("description"));
             film.setRating(result.getString("rating"));
             film.setRentalPrice(result.getDouble("rental_rate"));
             filmList.add(film);
         }
         
        return filmList;
     }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
