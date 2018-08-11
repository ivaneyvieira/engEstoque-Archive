-- apply changes
create table etiquetas (
  id                            bigint auto_increment not null,
  titulo                        varchar(60) not null,
  template                      longtext not null,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  version                       integer not null,
  constraint pk_etiquetas primary key (id)
);

alter table itens_nota add column etiqueta_id bigint;

create index ix_itens_nota_etiqueta_id on itens_nota (etiqueta_id);
alter table itens_nota add constraint fk_itens_nota_etiqueta_id foreign key (etiqueta_id) references etiquetas (id) on delete restrict on update restrict;

