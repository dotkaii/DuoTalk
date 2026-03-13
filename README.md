# DuoTalk

DuoTalk is a Java client-server chat application built with sockets, Swing, and SQLite. It supports multiple users, private messages, and persisted chat history.

## Features

- Multi-user chat over TCP sockets
- Private messaging between connected users
- Swing login and chat windows
- Online user list
- SQLite-backed message history
- Automatic timestamp storage

## Project Structure

```text
DuoTalk
|-- client/     Client startup and socket communication
|-- common/     Shared message model
|-- database/   SQLite access and schema setup
|-- gui/        Swing login and chat windows
|-- server/     Server entry point and client handlers
|-- lib/        Third-party jars
|-- out/        Compiled classes
```

## Requirements

- Java JDK 17 or newer
- A terminal with `javac` and `java` available
- The SQLite JDBC driver jar in `lib/`

This repository already includes the JDBC driver at `lib/sqlite-jdbc-3.51.2.0.jar`.

## Setup

1. Clone or open the repository.
2. Make sure Java is installed:

```powershell
javac -version
java -version
```

3. Confirm the JDBC jar exists:

```powershell
Get-ChildItem lib
```

## Build

From the project root, compile all source files into `out/`:

```powershell
javac -cp "lib/*" -d out common\*.java database\*.java gui\*.java client\*.java server\*.java
```

## Run The Project

DuoTalk uses `localhost` and port `5000` by default.

### 1. Start the server

Open a terminal in the project root and run:

```powershell
java -cp "out;lib/*" server.ServerMain
```

Expected output:

```text
Server started on port 5000
```

When the server starts, it creates the `messages` table automatically if it does not already exist.

### 2. Start one or more clients

Open another terminal for each client and run:

```powershell
java -cp "out;lib/*" client.ClientMain
```

Then:

1. Enter a username in the login window.
2. Click `Connect`.
3. Type a message and press `Send` or `Enter`.
4. To send a private message, select a user from the list on the right before sending.

## Database Notes

- The app stores chat history in `duotalk.db`.
- The database file is created in the project root.
- Existing chat history is sent to newly connected clients when they join.

## Clean Rebuild

If you want to rebuild from scratch, delete compiled classes in `out/` and run the build command again.

## Troubleshooting

### `SQLite JDBC driver not found on the classpath`

Make sure you are running both `javac` and `java` with the library classpath:

```powershell
-cp "lib/*"
```

or, when running compiled classes:

```powershell
-cp "out;lib/*"
```

### `Connection refused`

The client is trying to connect to `localhost:5000`. Start the server first.

### `Address already in use`

Port `5000` is already occupied by another process or an earlier server instance. Stop that process, then restart DuoTalk.

### Messages are still showing from an older run

Delete `duotalk.db` if you want to reset chat history completely, then start the server again.

## Technologies Used

- Java
- Java Swing
- Java Sockets
- JDBC
- SQLite
