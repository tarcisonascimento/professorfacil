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
uso_licenca varchar (50),
uso_data_licensa varchar (30),
uso_data_validade varchar (30),
uso_perfil varchar(50),
uso_datacadastro timestamp default current_timestamp

);
insert into usuarios (uso_nome,uso_cidade,uso_estado,uso_endereco,uso_end_num,uso_email,uso_cpf,uso_celular,uso_cep,uso_usuario,uso_senha,uso_perfil,uso_licenca,uso_data_licensa,uso_data_validade)
values ("Tarciso Nascimento Bezerra","Ariquemes","RO","Rua Presidente Prudente de Morais","1852","tarcisoedf@gmail.com","983.284.252-20","(69) 9.9209-9315","76.873-384","tarciso","soeusei123","Administrador","Ativa","21/07/2021","21/07/2099"),
("AA Primeiro cliente","Ariquemes","RO","Rua Presidente Prudente de Morais","1852","contato@professorfacil.com.br","000.000.000-00","(00) 0.0000-0000","00.000-000","user","123","Usuario","Inativa","00/00/0000","21/07/2099");

create table componentes (
idcomponente int primary key auto_increment not null,
comp_nome varchar (100)
);
insert into componentes (comp_nome)
values ("Educação Física"),("Ciências");

create table editora (
ideditora int primary key auto_increment not null,
edit_nome varchar (100)
);
insert into editora (edit_nome)
values ("Moderna"),("Manole");

create table serie (
idserie int primary key auto_increment not null,
seri_nivel varchar (10)
);
insert into serie (seri_nivel)
values ("1º E.M."),("2º E.M."),("3º E.M."),("4º E.M."),("5º E.F."),("6º E.F."),("7º E.F."),("8º E.F."),("9º E.F.");

create table livro (
idliv int primary key auto_increment not null,
liv_componente varchar (150),
liv_nomeautor varchar (200),
liv_nome varchar (200),
liv_editora varchar (150),
liv_edicao varchar (50),
liv_ano varchar (10),
liv_serie varchar (10),
liv_periodo varchar (50),
liv_observacoes varchar (200),
liv_foto longblob

);
insert into livro (liv_componente,liv_nomeautor,liv_nome,liv_editora,liv_edicao,liv_ano,liv_serie,liv_periodo,liv_observacoes)
values ("Educação Física","Nome do primeiro autor","Educação acima de tudo","Moderna","1º Edição","2020","6º E.F.","2020,2021,2022,2023","Sem Observações");

create table conteudo (
idconteudo int primary key auto_increment not null,
cont_conteudo longblob,
cont_habilidade longblob,
cont_atividade longblob,
cont_recurso longblob,
cont_code_conteudo varchar (60),
cont_serie varchar (10),
cont_componente varchar (100),
cont_unidadetematica varchar (200),
idliv int not null
);
insert into conteudo (cont_conteudo,cont_habilidade,cont_atividade,cont_recurso,cont_code_conteudo,cont_serie,cont_componente,idliv)
values ("Aqui vai o conteúdo","Aqui Vai as habilidades","aqui vai os Atividades","aqui vao os recursos","(EF254DF)","6º E.F.","Educação Física","1");

insert into conteudo (cont_code_conteudo,cont_serie,cont_componente,idliv)
values ("10","1º","Ciências","1");

create table licenca (
idlicenca int primary key auto_increment not null,
idcli int not null,
lic_chave varchar (200), -- aqui vai a chave informada pelo usuario
lic_licenca varchar (200), -- aqui vai a chave de licença para o usuaqui
lic_data_valida varchar (30),
lic_status boolean,
lic_descricao varchar (50) default 'Não Licenciado',
lic_nomecli varchar (200),
lic_cpfcli varchar (50),
lic_emailcli varchar (150),
lic_celularcli varchar (50),
lic_data_licenca timestamp default current_timestamp
);
insert into licenca (idcli)
values ();

create table escola (
idescola int primary key auto_increment not null,
esc_estado varchar (400),
esc_secretaria varchar (400),
esc_ecola varchar (400),
esc_cidade varchar (150),
esc_uf varchar (10),
esc_endereco varchar (250),
esc_num varchar (10),
esc_brasao longblob,
idcli int not null
);
insert into escola (esc_estado,esc_secretaria,esc_ecola,esc_cidade,esc_uf,esc_endereco,esc_num,idcli)
values ("Estado de Rondônia","Secretaria de estado da educação - SEDUC","E.E.E.F.M. Nome da Escola","Nome da Cidade","RO","Rua nome da rua","1000","2");

create table compselecionado (
idcompselec int primary key auto_increment not null,
iduso int not null,
nomecomponente varchar(100)

);
insert into compselecionado (iduso,nomecomponente)
values ("2","Educação Física");


describe conteudo;
select idliv,idconteudo,cont_code_conteudo,cont_unidadetematica from conteudo where idliv = '1' and cont_componente = "Educação Física" and cont_serie = "1º";