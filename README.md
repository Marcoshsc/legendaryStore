# Legendary Store

## Visão geral
Servidor REST simples que implementa a função de API de vendas, implementado em linguagem JAVA com o framework Spring.

## Pré requisitos
Possuir um banco de dados POSTGRESQL rodando no endereço https://localhost:5432/storeAPI (o nome do banco de dados é "storeAPI") com as seguintes tabelas (será mostrado em código SQL para ser rodado em caso de inexistência das tabelas):

	CREATE TABLE clients (
		id bigserial PRIMARY KEY,
		name varchar(100) NOT NULL
	);

	CREATE TABLE products (
		id bigserial PRIMARY KEY,
		name varchar(100) NOT NULL,
		stock bigint NOT NULL,
		price float NOT NULL
	);

	CREATE TABLE sales (
		id bigserial NOT NULL,
		client_id bigint,
		sale_date date NOT NULL,
		FOREIGN KEY (client_id) REFERENCES clients(id)
	);

	CREATE TABLE sale_items (
		id bigserial NOT NULL,
		sale_id bigint,
		product_id bigint,
		quantity bigint NOT NULL,
		finalPrice float NOT NULL,
		FOREIGN KEY (sale_id) REFERENCES sales(id),
		FOREIGN KEY (product_id) REFERENCES products(id)
	);

## Execução

	git clone https://github.com/Marcoshsc/legendaryStore.git
	cd legendaryStore
	
