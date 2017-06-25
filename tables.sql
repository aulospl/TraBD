/*
DROP TABLE MODALIDADE;
DROP TABLE MEDICO;
DROP TABLE NACAO;
DROP TABLE TREINADOR;
DROP TABLE ATLETA;
DROP TABLE ROTINA;
DROP TABLE RECUPERACAO;
DROP TABLE PREPARO;
DROP TABLE TREINO;
DROP TABLE CONSULTA;
DROP TABLE TRATAMENTO;
DROP TABLE RESULTADO_CONSULTA;
DROP TABLE LESAO;
DROP TABLE DESCRICAO_LESAO;
DROP TABLE EXAME_DOPING;
DROP TABLE DESCRICAO_DOPING;
*/

CREATE TABLE MODALIDADE (
  codigo INT NOT NULL,
  nome   VARCHAR2(50),
  descricao VARCHAR2(200),
  CONSTRAINT modalidade_pk PRIMARY KEY (codigo),
  CONSTRAINT modalidade_codigo_positivo CHECK (codigo > 0)
);

CREATE TABLE MEDICO(
  codigo INT NOT NULL,
  crm    INT NOT NULL,
  nome   VARCHAR2(60) NOT NULL,
  carteira_id VARCHAR2(30) NOT NULL,
  telefone_1 VARCHAR2(30) NOT NULL,
  telefone_2 VARCHAR2(30),
  telefone_3 VARCHAR2(30),
  CONSTRAINT medico_pk PRIMARY KEY (codigo),
  CONSTRAINT medico_codigo_positivo CHECK (codigo > 0),
  CONSTRAINT medico_crm_positivo CHECK (crm > 0),
  CONSTRAINT medico_crm_sk UNIQUE (crm) 
);

CREATE TABLE NACAO(
  nome VARCHAR2(50) NOT NULL,
  continente VARCHAR2(7) NOT NULL,
  bandeira VARCHAR2(100) NOT NULL,
  hino VARCHAR2(100) NOT NULL,
  num_atletas INT DEFAULT 0,
  modalidade_principal INT NOT NULL,
  CONSTRAINT nacao_pk PRIMARY KEY (nome),
  CONSTRAINT fk_nacao_modalidade FOREIGN KEY (modalidade_principal)
    REFERENCES MODALIDADE(codigo),
  CONSTRAINT num_atletas_min CHECK (num_atletas >= 0)
);

CREATE TABLE TREINADOR(
  passaporte VARCHAR2(8) NOT NULL,
  data_nasc  DATE NOT NULL,
  nome       VARCHAR2(60) NOT NULL,
  sexo       CHAR(1) NOT NULL,
  nacao      VARCHAR2(50) NOT NULL,
  estado     VARCHAR2(50) NOT NULL,
  cidade     VARCHAR2(50) NOT NULL,
  telefone_1 VARCHAR2(30) NOT NULL,
  telefone_2 VARCHAR2(30),
  email      VARCHAR2(40) NOT NULL,
  senha      VARCHAR2(30) DEFAULT 'a',
  CONSTRAINT treinador_pk PRIMARY KEY (passaporte),
  CONSTRAINT fk_treinador_nacao FOREIGN KEY (nacao)
    REFERENCES NACAO(nome),
  CONSTRAINT sexo_treinador CHECK (sexo = 'm' OR sexo = 'f')
);

CREATE TABLE ATLETA(
  passaporte VARCHAR2(8) NOT NULL,
  data_nasc  DATE NOT NULL,
  nome       VARCHAR2(60) NOT NULL,
  sexo       CHAR(1) NOT NULL,
  altura     NUMBER(3,2) NOT NULL,
  peso       NUMBER(6, 3) NOT NULL,
  regularizado CHAR(1) DEFAULT 's',
  cpf        VARCHAR2(14),
  treinador  VARCHAR2(8) NOT NULL,
  modalidade INT NOT NULL,
  CONSTRAINT atleta_pk PRIMARY KEY (passaporte),
  CONSTRAINT sexo_atleta CHECK (sexo = 'm' OR sexo = 'f'),
  CONSTRAINT altura_atleta CHECK (altura > 0),
  CONSTRAINT peso_atleta CHECK (peso > 0),
  CONSTRAINT fk_atleta_treinador FOREIGN KEY (treinador)
    REFERENCES TREINADOR(passaporte),
  CONSTRAINT fk_atleta_modalidade FOREIGN KEY (modalidade)
    REFERENCES MODALIDADE(codigo)
  
);

CREATE TABLE ROTINA(
  atleta VARCHAR2(8) NOT NULL,
  treinador VARCHAR2(8) NOT NULL,
  data_rotina DATE NOT NULL,
  CONSTRAINT rotina_pk PRIMARY KEY (atleta, treinador, data_rotina),
  CONSTRAINT fk_rotina_atleta FOREIGN KEY (atleta)
    REFERENCES ATLETA(passaporte),
  CONSTRAINT fk_rotina_treinador FOREIGN KEY (treinador)
    REFERENCES TREINADOR(passaporte)
);

