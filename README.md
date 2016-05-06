#_README for Hair Salon_#
###Created by Noah Kittleson###

##**Description**##
This is a webpage that allows the user to enter stylists, and then clients for each stylists.  All stylists are listed on main page and each provides a link to see all their clients.

##**Setup**##
Clone the repository or otherwise download the source files to your computer.  Install Postgres and start it from the command line.  In PSQL, input the following commands:

* CREATE DATABASE hair_salon;
* CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, specialty varchar);
* CREATE TABLE clients (id serial PRIMARY KEY, name varchar, appointment date, stylist_id int);
* CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;

Then, within the command line navigate to the Dictionary file and run gradle.  This should set up a local server so that you can view the webpage on any modern browser at localhost:4567.

##**Technologies Used**
* Java
* Spark
* Velocity
* FluentLenium
* SQL
* CSS
* BootStrap
* HTML

##**Licensing**##
This is protected under the [MIT](https://en.wikipedia.org/wiki/MIT_License) license.
