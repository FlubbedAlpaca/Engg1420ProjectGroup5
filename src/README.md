# Campus Event Booking System

### ENGG*1420: Object-Oriented Programming - Winter 2025

## Overview
***
This project is a Campus Event Booking System in Java designed to manage event bookings for a university campus. The system supports administrative workflows, such as waitlists, booking managers, maintaining user accounts, creating and updating event system records, cancelling bookings, and automatic promotion when space becomes available.

The project demonstrates the use of object-oriented programming principles, including concepts such as encapsulation, inheritance, polymorphism, clear class hierarchies, and design patterns. The system is designed to be modular, maintainable, and extensible, allowing for future enhancements and scalability.

## Table of Contents 
1. [System Architecture](system-architecture)
2. [Core Features](core-features)
3. [Class Designs](class-designs)
4. [Setup and Execution](setup-and-execution)


## System Architecture
The system architecture is split into two primary categories:
1. **Objects**: This category includes the core entities of the system, such as `User`, `Event` and`Booking`.
2. **Managers**: This category includes classes responsible for managing the core entities, such as `EventManagement`, `BookingManager` and 'WaitlistManager'.


## Core Features
- **Event Management**: The system allows for the creation and management of events, including event details, scheduling, and capacity management. The system also supports searching for events by title and specifying event types.
- **Role Based Access**: The system supports different user roles, such as `Staff`, `Student`, and `Guest`, each with specific permissions and access levels.
- **Intelligent Booking Management**: Users can book events, and the system manages bookings, including waitlists and automatic promotion when space becomes available. Users can also cancel their bookings, and the system will update the waitlist accordingly.

## Class Designs
The system utilizes OOP principles to design classes that represent the core entities and their interactions.
-**Inheritance**: Used to avoid code duplication and promote code reuse.
- User is extended by `Staff`, `Student`, and `Guest` classes, which inherit common attributes and behaviors while adding specific functionalities relevant to their roles.
- Event is extended by 'Concert', 'Seminar', and 'Workshop' classes, which inherit common attributes and behaviors while adding specific functionalities relevant to their event types.
- **Encapsulation**: Each class encapsulates its data and provides public methods to access and modify that data, ensuring that the internal state of the objects is protected from unauthorized access and modification.
- **Polymorphism**: The system uses polymorphism to allow for flexible and dynamic behavior. For example, the `BookingManager` can handle bookings for different types of events without needing to know the specific details of each event type.

## Setup and Execution
To set up and run the Campus Event Booking System, follow these steps:
1. Clone the repository to your local machine.
2. Ensure you have Java Development Kit (JDK) installed on your machine.
3. Compile the Java files using the command line or an IDE such as Intellij IDEA.
4. Run the main class to start the application and interact with the system through the graphical user interface.

