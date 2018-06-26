-- apply changes
create table itens_nota (
  id                            bigint auto_increment not null,
  quantidade                    integer not null,
  produto_id                    bigint,
  nota_id                       bigint,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  version                       integer not null,
  constraint pk_itens_nota primary key (id)
);

create table lojas (
  id                            bigint auto_increment not null,
  numero                        integer not null,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  version                       integer not null,
  constraint pk_lojas primary key (id)
);

create table lotes (
  id                            bigint auto_increment not null,
  sequencia                     integer not null,
  total                         integer not null,
  produto_id                    bigint,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  version                       integer not null,
  constraint pk_lotes primary key (id)
);

create table movimentacoes (
  id                            bigint auto_increment not null,
  quantidade                    integer not null,
  saldo                         integer not null,
  lote_id                       bigint,
  item_nota_id                  bigint,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  version                       integer not null,
  constraint pk_movimentacoes primary key (id)
);

create table notas (
  id                            bigint auto_increment not null,
  numero                        varchar(15) not null,
  tipo_mov                      varchar(7) not null,
  data                          date not null,
  hora                          time not null,
  observacao                    varchar(100) not null,
  loja_id                       bigint,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  version                       integer not null,
  constraint ck_notas_tipo_mov check ( tipo_mov in ('ENTRADA','SAIDA')),
  constraint pk_notas primary key (id)
);

create table produtos (
  id                            bigint auto_increment not null,
  codigo                        varchar(16) not null,
  grade                         varchar(8) not null,
  codebar                       varchar(16) not null,
  data_cadastro                 date not null,
  tipo                          varchar(6) not null,
  quant_lote                    integer not null,
  quant_bobina                  integer not null,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  version                       integer not null,
  constraint ck_produtos_tipo check ( tipo in ('NORMAL','PECA','BOBINA','CAIXA')),
  constraint pk_produtos primary key (id)
);

create table saldos (
  id                            bigint auto_increment not null,
  produto_id                    bigint,
  loja_id                       bigint,
  quantidade                    integer not null,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  version                       integer not null,
  constraint pk_saldos primary key (id)
);

create table usuarios (
  id                            bigint auto_increment not null,
  login_name                    varchar(8) not null,
  loja_id                       bigint,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  version                       integer not null,
  constraint pk_usuarios primary key (id)
);

create index ix_itens_nota_produto_id on itens_nota (produto_id);
alter table itens_nota add constraint fk_itens_nota_produto_id foreign key (produto_id) references produtos (id) on delete restrict on update restrict;

create index ix_itens_nota_nota_id on itens_nota (nota_id);
alter table itens_nota add constraint fk_itens_nota_nota_id foreign key (nota_id) references notas (id) on delete restrict on update restrict;

create index ix_lotes_produto_id on lotes (produto_id);
alter table lotes add constraint fk_lotes_produto_id foreign key (produto_id) references produtos (id) on delete restrict on update restrict;

create index ix_movimentacoes_lote_id on movimentacoes (lote_id);
alter table movimentacoes add constraint fk_movimentacoes_lote_id foreign key (lote_id) references lotes (id) on delete restrict on update restrict;

create index ix_movimentacoes_item_nota_id on movimentacoes (item_nota_id);
alter table movimentacoes add constraint fk_movimentacoes_item_nota_id foreign key (item_nota_id) references itens_nota (id) on delete restrict on update restrict;

create index ix_notas_loja_id on notas (loja_id);
alter table notas add constraint fk_notas_loja_id foreign key (loja_id) references lojas (id) on delete restrict on update restrict;

create index ix_saldos_produto_id on saldos (produto_id);
alter table saldos add constraint fk_saldos_produto_id foreign key (produto_id) references produtos (id) on delete restrict on update restrict;

create index ix_saldos_loja_id on saldos (loja_id);
alter table saldos add constraint fk_saldos_loja_id foreign key (loja_id) references lojas (id) on delete restrict on update restrict;

create index ix_usuarios_loja_id on usuarios (loja_id);
alter table usuarios add constraint fk_usuarios_loja_id foreign key (loja_id) references lojas (id) on delete restrict on update restrict;

