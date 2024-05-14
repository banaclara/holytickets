CREATE DATABASE SantaClara1_0;
USE SantaClara1_0;

CREATE TRIGGER UpdateAssentoIngressoStatus
ON Ingresso
AFTER UPDATE
AS
BEGIN
    IF UPDATE(StatusIngresso)
    BEGIN
        -- Busca o AssentoID correspondente ao IngressoID atualizado
        DECLARE @AssentoID INT;
        SELECT @AssentoID = AssentoID FROM inserted;

        -- Atualiza o status do assento com base no novo status do ingresso
        UPDATE Assento
        SET StatusAssento = CASE WHEN (SELECT StatusIngresso FROM inserted) = 'Vendido' THEN 'Ocupado' ELSE 'Livre' END
        WHERE AssentoID = @AssentoID;
    END 
END;

-- Verifica os Triggers implementados
SELECT * FROM sys.triggers;

-- Deleta o Trigger declarado
DROP TRIGGER UpdateAssentoIngressoStatus;

-- Altera a tabela ingresso e adiciona Assento ID e uma FK para facilitar a Atualização do status
ALTER TABLE Ingresso
ADD AssentoID INT,
FOREIGN KEY (AssentoID) REFERENCES Assento(AssentoID);

-- Opcional
CREATE TABLE Bilheteria (
  BilheteriaID INT PRIMARY KEY,
  NomeBilheteria VARCHAR(255) NOT NULL,
  Endereco VARCHAR(255) NOT NULL,
  Telefone VARCHAR(20) NOT NULL,
  Email VARCHAR(50) NOT NULL
);
-- Tabelas que achei pertinentes
CREATE TABLE Cliente (
  ClienteID INT PRIMARY KEY,
  NomeCompleto VARCHAR(255) NOT NULL,
  CPF VARCHAR(11) NOT NULL UNIQUE,
  DataNascimento DATE NOT NULL,
  Email VARCHAR(50) NOT NULL
);

CREATE TABLE Vendas (
  VendaID INT PRIMARY KEY,
  DataVenda DATETIME NOT NULL,
  ValorVenda DECIMAL(10,2) NOT NULL,
  FormaPagamento VARCHAR(50) NOT NULL,
  ClienteID INT NOT NULL,
  FOREIGN KEY (ClienteID) REFERENCES Cliente(ClienteID)
);

CREATE TABLE Ingresso (
  IngressoID INT PRIMARY KEY,
  VendaID INT NOT NULL,
  CodigoIngresso VARCHAR(50) UNIQUE,
  TipoIngresso VARCHAR(50) NOT NULL,
  -- Achei interessante ter uma forma de verificar a validade
  DataValidade DATE NOT NULL,
  StatusIngresso VARCHAR(50) NOT NULL DEFAULT 'Disponível',
  FOREIGN KEY (VendaID) REFERENCES Vendas(VendaID),
  CONSTRAINT CK_Ingresso CHECK (TipoIngresso IN ('Inteiro', 'Meio', 'Social'))
);

CREATE TABLE Espetáculo (
  EspetáculoID INT PRIMARY KEY,
  NomeEspetáculo VARCHAR(255) NOT NULL,
  Genero VARCHAR(50) NOT NULL,
  Duracao INT NOT NULL,
  Diretor VARCHAR(50) NOT NULL,
  Elenco TEXT NOT NULL,
  Descrição TEXT NOT NULL
);

CREATE TABLE Programacao (
  ProgramacaoID INT PRIMARY KEY,
  EspetáculoID INT NOT NULL,
  DataHoraInicio DATETIME NOT NULL,
  DataHoraFim DATETIME NOT NULL,
  BilheteriaID INT NOT NULL,
  FOREIGN KEY (EspetáculoID) REFERENCES Espetáculo(EspetáculoID),
  FOREIGN KEY (BilheteriaID) REFERENCES Bilheteria(BilheteriaID)
);

CREATE TABLE Assento (
  AssentoID INT PRIMARY KEY,
  Fila CHAR(1) NOT NULL,
  Numero INT NOT NULL,
  TipoAssento VARCHAR(50) NOT NULL,
  StatusAssento VARCHAR(50) NOT NULL DEFAULT 'Livre',
  ProgramacaoID INT NOT NULL,
  FOREIGN KEY (ProgramacaoID) REFERENCES Programacao(ProgramacaoID),
  CONSTRAINT CK_Setor CHECK (TipoAssento IN ('Plateia', 'Camarote'))
);



