-- apply changes
alter table notas add column fornecedor varchar(60) not null;
alter table notas add column cliente varchar(60) not null;

