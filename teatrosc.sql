-- Cria o banco de dados teatrosc
CREATE DATABASE teatrosc ON (NAME = teatrosc, FILENAME = 'C:\SQL\teatrosc\teatrosc.mdf');



-- Cria a tabela espetaculos
CREATE TABLE espetaculos (
    id INT PRIMARY KEY IDENTITY,
    titulo VARCHAR(200) NOT NULL,
    diretor VARCHAR(100) NOT NULL,
	elenco VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL
);

-- Cria a tabela programacao para armazenar os dias de exibição de cada espetáculo
CREATE TABLE programacao (
    id INT PRIMARY KEY IDENTITY,
    espetaculo_id INT NOT NULL,
    data_exibicao DATE NOT NULL,
    FOREIGN KEY (espetaculo_id) REFERENCES espetaculos(id)
);

-- Cria a tabela assentos
CREATE TABLE assentos (
    id CHAR(4) PRIMARY KEY,
    tipo_assento VARCHAR(15) NOT NULL CHECK (tipo_assento IN ('Plateia', 'Camarote')), -- Pode ser 'Plateia' ou 'Camarote'
    status VARCHAR(15) NOT NULL DEFAULT 'Disponível' -- Pode ser 'Disponível' ou 'Vendido'
);

-- Cria a tabela ingressos
CREATE TABLE ingressos (
    id INT PRIMARY KEY IDENTITY,
    programacao_id INT NOT NULL,
    assento_id CHAR(4) NOT NULL,
    tipo_ingresso VARCHAR(15) NOT NULL CHECK (tipo_ingresso IN ('Meio', 'Inteiro', 'Social')), -- Pode ser 'Meio', 'Inteiro' ou 'Social'
    status VARCHAR(15) NOT NULL DEFAULT 'Disponível', -- Pode ser 'Disponível' ou 'Vendido'
    FOREIGN KEY (programacao_id) REFERENCES programacao(id),
    FOREIGN KEY (assento_id) REFERENCES assentos(id)
);

-- Cria a tabela vendas
CREATE TABLE vendas (
    id INT PRIMARY KEY IDENTITY,
    ingresso_id INT NOT NULL,
    data_venda DATETIME NOT NULL DEFAULT GETDATE(),
    FOREIGN KEY (ingresso_id) REFERENCES ingressos(id)
);

