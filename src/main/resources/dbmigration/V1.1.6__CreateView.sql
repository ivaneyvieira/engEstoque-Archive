CREATE OR REPLACE VIEW v_loc_localizacao AS
  SELECT DISTINCT loja_id, localizacao
  FROM v_loc_produtos;

CREATE OR REPLACE VIEW v_loc_abreviacao AS
  SELECT DISTINCT loja_id, abreviacao
  FROM v_loc_produtos;