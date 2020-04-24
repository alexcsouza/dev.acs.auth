--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Databases
--

--
-- Database "template1" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2 (Debian 12.2-2.pgdg100+1)
-- Dumped by pg_dump version 12.2 (Debian 12.2-2.pgdg100+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

UPDATE pg_catalog.pg_database SET datistemplate = false WHERE datname = 'template1';
DROP DATABASE template1;
--
-- Name: template1; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE template1 WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.utf8' LC_CTYPE = 'en_US.utf8';


ALTER DATABASE template1 OWNER TO postgres;

\connect template1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE template1; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE template1 IS 'default template for new databases';


--
-- Name: template1; Type: DATABASE PROPERTIES; Schema: -; Owner: postgres
--

ALTER DATABASE template1 IS_TEMPLATE = true;


\connect template1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE template1; Type: ACL; Schema: -; Owner: postgres
--

REVOKE CONNECT,TEMPORARY ON DATABASE template1 FROM PUBLIC;
GRANT CONNECT ON DATABASE template1 TO PUBLIC;


--
-- PostgreSQL database dump complete
--

--
-- Database "auth" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2 (Debian 12.2-2.pgdg100+1)
-- Dumped by pg_dump version 12.2 (Debian 12.2-2.pgdg100+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: auth; Type: DATABASE; Schema: -; Owner: postgres
--
DROP DATABASE IF EXISTS auth;
CREATE DATABASE auth WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.utf8' LC_CTYPE = 'en_US.utf8';


ALTER DATABASE auth OWNER TO postgres;

\connect auth

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: app; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.app (
    id bigint NOT NULL,
    creation_date timestamp without time zone,
    last_update_date timestamp without time zone,
    version timestamp without time zone,
    description character varying(255),
    label character varying(255),
    name character varying(255)
);


ALTER TABLE public.app OWNER TO postgres;

--
-- Name: app_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.app_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    description character varying(255),
    label character varying(255),
    name character varying(255)
);


ALTER TABLE public.app_aud OWNER TO postgres;

--
-- Name: app_contract_list; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.app_contract_list (
    app_id bigint NOT NULL,
    contract_list_id bigint NOT NULL
);


ALTER TABLE public.app_contract_list OWNER TO postgres;

--
-- Name: app_contract_list_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.app_contract_list_aud (
    rev integer NOT NULL,
    app_id bigint NOT NULL,
    contract_list_id bigint NOT NULL,
    revtype smallint
);


ALTER TABLE public.app_contract_list_aud OWNER TO postgres;

--
-- Name: app_module_list; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.app_module_list (
    app_id bigint NOT NULL,
    module_list_id bigint NOT NULL
);


ALTER TABLE public.app_module_list OWNER TO postgres;

--
-- Name: app_module_list_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.app_module_list_aud (
    rev integer NOT NULL,
    app_id bigint NOT NULL,
    module_list_id bigint NOT NULL,
    revtype smallint
);


ALTER TABLE public.app_module_list_aud OWNER TO postgres;

--
-- Name: company; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.company (
    id bigint NOT NULL,
    creation_date timestamp without time zone,
    last_update_date timestamp without time zone,
    version timestamp without time zone,
    name character varying(255),
    site character varying(255)
);


ALTER TABLE public.company OWNER TO postgres;

--
-- Name: company_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.company_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    name character varying(255),
    site character varying(255)
);


ALTER TABLE public.company_aud OWNER TO postgres;

--
-- Name: company_user_list; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.company_user_list (
    company_id bigint NOT NULL,
    user_list_id bigint NOT NULL
);


ALTER TABLE public.company_user_list OWNER TO postgres;

--
-- Name: company_user_list_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.company_user_list_aud (
    rev integer NOT NULL,
    company_id bigint NOT NULL,
    user_list_id bigint NOT NULL,
    revtype smallint
);


ALTER TABLE public.company_user_list_aud OWNER TO postgres;

--
-- Name: contract; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contract (
    id bigint NOT NULL,
    creation_date timestamp without time zone,
    last_update_date timestamp without time zone,
    version timestamp without time zone,
    app_id bigint,
    company_id bigint
);


