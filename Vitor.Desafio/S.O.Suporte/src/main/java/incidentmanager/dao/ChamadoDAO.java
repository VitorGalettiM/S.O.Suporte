package incidentmanager.dao;

import incidentmanager.model.Chamado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChamadoDAO {
    private final String url = "jdbc:mysql://localhost:3306/incident_db";
    private final String user = "root";
    private final String password = "sua_senha_aqui";

    public void adicionarChamado(Chamado chamado) {
        String sql = "INSERT INTO chamados (titulo, descricao, prioridade) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, chamado.getTitulo());
            stmt.setString(2, chamado.getDescricao());
            stmt.setString(3, chamado.getPrioridade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Chamado> listarChamados(String prioridadeFiltro, String statusFiltro) {
        List<Chamado> chamados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM chamados WHERE 1=1");

        if (!prioridadeFiltro.equals("Todos")) {
            sql.append(" AND prioridade = '").append(prioridadeFiltro).append("'");
        }
        if (!statusFiltro.equals("Todos")) {
            sql.append(" AND status = '").append(statusFiltro).append("'");
        }

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString())) {

            while (rs.next()) {
                Chamado chamado = new Chamado(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getString("prioridade"),
                        rs.getString("status")
                );
                chamados.add(chamado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chamados;
    }

    public void atualizarStatus(int id, String novoStatus) {
        String sql = "UPDATE chamados SET status = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoStatus);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
