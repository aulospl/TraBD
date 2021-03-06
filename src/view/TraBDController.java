package view;

import controller.DBConnection;
import controller.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Nacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TraBDController {
	private ObservableList<Nacao> data;
	
	@FXML
	private TableView<Nacao> table;
	
	@FXML
	private TableColumn<Nacao, String> colNome;
	@FXML
	private TableColumn<Nacao, String> colContinente;
	@FXML
	private TableColumn<Nacao, Integer> colNumAtletas;
	@FXML
	private TableColumn<Nacao, String> colModalidade;
	@FXML
	private TableColumn<Nacao, String> colHino;
	@FXML
	private TableColumn<Nacao, String> colBandeira;

	@FXML
	private TextField txtModalidade;
	@FXML
	private TextField txtMedico;
	@FXML
	private TextField txtTreinador;

	@FXML
	private TextField txtNumAtletas;
	@FXML
	private TextField txtNacao;
	
	@FXML
	private void initialize() {
		data = FXCollections.observableArrayList();
		
		table.setItems(data);
		colNome.setCellValueFactory(cell -> cell.getValue().getNome());
		colContinente.setCellValueFactory(cell -> cell.getValue().getContinente());
		colNumAtletas.setCellValueFactory(cell -> cell.getValue().getNumAtletas().asObject());
		colModalidade.setCellValueFactory(cell -> cell.getValue().getModalidade());
		colHino.setCellValueFactory(cell -> cell.getValue().getHino());
		colBandeira.setCellValueFactory(cell -> cell.getValue().getBandeira());
	}
	
	public void load(DBConnection con) throws SQLException {
		Statement st = con.createStatement();
		ResultSet res = st.executeQuery("select nc.nome, continente, num_atletas, md.nome, hino, bandeira from NACAO nc "
											+ "join MODALIDADE md on md.codigo = nc.modalidade_principal");
		try {
			while (res.next()) {
				data.add(new Nacao(res.getString(1), res.getString(2), res.getInt(3), res.getString(4), res.getString(5), res.getString(6)));
			}
		} catch (SQLException ex) {
			// Dependendo de implementação, res.next() pode jogar uma exceção em vez de retornar false.
			// Este try-catch só serve para parar o loop quando não houver mais resultados
		}
	}
	
	public ObservableList<Nacao> getNationData() {
        return data;
    }
	
	@FXML
	private void handleNovaNacao() {
	    Nacao tempNacao = new Nacao("", "", 0, "", "", "");
	    boolean okClicked = Main.showModalNacao(tempNacao);
	    if (okClicked) {
	    	getNationData().add(tempNacao);
	        //Main.getPersonData().add(tempPerson);
	    	//Adicionar na base de dados
	    }
	}
	
	@FXML
	private void handleEditarNacao() {
	    Nacao nacaoSelecionada = table.getSelectionModel().getSelectedItem();
	    boolean okClicked = Main.showModalNacao(nacaoSelecionada);
	    if (okClicked) {
	        
	    	//alterar na base de dados
	    }
	}
	
	@FXML
	private void handleDeletarNacao() {
		Nacao nacaoSelecionada = table.getSelectionModel().getSelectedItem();
		PreparedStatement ps = null;
		try{
			Main.conn.getCon().setAutoCommit(false);
			table.getItems().remove(nacaoSelecionada);
			ps = Main.conn.getCon().prepareStatement("DELETE FROM NACAO WHERE NOME = ?");
			ps.setString(1, nacaoSelecionada.getNome().getValue());
			ps.execute();
			Main.conn.getCon().commit();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@FXML
	private void handleGerarRelatorio1() {
	    try {
	    	int modalidade, medico;
	    	String treinador;
	    	modalidade = Integer.parseInt(txtModalidade.getText());
	    	medico = Integer.parseInt(txtMedico.getText());
	    	treinador = txtTreinador.getText();
			Main.showModalRelatorio1(modalidade, treinador, medico);
		} catch (NumberFormatException ex) {}
	}

	@FXML
	private void handleGerarRelatorio2() {
		try {
			int num;
			String nacao;

			num = Integer.parseInt(txtNumAtletas.getText());
			nacao = txtNacao.getText();
			Main.showModalRelatorio2(num, nacao);
		} catch (NumberFormatException ex) {}
	}
}
