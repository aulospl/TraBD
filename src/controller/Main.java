package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


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
		
		initRootLayout();
		showMainWindow();
		
		conn.setCon(DBConnection.conectaBD("6436060", "a"));
		if(conn.getCon() != null){
			System.out.println("CONECTADO!");
			System.out.println("Atletas: ");
			
			try {
				Statement stmt = conn.createStatement();
				ResultSet res = stmt.executeQuery("SELECT passaporte, nome, altura, peso FROM ATLETA");
				try {
					while (res.next()) {
						System.out.printf("%s\t%s\t%f\t%f\n", res.getString(1), res.getString(2), res.getFloat(3), res.getFloat(4));
					}
				} catch (SQLException e) {}
				// res pode ou retornar false ou jogar SQLException quando acabar
				// depende de implementação
				
				conn.desconectaBD();
			} catch (SQLException e) {
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
