#use testdb;
INSERT INTO roles(id, name) VALUES(1, 'ROLE_USER') on duplicate key update name='ROLE_USER';
INSERT INTO roles(id, name) VALUES(2, 'ROLE_MODERATOR') on duplicate key update name='ROLE_MODERATOR';
INSERT INTO roles(id, name) VALUES(3, 'ROLE_ADMIN') on duplicate key update name='ROLE_ADMIN';

INSERT INTO users (id, email, password, username) VALUES (1, 'rasraziel@gmail.com', '$2a$10$HYndxB2PaalAxJmMnIlrvO1XiwHXssgT4CuMwtXoUXD72ll9OcGw6', 'user') on duplicate key update id=1;
INSERT INTO users (id, email, password, username) VALUES (2, 'eliasmartidis@gmail.com', '$2a$10$HYndxB2PaalAxJmMnIlrvO1XiwHXssgT4CuMwtXoUXD72ll9OcGw6', 'admin') on duplicate key update id=2;
INSERT INTO users (id, email, password, username) VALUES (3, 'a@mail.kk', '$2a$10$HYndxB2PaalAxJmMnIlrvO1XiwHXssgT4CuMwtXoUXD72ll9OcGw6', 'mod') on duplicate key update id=3;
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1) on duplicate key update user_id=1;
INSERT INTO user_roles (user_id, role_id) VALUES (2, 3) on duplicate key update user_id=2;
INSERT INTO user_roles (user_id, role_id) VALUES (3, 2) on duplicate key update user_id=3;



INSERT INTO course(id, duration, description, price, teacher, title, image) VALUES(1, '03:30:00', 'AngularJS is a JavaScript-based open-source front-end web framework mainly maintained by Google and by a community of individuals and corporations to address many of the challenges encountered in developing single-page applications. It aims to simplify both the development and the testing of such applications by providing a framework for client-side model–view–controller (MVC) and model–view–viewmodel (MVVM) architectures, along with components commonly used in rich Internet applications. AngularJS is used as the frontend of the MEAN stack, consisting of MongoDB database, Express.js web application server framework, Angular.js itself, and Node.js server runtime environment.',
150, 'Mosh Hamedani', 'Angular', './course-images/Angular.jpg') on duplicate key update id=1;

INSERT INTO course(id, duration, description, price, teacher, title, image) VALUES(2, '06:16:00', 'Java is a class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. It is a general-purpose programming language intended to let application developers write once, run anywhere (WORA), meaning that compiled Java code can run on all platforms that support Java without the need for recompilation. Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of the underlying computer architecture. The syntax of Java is similar to C and C++, but has fewer low-level facilities than either of them. The Java runtime provides dynamic capabilities (such as reflection and runtime code modification) that are typically not available in traditional compiled languages. As of 2019, Java was one of the most popular programming languages in use according to GitHub, particularly for client-server web applications, with a reported 9 million developers.',
250, 'Nelson Djalo', 'Java', './course-images/Java.jpg') on duplicate key update id=2;

INSERT INTO course(id, duration, description, price, teacher, title, image) VALUES(3, '02:01:00', 'Git is a distributed version-control system for tracking changes in source code during software development. It is designed for coordinating work among programmers, but it can be used to track changes in any set of files. Its goals include speed, data integrity, and support for distributed, non-linear workflows.',
200, 'Zach Gollwitzer', 'Git', './course-images/git.jpg') on duplicate key update id=3;

INSERT INTO course(id, duration, description, price, teacher, title, image) VALUES(4, '02:40:00', 'JavaScript, often abbreviated as JS, is a programming language that conforms to the ECMAScript specification. JavaScript is high-level, often just-in-time compiled, and multi-paradigm. It has curly-bracket syntax, dynamic typing, prototype-based object-orientation, and first-class functions. Alongside HTML and CSS, JavaScript is one of the core technologies of the World Wide Web. JavaScript enables interactive web pages and is an essential part of web applications. The vast majority of websites use it for client-side page behavior, and all major web browsers have a dedicated JavaScript engine to execute it.',
350, 'Mosh Hamedani', 'JavaScript', './course-images/js.png') on duplicate key update id=4;

INSERT INTO course(id, duration, description, price, teacher, title, image) VALUES(5, '00:45:00', 'C# (pronounced see sharp, like the musical note C♯, but written with the number sign) is a general-purpose, multi-paradigm programming language encompassing static typing, strong typing, lexically scoped, imperative, declarative, functional, generic, object-oriented (class-based), and component-oriented programming disciplines.',
500, 'Avetis Ghukasyan', 'C#', './course-images/c-sharp.jpg') on duplicate key update id=5;

INSERT INTO course(id, duration, description, price, teacher, title, image) VALUES(6, '03:14:00', 'Node.js is an open-source, cross-platform, back-end, JavaScript runtime environment that executes JavaScript code outside a web browser. Node.js lets developers use JavaScript to write command line tools and for server-side scripting—running scripts server-side to produce dynamic web page content before the page is sent to the users web browser. Consequently, Node.js represents a JavaScript everywhere paradigm, unifying web-application development around a single programming language, rather than different languages for server- and client-side scripts.',
 600, 'Mosh Hamedani', 'Node JS', './course-images/node.png') on duplicate key update id=6;

