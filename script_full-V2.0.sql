/*
Database name		DB_CRUDPECAS		
Database version	PostgreSQL 11 
*/




Create table FORNECEDOR
(
	FOR_CODIGO Serial NOT NULL,
	FOR_NOME Varchar(100) NOT NULL,
	FOR_CNPJ Varchar(18) NOT NULL,
	FOR_CIDADE Varchar(100) NOT NULL,
constraint pk_FORNECEODR primary key (FOR_CODIGO)
) With Oids;

Create table CATEGORIA
(
	CAT_CODIGO Serial NOT NULL,
	CAT_NOME Varchar(100) NOT NULL,
constraint pk_CATEGORIA primary key (CAT_CODIGO)
) ;

Create table USUARIO
(
	USR_CODIGO Serial NOT NULL,
	USR_DATACADASTRO Date NOT NULL,
	USR_NOME Varchar(100) NOT NULL,
	USR_LOGIN Varchar(50) NOT NULL,
	USR_SENHA Varchar(50) NOT NULL,
	USR_BLOQUEADO Boolean NOT NULL,
	USR_EMAIL Varchar(50) NOT NULL,
constraint pk_USUARIO primary key (USR_CODIGO)
) ;

Create table PECAS
(
	PEC_CODIGO Serial NOT NULL,
	PEC_NOME Varchar(100) NOT NULL,
	CAT_CODIGO Integer NOT NULL,
	FOR_CODIGO Integer NOT NULL,
	PEC_VALORUNITARIO Numeric(6,2) NOT NULL,
	PEC_ATIVO Boolean NOT NULL Default true,
	USR_CODIGO Integer NOT NULL,
constraint pk_PECA primary key (PEC_CODIGO)
) ;

Create table COMPRA
(
	CMP_CODIGO Serial NOT NULL,
	CMP_DATAHORA Date NOT NULL,
	CMP_VALORTOTALCOMPRA Numeric(10,2),
	USR_CODIGO Integer NOT NULL,
constraint pk_COMPRA primary key (CMP_CODIGO)
) ;

Create table COMPRAPRODUTO
(
	PEC_CODIGO Integer NOT NULL,
	CPR_CODIGO Serial NOT NULL,
	CPR_VALORUNITARIO Numeric(10,2) NOT NULL,
	CPR_QUANTIDADE Integer NOT NULL,
	CPR_VALORTOTAL Numeric(10,2) NOT NULL,
	CMP_CODIGO Integer NOT NULL,
constraint pk_COMPRAPRODUTO primary key (CPR_CODIGO)
) ;





Alter table PECAS add Constraint Relationship1 foreign key (CAT_CODIGO) references CATEGORIA (CAT_CODIGO) on update restrict on delete restrict;

Alter table PECAS add Constraint Relationship2 foreign key (USR_CODIGO) references USUARIO (USR_CODIGO) on update restrict on delete restrict;

Alter table PECAS add Constraint Relationship7 foreign key (FOR_CODIGO) references FORNECEDOR (FOR_CODIGO) on update restrict on delete restrict;

Alter table COMPRA add Constraint Relationship3 foreign key (USR_CODIGO) references USUARIO (USR_CODIGO) on update restrict on delete restrict;

Alter table COMPRAPRODUTO add Constraint Relationship4 foreign key (PEC_CODIGO) references PECAS (PEC_CODIGO) on update restrict on delete restrict;

Alter table COMPRAPRODUTO add Constraint Relationship5 foreign key (CMP_CODIGO) references COMPRA (CMP_CODIGO) on update restrict on delete restrict;


Usuario admin

INSERT INTO usuario (USR_CODIGO, USR_DATACADASTRO, USR_NOME, USR_LOGIN, USR_SENHA, USR_BLOQUEADO, USR_EMAIL)
	VALUES (1, '02/12/2021', 'Carlos Eduardo Bertoglio', 'carlos', 'admin', ,'183000@upf.br');







