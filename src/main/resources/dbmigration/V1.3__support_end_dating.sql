-- apply changes
alter table itens_nota add column usuario_id bigint;

create index ix_itens_nota_usuario_id on itens_nota (usuario_id);
alter table itens_nota add constraint fk_itens_nota_usuario_id foreign key (usuario_id) references usuarios (id) on delete restrict on update restrict;