INSERT INTO course(id, duration, description, price, teacher, title, image) VALUES(7, '06:37:00', 'React (also known as React.js or ReactJS) is an open-source, front end, JavaScript library for building user interfaces or UI components. It is maintained by Facebook and a community of individual developers and companies. React can be used as a base in the development of single-page or mobile applications. However, React is only concerned with rendering data to the DOM, and so creating React applications usually requires the use of additional libraries for state management and routing.',
180, 'Mosh Hamedani', 'React', './course-images/react.jpg') on duplicate key update id=7;

INSERT INTO course(id, duration, description, price, teacher, title, image) VALUES(8, '02:33:00', 'Redux is an open-source JavaScript library for managing application state. It is most commonly used with libraries such as React or Angular for building user interfaces.',
375, 'Mosh Hamedani', 'Redux', './course-images/Redux.jpg') on duplicate key update id=8;

INSERT INTO course(id, duration, description, price, teacher, title, image) VALUES(9, '01:08:00', 'Virtual reality (VR) is a simulated experience that can be similar to or completely different from the real world. Applications of virtual reality can include entertainment (i.e. video games) and educational purposes (i.e. medical or military training).',
450, 'Quentin Valembois', 'Intro to VR', './course-images/vr.jpg') on duplicate key update id=9;

INSERT INTO course(id, duration, description, price, teacher, title, image) VALUES(10, '00:27:00', 'Augmented reality (AR) is an interactive experience of a real-world environment where the objects that reside in the real world are enhanced by computer-generated perceptual information, sometimes across multiple sensory modalities, including visual, auditory, haptic, somatosensory and olfactory. AR can be defined as a system that fulfills three basic features: a combination of real and virtual worlds, real-time interaction, and accurate 3D registration of virtual and real objects.',
400, 'John Smith', 'Intro to AR', './course-images/ar.jpg') on duplicate key update id=10;

INSERT INTO category(id, name) VALUES (1, 'AR') on duplicate key update id=1;
INSERT INTO category(id, name) VALUES (2, 'VR') on duplicate key update id=2;
INSERT INTO category(id, name) VALUES (3, 'IoT') on duplicate key update id=3;
INSERT INTO category(id, name) VALUES (4, 'AI') on duplicate key update id=4;
INSERT INTO category(id, name) VALUES (5, 'Data Science') on duplicate key update id=5;
INSERT INTO category(id, name) VALUES (6, 'Programming') on duplicate key update id=6;
INSERT INTO category(id, name) VALUES (7, 'Data Visualization') on duplicate key update id=7;
INSERT INTO category(id, name) VALUES (8, 'Front End') on duplicate key update id=8;
INSERT INTO category(id, name) VALUES (9, 'Back End') on duplicate key update id=9;
INSERT INTO category(id, name) VALUES (10, 'Version Control') on duplicate key update id=10;

INSERT INTO category_course(category_id, course_id) VALUES (6,1) on duplicate key update category_id=6, course_id=1;
INSERT INTO category_course(category_id, course_id) VALUES (8,1) on duplicate key update category_id=8, course_id=1;
INSERT INTO category_course(category_id, course_id) VALUES (6,2) on duplicate key update category_id=6, course_id=2;
INSERT INTO category_course(category_id, course_id) VALUES (9,2) on duplicate key update category_id=9, course_id=2;
INSERT INTO category_course(category_id, course_id) VALUES (10,3) on duplicate key update category_id=10, course_id=3;
INSERT INTO category_course(category_id, course_id) VALUES (6,4) on duplicate key update category_id=6, course_id=4;
INSERT INTO category_course(category_id, course_id) VALUES (8,4) on duplicate key update category_id=8, course_id=4;
INSERT INTO category_course(category_id, course_id) VALUES (9,4) on duplicate key update category_id=9, course_id=4;
INSERT INTO category_course(category_id, course_id) VALUES (6,5) on duplicate key update category_id=6, course_id=5;
INSERT INTO category_course(category_id, course_id) VALUES (9,5) on duplicate key update category_id=9, course_id=5;
INSERT INTO category_course(category_id, course_id) VALUES (6,6) on duplicate key update category_id=6, course_id=6;
INSERT INTO category_course(category_id, course_id) VALUES (9,6) on duplicate key update category_id=9, course_id=6;
INSERT INTO category_course(category_id, course_id) VALUES (6,7) on duplicate key update category_id=6, course_id=7;
INSERT INTO category_course(category_id, course_id) VALUES (8,7) on duplicate key update category_id=8, course_id=7;
INSERT INTO category_course(category_id, course_id) VALUES (6,8) on duplicate key update category_id=6, course_id=8;
INSERT INTO category_course(category_id, course_id) VALUES (8,8) on duplicate key update category_id=8, course_id=8;
INSERT INTO category_course(category_id, course_id) VALUES (2,9) on duplicate key update category_id=2, course_id=9;
INSERT INTO category_course(category_id, course_id) VALUES (6,9) on duplicate key update category_id=6, course_id=9;
INSERT INTO category_course(category_id, course_id) VALUES (1,10) on duplicate key update category_id=1, course_id=10;
INSERT INTO category_course(category_id, course_id) VALUES (6,10) on duplicate key update category_id=6, course_id=10;

