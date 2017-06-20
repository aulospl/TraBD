package controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application{
	
	private Conn conn;
	
	private Stage primaryStage;
	private AnchorPane root;
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		launch(args);
	}
	
	@Override 
	public void start(Stage primaryStage) {
		this.conn = new Conn();
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("TraBD");
		
		initRootLayout();
		showMainWindow();
		
		conn.setCon(conn.conectaBD("7986409", "notapassword"));
		if(conn.getCon() != null){
			System.out.println("CONECTADO!");
			try {
				conn.desconectaBD();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			System.out.println("FALHA DE CONEXAO");
		
	}
	
	public void initRootLayout(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/TraBD.fxml"));
			
			System.out.println("teste 1");
			root = (AnchorPane) loader.load();
			System.out.println("teste 2");
			
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void showMainWindow(){
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
}
