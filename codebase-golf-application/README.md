# Project Name
Golf Application

## Overview

The Golf Application is a versatile management system designed to facilitate the efficient administration of golf clubs and their activities. Key functionalities include:

- **Golf Club Management**: Manage club operations, including player profiles, and general club activities.
- **Competition Management**: Organize and oversee golf tournaments. This includes scheduling and participant tracking.
- **Player Management**: Maintain detailed records of golf club players, including personal profiles, competition history, and performance statistics.
- **Handicap Management**: Efficiently calculate and manage player handicaps.

Ideal for golf clubs looking to enhance their operational efficiency, this application ensures a professional and organized management of golf-related activities.

## Installation Instructions

To run this project locally, follow these steps:

1. Clone the repository to your local machine: `https://github.com/PolinaTolkachova/golf-application`
2. Install and start the MySQL server
3. Navigate to the project directory:
   ```bash
   cd codebase-golf-application
   ```
4. Change the application configuration to the local configuration in the `src/main/resources/application.properties` file
5. Install the project:
   ```bash
   mvn clean install
   ```
6. Run the project by executing the `src/main/java/com/golf/app/GolfWebApplication.java` class
7. Open your browser and visit `http://localhost:8082` to view the application. See existing passwords/logins in the `src/main/java/com/golf/app/security/AppSecurityConfig.java` class

## License

This project is licensed under the [BSD 3-Clause](LICENSE).