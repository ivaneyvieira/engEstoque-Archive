SELECT CAST(IFNULL(X.xrouteno, '') AS CHAR) AS rota,
       N.storeno,
       N.nfno                               AS numero,
       N.nfse                               AS serie,
       N.issuedate                          AS date,
       N.issuedate                          AS dt_emissao,
       P.prdno                              AS prdno,
       P.grade,
       P.qtty                               AS quant,
       C.name                               AS clienteName,
       CASE WHEN N.nfse = '66'
                 THEN 'ACERTO_S'
            WHEN N.nfse = '3'
                 THEN 'ENT_RET'
            WHEN tipo = 0
                 THEN 'VENDA'
            WHEN tipo = 1
                 THEN 'TRANSFERENCIA_S'
            WHEN tipo = 2
                 THEN 'DEV_FOR'
            WHEN tipo = 3
                 THEN 'DEV_FOR'
            WHEN tipo = 7
                 THEN 'OUTRAS_NFS'
            ELSE 'SP_REME'
           END                              AS tipo
FROM sqldados.nf AS                      N
       INNER JOIN sqldados.xaprd AS      P USING (storeno, pdvno, xano)
       INNER JOIN engEstoque.produtos AS E
         ON E.codigo = P.prdno AND E.grade = P.grade
       LEFT JOIN  sqldados.custp AS      C
         ON C.no = N.custno
       LEFT JOIN  sqldados.inv AS        I
         ON N.invno = I.invno
       LEFT JOIN  sqldados.xfr AS        X
         ON X.no = I.xfrno
WHERE N.storeno = :storeno AND
      N.nfno = :nfno AND
      N.nfse = :nfse AND
      N.issuedate > DATE_SUB(current_date, INTERVAL 100 DAY) AND
      N.status <> 1
UNION
SELECT DISTINCT ''            AS rota,
                N.storeno,
                N.nfno,
                N.nfse,
                N.date,
                N.date,
                P.prdno,
                P.grade,
                P.qtty / 1000 AS quant,
                C.name        AS clienteName,
                CASE WHEN N.nfse = '66'
                          THEN 'ACERTO_S'
                     WHEN N.nfse = '3'
                          THEN 'ENT_RET'
                     ELSE 'VENDA'
                    END       AS tipo
FROM sqlpdv.pxa AS                       N
       INNER JOIN sqlpdv.pxaprd AS       P USING (storeno, pdvno, xano)
       INNER JOIN engEstoque.produtos AS E
         ON E.codigo = P.prdno AND E.grade = P.grade
       LEFT JOIN  sqldados.custp AS      C
         ON C.no = N.custno
WHERE N.storeno = :storeno AND
      N.nfno = :nfno AND
      N.nfse = :nfse AND
      processed = 0 AND
      N.date > DATE_SUB(current_date, INTERVAL 7 DAY)
UNION
SELECT DISTINCT ''            AS rota,
                N.storeno,
                N.ordno       AS nfno,
                ''            AS nfse,
                N.date,
                N.date,
                P.prdno,
                P.grade,
                P.qtty / 1000 AS quant,
                C.name        AS clienteName,
                'PEDIDO_S'    AS tipo
FROM sqldados.eord AS                    N
       INNER JOIN sqldados.eoprd AS      P USING (storeno, ordno)
       INNER JOIN engEstoque.produtos AS E
         ON E.codigo = P.prdno AND E.grade = P.grade
       LEFT JOIN  sqldados.custp AS      C
         ON C.no = N.custno
WHERE N.date > DATE_SUB(current_date, INTERVAL 7 DAY) AND
      N.paymno = 291 AND
      N.storeno = :storeno AND
      (N.ordno = :nfno) AND
      (:nfse = '')


