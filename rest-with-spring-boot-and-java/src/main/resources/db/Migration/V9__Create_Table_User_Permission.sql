CREATE TABLE IF NOT EXISTS public.user_permission (
    id_user bigint NOT NULL,
    id_permission bigint NOT NULL
);

ALTER TABLE ONLY public.user_permission 
    ADD CONSTRAINT FK_user_permission_users FOREIGN KEY (id_user) REFERENCES public.users(id),
    ADD CONSTRAINT fk_user_permission_permission FOREIGN KEY (id_permission) REFERENCES public.permission(id);