--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: artifact; Type: TABLE; Schema: public; Owner: adminvq5ckvd; Tablespace: 
--

CREATE TABLE artifact (
    id integer NOT NULL,
    filename character varying(100),
    build_result_id integer,
    checksum character varying(50)
);


ALTER TABLE public.artifact OWNER TO adminvq5ckvd;

--
-- Name: build_configuration; Type: TABLE; Schema: public; Owner: adminvq5ckvd; Tablespace: 
--

CREATE TABLE build_configuration (
    id integer NOT NULL,
    project_id integer,
    source_url character varying(200),
    build_script text
);


ALTER TABLE public.build_configuration OWNER TO adminvq5ckvd;

--
-- Name: COLUMN build_configuration.source_url; Type: COMMENT; Schema: public; Owner: adminvq5ckvd
--

COMMENT ON COLUMN build_configuration.source_url IS 'URL to sources';


--
-- Name: COLUMN build_configuration.build_script; Type: COMMENT; Schema: public; Owner: adminvq5ckvd
--

COMMENT ON COLUMN build_configuration.build_script IS 'Command line instructions to run the build';


--
-- Name: build_result; Type: TABLE; Schema: public; Owner: adminvq5ckvd; Tablespace: 
--

CREATE TABLE build_result (
    id integer NOT NULL,
    start_time timestamp without time zone,
    end_time timestamp without time zone,
    source_url character varying(100),
    build_environment_description text,
    username character varying(20),
    build_log text,
    build_script text,
    project_id integer,
    project_name character varying(40)
);


ALTER TABLE public.build_result OWNER TO adminvq5ckvd;

--
-- Name: build_trigger; Type: TABLE; Schema: public; Owner: adminvq5ckvd; Tablespace: 
--

CREATE TABLE build_trigger (
    id integer NOT NULL,
    build_configuration_id integer,
    triggered_build_configuration_id integer
);


ALTER TABLE public.build_trigger OWNER TO adminvq5ckvd;

--
-- Name: license; Type: TABLE; Schema: public; Owner: adminvq5ckvd; Tablespace: 
--

CREATE TABLE license (
    id integer NOT NULL,
    short_name character varying(20),
    full_name character varying(100),
    full_text text,
    ref_url character varying(100)
);


ALTER TABLE public.license OWNER TO adminvq5ckvd;

--
-- Name: TABLE license; Type: COMMENT; Schema: public; Owner: adminvq5ckvd
--

COMMENT ON TABLE license IS 'Software Licenses';


--
-- Name: project; Type: TABLE; Schema: public; Owner: adminvq5ckvd; Tablespace: 
--

CREATE TABLE project (
    id integer NOT NULL,
    current_license_id integer,
    description text,
    name character varying(20)
);


ALTER TABLE public.project OWNER TO adminvq5ckvd;

--
-- Name: system_image; Type: TABLE; Schema: public; Owner: adminvq5ckvd; Tablespace: 
--

CREATE TABLE system_image (
    id integer NOT NULL,
    description text,
    image_blob bytea,
    image_url character varying(100),
    name character varying(20)
);


ALTER TABLE public.system_image OWNER TO adminvq5ckvd;

--
-- Name: COLUMN system_image.image_blob; Type: COMMENT; Schema: public; Owner: adminvq5ckvd
--

COMMENT ON COLUMN system_image.image_blob IS 'Build system image file, this could be something like a VM or Docker image.';


--
-- Name: COLUMN system_image.image_url; Type: COMMENT; Schema: public; Owner: adminvq5ckvd
--

COMMENT ON COLUMN system_image.image_url IS 'URL location of the system image to use for the build';


--
-- Name: COLUMN system_image.name; Type: COMMENT; Schema: public; Owner: adminvq5ckvd
--

COMMENT ON COLUMN system_image.name IS 'Short name of the image for easy reference';


--
-- Name: pk_artifact_id; Type: CONSTRAINT; Schema: public; Owner: adminvq5ckvd; Tablespace: 
--

ALTER TABLE ONLY artifact
    ADD CONSTRAINT pk_artifact_id PRIMARY KEY (id);


--
-- Name: pk_build_configuration_id; Type: CONSTRAINT; Schema: public; Owner: adminvq5ckvd; Tablespace: 
--

ALTER TABLE ONLY build_configuration
    ADD CONSTRAINT pk_build_configuration_id PRIMARY KEY (id);


--
-- Name: pk_build_result; Type: CONSTRAINT; Schema: public; Owner: adminvq5ckvd; Tablespace: 
--

ALTER TABLE ONLY build_result
    ADD CONSTRAINT pk_build_result PRIMARY KEY (id);


--
-- Name: pk_build_trigger_id; Type: CONSTRAINT; Schema: public; Owner: adminvq5ckvd; Tablespace: 
--

ALTER TABLE ONLY build_trigger
    ADD CONSTRAINT pk_build_trigger_id PRIMARY KEY (id);


--
-- Name: pk_license_id; Type: CONSTRAINT; Schema: public; Owner: adminvq5ckvd; Tablespace: 
--

ALTER TABLE ONLY license
    ADD CONSTRAINT pk_license_id PRIMARY KEY (id);


--
-- Name: pk_project_id; Type: CONSTRAINT; Schema: public; Owner: adminvq5ckvd; Tablespace: 
--

ALTER TABLE ONLY project
    ADD CONSTRAINT pk_project_id PRIMARY KEY (id);


--
-- Name: pk_system_image_id; Type: CONSTRAINT; Schema: public; Owner: adminvq5ckvd; Tablespace: 
--

ALTER TABLE ONLY system_image
    ADD CONSTRAINT pk_system_image_id PRIMARY KEY (id);


--
-- Name: fk_artifact_build_result_id; Type: FK CONSTRAINT; Schema: public; Owner: adminvq5ckvd
--

ALTER TABLE ONLY artifact
    ADD CONSTRAINT fk_artifact_build_result_id FOREIGN KEY (build_result_id) REFERENCES build_result(id);


--
-- Name: fk_build_result_project_id; Type: FK CONSTRAINT; Schema: public; Owner: adminvq5ckvd
--

ALTER TABLE ONLY build_result
    ADD CONSTRAINT fk_build_result_project_id FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: fk_build_trigger_build_configuration_id; Type: FK CONSTRAINT; Schema: public; Owner: adminvq5ckvd
--

ALTER TABLE ONLY build_trigger
    ADD CONSTRAINT fk_build_trigger_build_configuration_id FOREIGN KEY (build_configuration_id) REFERENCES build_configuration(id);


--
-- Name: fk_build_trigger_triggered_build_configuration_id; Type: FK CONSTRAINT; Schema: public; Owner: adminvq5ckvd
--

ALTER TABLE ONLY build_trigger
    ADD CONSTRAINT fk_build_trigger_triggered_build_configuration_id FOREIGN KEY (triggered_build_configuration_id) REFERENCES build_configuration(id);


--
-- Name: fk_project_license_id; Type: FK CONSTRAINT; Schema: public; Owner: adminvq5ckvd
--

ALTER TABLE ONLY project
    ADD CONSTRAINT fk_project_license_id FOREIGN KEY (current_license_id) REFERENCES license(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