CREATE TABLE RECUPERACAO(
  atleta VARCHAR2(8) NOT NULL,
  treinador VARCHAR2(8) NOT NULL,
  data_rotina DATE NOT NULL,
  descricao VARCHAR2(200) NOT NULL,
  CONSTRAINT recuperacao_pk PRIMARY KEY (atleta, treinador, data_rotina),
  CONSTRAINT fk_recuperacao_rotina FOREIGN KEY (atleta, treinador, data_rotina)
    REFERENCES ROTINA(atleta, treinador, data_rotina)
  
);

CREATE TABLE PREPARO(
  atleta VARCHAR2(8) NOT NULL,
  treinador VARCHAR2(8) NOT NULL,
  data_rotina DATE NOT NULL,
  descricao VARCHAR2(200) NOT NULL,
  CONSTRAINT preparo_pk PRIMARY KEY (atleta, treinador, data_rotina),
  CONSTRAINT fk_preparo_rotina FOREIGN KEY (atleta, treinador, data_rotina)
    REFERENCES ROTINA(atleta, treinador, data_rotina)
  
);

CREATE TABLE TREINO(
  atleta VARCHAR2(8) NOT NULL,
  treinador VARCHAR2(8) NOT NULL,
  data_rotina DATE NOT NULL,
  descricao VARCHAR2(200) NOT NULL,
  CONSTRAINT treino_pk PRIMARY KEY (atleta, treinador, data_rotina),
  CONSTRAINT fk_treino_rotina FOREIGN KEY (atleta, treinador, data_rotina)
    REFERENCES ROTINA(atleta, treinador, data_rotina)
  
);

CREATE TABLE CONSULTA(
  codigo INT NOT NULL,
  medico INT NOT NULL,
  atleta VARCHAR2(8) NOT NULL,
  CONSTRAINT consulta_pk PRIMARY KEY (codigo),
  CONSTRAINT fk_consulta_medico FOREIGN KEY (medico)
    REFERENCES MEDICO(codigo),
  CONSTRAINT fk_consulta_atleta FOREIGN KEY (atleta)
    REFERENCES ATLETA(passaporte),
  CONSTRAINT consulta_codigo CHECK(codigo > 0)
);

CREATE TABLE TRATAMENTO(
  codigo INT NOT NULL,
  descricao VARCHAR2(200) NOT NULL,
  efetividade VARCHAR2(200),
  consulta INT NOT NULL,
  CONSTRAINT tratamento_pk PRIMARY KEY (codigo),
  CONSTRAINT fk_tratamento_consulta FOREIGN KEY (consulta)
    REFERENCES CONSULTA(codigo),
  CONSTRAINT tratamento_codigo CHECK(codigo > 0)
);

CREATE TABLE RESULTADO_CONSULTA(
  codigo INT NOT NULL,
  data_res DATE NOT NULL,
  sintomas VARCHAR2(200) NOT NULL,
  diagnostico VARCHAR2(200) NOT NULL,
  CONSTRAINT resultado_pk PRIMARY KEY (codigo),
  CONSTRAINT fk_resultado_consulta FOREIGN KEY (codigo)
    REFERENCES CONSULTA(codigo),
  CONSTRAINT resultado_codigo CHECK(codigo > 0)
);

CREATE TABLE LESAO(
  codigo INT NOT NULL,
  medico INT NOT NULL,
  atleta VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_lesao PRIMARY KEY (codigo),
  CONSTRAINT fk_lesao_medico FOREIGN KEY (medico)
    REFERENCES MEDICO(codigo),
  CONSTRAINT fk_lesao_atleta FOREIGN KEY (atleta)
    REFERENCES ATLETA(passaporte),
  CONSTRAINT codigo_lesao CHECK(codigo > 0)
);

CREATE TABLE DESCRICAO_LESAO(
  lesao INT NOT NULL,
  descricao VARCHAR2(200) NOT NULL,
  CONSTRAINT pk_descricao_lesao PRIMARY KEY (lesao),
  CONSTRAINT fk_descricao_lesao_lesao FOREIGN KEY (lesao)
    REFERENCES LESAO(codigo)
);

CREATE TABLE EXAME_DOPING(
  codigo INT NOT NULL,
  medico INT NOT NULL,
  atleta VARCHAR2(8) NOT NULL,
  CONSTRAINT pk_exame_doping PRIMARY KEY (codigo),
  CONSTRAINT fk_exame_doping_medico FOREIGN KEY (medico)
    REFERENCES MEDICO(codigo),
  CONSTRAINT fk_exame_doping_atleta FOREIGN KEY (atleta)
    REFERENCES ATLETA(passaporte),
  CONSTRAINT codigo_exame_doping CHECK(codigo > 0)
);

CREATE TABLE DESCRICAO_DOPING(
  doping INT NOT NULL,
  descricao VARCHAR2(200) NOT NULL,
  CONSTRAINT pk_descricao_doping PRIMARY KEY (doping),
  CONSTRAINT fk_desc_doping FOREIGN KEY (doping)
    REFERENCES EXAME_DOPING(codigo)
);