select storeno, trim(prdno) as codigo, grade, SUBSTRING_INDEX(IFNULL(L.localizacao, ''), '.', 1) as localizacao
from sqldados.prdloc
WHERE (storeno = :storeno)
  AND (prdno = :prdno OR :prdno = '')
  AND (grade = :grade OR :grade = '')
HAVING (localizacao = :localizacao OR :localizacao = '')