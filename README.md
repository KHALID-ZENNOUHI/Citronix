# Citronix Farm Management System

## Overview

Citronix is a **farm management system** designed for efficient tracking of lemon farms. The application helps farmers manage production, harvests, and sales with tools for monitoring tree productivity, seasonal harvests, and farm activities.

This system includes features for managing:
- Farms
- Fields
- Trees
- Harvests
- Sales

---

## Features

### 1. **Farm Management**
- Create, update, delete, and view farms.
- Multi-criteria search functionality using `CriteriaBuilder`.
- Each farm includes attributes:
  - Name
  - Location
  - Total area (hectares)
  - Creation date

### 2. **Field Management**
- Assign fields to a farm with defined areas.
- Enforce constraints:
  - Total field area < Farm area.
  - Minimum field area: 0.1 hectare (1,000 m²).
  - Maximum field area: 50% of the farm's total area.
  - Maximum of 10 fields per farm.

### 3. **Tree Management**
- Track trees with attributes like:
  - Plantation date
  - Age
  - Belonging field
- Calculate tree age.
- Determine annual productivity:
  - **Young tree (< 3 years)**: 2.5 kg per season.
  - **Mature tree (3–10 years)**: 12 kg per season.
  - **Old tree (> 10 years)**: 20 kg per season.
- Ensure tree density ≤ 100 trees/hectare (10 trees/1,000 m²).
- Enforce planting only between March and May.

### 4. **Harvest Management**
- Track harvests per season:
  - **Winter, Spring, Summer, Autumn**.
  - One harvest per field per season.
- Attributes:
  - Harvest date
  - Total harvested quantity
- Restriction:
  - Each tree can only be harvested once per season.

### 5. **Harvest Details**
- Track quantities harvested per tree for a specific harvest.
- Associate each detail with a specific tree.

### 6. **Sales Management**
- Record sales with:
  - Sale date
  - Unit price
  - Customer information
  - Associated harvest
- Calculate revenue:
  - `Revenue = Quantity × Unit Price`.

---

## Technical Constraints
- A field's area cannot exceed 50% of its farm's area.
- Trees older than 20 years are non-productive.
- Each field can only have one harvest per season.

---

## Technologies Used

### Backend:
- **Java**: Primary programming language.
- **Spring Boot**: Framework for creating REST APIs.
- **Lombok**: Simplify entity management using the Builder pattern.
- **MapStruct**: Convert entities, DTOs, and View Models.

### Database:
- **PostgreSQL**: Relational database management system.
- **JPA/Hibernate**: Data persistence.

### Testing:
- **JUnit**: Unit testing.
- **Mockito**: Mocking dependencies in tests.

### API Documentation:
- **Swagger**: Generate interactive API documentation.
- **Postman**: Collection for testing API endpoints.

---

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/KHALID-ZENNOUHI/Citronix
   cd citronix
