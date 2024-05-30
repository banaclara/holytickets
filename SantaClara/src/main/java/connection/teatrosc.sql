-- Cria o banco de dados teatrosc
CREATE DATABASE teatrosc ON (NAME = teatrosc, FILENAME = 'C:\SQL\teatrosc\teatrosc.mdf');


-- Cria a tabela espetaculos
CREATE TABLE Espetaculos (
    id INT NOT NULL IDENTITY CONSTRAINT pk_espetaculos PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    diretor VARCHAR(100) NOT NULL,
	elenco VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL
);

-- Cria a tabela programacao para armazenar os dias de exibição de cada espetáculo
CREATE TABLE Programacao (
    id INT NOT NULL IDENTITY CONSTRAINT pk_programacao PRIMARY KEY,
    espetaculo_id INT NOT NULL,
    data_exibicao DATE NOT NULL CONSTRAINT un_data UNIQUE,
    CONSTRAINT fk_espetaculo_id FOREIGN KEY (espetaculo_id) REFERENCES Espetaculos(id) ON DELETE CASCADE
);

-- Cria a tabela assentos
CREATE TABLE Assentos (
    id VARCHAR(4) NOT NULL CONSTRAINT pk_assentos PRIMARY KEY,
    tipo_assento VARCHAR(15) NOT NULL CHECK (tipo_assento IN ('comum', 'camarote')),
);

-- Cria a tabela ingressos
CREATE TABLE IngressosVendidos (
    data_exibicao DATE NOT NULL,
    assento_id VARCHAR(4) NOT NULL,
    tipo_ingresso VARCHAR(15) NOT NULL CHECK (tipo_ingresso IN ('meia', 'inteira', 'social')),
	valor_pago DECIMAL(5, 2) NOT NULL,
	data_venda DATETIME NOT NULL DEFAULT GETDATE(),
	CONSTRAINT pk_ingressos PRIMARY KEY (assento_id, data_exibicao),
    CONSTRAINT fk_data_exibicao FOREIGN KEY (data_exibicao) REFERENCES Programacao(data_exibicao) ON DELETE CASCADE,
    CONSTRAINT fk_assento_id FOREIGN KEY (assento_id) REFERENCES Assentos(id)
);

DECLARE @id CHAR(4)
DECLARE @tipo VARCHAR(15)

SET @tipo = 'comum'

DECLARE @letra CHAR(1)
SET @letra = 'A'
WHILE @letra <= 'I'
BEGIN
    DECLARE @numero INT = 1
    WHILE @numero <= 8
    BEGIN
        SET @id = @letra + RIGHT('0' + CAST(@numero AS VARCHAR), 2)

        INSERT INTO Assentos (id, tipo_assento) VALUES (@id, @tipo)

        SET @numero = @numero + 1
    END
    SET @letra = CHAR(ASCII(@letra) + 1)
END

SET @tipo = 'camarote'

DECLARE @i INT = 1
WHILE @i <= 28
BEGIN
    SET @id = 'CA' + RIGHT('0' + CAST(@i AS VARCHAR), 2)

    INSERT INTO Assentos (id, tipo_assento) VALUES (@id, @tipo)

    SET @i = @i + 1
END

-- visualizando

select * from Assentos
select * from IngressosVendidos
