INSERT INTO MODALIDADE (codigo, nome, descricao) VALUES (1, 'futebol', 'segundo regulamento FIFA');
INSERT INTO MODALIDADE (codigo, nome, descricao) VALUES (2, 'remo', 'remo 12 pessoas');
INSERT INTO MODALIDADE (codigo, nome, descricao) VALUES (3, 'triatlo', 'agora com 4 esportes incluindo maratona de programação');

INSERT INTO NACAO (nome, continente, bandeira, hino, modalidade_principal) VALUES ('Brasil', 'America', './bandeiras/br.png', './hinos/br.wav', 1);
INSERT INTO NACAO (nome, continente, bandeira, hino, modalidade_principal) VALUES ('Indonesia', 'Oceania', './bandeiras/jv.png', './hinos/jv.wav', 2);
INSERT INTO NACAO (nome, continente, bandeira, hino, modalidade_principal) VALUES ('Republica Checa', 'Europa', './bandeiras/rc.png', './hinos/rc.wav', 3);

INSERT INTO TREINADOR (passaporte, data_nasc, nome, sexo, nacao, estado, cidade, telefone_1, email, senha)
    VALUES ('00100B90', TO_DATE('1980/05/21', 'yyyy/mm/dd'), 'Joaquim Ferdinando', 'm', 'Brasil', 'São Paulo', 'Cesário Lange', '+55 (22) 99999-9993', 'em@il.com', 'b');
INSERT INTO TREINADOR (passaporte, data_nasc, nome, sexo, nacao, estado, cidade, telefone_1, telefone_2, email, senha)
    VALUES ('383498JK', TO_DATE('1980/04/22', 'yyyy/mm/dd'), 'Ioakin Ferdinand', 'm', 'Republica Checa', 'Praga', 'Praga', '+420 084949-037', '+420 4288-9029', 'ioak.frd@gmail.com', '1234_6');
INSERT INTO TREINADOR (passaporte, data_nasc, nome, sexo, nacao, estado, cidade, telefone_1, telefone_2, email, senha)
    VALUES ('89023EUL', TO_DATE('1977/03/23', 'yyyy/mm/dd'), 'Susilo Suparman Bambang', 'm', 'Indonesia', 'Java', 'Jakarta', '+62 93663-2811', '+62 283921-42', 'suparman@gmail.com', 'BaMbAnG');

INSERT INTO ATLETA (passaporte, data_nasc, nome, sexo, altura, peso, cpf, treinador, modalidade)
    VALUES ('09876543', TO_DATE('1990/05/21', 'yyyy/mm/dd'), 'Eduardo Marques do Vale', 'm', 1.74, 80.3, '000.000.000-11', '00100B90', 1);
INSERT INTO ATLETA (passaporte, data_nasc, nome, sexo, altura, peso, treinador, modalidade)
    VALUES ('12345678', TO_DATE('1990/04/22', 'yyyy/mm/dd'), 'Ivan Sascha', 'm', 1.80, 83, '383498JK', 2);
INSERT INTO ATLETA (passaporte, data_nasc, nome, sexo, altura, peso, treinador, modalidade)
    VALUES ('00110022', TO_DATE('1987/03/23', 'yyyy/mm/dd'), 'Hasan Djojohadikusumo', 'm', 1.50, 60.7, '89023EUL', 3);

INSERT INTO MEDICO (codigo, crm, nome, carteira_id, telefone_1, telefone_2)
    VALUES (1, 10932, 'Josivaldo da Silva', '01233210', '+55 (21) 99288-2282', '+55 (19) 88392-1101');
INSERT INTO MEDICO (codigo, crm, nome, carteira_id, telefone_1)
    VALUES (2, 22910, 'Victor Frankenstein', '00111001', '+55 (01) 99119-3992');
INSERT INTO MEDICO (codigo, crm, nome, carteira_id, telefone_1)
    VALUES (3, 98651, 'Nomevaldo Sobrezildo', '82772911', '+41 09382-2881');

INSERT INTO LESAO (codigo, medico, atleta)
    VALUES (1, 1, '09876543');
INSERT INTO LESAO (codigo, medico, atleta)
    VALUES (2, 1, '09876543');
INSERT INTO LESAO (codigo, medico, atleta)
    VALUES (3, 3, '00110022');
INSERT INTO LESAO (codigo, medico, atleta)
    VALUES (4, 2, '09876543');

INSERT INTO DESCRICAO_LESAO (lesao, descricao)
    VALUES (1, 'Joelho direito deslocado');
INSERT INTO DESCRICAO_LESAO (lesao, descricao)
    VALUES (2, 'Joelho esquerdo deslocado');
INSERT INTO DESCRICAO_LESAO (lesao, descricao)
    VALUES (3, 'Torção de tornozelo');
INSERT INTO DESCRICAO_LESAO (lesao, descricao)
    VALUES (4, 'Ambos os joelhos deslocados');
    
INSERT INTO EXAME_DOPING (codigo, atleta, medico)
    VALUES (1, '12345678', 2);
INSERT INTO EXAME_DOPING (codigo, atleta, medico)
    VALUES (2, '00110022', 1);
INSERT INTO EXAME_DOPING (codigo, atleta, medico)
    VALUES (3, '12345678', 3);
    
INSERT INTO DESCRICAO_DOPING (doping, descricao)
    VALUES (1, 'Resultado negativo');
INSERT INTO DESCRICAO_DOPING (doping, descricao)
    VALUES (2, 'Resultado negativo');
