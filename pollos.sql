--Base 
DROP SCHEMA IF EXISTS pollos;
CREATE SCHEMA IF NOT EXISTS pollos;
USE pollos;
-- Pessoas
DROP TABLE IF EXISTS pessoas;
CREATE TABLE IF NOT EXISTS pessoas ( 
	id INT NULL AUTO_INCREMENT , 
	cpf TEXT NOT NULL , 
	nome TEXT NOT NULL , 
	idade INT NOT NULL , 
	email TEXT NOT NULL , 
	funcionario INT NOT NULL ,  
	telefone TEXT NOT NULL , 
	endereco TEXT NOT NULL , 
	observacoes TEXT NOT NULL , 
	PRIMARY KEY (id)
);
-- Pedidos
DROP TABLE IF EXISTS pedidos;
CREATE TABLE IF NOT EXISTS pedidos ( 
	id INT NULL AUTO_INCREMENT , 
	codCliente TEXT NOT NULL , 
	codProduto TEXT NOT NULL , 
	quantidade INT NOT NULL , 
	status TEXT NOT NULL , 
	observacoes TEXT NOT NULL , 
	valor DOUBLE NOT NULL ,  
	PRIMARY KEY (id)
);
