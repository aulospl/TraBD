package controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Nacao;
import view.ModalNacaoController;
import view.TraBDController;


public class Main extends Application{
	
	private DBConnection conn;
	
	private static Stage primaryStage;
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
	
	static public boolean showModalNacao(Nacao nacao) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("../view/ModalNacao.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        
	        if(nacao.getNome().getValue() == ""){
	        	dialogStage.setTitle("Inserir Nacao");
	        }
	        else{
	        	dialogStage.setTitle("Editar "+nacao.getNome().getValue()+"");
	        }
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	       ModalNacaoController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setNacao(nacao);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
}
