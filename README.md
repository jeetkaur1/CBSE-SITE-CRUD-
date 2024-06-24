# CBSE-SITE-CRUD-OPERAtIONS

## Project Description

This web application allows users to:
- View CBSE results
- Add new results
- Update existing results
- Delete results

The backend is implemented using Java Servlets, and the frontend is developed using HTML and CSS.

## Features

- **Create**: Add new student results.
- **Read**: View existing student results.
- **Update**: Modify student results.
- **Delete**: Remove student results.

## Prerequisites

- JDK 8 or higher
- Apache Tomcat 9 or higher
- An IDE such as Eclipse or IntelliJ IDEA
- MySQL database (or any preferred database)

## Installation



1. **Set up the database**:
    - Create a new database (e.g., `cbse_db`).
    - Run the provided SQL script (`database.sql`) to create the required tables.

2. **Configure the project**:
    - Import the project into your IDE.
    - Configure the database connection in `src/com/cbse/util/DatabaseUtil.java`.

3. **Deploy to Tomcat**:
    - Right-click on the project and select `Run As > Run on Server`.
    - Choose Apache Tomcat and click `Finish`.

## Usage

- **Access the application**:
    Open a web browser and go to `http://localhost:8080/CBSEWebsite/`.

- **Perform CRUD operations**:
    - Use the navigation menu to access different features (Results, Add Result, Update Result, Delete Result).
