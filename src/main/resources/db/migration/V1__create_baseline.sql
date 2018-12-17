--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: test; Type: SCHEMA; Schema: -; Owner: -
--

SET search_path = test;
SET default_tablespace = '';
SET default_with_oids = false;

--
-- Name: course; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE course (
    cid serial PRIMARY KEY,
    name character varying(20)
);


--
-- Name: student; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE student (
    sid serial PRIMARY KEY,
    name character varying(20)
);


--
-- Name: registration; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE registration (
    rid serial PRIMARY KEY,
    sid integer REFERENCES student(sid),
    cid integer REFERENCES course(cid),
    grade integer
);


--
-- PostgreSQL database dump complete
--

