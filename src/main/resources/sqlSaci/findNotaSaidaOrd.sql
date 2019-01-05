SELECT DISTINCT cast(CONCAT(N.paymno) as char)     AS rota,
                N.storeno,
                N.ordno       AS numero,
                ''            AS serie,
                N.date,
                N.date as dt_emissao,
                P.prdno,
                P.grade,
                P.qtty / 1000 AS quant,
                C.name        AS clienteName,
                'PEDIDO_S'    AS tipo
FROM sqldados.eord AS                    N
       INNER JOIN sqldados.eoprd AS      P USING (storeno, ordno)
       INNER JOIN engEstoque.produtos AS E
         ON E.codigo = P.prdno
        AND E.grade = P.grade
       INNER JOIN sqldados.paym AS M
                  ON N.paymno = M.no
                 AND M.sname  = :cd
       LEFT JOIN  sqldados.custp AS      C
         ON C.no = N.custno
WHERE N.date > DATE_SUB(current_date, INTERVAL 7 DAY) AND
      N.storeno = :storeno AND
      (N.ordno = :nfno)
