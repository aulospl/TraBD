package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Nacao;

public class ModalNacaoController {

	@FXML
	private ComboBox<String> comboContinente;

	@FXML
	private ComboBox<String> comboModalidade;
	
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
	private boolean okClicked = false;
	
	@FXML
    private void initialize() {
    }
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	public boolean isOkClicked(){
		return okClicked;
	}
	
	@FXML
	private void handleOK(){
		//faz as coisas
		System.out.println("OK");
		okClicked = true;
		dialogStage.close();
		
	}
	
	@FXML
	private void handleCancel(){
		dialogStage.close();
	}

	public void setNacao(Nacao nacao) {
		this.textFieldNome.setText(nacao.getNome().getValue());
		this.textFieldHino.setText(nacao.getHino().getValue());
		this.textFieldBandeira.setText(nacao.getBandeira().getValue());
		this.comboContinente.setValue(nacao.getContinente().getValue());
		this.comboModalidade.setValue(nacao.getModalidade().getValue());
	}
	
}
