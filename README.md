# EPD Data Service

This is a **Spring Boot application** that provides APIs to manage products, including categories and tags, stored in **MongoDB**.

## 🚀 Features
- Store and retrieve product details from MongoDB.
- Fetch products based on categories and tags.
- Load default product data from a JSON file.
- Uses **Spring Boot**, **Spring Data MongoDB**, and **Lombok** for clean and efficient code.

## 🛠️ Tech Stack
- **Java 17** (Spring Boot)
- **MongoDB** (Spring Data MongoDB)
- **Lombok** (for reducing boilerplate code)
- **Jackson** (for JSON serialization/deserialization)


POST Method -- http://localhost:8080/init -- For Creation of default data

GET Method -- http://localhost:8080/show_all -- show all documents with-in the collection epd_data

GET Method -- http://localhost:8080/categories/Insulation -- to search for documents with cat_name matching categories.name in collection

GET Method -- http://localhost:8080/tags/bio-based -- to search for docuemnts with tag name

