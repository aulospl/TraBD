package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private ObservableList<String> comboContinenteData = FXCollections.observableArrayList();
	
	
	
	@FXML
	private ComboBox<String> comboModalidade;
	private ObservableList<String> comboModalidadeData = FXCollections.observableArrayList();

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
	
	
	public void setContinents(){
		comboContinenteData.add(new String("Africa"));
		comboContinenteData.add(new String("America"));
		comboContinenteData.add(new String("Asia"));
		comboContinenteData.add(new String("Europa"));
		comboContinenteData.add(new String("Oceania"));
		comboContinente.setItems(comboContinenteData);
	}
	
	public void setModalidades(){
		ArrayList<String> modalidadeData = new ArrayList<String>();
		try {
			Statement st = Main.conn.getCon().createStatement();
			ResultSet res = st.executeQuery("select nome from MODALIDADE");
			while (res.next()) {
				modalidadeData.add(new String(res.getString(1)));
			}
		} catch (SQLException ex) {
			// Dependendo de implementação, res.next() pode jogar uma exceção em vez de retornar false.
			// Este try-catch só serve para parar o loop quando não houver mais resultados
		}
		for(String mod : modalidadeData){
			comboModalidadeData.add(mod);
		}
		
		comboModalidade.setItems(comboModalidadeData);
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
