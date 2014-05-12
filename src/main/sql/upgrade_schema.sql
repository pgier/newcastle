
ALTER TABLE build_configuration
	DROP COLUMN source_url,
	ADD COLUMN source_id character varying(30),
	ADD COLUMN system_image_id integer;

COMMENT ON COLUMN build_configuration.source_id IS 'Unique ID of the source to build';

COMMENT ON COLUMN build_configuration.system_image_id IS 'ID of the system image to use to execute the build';

ALTER TABLE project
	ADD COLUMN scm_url character varying(50),
	ADD COLUMN issue_tracker_url character varying(50),
	ADD COLUMN project_url character varying(50);

COMMENT ON COLUMN project.scm_url IS 'URL of the SCM repository';

COMMENT ON COLUMN project.issue_tracker_url IS 'URL to the project issue tracking system';

COMMENT ON COLUMN project.project_url IS 'URL to homepage of the project';

ALTER TABLE build_configuration
	ADD CONSTRAINT fk_build_configuration_system_image_id FOREIGN KEY (system_image_id) REFERENCES system_image(id);

ALTER TABLE project
	ADD CONSTRAINT unique_project_name UNIQUE (name);
