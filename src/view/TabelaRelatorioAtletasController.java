package view;

import controller.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TabelaRelatorioAtletasController {

    private static class TuplaRelatorio {
        StringProperty passaporte;
        StringProperty nome;
        StringProperty nacao;
        StringProperty dataNasc;

        TuplaRelatorio(String pas, String nom, String nac, String dat) {
            passaporte = new SimpleStringProperty(pas);
            nome = new SimpleStringProperty(nom);
            nacao = new SimpleStringProperty(nac);
            dataNasc = new SimpleStringProperty(dat);
        }
    }

    private ObservableList<TuplaRelatorio> data;

    @FXML
    private TableView<TuplaRelatorio> table;

    @FXML
    private TableColumn<TuplaRelatorio, String> colPassaporte;
    @FXML
    private TableColumn<TuplaRelatorio, String> colNome;
    @FXML
    private TableColumn<TuplaRelatorio, String> colNacao;
    @FXML
    private TableColumn<TuplaRelatorio, String> colData;

    @FXML
    private void initialize() {
        data = FXCollections.observableArrayList();

        table.setItems(data);

        colPassaporte.setCellValueFactory(cell -> cell.getValue().passaporte);
        colNome.setCellValueFactory(cell -> cell.getValue().nome);
        colNacao.setCellValueFactory(cell -> cell.getValue().nacao);
        colData.setCellValueFactory(cell -> cell.getValue().dataNasc);
    }

    public void load(DBConnection con, int modalidade, String passaporte, int medico) throws SQLException {
        Statement st = con.createStatement();
        ResultSet res = st.executeQuery(
                String.format("select atl.nome, atl.passaporte, trd.nacao, atl.data_nasc\n" +
                "        from Atleta atl\n" +
                "            join Treinador trd on atl.treinador = trd.passaporte\n" +
                "            join Consulta cns on cns.atleta = atl.passaporte\n" +
                "        where atl.modalidade = %d and trd.passaporte = '%s' and cns.medico = %d;", modalidade, passaporte, medico));
        try {
            while (res.next()) {
                data.add(new TuplaRelatorio(res.getString(1), res.getString(2), res.getDate(3).toString(), res.getString(4)));
            }
        } catch (SQLException ex) {
            // Dependendo de implementação, res.next() pode jogar uma exceção em vez de retornar false.
            // Este try-catch só serve para parar o loop quando não houver mais resultados
        }
    }
}
