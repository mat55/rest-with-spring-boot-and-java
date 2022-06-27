CREATE TABLE IF NOT EXISTS public.person (
    id bigint NOT NULL,
    first_name character varying(80) NOT NULL,
    last_name character varying(80) NOT NULL,
    address character varying(100) NOT NULL,
    gender character varying(6) NOT NULL
);

ALTER TABLE public.person OWNER TO postgres;

CREATE SEQUENCE public.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.person_id_seq OWNER TO postgres;

ALTER SEQUENCE public.person_id_seq OWNED BY public.person.id;

ALTER TABLE ONLY public.person ALTER COLUMN id SET DEFAULT nextval('public.person_id_seq'::regclass);

SELECT pg_catalog.setval('public.person_id_seq', 6, true);

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);