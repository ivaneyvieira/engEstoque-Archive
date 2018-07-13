-- apply changes
alter table notas add column tipo_nota varchar(15);
alter table notas add constraint ck_notas_tipo_nota check ( tipo_nota in ('COMPRA','TRANSFERENCIA_E','DEV_CLI','ACERTO_E','VENDA','DEV_FOR','TRANSFERENCIA_S','ACERTO_S','OUTROS'));
alter table notas add column rota varchar(6) not null;

