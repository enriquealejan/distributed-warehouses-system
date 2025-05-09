


# 🏭 Warehouses Management System

📦 Final project for the subject **"Programación Distribuida"**  
🌐 Developed with **Spring Boot**, **RabbitMQ**, **MongoDB**, and a custom **Java GUI**.

---

## 📚 Introduction

This application is intended to ease the management of a company's warehouses. It simulates a distributed system where different services handle warehouse data, orders, and user interaction via message queues and persistent storage.

---

## ✨ Features

- Management of multiple warehouses (create, update, delete)
- Asynchronous order processing with RabbitMQ
- Java-based graphical user interface (GUI)
- Communication between services using message queues
- Data persistence with MongoDB
- Cross-platform launch scripts using Docker, Bash, and PowerShell

---

## 🛠️ Technologies Used

- Java
- Spring Boot
- RabbitMQ
- MongoDB
- Maven
- Docker & Docker Compose
- PowerShell / Bash scripting

## 🚀 How to Run

### 📦 Prerequisites

Install Docker Engine:

- [Windows Installation Guide](https://docs.docker.com/desktop/setup/install/windows-install/)
- [Linux Installation Guide](https://docs.docker.com/engine/install/ubuntu/)
- [macOS Installation Guide](https://docs.docker.com/desktop/setup/install/mac-install/)

---

### 🔧 Run Backend

#### 🐧 Linux

cd Docker && chmod +x LaunchApp.sh && ./LaunchApp.sh


Or (without changing directory):
chmod +x Docker/LaunchApp.sh && ./Docker/LaunchApp.sh


#### 🪟 Windows (PowerShell)

First, check if the execution policy allows running scripts:

powershell
Get-ExecutionPolicy


If the output is not `Unrestricted`, run:
powershell
Set-ExecutionPolicy Unrestricted

Then execute the script:
powershell
cd Docker
./LaunchApp.ps1

Once executed, the backend services will be running.

### 🖥️ Run GUI

You need Maven installed locally to compile and run the GUI.
Download it from: [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)

After adding it to your system's PATH:
cd GUI
mvn compile
mvn exec:java

## 🧪 Testing

Once both backend and GUI are running:

* Perform basic operations (e.g., create warehouse, place orders)
* Monitor messages in RabbitMQ (if the management console is exposed)
* Verify data persistence in MongoDB

---

## 👥 Contributors

This project was developed as a final assignment for the **"Programación Distribuida"** course.

### 👤 My Contribution

* Setup of Docker and multi-platform deployment scripts
* Backend logic development with Spring Boot
* Integration with MongoDB
* System-wide testing and debugging

---

## 📸 Screenshots (Optional)

<!-- Uncomment and replace with actual image if available -->

<!-- ![GUI demo](docs/gui.png) -->

## 📄 License

This project was created for educational purposes and is not distributed under a commercial license.




