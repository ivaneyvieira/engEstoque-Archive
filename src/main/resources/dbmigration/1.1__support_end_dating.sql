-- apply changes
create table saldos (
  produto_id                    bigint,
  loja_id                       bigint,
  quantidade                    integer not null
);

create index ix_saldos_produto_id on saldos (produto_id);
alter table saldos add constraint fk_saldos_produto_id foreign key (produto_id) references produtos (id) on delete restrict on update restrict;

create index ix_saldos_loja_id on saldos (loja_id);
alter table saldos add constraint fk_saldos_loja_id foreign key (loja_id) references lojas (id) on delete restrict on update restrict;

