-- 1
select atl.nome, atl.passaporte, trd.nacao, atl.data_nasc
        from Atleta atl
            join Treinador trd on atl.treinador = trd.passaporte
            join Consulta cns on cns.atleta = atl.passaporte
        where atl.modalidade = 1 and trd.passaporte = '00100B90' and cns.medico = 1;

-- 2
select mdc.codigo, mdc.nome, count(atl.passaporte) as num_atletas
        from Medico mdc
            join Consulta cns on cns.medico = mdc.codigo
            join Atleta atl on atl.passaporte = cns.atleta
            join Treinador trn on trn.passaporte = atl.treinador
        where trn.nacao = 'Brasil'
        group by mdc.codigo, mdc.nome
            having count(*) > 0
        order by mdc.codigo asc;