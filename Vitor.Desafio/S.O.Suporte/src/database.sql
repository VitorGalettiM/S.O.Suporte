CREATE DATABASE incident_db;

USE incident_db;

CREATE TABLE chamados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    prioridade ENUM('Alta', 'Média', 'Baixa') NOT NULL,
    status ENUM('Aberto', 'Em andamento', 'Resolvido', 'Fechado') NOT NULL DEFAULT 'Aberto',
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
