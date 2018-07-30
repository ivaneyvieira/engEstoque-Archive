select storeno, trim(prdno) as codigo, grade, localizacao
from sqldados.prdloc
WHERE (storeno = :storeno)
  AND (localizacao = :localizacao OR :localizacao = '')
  AND (prdno = :prdno OR :prdno = '')
  AND (grade = :grade OR :grade = '')