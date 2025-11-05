DROP DATABASE IF EXISTS locadoradb;
CREATE DATABASE IF NOT EXISTS locadoradb;
USE locadoradb;

-- TABLES
CREATE TABLE IF NOT EXISTS Cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(11) UNIQUE,
    telefone VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS Locadora (
    id_locadora INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    endereco VARCHAR(200),
    telefone VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS Categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Filme (
    id_filme INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    quantidade INT,
    id_categoria INT,
    id_locadora INT,
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria),
    FOREIGN KEY (id_locadora) REFERENCES Locadora(id_locadora)
);

CREATE TABLE IF NOT EXISTS Locacao (
    id_locacao INT AUTO_INCREMENT PRIMARY KEY,
    data_locacao DATE,
    data_devolucao DATE,
    id_cliente INT,
    id_locadora INT,
    id_filme INT,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente) ON DELETE CASCADE,
    FOREIGN KEY (id_locadora) REFERENCES Locadora(id_locadora) ON DELETE CASCADE,
    FOREIGN KEY (id_filme) REFERENCES Filme(id_filme) ON DELETE CASCADE
);

-- INSERTS
INSERT INTO Categoria (nome) VALUES 
('Ação'), ('Comédia'), ('Terror');

INSERT INTO Locadora (nome, endereco, telefone) VALUES
('CineStar', 'Rua A, 123', '61999990000');

INSERT INTO Filme (nome, quantidade, id_categoria, id_locadora) VALUES 
('Vingadores', 5, 1, 1);

INSERT INTO Cliente (nome, cpf, telefone) VALUES 
('Ana Souza', '11111111111', '61999998888');

INSERT INTO Locacao (data_locacao, data_devolucao, id_cliente, id_locadora, id_filme) VALUES
('2025-10-20', '2025-10-27', 1, 1, 1);

-- SELECTS
SELECT * FROM Cliente;
SELECT * FROM Filme;
SELECT * FROM Locacao;
