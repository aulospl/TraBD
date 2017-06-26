package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Nacao;

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
		ResultSet res = st.executeQuery("SELECT nome, continente, num_atletas, modalidade_principal, hino, bandeira FROM NACAO");
		try {
			while (res.next()) {
				data.add(new Nacao(res.getString(1), res.getString(2), res.getInt(3), res.getString(4), res.getString(5), res.getString(6)));
			}
		} catch (SQLException ex) {
			// Dependendo de implementação, res.next() pode jogar uma exceção em vez de retornar false.
			// Este try-catch só serve para parar o loop quando não houver mais resultados
		}
		System.out.println("fim loop");
	}
}