INSERT INTO DESCRICAO_DOPING (doping, descricao)
    VALUES (3, 'Resultado positivo');
    
INSERT INTO CONSULTA (codigo, medico, atleta)
    VALUES (1, 1, '09876543');
INSERT INTO CONSULTA (codigo, medico, atleta)
    VALUES (2, 3, '00110022');
INSERT INTO CONSULTA (codigo, medico, atleta)
    VALUES (3, 3, '12345678');
    
INSERT INTO RESULTADO_CONSULTA (codigo, data_res, sintomas, diagnostico)
    VALUES (1, TO_DATE('2017/05/20', 'yyyy/mm/dd'), 'Dor de cabeça intensa', 'Desidratacao');
INSERT INTO RESULTADO_CONSULTA (codigo, data_res, sintomas, diagnostico)
    VALUES (2, TO_DATE('2017/05/20', 'yyyy/mm/dd'), 'Dor de cabeça intensa', 'Desidratacao');
INSERT INTO RESULTADO_CONSULTA (codigo, data_res, sintomas, diagnostico)
    VALUES (3, TO_DATE('2017/05/21', 'yyyy/mm/dd'), 'Dor de cabeça insuportável', 'Hipocondria');

INSERT INTO TRATAMENTO (codigo, descricao, consulta)
    VALUES (1, 'Beber muito líquido', 1);
INSERT INTO TRATAMENTO (codigo, descricao, consulta)
    VALUES (2, 'Beber muito líquido não alcoólico', 2);
INSERT INTO TRATAMENTO (codigo, descricao, consulta)
    VALUES (3, 'Descansar 30min de manhã antes do treino', 3);
    
INSERT INTO ROTINA (atleta, treinador, data_rotina)
    VALUES ('09876543', '00100B90', TO_DATE('2017/05/19', 'yyyy/mm/dd'));
INSERT INTO ROTINA (atleta, treinador, data_rotina)
    VALUES ('09876543', '00100B90', TO_DATE('2017/05/20', 'yyyy/mm/dd'));
INSERT INTO ROTINA (atleta, treinador, data_rotina)
    VALUES ('09876543', '00100B90', TO_DATE('2017/05/21', 'yyyy/mm/dd'));

INSERT INTO ROTINA (atleta, treinador, data_rotina)
    VALUES ('12345678', '383498JK', TO_DATE('2017/05/19', 'yyyy/mm/dd'));
INSERT INTO ROTINA (atleta, treinador, data_rotina)
    VALUES ('12345678', '383498JK', TO_DATE('2017/05/20', 'yyyy/mm/dd'));
INSERT INTO ROTINA (atleta, treinador, data_rotina)
    VALUES ('12345678', '383498JK', TO_DATE('2017/05/21', 'yyyy/mm/dd'));

INSERT INTO ROTINA (atleta, treinador, data_rotina)
    VALUES ('00110022', '89023EUL', TO_DATE('2017/05/19', 'yyyy/mm/dd'));
INSERT INTO ROTINA (atleta, treinador, data_rotina)
    VALUES ('00110022', '89023EUL', TO_DATE('2017/05/20', 'yyyy/mm/dd'));
INSERT INTO ROTINA (atleta, treinador, data_rotina)
    VALUES ('00110022', '89023EUL', TO_DATE('2017/05/21', 'yyyy/mm/dd'));
    
INSERT INTO PREPARO (atleta, treinador, data_rotina, descricao)
    VALUES ('09876543', '00100B90', TO_DATE('2017/05/19', 'yyyy/mm/dd'), 'Preparo do campo');
INSERT INTO PREPARO (atleta, treinador, data_rotina, descricao)
    VALUES ('12345678', '383498JK', TO_DATE('2017/05/19', 'yyyy/mm/dd'), 'Polimento dos remos');
INSERT INTO PREPARO (atleta, treinador, data_rotina, descricao)
    VALUES ('00110022', '89023EUL', TO_DATE('2017/05/19', 'yyyy/mm/dd'), 'Organização dos equipamentos');
    
INSERT INTO TREINO (atleta, treinador, data_rotina, descricao)
    VALUES ('09876543', '00100B90', TO_DATE('2017/05/20', 'yyyy/mm/dd'), 'Treinamento de tiro de meta');
INSERT INTO TREINO (atleta, treinador, data_rotina, descricao)
    VALUES ('12345678', '383498JK', TO_DATE('2017/05/20', 'yyyy/mm/dd'), 'Remar em voltas');
INSERT INTO TREINO (atleta, treinador, data_rotina, descricao)
    VALUES ('00110022', '89023EUL', TO_DATE('2017/05/20', 'yyyy/mm/dd'), 'Realizar o circuito para prática');

INSERT INTO RECUPERACAO (atleta, treinador, data_rotina, descricao)
    VALUES ('09876543', '00100B90', TO_DATE('2017/05/21', 'yyyy/mm/dd'), 'Dormir até meio dia');
INSERT INTO RECUPERACAO (atleta, treinador, data_rotina, descricao)
    VALUES ('12345678', '383498JK', TO_DATE('2017/05/21', 'yyyy/mm/dd'), 'Dormir 8h');
INSERT INTO RECUPERACAO (atleta, treinador, data_rotina, descricao)
    VALUES ('00110022', '89023EUL', TO_DATE('2017/05/21', 'yyyy/mm/dd'), 'Dormir 12h');