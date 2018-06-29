-- apply changes
alter table itens_nota add constraint uq_itens_nota_nota_id_produto_id unique  (nota_id,produto_id);
alter table lojas add constraint uq_lojas_numero unique  (numero);
alter table lotes add constraint uq_lotes_sequencia unique  (sequencia);
alter table movimentacoes add constraint uq_movimentacoes_lote_id_item_nota_id unique  (lote_id,item_nota_id);
alter table produtos add constraint uq_produtos_codigo_grade unique  (codigo,grade);
alter table saldos add constraint uq_saldos_produto_id_loja_id unique  (produto_id,loja_id);
alter table usuarios add constraint uq_usuarios_login_name unique  (login_name);
create index ix_notas_numero on notas (numero);
create index ix_produtos_codebar on produtos (codebar);
