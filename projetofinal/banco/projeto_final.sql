CREATE DATABASE projeto_final;
USE projeto_final;

CREATE TABLE categoria (
  codigo INT PRIMARY KEY,
  nome VARCHAR(100),
  descricao VARCHAR(255)
);

CREATE TABLE cliente (
  codigo INT PRIMARY KEY,
  nome VARCHAR(100),
  email VARCHAR(100),
  telefone VARCHAR(20)
);

CREATE TABLE funcionario (
  codigo INT PRIMARY KEY,
  nome VARCHAR(100),
  email VARCHAR(100),
  cpf VARCHAR(14)
);

CREATE TABLE fornecedor (
  codigo INT PRIMARY KEY,
  nome VARCHAR(100),
  email VARCHAR(100),
  telefone VARCHAR(20),
  cnpj VARCHAR(18),
  endereco VARCHAR(255)
);

CREATE TABLE produto (
  codigo INT PRIMARY KEY,
  nome VARCHAR(100),
  estoque INT,
  preco DECIMAL(10,2),
  codcategoria INT,
  FOREIGN KEY (codcategoria) REFERENCES categoria(codigo)
);