ALTER TABLE public.contract OWNER TO postgres;

--
-- Name: contract_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contract_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    app_id bigint,
    company_id bigint
);


ALTER TABLE public.contract_aud OWNER TO postgres;

--
-- Name: feature; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.feature (
    id bigint NOT NULL,
    creation_date timestamp without time zone,
    last_update_date timestamp without time zone,
    version timestamp without time zone,
    description character varying(255),
    label character varying(255),
    name character varying(255),
    module_id bigint,
    permission_id bigint
);


ALTER TABLE public.feature OWNER TO postgres;

--
-- Name: feature_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.feature_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    description character varying(255),
    label character varying(255),
    name character varying(255),
    module_id bigint,
    permission_id bigint
);


ALTER TABLE public.feature_aud OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: module; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.module (
    id bigint NOT NULL,
    creation_date timestamp without time zone,
    last_update_date timestamp without time zone,
    version timestamp without time zone,
    description character varying(255),
    label character varying(255),
    name character varying(255),
    app_id bigint
);


ALTER TABLE public.module OWNER TO postgres;

--
-- Name: module_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.module_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    description character varying(255),
    label character varying(255),
    name character varying(255),
    app_id bigint
);


ALTER TABLE public.module_aud OWNER TO postgres;

--
-- Name: module_feature_list; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.module_feature_list (
    module_id bigint NOT NULL,
    feature_list_id bigint NOT NULL
);


ALTER TABLE public.module_feature_list OWNER TO postgres;

--
-- Name: module_feature_list_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.module_feature_list_aud (
    rev integer NOT NULL,
    module_id bigint NOT NULL,
    feature_list_id bigint NOT NULL,
    revtype smallint
);


ALTER TABLE public.module_feature_list_aud OWNER TO postgres;

--
-- Name: permission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permission (
    id bigint NOT NULL,
    creation_date timestamp without time zone,
    last_update_date timestamp without time zone,
    version timestamp without time zone,
    description character varying(255),
    label character varying(255),
    name character varying(255),
    feature_id bigint
);


ALTER TABLE public.permission OWNER TO postgres;

--
-- Name: permission_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permission_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    description character varying(255),
    label character varying(255),
    name character varying(255),
    feature_id bigint
);


ALTER TABLE public.permission_aud OWNER TO postgres;

--
-- Name: permission_group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permission_group (
    id bigint NOT NULL,
    creation_date timestamp without time zone,
    last_update_date timestamp without time zone,
    version timestamp without time zone,
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.permission_group OWNER TO postgres;

--
-- Name: permission_group_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permission_group_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.permission_group_aud OWNER TO postgres;

--
-- Name: permission_group_permission_list; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permission_group_permission_list (
    permission_group_id bigint NOT NULL,
    permission_list_id bigint NOT NULL
);


ALTER TABLE public.permission_group_permission_list OWNER TO postgres;

--
-- Name: permission_group_permission_list_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permission_group_permission_list_aud (
    rev integer NOT NULL,
    permission_group_id bigint NOT NULL,
    permission_list_id bigint NOT NULL,
    revtype smallint
);


ALTER TABLE public.permission_group_permission_list_aud OWNER TO postgres;

--
-- Name: permission_permission_group_list; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permission_permission_group_list (
    permission_id bigint NOT NULL,
    permission_group_list_id bigint NOT NULL
);


ALTER TABLE public.permission_permission_group_list OWNER TO postgres;

--
-- Name: permission_permission_group_list_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permission_permission_group_list_aud (
    rev integer NOT NULL,
    permission_id bigint NOT NULL,
    permission_group_list_id bigint NOT NULL,
    revtype smallint
);


ALTER TABLE public.permission_permission_group_list_aud OWNER TO postgres;

--
-- Name: profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profile (
    id bigint NOT NULL,
    creation_date timestamp without time zone,
    last_update_date timestamp without time zone,
    version timestamp without time zone
);


ALTER TABLE public.profile OWNER TO postgres;

--
-- Name: profile_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profile_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint
);


ALTER TABLE public.profile_aud OWNER TO postgres;

