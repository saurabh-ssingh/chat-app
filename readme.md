

#  High-Level Design – Chat Application

## **1️⃣ Overview**

The Chat Application is a **real-time messaging system** built on **Spring Boot**, using **WebSockets (STOMP)** for instant delivery and **JPA + Database** for message persistence and history.

Users can:

* Register/login (via Cognito or API)
* Send **1:1** or **1\:N** messages
* Retrieve past conversations from DB

---

## **2️⃣ Architecture Diagram**

```
+-------------+         WebSocket/STOMP        +-----------------+
|  Frontend   | <----------------------------> |   WebSocket     |
|  (HTML/JS)  |                                |  Controller     |
+-------------+                                +-----------------+
         ^                                               |
         | REST API (history, user mgmt)                 v
         |                                      +-----------------+
         +------------------------------------> |   Chat Service  |
                                                +-----------------+
                                                          |
                                                          v
                                                +-----------------+
                                                |  JPA Repository |
                                                +-----------------+
                                                          |
                                                          v
                                                +-----------------+
                                                |     Database    |
                                                +-----------------+
```

---

## **3️⃣ Key Components**

### **Frontend (UI)**

* Static `index.html` (or SPA)
* Uses:

    * **SockJS + STOMP.js** for WebSocket connection
    * **Fetch API (REST)** for loading chat history
* Displays **real-time messages** & stored conversation history

---

### **Backend – Spring Boot**

#### **a) WebSocket Layer**

* **STOMP endpoint:** `/ws`
* Users connect and subscribe to:

    * `/user/{username}/queue/messages` → private message delivery
* Messages are sent by clients to:

    * `/app/chat` → processed by server

#### **b) Chat Service**

* Receives chat messages, sets the sender, and:

    1. Persists the message(s) in **DB** (one row per receiver)
    2. Pushes the message(s) to receiver(s) via **SimpMessagingTemplate**

#### **c) REST API**

* Provides conversation history & inbox:

    * `GET /api/messages/conversation/{u1}/{u2}`
    * `GET /api/messages/inbox/{username}`
* Returns **paginated message history**

---

### **Database**

#### **Tables**

1. **`chat_users`**

    * Stores user info (username, email, profile, status)
    * `cognito_user_name` is the unique identifier

2. **`chat_messages`**

    * Stores each message with:

        * sender
        * receiver
        * content
        * timestamps
        * read/unread status
    * Indexed for fast lookups by `receiver` & `sender/receiver`

---

## **4️⃣ Data Flow**

### **a) Send Message (WebSocket)**

1. User sends message via WebSocket to `/app/chat`
2. `ChatController` → `ChatService`
3. Service:

    * Saves message in DB
    * Sends message to each receiver's private WebSocket queue (`/user/{receiver}/queue/messages`)

### **b) Load Chat History (REST)**

1. Frontend calls REST API:

   ```
   GET /api/messages/conversation/alice/bob
   ```
2. `ChatHistoryController` fetches messages from DB via repository
3. Returns paginated JSON, UI displays it

---

## **5️⃣ Technology Stack**

* **Backend:** Spring Boot 3.3.x, WebSocket (STOMP), JPA (Hibernate)
* **Database:** PostgreSQL / MySQL / H2 (for dev)
* **Frontend:** Static HTML/JS (SockJS, STOMP.js)
* **Auth (optional):** AWS Cognito JWT

---

## **6️⃣ Future Enhancements**

* Add **group chat (rooms)** using `/topic/{roomId}`
* Add **attachments** (S3 storage)
* Add **message delivery/read receipts**
* Improve UI with **React/Angular**
* Add **Cognito authentication** for WebSocket connections

---
