# Warehouses_Management
## Introduction
This project is our final practice for the subject "Programación Distribuida". This application is intended to ease the management
of the company's warehouses. It has been developed using Spring Boot, RabbitMQ and MongoDB.
## How to run
1. Installing Docker Engine
   - **Windows** ➝ https://docs.docker.com/desktop/setup/install/windows-install/
   - **Linux** ➝ https://docs.docker.com/engine/install/ubuntu/
   - **macOS** ➝ https://docs.docker.com/desktop/setup/install/mac-install/
2. Run LaunchApp
   
   Inside the Docker directory you only need give the script the execution permission
   - For Linux
   
   ```bash
   cd Docker && chmod +x LaunchApp.sh && ./LaunchApp.sh
   ```
   or (without getting into)
   
   ```bash
   chmod +x Docker/LaunchApp.sh && ./Docker/LaunchApp.sh
   ```

   - For Windows
   
   Once you execute it, the backend is already running
