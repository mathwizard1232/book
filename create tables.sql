create table authors
(
	author_id INT auto_increment primary key,
    search_name VARCHAR(50) NOT NULL UNIQUE,
    display_name VARCHAR(200)
);

create index asearchname on authors (search_name);

create table books
(
	book_id INT auto_increment primary key,
    author INT NOT NULL,
    search_title VARCHAR(50) NOT NULL,
    full_title VARCHAR(200)
);

ALTER TABLE books
ADD CONSTRAINT Ubook UNIQUE (author,search_title);

create index bsearchname on books (author, search_title);

create table boxes
(
	box_id INT auto_increment primary key,
    box_title VARCHAR(50),
    box_label VARCHAR(200)
);

create table box_contents
(
   box_contents_id INT auto_increment primary key,
   box INT,
   book INT
);

ALTER TABLE box_contents
ADD FOREIGN KEY (box)
REFERENCES boxes(box_id);

ALTER TABLE box_contents
ADD FOREIGN KEY (book)
REFERENCES books(book_id);