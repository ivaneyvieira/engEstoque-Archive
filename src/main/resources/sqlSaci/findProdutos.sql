select TRIM(prdno) as codigo, grade, TRIM(S.barcode) codebar,
       TRIM(MID(S.name, 1, 37)) as nome, cost/10000 as custo
from sqlpdv.prdstk AS S
  inner join sqldados.prd AS P
    ON P.no = S.prdno
where no = :prdno
      and storeno IN (1, 2, 3, 4, 5, 6, 7, 10)
GROUP BY prdno, grade