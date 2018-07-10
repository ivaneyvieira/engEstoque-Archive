-- apply changes
create table labels (
  id                            bigint auto_increment not null,
  tipo                          varchar(6) not null,
  layout                        longtext not null,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  version                       integer not null,
  constraint ck_labels_tipo check ( tipo in ('NORMAL','PECA','BOBINA','CAIXA')),
  constraint uq_labels_tipo unique (tipo),
  constraint pk_labels primary key (id)
);

alter table lotes add constraint uq_lotes_loja_id_produto_id_sequencia unique  (loja_id,produto_id,sequencia);
alter table produtos add column label_id bigint;

create index ix_produtos_label_id on produtos (label_id);
alter table produtos add constraint fk_produtos_label_id foreign key (label_id) references labels (id) on delete restrict on update restrict;

