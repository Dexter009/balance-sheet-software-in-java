/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travel.accounting.software;

import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;
import travel.accounting.database.DatabaseHandler;

/**
 *
 * @author gaj
 */
public class TravelAccountingSoftware extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root,700,700);
        stage.setTitle("TICKET ACCOUNT MANAGER");
        stage.setScene(scene);
       /*try{ conn = sqlconnection.DBConnector();}
    catch(NullPointerException e3){
    e3.printStackTrace();
    }
       if(conn == null){
       System.out.println("failed");
       }
       else{
       System.out.println("passed");}
       */
stage.show();
    
    
    }
     @Override
    public void stop() {
        DatabaseHandler.shutdown();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    
    
    
    
}
}