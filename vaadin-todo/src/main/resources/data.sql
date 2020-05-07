CREATE TABLE IF NOT EXISTS Todo(
  id IDENTITY PRIMARY KEY,
  done BOOLEAN,
  TEXT VARCHAR,
  due DATE,
  priority VARCHAR
);
DELETE FROM Todo;
INSERT INTO Todo(id, done, text, due, priority) VALUES(99991, TRUE, 'Do something', null, 'HIGH');
INSERT INTO Todo(id, done, text, due, priority) VALUES(99992, FALSE, 'Do something else', null, 'LOW');
INSERT INTO Todo(id, done, text, due, priority) VALUES(99993, TRUE, 'Test application', CURRENT_DATE, 'NORMAL');