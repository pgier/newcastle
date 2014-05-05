
CREATE TABLE build_collection (
	id integer NOT NULL,
	name character varying(20)
);

COMMENT ON TABLE build_collection IS 'Group of build results, such as all builds that are part of a single product release';

CREATE TABLE build_collection_build_result (
	build_collection_id integer NOT NULL,
	build_result_id integer NOT NULL
);

COMMENT ON TABLE build_collection_build_result IS 'Mapping between build results and build collections';

CREATE TABLE "user" (
	id integer NOT NULL,
	username character varying(20),
	first_name character varying(20),
	last_name character varying(20),
	email character varying(30)
);

COMMENT ON TABLE "user" IS 'System Users';

COMMENT ON TABLE artifact IS 'Build Artifacts';

COMMENT ON TABLE build_configuration IS 'Build environment configuration';

ALTER TABLE build_result
	ADD COLUMN user_id integer;

COMMENT ON TABLE build_result IS 'Result of a build such as the command used to execute the build, the log file, and whether the build was successful.';

COMMENT ON TABLE build_trigger IS 'Relationship to allow one build to automatically trigger execution of another build';

COMMENT ON TABLE project IS 'Project to be built';

COMMENT ON TABLE system_image IS 'Build system image';

ALTER TABLE build_collection
	ADD CONSTRAINT pk_build_collection_id PRIMARY KEY (id);

ALTER TABLE build_collection_build_result
	ADD CONSTRAINT pk_build_collection_build_result PRIMARY KEY (build_collection_id, build_result_id);

ALTER TABLE "user"
	ADD CONSTRAINT pk_user_id PRIMARY KEY (id);

ALTER TABLE build_collection_build_result
	ADD CONSTRAINT fk_build_collection_build_result_build_collection_id FOREIGN KEY (build_collection_id) REFERENCES build_collection(id);

ALTER TABLE build_collection_build_result
	ADD CONSTRAINT fk_build_collection_build_result_build_result_id FOREIGN KEY (build_result_id) REFERENCES build_result(id);

ALTER TABLE build_configuration
	ADD CONSTRAINT fk_build_configuration_project_id FOREIGN KEY (project_id) REFERENCES project(id);

ALTER TABLE build_result
	ADD CONSTRAINT fk_build_result_user_id FOREIGN KEY (user_id) REFERENCES "user"(id);
