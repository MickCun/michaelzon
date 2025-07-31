# 🏎️ MichaelZon – Full-Stack Retail Platform

MichaelZon is a full-stack retail eCommerce platform built using **Spring Boot**, **Thymeleaf**, and **PostgreSQL**. Designed as a university project, it offers a fully functional shopping experience with customer accounts, a cart system, product catalog, and a secure admin dashboard — all wrapped in a Bootstrap-powered UI.

## 📸 Demo

▶️ [Video File is available in the Commit's]

---

## ✨ Features

- 🛍️ Product Catalog with Visibility Controls
- 🧾 Add to Cart, Adjust Quantity, and Checkout
- 🔐 Customer Registration & Login
- 👨‍💼 Admin Role Support (Product + Order Management)
- 📊 Order Status Management
- 🐘 PostgreSQL Integration
- 🐳 Docker Support (`dockerfile` included)
- 📄 Project walkthrough included in `solution.html`

---

## 🧠 Technologies Used

- Java 21 (Temurin)
- Spring Boot
- Spring MVC
- Thymeleaf
- PostgreSQL
- Bootstrap 5
- HTML/CSS
- Docker

---

## 🚀 Getting Started

1. **Clone the repository**

```bash
git clone https://github.com/MickCun/michaelzon.git
cd michaelzon
```

2. **Configure Database**

Update your PostgreSQL credentials in `src/main/resources/application.properties`.

3. **Run the App**

```bash
./mvnw spring-boot:run
```

Or build the JAR:

```bash
./mvnw clean package
java -jar target/*.jar
```

4. **Access Locally**

Visit `http://localhost:8080`

---

## 🧪 Sample Admin Login

> To access admin dashboard:
> 
> - **Username:** `admin`
> - **Password:** `admin123`
> 
> _(You can change this in the seeded DB or register manually and flip the `admin` boolean.)_

---

## 📄 Project Report

A full breakdown of the development process and design decisions is included in `solution.html`. This includes screenshots, code walkthroughs, and task explanations.

---

## 📦 Folder Structure

```
├── src
│   └── main
│       ├── java
│       │   └── com.example.retail
│       │       ├── Customers/
│       │       ├── Orders/
│       │       ├── Products/
│       ├── resources
│       │   ├── templates/
│       │   └── static/
├── dockerfile
├── pom.xml
├── HELP.md
├── solution.html
```

---

## 🧼 .gitignore Recommendation

```gitignore
target/
.idea/
.vscode/
*.iml
application.properties
*.log
```

---

## 📬 Contact

Built with ❤️ by [Michael E. Cunningham](https://mickcun.github.io)  
Want to collaborate or have questions? Reach out via [LinkedIn (https://www.linkedin.com/in/michael-e-c-ba5584167/)]. 
