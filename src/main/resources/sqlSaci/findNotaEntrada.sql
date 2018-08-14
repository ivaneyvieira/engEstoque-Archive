select P.invno, N.storeno, nfname as numero, invse as serie,
  CAST(IFNULL(X.xrouteno, '') AS CHAR) as rota, N.date, P.prdno as prdno,
  P.grade, P.qtty/1000 as quant, P.cost/10000 as custo, V.name as vendName,
  CASE
    WHEN invse = '66' then 'ACERTO_E'
    WHEN type = 0 then "COMPRA"
    WHEN type = 1 then "TRANSFERENCIA_E"
    WHEN type = 2 then "DEV_CLI"
    ELSE "INVALIDA"
  END AS tipo, SUBSTRING_INDEX(IFNULL(L.localizacao, ''), '.', 1) AS localizacao
from sqldados.inv AS N
  inner join sqldados.iprd AS P
  USING(invno)
  inner join sqldados.vend AS V
    ON V.no = N.vendno
  left join sqldados.xfr AS X
    ON X.no = N.xfrno
  left join sqldados.prdloc AS L
    ON N.storeno = L.storeno
    AND P.prdno = L.prdno
    AND P.grade = L.grade
where N.storeno = :storeno
      and (nfname = :nfname
        OR nfname = CONCAT('0', :nfname)
        OR nfname = CONCAT('00', :nfname)
        OR nfname = CONCAT('000', :nfname)
        OR nfname = CONCAT('0000', :nfname)
        OR nfname = CONCAT('00000', :nfname)
        OR nfname = CONCAT('000000', :nfname)
        OR nfname = CONCAT('0000000', :nfname)
      )
      and invse = :invse
      AND N.date > DATE_SUB(current_date, INTERVAL 12 MONTH)
      AND N.bits & POW(2, 4) = 0
      AND N.auxShort13 & pow(2, 15) = 0
      AND invse <> ''
UNION
select 0 as invno, N.storeno, ordno as numero, '' as serie,
  '' as rota, N.date, P.prdno as prdno,
  P.grade, P.qtty/1000 as quant, 0/10000 as custo, C.name as vendName,
  'PEDIDO_E' AS tipo, L.localizacao
from sqldados.eord AS N
  inner join sqldados.eoprd AS P
  USING(storeno, ordno)
  inner join engEstoque.produtos AS E
    ON E.codigo = P.prdno AND E.grade = P.grade
  left join sqldados.custp AS C
    ON C.no = N.custno
  left join sqldados.prdloc AS L
    ON N.storeno = L.storeno
    AND P.prdno = L.prdno
    AND P.grade = L.grade
where N.storeno  = :storeno
      and (N.ordno = :nfname)
      and (:invse = '')