INSERT INTO video(id, description, title, video_url, course_id) VALUES(1, 'Angular - part 1', 'Angular - part 1', '_-CD_5YhJTA', 1) on duplicate key update id=1, course_id=1;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(2, 'Angular - part 2', 'Angular - part 2', 'k5E2AVpwsko', 1) on duplicate key update id=2, course_id=1;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(3, 'Angular - part 3', 'Angular - part 3', 'ra5qNKNc95U', 1) on duplicate key update id=3, course_id=1;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(4, 'Java - part 1', 'Java - part 1', 'Qgl81fPcLc8', 2) on duplicate key update id=4, course_id=2;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(5, 'Java - part 2', 'Java - part 2', 'VRpHdSFWGPs', 2) on duplicate key update id=5, course_id=2;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(6, 'Java - part 3', 'Java - part 3', 'Q93JsQ8vcwY', 2) on duplicate key update id=6, course_id=2;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(7, 'Git - part 1', 'Git - part 1', 'kmGsHjQ2wsY', 3) on duplicate key update id=7, course_id=3;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(8, 'Git - part 2', 'Git - part 2', 'BF2OHMM86Ik', 3) on duplicate key update id=8, course_id=3;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(9, 'Git - part 3', 'Git - part 3', 'NXaEImbo-n8', 3) on duplicate key update id=9, course_id=3;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(10, 'Javascript - part 1', 'Javascript - part 1', 'W6NZfCO5SIk', 4) on duplicate key update id=10, course_id=4;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(11, 'Javascript - part 2', 'Javascript - part 2', 'PFmuCDHHpwk', 4) on duplicate key update id=11, course_id=4;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(12, 'Javascript - part 3', 'Javascript - part 3', 'NCwa_xi0Uuc', 4) on duplicate key update id=12, course_id=4;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(13, 'C# - part 1', 'C# - part 1', 'cL0OftSZ7V8', 5) on duplicate key update id=13, course_id=5;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(14, 'C# - part 2', 'C# - part 2', 'kkDm5wBYVp0', 5) on duplicate key update id=14, course_id=5;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(15, 'C# - part 3', 'C# - part 3', '0xBRJfl95MU', 5) on duplicate key update id=15, course_id=5;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(16, 'NodeJs - part 1', 'NodeJs - part 1', 'TlB_eWDSMt4', 6) on duplicate key update id=16, course_id=6;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(17, 'NodeJs - part 2', 'NodeJs - part 2', 'pKd0Rpw7O48', 6) on duplicate key update id=17, course_id=6;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(18, 'NodeJs - part 3', 'NodeJs - part 3', 'pKd0Rpw7O48', 6) on duplicate key update id=18, course_id=6;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(19, 'React - part 1', 'React - part 1', 'Ke90Tje7VS0', 7) on duplicate key update id=19, course_id=7;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(20, 'React - part 2', 'React - part 2', '0-S5a0eXPoc', 7) on duplicate key update id=20, course_id=7;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(21, 'React - part 3', 'React - part 3', '0-S5a0eXPoc', 7) on duplicate key update id=21, course_id=7;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(22, 'Redux - part 1', 'Redux - part 1', 'poQXNp9ItL4', 8) on duplicate key update id=22, course_id=8;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(23, 'Redux - part 2', 'Redux - part 2', 'UEcdQR-NoNA', 8) on duplicate key update id=23, course_id=8;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(24, 'Redux - part 3', 'Redux - part 3', 'UEcdQR-NoNA', 8) on duplicate key update id=24, course_id=8;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(25, 'VR - part 1', 'VR - part 1', 'gGYtahQjmWQ', 9) on duplicate key update id=25, course_id=9;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(26, 'VR - part 2', 'VR - part 2', 'VdT0zMcggTQ', 9) on duplicate key update id=26, course_id=9;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(27, 'VR - part 3', 'VR - part 3', 'fZXKGJYri1Y', 9) on duplicate key update id=27, course_id=9;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(28, 'AR - part 1', 'AR - part 1', 'cEX62jVUox0', 10) on duplicate key update id=28, course_id=10;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(29, 'AR - part 2', 'AR - part 2', 'jv6pesoHYys', 10) on duplicate key update id=29, course_id=10;
INSERT INTO video(id, description, title, video_url, course_id) VALUES(30, 'AR - part 3', 'AR - part 3', 'dwWY475gSHA', 10) on duplicate key update id=30, course_id=10;
