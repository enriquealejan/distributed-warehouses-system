# Warehouses_Management
## Introduction
This project is our final practice for the subject "Programación Distribuida". This application is intended to ease the management
of the company's warehouses. It has been developed using Spring Boot, RabbitMQ and MongoDB.
## How to run
1. Installing Docker Engine
   - **Windows** ➝ https://docs.docker.com/desktop/setup/install/windows-install/
   - **Linux** ➝ https://docs.docker.com/engine/install/ubuntu/
   - **macOS** ➝ https://docs.docker.com/desktop/setup/install/mac-install/
2. Run Backend
   
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
     
     First, you need to check if the execution policy (PowerShell) is "Unrestricted".
     ```powershell
     Get-ExecutionPolicy
     ```
     If the output is "Unrestricted", you won't need to do anything. But if not, you will need to change it.
     ```powershell
     Set-ExecutionPolicy Unrestricted
     ```
     Once you do it, get into Docker directory and execute the script
     ```powershell
     ./LaunchApp.ps1
     ```
   
   Once you execute it, the backend is already running

3. Run GUI
   
   For running the GUI you need to install locally Maven. First you have to download Maven and then include it on environment variables.