package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModalNacaoController {

	@FXML
	private ComboBox comboContinente;

	@FXML
	private ComboBox comboModalidade;
	
	@FXML
	private TextField textFieldNome;
	
	@FXML
	private TextField textFieldHino;
	
	@FXML
	private TextField textFieldBandeira;
	
	@FXML
	private Button buttonCancel;
	
	@FXML
	private Button buttonOK;
	
	private Stage dialogStage;
	
	@FXML
    private void initialize() {
    }
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	@FXML
	private void handleOK(){
		//faz as coisas
		System.out.println("OK");
		dialogStage.close();
		
	}
	
	@FXML
	private void handleCancel(){
		dialogStage.close();
	}
	
}
