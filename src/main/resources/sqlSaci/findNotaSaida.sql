select CAST(IFNULL(X.xrouteno, '') AS CHAR) as rota, N.storeno,
       N.nfno                               AS numero,
       N.nfse                               AS serie,
       N.issuedate                          AS date,
       N.issuedate                          AS dt_emissao,
       P.prdno                              AS prdno,
       P.grade,
       P.qtty                               AS quant,
       C.name                               as clienteName,
  CASE
    WHEN N.nfse = '66' then 'ACERTO_S'
    WHEN N.nfse = '3' then 'ENT_RET'
    WHEN tipo = 0 then 'VENDA'
    WHEN tipo = 1 then 'TRANSFERENCIA_S'
    WHEN tipo = 2 then 'DEV_FOR'
    ELSE 'INVALIDA'
  END                                       AS tipo
from sqldados.nf AS N
  inner join sqldados.xaprd AS P
  USING(storeno, pdvno, xano)
  inner join engEstoque.produtos AS E
  ON E.codigo = P.prdno AND E.grade = P.grade
  left join sqldados.custp AS C
    ON C.no = N.custno
  left join sqldados.inv AS I
    ON N.invno = I.invno
  left join sqldados.xfr AS X
    ON X.no = I.xfrno
where N.storeno  = :storeno
      and N.nfno = :nfno
      and N.nfse = :nfse AND
      N.issuedate > DATE_SUB(current_date, INTERVAL 60 DAY)
      AND N.status <> 1
UNION
select DISTINCT ''            as rota, N.storeno,
                N.nfno,
                N.nfse,
                N.date,
                N.date,
                P.prdno,
                P.grade,
                P.qtty / 1000 AS quant,
                C.name        as clienteName,
  CASE
    WHEN N.nfse = '66' then 'ACERTO_S'
    WHEN N.nfse = '3' then 'ENT_RET'
    ELSE 'VENDA'
  END                         AS tipo
from sqlpdv.pxa AS N
  inner join sqlpdv.pxaprd AS P
  USING(storeno, pdvno, xano)
  inner join engEstoque.produtos AS E
    ON E.codigo = P.prdno AND E.grade = P.grade
  left join sqldados.custp AS C
    ON C.no = N.custno
where N.storeno  = :storeno
      and N.nfno = :nfno
      and N.nfse = :nfse
      and processed = 0 AND
      N.date > DATE_SUB(current_date, INTERVAL 7 DAY)
UNION
select DISTINCT ''            as rota, N.storeno,
                N.ordno       AS nfno,
                ''            AS nfse,
                N.date,
                N.date,
                P.prdno,
                P.grade,
                P.qtty / 1000 AS quant,
                C.name        as clienteName,
                'PEDIDO_S'    AS tipo
from sqldados.eord AS N
  inner join sqldados.eoprd AS P
  USING(storeno, ordno)
  inner join engEstoque.produtos AS E
    ON E.codigo = P.prdno AND E.grade = P.grade
  left join sqldados.custp AS C
    ON C.no = N.custno
WHERE N.date > DATE_SUB(current_date, INTERVAL 7 DAY) AND
      N.paymno = 291 AND
      N.storeno = :storeno
      and (N.ordno = :nfno)
      and (:nfse = '')


