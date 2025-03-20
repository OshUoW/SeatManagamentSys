# Seat Management System in a Plane ✈️

## Overview
The **Seat Management System in a Plane** is a Java-based console application that allows users to manage seat reservations on a plane. It provides functionality for buying and canceling seats, viewing seat availability, printing ticket information, and more. This system could be a basic foundation for an airline's ticket reservation system.

---

## Features
✅ **Buy a Seat**  
✅ **Cancel a Seat**  
✅ **Find First Available Seat**  
✅ **Show Seating Plan**  
✅ **Print Tickets Information and Total Sales**  
✅ **Search Ticket by Seat**  
✅ **Quit the Application**

---

## Project Structure
The system is implemented in **Java** and consists of three main classes:

| Class             | Description                                                                                      |
|-------------------|--------------------------------------------------------------------------------------------------|
| `Person.java`     | Represents a person with `name`, `surname`, and `email` attributes.                              |
| `Ticket.java`     | Represents a ticket. Contains seat and person information, and includes methods to save tickets. |
| `PlaneManagement.java` | The main application class with the menu-driven interface. Manages seat availability and ticket sales. |

---

## How It Works
- The plane has **4 rows (A-D)** and **14 seats per row**, but **rows B and C have restricted seat availability (1-12 only)**.
- Seats are marked as **`O`** (available) and **`X`** (sold).
- Seat prices vary based on seat numbers:
  - Seats 1-5: **$200**
  - Seats 6-9: **$150**
  - Seats 10-14: **$180**
- Ticket purchases are saved as text files in the format `RowSeat.txt` (e.g., `A1.txt`).

---

## Installation & Running
### Prerequisites
- Java JDK (version 8 or higher)
- A terminal or command prompt
