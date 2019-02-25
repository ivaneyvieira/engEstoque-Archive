CREATE OR REPLACE VIEW v_codigo_barra_conferencia AS
SELECT I.id AS id_itens_nota,
REPLACE(
    concat(L.numero,' ', N.numero,' ', N.sequencia,' ', substring_index(I.localizacao, '.',1)),
    '/',
    ' '
  )
  AS codbar,
  L.numero as storeno, N.numero, N.sequencia, substring_index(I.localizacao, '.',1) as abreviacao
FROM engEstoque.itens_nota AS I
  JOIN engEstoque.notas AS N
  ON  N.id = I.nota_id
  JOIN engEstoque.lojas AS L
  ON  N.loja_id = L.id;

CREATE OR REPLACE VIEW v_codigo_barra_entrega AS
select I.id AS id_itens_nota,
replace(concat(L.numero,' ',N.numero,' ',N.sequencia,' ',substring_index(I.localizacao,'.',1),
  ' ',trim(P.codigo),' ',P.grade,' ',I.quantidade),
  '/',' ') AS codbar,
  L.numero as storeno, N.numero, N.sequencia, substring_index(I.localizacao,'.',1) AS abreviacao,
  P.codigo, P.grade, I.quantidade
from engEstoque.itens_nota I
join engEstoque.notas N on N.id = I.nota_id
join engEstoque.lojas L on N.loja_id = L.id
join engEstoque.produtos P on P.id = I.produto_id;