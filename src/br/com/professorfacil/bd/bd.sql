create database professorfacil;
use professorfacil;

create table usuarios(
iduso int primary key auto_increment not null,
uso_nome varchar(200) not null,
uso_cidade varchar(100),
uso_estado varchar(4),
uso_endereco varchar(200),
uso_end_num varchar(8),
uso_email varchar(100),
uso_cpf varchar(20),
uso_celular varchar(50) not null,
uso_cep varchar(30),
uso_usuario varchar(50) not null,
uso_senha varchar(50) not null,
uso_componente varchar(400),
uso_licenca varchar (50),
uso_data_licensa varchar (30),
uso_data_validade varchar (30),
uso_perfil varchar(50),
uso_datacadastro timestamp default current_timestamp

);
insert into usuarios (uso_nome,uso_cidade,uso_estado,uso_endereco,uso_end_num,uso_email,uso_cpf,uso_celular,uso_cep,uso_usuario,uso_senha,uso_perfil,uso_componente)
values ("Tarciso Nascimento Bezerra","Atiquemes","RO","Rua Presidente Prudente de Morais","1852","tarcisoedf@gmail.com","983.284.252-20","(69) 9.9209-9315","76.873-384","admin","admin","Administrador","Educação Física,Artes");

create table componentes (
idcomponente int primary key auto_increment not null,
comp_nome varchar (100)
);
insert into componentes (comp_nome)
values ("Educação Física"),("Artes");

create table editora (
ideditora int primary key auto_increment not null,
edit_nome varchar (100)
);
insert into editora (edit_nome)
values ("Moderna");

create table serie (
idserie int primary key auto_increment not null,
seri_nivel varchar (10)
);
insert into serie (seri_nivel)
values ("1º");

create table livro (
idliv int primary key auto_increment not null,
liv_nomeautor varchar (200),
liv_editora varchar (150),
liv_edicao varchar (50),
liv_ano varchar (10),
liv_serie varchar (10),
liv_periodo varchar (50),
liv_observacoes varchar (200),
liv_foto longblob

);
insert into livro (liv_nomeautor,liv_editora,liv_edicao,liv_ano,liv_serie,liv_periodo,liv_observacoes)
values ("AA_Autor","Moderna","1º Edição","2020","1º","2020,2021,2022,2023","Sem Observações");

create table conteudo (
idconteudo int primary key auto_increment not null,
cont_conteudo longblob,
cont_habilidade longblob,
cont_atividade longblob,
cont_recurso longblob,
cont_code_conteudo varchar (60),
cont_serie varchar (10),
cont_componente varchar (50),
idliv int not null,
foreign key (idliv) references livro (idliv)
);
insert into conteudo (cont_conteudo,cont_habilidade,cont_atividade,cont_recurso,cont_code_conteudo,cont_serie,cont_componente,idliv)
values ("Aqui vai o conteudo","Aqui Vai as habilidades","aqui vai os recursos","aqui vao os conteudos","(EF254DF)","1º","Educação Física","1");

create table licenca (
idlicenca int primary key auto_increment not null,
idcli int not null,
lic_chave int not null,
lic_licenca varchar (100),
lic_data_valida varchar (30),
lic_data_licenca timestamp default current_timestamp
);

create table escola (
idescola int primary key auto_increment not null,
esc_estado varchar (400),
esc_secretaria varchar (400),
esc_ecola varchar (400),
esc_cidade varchar (150),
esc_Uf varchar (10),
esc_endereco varchar (250),
esc_num varchar (10),
esc_brasao longblob
);
