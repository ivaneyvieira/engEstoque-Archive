-- apply changes
alter table notas add constraint ck_notas_tipo_nota check ( tipo_nota in ('COMPRA','TRANSFERENCIA_E','DEV_CLI','ACERTO_E','OUTROS_E','VENDA','TRANSFERENCIA_S','ENT_RET','DEV_FOR','ACERTO_S','OUTROS_S'));
