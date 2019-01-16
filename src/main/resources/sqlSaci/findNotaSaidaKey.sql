select nf.storeno, nf.nfno, nf.nfse, nf2.nfekey
from sqldados.nf2
  INNER JOIN sqldados.nf
    USING(storeno, pdvno, xano)
WHERE nfekey = :nfekey