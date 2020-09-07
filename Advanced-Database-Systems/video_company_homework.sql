/*Create database*/
CREATE DATABASE video_company;
USE video_company;
CREATE TABLE Client (
  client_id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(50) NOT NULL,
  telephone VARCHAR(10) NOT NULL,
  company_name VARCHAR(30) NOT NULL
);

/*Create tables*/

CREATE TABLE preloaded_scene (
  preloaded_scene_id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name VARCHAR(30) NOT NULL,
  description VARCHAR(200) NOT NULL,
  max_words SMALLINT NOT NULL
);

CREATE TABLE base_plot (
  base_plot_id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  title VARCHAR(50) NOT NULL,
  video_type VARCHAR(15) NOT NULL,
  technique VARCHAR(50) NOT NULL
);

CREATE TABLE detailed_scene (
  detailed_scene_id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  texts VARCHAR(100) NOT NULL,
  images VARCHAR(100) NOT NULL,
  off_text VARCHAR(100) NOT NULL,
  preloaded_scene_id INTEGER
);

CREATE TABLE video_plot (
    video_plot_id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    base_plot_id INTEGER NOT NULL,
    client_id INTEGER NOT NULL
);

CREATE TABLE client_video_plot (
  client_id INTEGER NOT NULL,
  plot_id INTEGER NOT NULL
);

CREATE TABLE detailed_scene_video_plot (
  detailed_scene_id INTEGER NOT NULL,
  video_plot_id INTEGER NOT NULL
);

CREATE TABLE base_plot_preloaded_scene (
  base_plot_id INTEGER NOT NULL,
  preloaded_scene_id INTEGER NOT NULL
);

/*Insert sample information*/
INSERT INTO preloaded_scene (name, description, max_words) VALUES ('Introduction', 'Brief introduction', 100);
INSERT INTO preloaded_scene (name, description, max_words) VALUES ('Problem statement', 'Narrative turn', 200);
INSERT INTO preloaded_scene (name, description, max_words) VALUES ('Solution', 'Persuasive idea', 100);
INSERT INTO preloaded_scene (name, description, max_words) VALUES ('Call to action', 'What to do next', 50);
INSERT INTO preloaded_scene (name, description, max_words) VALUES ('Introduction', 'Brief introduction to the documentary', 150);
INSERT INTO preloaded_scene (name, description, max_words) VALUES ('Climax', 'Problem description', 1000);
INSERT INTO base_plot (title, video_type, technique) VALUES ('Documentary', 'Eyecatcher', 'Production and Live action');
INSERT INTO base_plot (title, video_type, technique) VALUES ('Infography', 'Promotional', 'Animation');
INSERT INTO base_plot_preloaded_scene (base_plot_id, preloaded_scene_id) VALUES (1, 5);
INSERT INTO base_plot_preloaded_scene (base_plot_id, preloaded_scene_id) VALUES (1, 6);
INSERT INTO base_plot_preloaded_scene (base_plot_id, preloaded_scene_id) VALUES (2, 1);
INSERT INTO base_plot_preloaded_scene (base_plot_id, preloaded_scene_id) VALUES (2, 2);
INSERT INTO base_plot_preloaded_scene (base_plot_id, preloaded_scene_id) VALUES (2, 3);
INSERT INTO base_plot_preloaded_scene (base_plot_id, preloaded_scene_id) VALUES (2, 4);
INSERT INTO detailed_scene (preloaded_scene_id, off_text, images, texts) VALUES (5, 'Welcome to our documentary on words!', 'Larousse logo', 'Welcome!');
INSERT INTO detailed_scene (preloaded_scene_id, off_text, images, texts) VALUES (6, 'We use to have a hard time with conjugations', 'Busy person', 'Conjugations');
INSERT INTO detailed_scene (preloaded_scene_id, off_text, images, texts) VALUES (1, 'Pending', 'Pending', 'Pending');
INSERT INTO detailed_scene (preloaded_scene_id, off_text, images, texts) VALUES (2, 'Pending', 'Pending', 'Pending');
INSERT INTO detailed_scene (preloaded_scene_id, off_text, images, texts) VALUES (4, 'Pending', 'Pending', 'Pending');
INSERT INTO client (name, email, telephone, company_name) VALUES ('Pending', 'Pending', 'Pending', 'Larousse');
INSERT INTO client (name, email, telephone, company_name) VALUES ('Pending', 'Pending', 'Pending', 'ITESM');
INSERT INTO video_plot (client_id, base_plot_id) VALUES (1, 1);
UPDATE base_plot SET title = 'Documentary on words' WHERE base_plot_id = 1;
INSERT INTO video_plot (client_id, base_plot_id) VALUES (2, 2);
INSERT INTO detailed_scene_video_plot (detailed_scene_id, video_plot_id) VALUES (2, 4);
INSERT INTO detailed_scene_video_plot (detailed_scene_id, video_plot_id) VALUES (2, 5);
INSERT INTO detailed_scene_video_plot (detailed_scene_id, video_plot_id) VALUES (2, 6);

/*Select information*/
SELECT base_plot.base_plot_id AS 'id', title, video_type, technique, preloaded_scene_id  FROM base_plot JOIN base_plot_preloaded_scene ON base_plot.base_plot_id = base_plot_preloaded_scene.base_plot_id;
SELECT * FROM preloaded_scene;
SELECT client.company_name, base_plot.* FROM client JOIN video_plot ON client.client_id = video_plot.client_id JOIN base_plot ON video_plot.base_plot_id = base_plot.base_plot_id;
SELECT * FROM detailed_scene;