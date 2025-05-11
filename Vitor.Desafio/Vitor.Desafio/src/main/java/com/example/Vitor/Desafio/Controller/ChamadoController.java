package com.sos.suporte.controller;

import com.sos.suporte.model.Chamado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoRepository chamadoRepository;

    // Endpoint para listar todos os chamados
    @GetMapping
    public List<Chamado> listarChamados() {
        return chamadoRepository.findAll();
    }

    // Endpoint para criar um novo chamado
    @PostMapping
    public Chamado criarChamado(@RequestBody Chamado chamado) {
        return chamadoRepository.save(chamado);
    }
}