--
-- Name: revinfo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.revinfo (
    rev integer NOT NULL,
    revtstmp bigint
);


ALTER TABLE public.revinfo OWNER TO postgres;

--
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id bigint NOT NULL,
    creation_date timestamp without time zone,
    last_update_date timestamp without time zone,
    version timestamp without time zone,
    email character varying(255),
    name character varying(255),
    password character varying(255),
    profile bytea
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- Name: user_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    email character varying(255),
    name character varying(255),
    password character varying(255),
    profile bytea
);


ALTER TABLE public.user_aud OWNER TO postgres;

--
-- Name: user_group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_group (
    id bigint NOT NULL,
    creation_date timestamp without time zone,
    last_update_date timestamp without time zone,
    version timestamp without time zone,
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.user_group OWNER TO postgres;

--
-- Name: user_group_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_group_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.user_group_aud OWNER TO postgres;

--
-- Name: user_group_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_group_user (
    user_id bigint NOT NULL,
    user_group_id bigint NOT NULL
);


ALTER TABLE public.user_group_user OWNER TO postgres;

--
-- Name: user_group_user_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_group_user_aud (
    rev integer NOT NULL,
    user_id bigint NOT NULL,
    user_group_id bigint NOT NULL,
    revtype smallint
);


ALTER TABLE public.user_group_user_aud OWNER TO postgres;

--
-- Name: user_profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_profile (
    user_id bigint,
    profile_id bigint NOT NULL
);


ALTER TABLE public.user_profile OWNER TO postgres;

--
-- Name: user_profile_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_profile_aud (
    profile_id bigint NOT NULL,
    rev integer NOT NULL,
    user_id bigint
);


ALTER TABLE public.user_profile_aud OWNER TO postgres;

--
-- Data for Name: app; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.app (id, creation_date, last_update_date, version, description, label, name) FROM stdin;
\.


--
-- Data for Name: app_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.app_aud (id, rev, revtype, description, label, name) FROM stdin;
\.


--
-- Data for Name: app_contract_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.app_contract_list (app_id, contract_list_id) FROM stdin;
\.


--
-- Data for Name: app_contract_list_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.app_contract_list_aud (rev, app_id, contract_list_id, revtype) FROM stdin;
\.


--
-- Data for Name: app_module_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.app_module_list (app_id, module_list_id) FROM stdin;
\.


--
-- Data for Name: app_module_list_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.app_module_list_aud (rev, app_id, module_list_id, revtype) FROM stdin;
\.


--
-- Data for Name: company; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.company (id, creation_date, last_update_date, version, name, site) FROM stdin;
\.


--
-- Data for Name: company_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.company_aud (id, rev, revtype, name, site) FROM stdin;
\.


--
-- Data for Name: company_user_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.company_user_list (company_id, user_list_id) FROM stdin;
\.


--
-- Data for Name: company_user_list_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.company_user_list_aud (rev, company_id, user_list_id, revtype) FROM stdin;
\.


--
-- Data for Name: contract; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.contract (id, creation_date, last_update_date, version, app_id, company_id) FROM stdin;
\.


--
-- Data for Name: contract_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.contract_aud (id, rev, revtype, app_id, company_id) FROM stdin;
\.


--
-- Data for Name: feature; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.feature (id, creation_date, last_update_date, version, description, label, name, module_id, permission_id) FROM stdin;
\.


--
-- Data for Name: feature_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.feature_aud (id, rev, revtype, description, label, name, module_id, permission_id) FROM stdin;
\.


--
-- Data for Name: module; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.module (id, creation_date, last_update_date, version, description, label, name, app_id) FROM stdin;
\.


--
-- Data for Name: module_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.module_aud (id, rev, revtype, description, label, name, app_id) FROM stdin;
\.


--
-- Data for Name: module_feature_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.module_feature_list (module_id, feature_list_id) FROM stdin;
\.


--
-- Data for Name: module_feature_list_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.module_feature_list_aud (rev, module_id, feature_list_id, revtype) FROM stdin;
\.


--
-- Data for Name: permission; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permission (id, creation_date, last_update_date, version, description, label, name, feature_id) FROM stdin;
\.


--
-- Data for Name: permission_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permission_aud (id, rev, revtype, description, label, name, feature_id) FROM stdin;
\.


--
-- Data for Name: permission_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permission_group (id, creation_date, last_update_date, version, description, name) FROM stdin;
\.


--
-- Data for Name: permission_group_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permission_group_aud (id, rev, revtype, description, name) FROM stdin;
\.


--
-- Data for Name: permission_group_permission_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permission_group_permission_list (permission_group_id, permission_list_id) FROM stdin;
\.


--
-- Data for Name: permission_group_permission_list_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permission_group_permission_list_aud (rev, permission_group_id, permission_list_id, revtype) FROM stdin;
\.


--
-- Data for Name: permission_permission_group_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permission_permission_group_list (permission_id, permission_group_list_id) FROM stdin;
\.


--
-- Data for Name: permission_permission_group_list_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permission_permission_group_list_aud (rev, permission_id, permission_group_list_id, revtype) FROM stdin;
\.


--
-- Data for Name: profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profile (id, creation_date, last_update_date, version) FROM stdin;
\.


--
-- Data for Name: profile_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profile_aud (id, rev, revtype) FROM stdin;
\.


--
-- Data for Name: revinfo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.revinfo (rev, revtstmp) FROM stdin;
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (id, creation_date, last_update_date, version, email, name, password, profile) FROM stdin;
1	2020-04-21 16:38:24.549381	2020-04-21 16:38:24.549381	2020-04-21 16:38:24.549381	admin@email.com	admin	pass	\N
\.


--
-- Data for Name: user_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_aud (id, rev, revtype, email, name, password, profile) FROM stdin;
\.


--
-- Data for Name: user_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_group (id, creation_date, last_update_date, version, description, name) FROM stdin;
\.


--
-- Data for Name: user_group_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_group_aud (id, rev, revtype, description, name) FROM stdin;
\.


--
-- Data for Name: user_group_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_group_user (user_id, user_group_id) FROM stdin;
\.


--
-- Data for Name: user_group_user_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_group_user_aud (rev, user_id, user_group_id, revtype) FROM stdin;
\.


--
-- Data for Name: user_profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_profile (user_id, profile_id) FROM stdin;
\.


--
-- Data for Name: user_profile_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_profile_aud (profile_id, rev, user_id) FROM stdin;
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- Name: app_aud app_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_aud
    ADD CONSTRAINT app_aud_pkey PRIMARY KEY (id, rev);


--
-- Name: app_contract_list_aud app_contract_list_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_contract_list_aud
    ADD CONSTRAINT app_contract_list_aud_pkey PRIMARY KEY (rev, app_id, contract_list_id);


--
-- Name: app_module_list_aud app_module_list_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_module_list_aud
    ADD CONSTRAINT app_module_list_aud_pkey PRIMARY KEY (rev, app_id, module_list_id);


--
-- Name: app app_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app
    ADD CONSTRAINT app_pkey PRIMARY KEY (id);


--
-- Name: company_aud company_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_aud
    ADD CONSTRAINT company_aud_pkey PRIMARY KEY (id, rev);


--
-- Name: company company_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_pkey PRIMARY KEY (id);


--
-- Name: company_user_list_aud company_user_list_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_user_list_aud
    ADD CONSTRAINT company_user_list_aud_pkey PRIMARY KEY (rev, company_id, user_list_id);


--
-- Name: contract_aud contract_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contract_aud
    ADD CONSTRAINT contract_aud_pkey PRIMARY KEY (id, rev);


--
-- Name: contract contract_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contract
    ADD CONSTRAINT contract_pkey PRIMARY KEY (id);


--
-- Name: feature_aud feature_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feature_aud
    ADD CONSTRAINT feature_aud_pkey PRIMARY KEY (id, rev);


--
-- Name: feature feature_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feature
    ADD CONSTRAINT feature_pkey PRIMARY KEY (id);


--
-- Name: module_aud module_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.module_aud
    ADD CONSTRAINT module_aud_pkey PRIMARY KEY (id, rev);


--
-- Name: module_feature_list_aud module_feature_list_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.module_feature_list_aud
    ADD CONSTRAINT module_feature_list_aud_pkey PRIMARY KEY (rev, module_id, feature_list_id);


--
-- Name: module module_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.module
    ADD CONSTRAINT module_pkey PRIMARY KEY (id);


--
-- Name: permission_aud permission_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission_aud
    ADD CONSTRAINT permission_aud_pkey PRIMARY KEY (id, rev);


--
-- Name: permission_group_aud permission_group_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission_group_aud
    ADD CONSTRAINT permission_group_aud_pkey PRIMARY KEY (id, rev);


--
-- Name: permission_group_permission_list_aud permission_group_permission_list_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission_group_permission_list_aud
    ADD CONSTRAINT permission_group_permission_list_aud_pkey PRIMARY KEY (rev, permission_group_id, permission_list_id);


--
-- Name: permission_group permission_group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission_group
    ADD CONSTRAINT permission_group_pkey PRIMARY KEY (id);


--
-- Name: permission_permission_group_list_aud permission_permission_group_list_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission_permission_group_list_aud
    ADD CONSTRAINT permission_permission_group_list_aud_pkey PRIMARY KEY (rev, permission_id, permission_group_list_id);


--
-- Name: permission permission_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY (id);


--
-- Name: profile_aud profile_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile_aud
    ADD CONSTRAINT profile_aud_pkey PRIMARY KEY (id, rev);


--
-- Name: profile profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (id);


--
-- Name: revinfo revinfo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.revinfo
    ADD CONSTRAINT revinfo_pkey PRIMARY KEY (rev);


--
-- Name: app_module_list uk_9rrd6ye52qo336ciei6r85dx0; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_module_list
    ADD CONSTRAINT uk_9rrd6ye52qo336ciei6r85dx0 UNIQUE (module_list_id);


--
-- Name: module_feature_list uk_rsprjf0kuowp2w0xn0yp80c7b; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.module_feature_list
    ADD CONSTRAINT uk_rsprjf0kuowp2w0xn0yp80c7b UNIQUE (feature_list_id);


--
-- Name: user_aud user_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_aud
    ADD CONSTRAINT user_aud_pkey PRIMARY KEY (id, rev);


--
-- Name: user_group_aud user_group_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group_aud
    ADD CONSTRAINT user_group_aud_pkey PRIMARY KEY (id, rev);


--
-- Name: user_group user_group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_group_pkey PRIMARY KEY (id);


--
-- Name: user_group_user_aud user_group_user_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group_user_aud
    ADD CONSTRAINT user_group_user_aud_pkey PRIMARY KEY (rev, user_id, user_group_id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: user_profile_aud user_profile_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_profile_aud
    ADD CONSTRAINT user_profile_aud_pkey PRIMARY KEY (profile_id, rev);


--
-- Name: user_profile user_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_profile
    ADD CONSTRAINT user_profile_pkey PRIMARY KEY (profile_id);


--
-- Name: user_group_user fk1e3rs5p2jdxf0p13mc7u341kd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group_user
    ADD CONSTRAINT fk1e3rs5p2jdxf0p13mc7u341kd FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: permission_group_permission_list_aud fk2ieob2lxnvnndcedeapb1d1u4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission_group_permission_list_aud
    ADD CONSTRAINT fk2ieob2lxnvnndcedeapb1d1u4 FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: user_group_user fk35c0kokvxaajs05y2dkeoj9x1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group_user
    ADD CONSTRAINT fk35c0kokvxaajs05y2dkeoj9x1 FOREIGN KEY (user_group_id) REFERENCES public.user_group(id);


--
-- Name: user_group_aud fk36b89wp7jt7tsk54x807gi18n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group_aud
    ADD CONSTRAINT fk36b89wp7jt7tsk54x807gi18n FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: app_contract_list fk3cr11nhovf19kihhxrhyswkte; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_contract_list
    ADD CONSTRAINT fk3cr11nhovf19kihhxrhyswkte FOREIGN KEY (contract_list_id) REFERENCES public.contract(id);


--
-- Name: company_aud fk4bojjw2sh9ku0m2giux40mu3h; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_aud
    ADD CONSTRAINT fk4bojjw2sh9ku0m2giux40mu3h FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: company_user_list_aud fk64g2stnscvjfkxuygb3rscpla; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_user_list_aud
    ADD CONSTRAINT fk64g2stnscvjfkxuygb3rscpla FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: user_profile_aud fk7pchode8vyqoyrphbbrsq5f0j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_profile_aud
    ADD CONSTRAINT fk7pchode8vyqoyrphbbrsq5f0j FOREIGN KEY (profile_id, rev) REFERENCES public.profile_aud(id, rev);


--
-- Name: user_aud fk89ntto9kobwahrwxbne2nqcnr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_aud
    ADD CONSTRAINT fk89ntto9kobwahrwxbne2nqcnr FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: permission_aud fk8p00qhf8aau42hacp13k6x5hh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission_aud
    ADD CONSTRAINT fk8p00qhf8aau42hacp13k6x5hh FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: app_contract_list fk8swqcdvg2mo76t5e5jy3gail7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_contract_list
    ADD CONSTRAINT fk8swqcdvg2mo76t5e5jy3gail7 FOREIGN KEY (app_id) REFERENCES public.app(id);


--
-- Name: module fkavsq5a1xkt0giamdu17hvjrxe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.module
    ADD CONSTRAINT fkavsq5a1xkt0giamdu17hvjrxe FOREIGN KEY (app_id) REFERENCES public.app(id);


--
-- Name: company_user_list fkbfoxkmj2uuahou4p35o5x6sj2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_user_list
    ADD CONSTRAINT fkbfoxkmj2uuahou4p35o5x6sj2 FOREIGN KEY (user_list_id) REFERENCES public."user"(id);


--
-- Name: feature_aud fkc9p40hnprs9t0qww7jhaxcdwa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feature_aud
    ADD CONSTRAINT fkc9p40hnprs9t0qww7jhaxcdwa FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: feature fkcw91wtdoys0dww14vq67m13jc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feature
    ADD CONSTRAINT fkcw91wtdoys0dww14vq67m13jc FOREIGN KEY (module_id) REFERENCES public.module(id);


--
-- Name: app_contract_list_aud fkd1ltvm46o2yxs5qqu1by8dd8x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_contract_list_aud
    ADD CONSTRAINT fkd1ltvm46o2yxs5qqu1by8dd8x FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: app_aud fkdhpq9stw5lxkw4r3qaj80302b; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_aud
    ADD CONSTRAINT fkdhpq9stw5lxkw4r3qaj80302b FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: contract_aud fkdwmknd8t7wjko72bg4ka0gtnb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contract_aud
    ADD CONSTRAINT fkdwmknd8t7wjko72bg4ka0gtnb FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: module_feature_list fketjo35sxivqacbiq51n4snyx0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.module_feature_list
    ADD CONSTRAINT fketjo35sxivqacbiq51n4snyx0 FOREIGN KEY (module_id) REFERENCES public.module(id);


--
-- Name: profile_aud fkfhfnj4lvjfvq7e83v34jwwouy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profile_aud
    ADD CONSTRAINT fkfhfnj4lvjfvq7e83v34jwwouy FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: feature fkftmlkvv3pa59csfpd7u7r5wv2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feature
    ADD CONSTRAINT fkftmlkvv3pa59csfpd7u7r5wv2 FOREIGN KEY (permission_id) REFERENCES public.permission(id);


--
-- Name: contract fkgbuv1w6x2we2iyl7ihl3vgedy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contract
    ADD CONSTRAINT fkgbuv1w6x2we2iyl7ihl3vgedy FOREIGN KEY (app_id) REFERENCES public.app(id);


--
-- Name: module_feature_list fkggg7b380b312ben52594o49p6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.module_feature_list
    ADD CONSTRAINT fkggg7b380b312ben52594o49p6 FOREIGN KEY (feature_list_id) REFERENCES public.feature(id);


--
-- Name: permission_group_aud fkjdqkntyl0r2gmsp9b4w7k5xci; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission_group_aud
    ADD CONSTRAINT fkjdqkntyl0r2gmsp9b4w7k5xci FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: permission fkjgkobdkd3ujnxlvrtpf19x68j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission
    ADD CONSTRAINT fkjgkobdkd3ujnxlvrtpf19x68j FOREIGN KEY (feature_id) REFERENCES public.feature(id);


--
-- Name: app_module_list_aud fkjw0u73n0jiwcqdm83qd5l87k; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_module_list_aud
    ADD CONSTRAINT fkjw0u73n0jiwcqdm83qd5l87k FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: permission_permission_group_list_aud fkljxtvs0t4gclcs7y1mcpiwh9y; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission_permission_group_list_aud
    ADD CONSTRAINT fkljxtvs0t4gclcs7y1mcpiwh9y FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: user_group_user_aud fklphv892k4y851m1rwd06m8s10; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group_user_aud
    ADD CONSTRAINT fklphv892k4y851m1rwd06m8s10 FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: permission_group_permission_list fklt1gtskce6bo6svtqj15x4wwx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission_group_permission_list
    ADD CONSTRAINT fklt1gtskce6bo6svtqj15x4wwx FOREIGN KEY (permission_list_id) REFERENCES public.permission(id);


--
-- Name: contract fkm8jvj0jm2ihmy0fvrupie0ndk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contract
    ADD CONSTRAINT fkm8jvj0jm2ihmy0fvrupie0ndk FOREIGN KEY (company_id) REFERENCES public.company(id);


--
-- Name: module_feature_list_aud fkmgkn28e1xjnlgtedk94btfwoq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.module_feature_list_aud
    ADD CONSTRAINT fkmgkn28e1xjnlgtedk94btfwoq FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: app_module_list fkmt1ffrxouy0mbdcyrp1d8k4dl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_module_list
    ADD CONSTRAINT fkmt1ffrxouy0mbdcyrp1d8k4dl FOREIGN KEY (app_id) REFERENCES public.app(id);


--
-- Name: permission_permission_group_list fkmy33aiw6rh9f7fh1q9v5ja3ry; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission_permission_group_list
    ADD CONSTRAINT fkmy33aiw6rh9f7fh1q9v5ja3ry FOREIGN KEY (permission_group_list_id) REFERENCES public.permission_group(id);


--
-- Name: company_user_list fknw6b880ff00pyaqxks3txxm6q; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_user_list
    ADD CONSTRAINT fknw6b880ff00pyaqxks3txxm6q FOREIGN KEY (company_id) REFERENCES public.company(id);


--
-- Name: permission_permission_group_list fkoebp1b488ue8jf213b2tiqhux; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission_permission_group_list
    ADD CONSTRAINT fkoebp1b488ue8jf213b2tiqhux FOREIGN KEY (permission_id) REFERENCES public.permission(id);


--
-- Name: app_module_list fkq4yrg3wwxgo8y0lf92r2p7daq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_module_list
    ADD CONSTRAINT fkq4yrg3wwxgo8y0lf92r2p7daq FOREIGN KEY (module_list_id) REFERENCES public.module(id);


--
-- Name: permission_group_permission_list fkqc8jr4v75b8n9vn34124jg8f9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permission_group_permission_list
    ADD CONSTRAINT fkqc8jr4v75b8n9vn34124jg8f9 FOREIGN KEY (permission_group_id) REFERENCES public.permission_group(id);


--
-- Name: user_profile fkqcd5nmg7d7ement27tt9sf3bi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_profile
    ADD CONSTRAINT fkqcd5nmg7d7ement27tt9sf3bi FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: user_profile fkqfbftbxicceqbmvj87g9be2qn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_profile
    ADD CONSTRAINT fkqfbftbxicceqbmvj87g9be2qn FOREIGN KEY (profile_id) REFERENCES public.profile(id);


--
-- Name: module_aud fkte1s1x3olmh0pxds6uyvvb84y; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.module_aud
    ADD CONSTRAINT fkte1s1x3olmh0pxds6uyvvb84y FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2 (Debian 12.2-2.pgdg100+1)
-- Dumped by pg_dump version 12.2 (Debian 12.2-2.pgdg100+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;


--
-- PostgreSQL database cluster dump complete
--

