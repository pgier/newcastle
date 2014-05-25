
CREATE SEQUENCE artifact_id_seq
	START WITH 1
	INCREMENT BY 1
	NO MAXVALUE
	NO MINVALUE
	CACHE 1;

CREATE SEQUENCE build_collection_id_seq
	START WITH 1
	INCREMENT BY 1
	NO MAXVALUE
	NO MINVALUE
	CACHE 1;

CREATE SEQUENCE build_configuration_id_seq
	START WITH 1
	INCREMENT BY 1
	NO MAXVALUE
	NO MINVALUE
	CACHE 1;

CREATE SEQUENCE build_result_id_seq
	START WITH 1
	INCREMENT BY 1
	NO MAXVALUE
	NO MINVALUE
	CACHE 1;

CREATE SEQUENCE build_trigger_id_seq
	START WITH 1
	INCREMENT BY 1
	NO MAXVALUE
	NO MINVALUE
	CACHE 1;

CREATE SEQUENCE license_id_seq
	START WITH 1
	INCREMENT BY 1
	NO MAXVALUE
	NO MINVALUE
	CACHE 1;

CREATE SEQUENCE project_id_seq
	START WITH 1
	INCREMENT BY 1
	NO MAXVALUE
	NO MINVALUE
	CACHE 1;

CREATE SEQUENCE system_image_id_seq
	START WITH 1
	INCREMENT BY 1
	NO MAXVALUE
	NO MINVALUE
	CACHE 1;

CREATE SEQUENCE user_id_seq
	START WITH 1
	INCREMENT BY 1
	NO MAXVALUE
	NO MINVALUE
	CACHE 1;

ALTER TABLE artifact
	ALTER COLUMN filename SET NOT NULL;

ALTER TABLE build_collection
	ADD COLUMN description text,
	ALTER COLUMN id SET DEFAULT nextval('build_collection_id_seq'::regclass),
	ALTER COLUMN name SET NOT NULL;

ALTER TABLE build_configuration
	ALTER COLUMN id SET DEFAULT nextval('build_configuration_id_seq'::regclass);

ALTER TABLE build_result
	ALTER COLUMN id SET DEFAULT nextval('build_result_id_seq'::regclass);

ALTER TABLE build_trigger
	ALTER COLUMN id SET DEFAULT nextval('build_trigger_id_seq'::regclass);

ALTER TABLE license
	ALTER COLUMN id SET DEFAULT nextval('license_id_seq'::regclass);

ALTER TABLE project
	ALTER COLUMN name SET NOT NULL,
	ALTER COLUMN id SET DEFAULT nextval('project_id_seq'::regclass);

ALTER TABLE system_image
	ALTER COLUMN id SET DEFAULT nextval('system_image_id_seq'::regclass);

ALTER TABLE "user"
	ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass),
	ALTER COLUMN username SET NOT NULL;
