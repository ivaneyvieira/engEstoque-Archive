select I.storeno as storenoT, N.storeno, N.nfno, N.nfse, N.issuedate as date,
  TRIM(P.prdno) as prdno, P.grade, P.qtty as quant, C.name as clienteName
from sqldados.nf AS N
  inner join sqldados.xaprd AS P
  USING(storeno, pdvno, xano)
  inner join sqldados.custp AS C
    ON C.no = N.custno
  left join sqldados.inv AS I
    ON N.invno = I.invno
where N.storeno  = :storeno
      and (N.nfno = :nfno
        OR N.nfno = CONCAT('0', :nfno)
        OR N.nfno = CONCAT('00', :nfno)
        OR N.nfno = CONCAT('000', :nfno)
        OR N.nfno = CONCAT('0000', :nfno)
        OR N.nfno = CONCAT('00000', :nfno)
        OR N.nfno = CONCAT('000000', :nfno)
      )
      and N.nfse = :nfse
      AND N.issuedate > DATE_SUB(current_date, INTERVAL 12 MONTH)
      AND N.status <> 1
