select storeno, trim(prdno) as codigo, grade, localizacao
from sqldados.prdloc
WHERE (storeno = :storeno OR :storeno = 0)
  AND (localizacao = :localizacao OR :localizacao = '')