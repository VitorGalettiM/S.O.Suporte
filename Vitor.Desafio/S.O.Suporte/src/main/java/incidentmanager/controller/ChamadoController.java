package incidentmanager.controller;

import incidentmanager.dao.ChamadoDAO;
import incidentmanager.model.Chamado;

import java.util.List;

public class ChamadoController {
    private ChamadoDAO dao = new ChamadoDAO();

    public void criarChamado(String titulo, String descricao, String prioridade) {
        Chamado chamado = new Chamado(titulo, descricao, prioridade);
        dao.adicionarChamado(chamado);
    }

    public List<Chamado> listarChamados(String prioridade, String status) {
        return dao.listarChamados(prioridade, status);
    }

    public void atualizarStatus(int id, String novoStatus) {
        dao.atualizarStatus(id, novoStatus);
    }
}
