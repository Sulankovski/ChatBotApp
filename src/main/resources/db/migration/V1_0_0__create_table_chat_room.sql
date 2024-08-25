create table public."user"(
    "id"                bigserial not null primary key,
    "date_created"      timestamp with time zone,
    "date_updated"      timestamp with time zone,
    "name"              text,
    "last_name"         text,
    "age"               integer,
    "username"          text
);

create table public.chat_room(
    "id"                bigserial not null primary key,
    "date_created"      timestamp with time zone,
    "date_updated"      timestamp with time zone,
    "title"             text,
    "user_id"           bigint not null
);

create table public.messages(
    "id"                bigserial not null primary key,
    "date_created"      timestamp with time zone,
    "date_updated"      timestamp with time zone,
    "sender"            bigint not null,
    "receiver"          bigint not null,
    "chat_room"         bigint not null,
    "content"           text
);

alter table public.chat_room add constraint "user_for_chat_room" foreign key ("user_id") references public.user("id");
alter table public.messages add constraint "sender_for_message" foreign key ("sender") references public.user("id");
alter table public.messages add constraint "receiver_for_message" foreign key ("receiver") references public.user("id");
alter table public.messages add constraint "chat_room_for_message" foreign key ("chat_room") references public.chat_room("id");

create index chat_room_for_user on public.chat_room("user_id");
create index sender_for_message on public.messages(sender);
create index receiver_for_message on public.messages(receiver);
create index chat_room_for_message on public.messages(chat_room);