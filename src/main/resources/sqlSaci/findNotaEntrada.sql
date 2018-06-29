select P.invno, N.storeno, N.date, TRIM(P.prdno) as prdno,
  P.grade, P.qtty/1000 as quant, P.cost/10000 as custo, V.name as vendName
from sqldados.inv AS N
  inner join sqldados.iprd AS P
  USING(invno)
  inner join sqldados.vend AS V
    ON V.no = N.vendno
where N.storeno = :storeno
      and nfname = :nfname
      and invse = :invse
      AND N.date > DATE_SUB(current_date, INTERVAL 2 MONTH)
      AND N.bits & POW(2, 4) = 0
      AND N.auxShort13 & pow(2, 15) = 0
      AND invse <> ''