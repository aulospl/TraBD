package controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.TraBDController;


public class Main extends Application{
	
	private DBConnection conn;
	
	private Stage primaryStage;
	private AnchorPane root;
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		launch(args);
	}
	
	@Override 
	public void start(Stage primaryStage) {
		this.conn = new DBConnection();
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("TraBD");

		conn.setCon(DBConnection.conectaBD("6436060", "a"));
		
		initRootLayout();
		showMainWindow();
		
		if(conn.getCon() != null)
			System.out.println("CONECTADO!");
		else
			System.out.println("FALHA DE CONEXAO");
		
	}
	
	@Override
	public void stop() {
		try {
			conn.desconectaBD();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void initRootLayout(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/TraBD.fxml"));
			
			System.out.println("teste 1");
			root = (AnchorPane) loader.load();
			System.out.println("teste 2");
			
			TraBDController cont = loader.getController();
			cont.load(conn);
		}
		catch(IOException | SQLException e){
			e.printStackTrace();
		}
	}
	
	public void showMainWindow(){
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
}
