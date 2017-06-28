package view;

import controller.DBConnection;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

public class TabelaRelatorioMedicosController {

    private class TuplaRelatorio {
        IntegerProperty codigo;
        StringProperty nome;
        IntegerProperty numAtletas;

        TuplaRelatorio(int c, String n, int num) {
            codigo = new SimpleIntegerProperty(c);
            nome = new SimpleStringProperty(n);
            numAtletas = new SimpleIntegerProperty(num);
        }
    }

    ObservableList<TuplaRelatorio> data;

    @FXML
    private TableView table;
    @FXML
    private TableColumn<TuplaRelatorio, Integer> colCodigo;
    @FXML
    private TableColumn<TuplaRelatorio, String> colNome;
    @FXML
    private TableColumn<TuplaRelatorio, Integer> colNumAtletas;

    @FXML
    private void initialize() {
        data = FXCollections.observableArrayList();
        table.setItems(data);

        colCodigo.setCellValueFactory(cell -> cell.getValue().codigo.asObject());
        colNome.setCellValueFactory(cell -> cell.getValue().nome);
        colNumAtletas.setCellValueFactory(cell -> cell.getValue().numAtletas.asObject());
    }

    public void load(DBConnection con, int num, String nacao) throws SQLException {
        Statement st = con.createStatement();
        ResultSet res = st.executeQuery(
                String.format("select mdc.codigo, mdc.nome, count(atl.passaporte) as num_atletas\n" +
                        "        from Medico mdc\n" +
                        "            join Consulta cns on cns.medico = mdc.codigo\n" +
                        "            join Atleta atl on atl.passaporte = cns.atleta\n" +
                        "            join Treinador trn on trn.passaporte = atl.treinador\n" +
                        "        where trn.nacao = '%s'\n" +
                        "        group by mdc.codigo, mdc.nome\n" +
                        "            having count(*) > %d\n" +
                        "        order by mdc.codigo asc", nacao, num)
        );
        System.out.println("A");
        try {
            while (res.next()) {
                System.out.println(res.getString(1));
                data.add(new TuplaRelatorio(res.getInt(1), res.getString(2), res.getInt(3)));
                System.out.println("done");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Dependendo de implementação, res.next() pode jogar uma exceção em vez de retornar false.
            // Este try-catch só serve para parar o loop quando não houver mais resultados
        }
    }
}
