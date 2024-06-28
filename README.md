# VollMed

###

VollMed is a RESTful API developed in Java using Spring Boot. The project aims to manage the registration of doctors, patients, and appointments, offering authentication methods to access the API. This personal project was developed to test my skills with Java and Spring Boot. 

###

## Technologies Used

- Java
- Spring Boot
- MySQL
- Flyway (ferramenta de migração de banco de dados)
- Bean Validation (para validações)
- Spring Security (para segurança)
- JSON Web Token (JWT) (para controle de acesso)
- Springdoc OpenAPI (para documentação das rotas)

###

## Features

- Doctor Registration: Complete management of the doctor's lifecycle.
- Patient Registration: Complete management of the patient's lifecycle.
- Appointment Registration: Complete management of the appointment's lifecycle.
- Authentication and Authorization: Use of JWT to control access to the API.
- Validations: Use of Bean Validation to ensure data integrity.
- Route Documentation: Use of Springdoc to generate the API route documentation.

###

## Database Migration

Flyway is used to manage database migrations. Ensure that the migrations are in the resources/db/migration folder. To apply the migrations, just start the application and Flyway will take care of the rest.
