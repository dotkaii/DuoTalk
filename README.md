# DuoTalk

DuoTalk is a Java-based client-server chat application built using **Socket Programming, Swing GUI, and SQLite database**.

It supports **multiple users, private messaging, and chat history storage**.

---

## Features

- Server-client architecture
- Multi-user chat
- Private messaging
- Swing GUI interface
- Login screen
- Online user list
- Chat history storage
- SQLite database integration
- Message timestamps

---

## Architecture
      +----------------+
      |     Server     |
      |----------------|
      | ClientHandler  |
      | Message Router |
      | Database Layer |
      +--------+-------+
               |
    --------------------------
    |           |            |
 Client 1    Client 2    Client 3

 
---

## Project Structure
DuoTalk
├── client
├── server
├── gui
├── database
├── common


---

## Technologies Used

- Java
- Java Swing
- Java Socket Programming
- JDBC
- SQLite

---

## How To Run

### 1 Start Server

Run:
ServerMain.java

---

### 2 Start Client

Run:
ClientMain.java

Enter a username and start chatting.

---

## Screenshots

### Login Screen

![Login](screenshots/login.png)

### Chat Window

![Chat](screenshots/chat.png)

---

## Future Improvements

- Group chat
- File sharing
- Emoji support
- Message notifications
- Dark mode UI

---

## Author

Kai