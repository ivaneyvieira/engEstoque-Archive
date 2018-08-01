-- apply changes
alter table etiquetas add column tipo_mov varchar(7) not null;
alter table etiquetas add constraint ck_etiquetas_tipo_mov check ( tipo_mov in ('ENTRADA','SAIDA'));

