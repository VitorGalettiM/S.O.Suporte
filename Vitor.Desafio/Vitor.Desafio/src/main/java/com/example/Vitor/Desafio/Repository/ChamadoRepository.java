package com.example.Vitor.Desafio.Repository;

import com.sos.suporte.model.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
}
