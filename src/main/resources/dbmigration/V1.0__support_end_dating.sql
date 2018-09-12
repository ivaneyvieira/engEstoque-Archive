-- apply changes
create table etiquetas (
  id                            bigint auto_increment not null,
  titulo                        varchar(60) not null,
  tipo_mov                      varchar(7),
  template                      longtext not null,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  version                       integer not null,
  constraint ck_etiquetas_tipo_mov check ( tipo_mov in ('ENTRADA','SAIDA')),
  constraint pk_etiquetas primary key (id)
);

create table itens_nota (
  id                            bigint auto_increment not null,
  data                          date not null,
  hora                          time not null,
  quantidade                    integer not null,
  produto_id                    bigint,
  nota_id                       bigint,
  etiqueta_id                   bigint,
  usuario_id                    bigint,
  saldo                         integer,
  impresso                      tinyint(1) default 0 not null,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  version                       integer not null,
  constraint uq_itens_nota_nota_id_produto_id unique (nota_id,produto_id),
  constraint pk_itens_nota primary key (id)
);

create table lojas (
  id                            bigint auto_increment not null,
  numero                        integer not null,
  sigla                         varchar(2) not null,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  version                       integer not null,
  constraint uq_lojas_numero unique (numero),
  constraint pk_lojas primary key (id)
);

create table notas (
  id                            bigint auto_increment not null,
  numero                        varchar(15) not null,
  tipo_mov                      varchar(7) not null,
  tipo_nota                     varchar(15),
  rota                          varchar(6) not null,
  fornecedor                    varchar(60) not null,
  cliente                       varchar(60) not null,
  data                          date not null,
  hora                          time not null,
  observacao                    varchar(100) not null,
  loja_id                       bigint,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  version                       integer not null,
  constraint ck_notas_tipo_mov check ( tipo_mov in ('ENTRADA','SAIDA')),
  constraint ck_notas_tipo_nota check ( tipo_nota in ('COMPRA','TRANSFERENCIA_E','DEV_CLI','ACERTO_E','PEDIDO_E','OUTROS_E','VENDA','TRANSFERENCIA_S','ENT_RET','DEV_FOR','ACERTO_S','PEDIDO_S','OUTROS_S')),
  constraint pk_notas primary key (id)
);

create table produtos (
  id                            bigint auto_increment not null,
  codigo                        varchar(16) not null,
  grade                         varchar(8) not null,
  codebar                       varchar(16) not null,
  data_cadastro                 date not null,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  version                       integer not null,
  constraint uq_produtos_codigo_grade unique (codigo,grade),
  constraint pk_produtos primary key (id)
);

create table usuarios (
  id                            bigint auto_increment not null,
  login_name                    varchar(8) not null,
  impressora                    varchar(30) not null,
  loja_id                       bigint,
  localizacaoes                 varchar(4000) not null,
  created_at                    datetime(6) not null,
  updated_at                    datetime(6) not null,
  version                       integer not null,
  constraint uq_usuarios_login_name unique (login_name),
  constraint pk_usuarios primary key (id)
);

create index ix_notas_loja_id_tipo_mov on notas (loja_id,tipo_mov);
create index ix_notas_numero on notas (numero);
create index ix_produtos_codebar on produtos (codebar);
create index ix_itens_nota_produto_id on itens_nota (produto_id);
alter table itens_nota add constraint fk_itens_nota_produto_id foreign key (produto_id) references produtos (id) on delete restrict on update restrict;

create index ix_itens_nota_nota_id on itens_nota (nota_id);
alter table itens_nota add constraint fk_itens_nota_nota_id foreign key (nota_id) references notas (id) on delete restrict on update restrict;

create index ix_itens_nota_etiqueta_id on itens_nota (etiqueta_id);
alter table itens_nota add constraint fk_itens_nota_etiqueta_id foreign key (etiqueta_id) references etiquetas (id) on delete restrict on update restrict;

create index ix_itens_nota_usuario_id on itens_nota (usuario_id);
alter table itens_nota add constraint fk_itens_nota_usuario_id foreign key (usuario_id) references usuarios (id) on delete restrict on update restrict;

create index ix_notas_loja_id on notas (loja_id);
alter table notas add constraint fk_notas_loja_id foreign key (loja_id) references lojas (id) on delete restrict on update restrict;

alter table produtos add constraint fk_produtos_id foreign key (id) references view_produtos (produto_id) on delete restrict on update restrict;

create index ix_usuarios_loja_id on usuarios (loja_id);
alter table usuarios add constraint fk_usuarios_loja_id foreign key (loja_id) references lojas (id) on delete restrict on update restrict;

