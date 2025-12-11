Collaborative Study Platform
Semestrálny projekt – predmet TSIKT (2024/2025)

Tento projekt je dvojmodulová aplikácia určená na podporu tímového štúdia, správu študijných skupín, úloh a študijných materiálov s možnosťou real-time notifikácií.
Projekt pozostáva z:

✔ Backend – Spring Boot
✔ Desktopový klient – JavaFX
✔ SQLite databáza
Architektúra projektu
/semestralna        – Spring Boot backend
/client             – JavaFX desktop aplikácia
Technológie
Backend
Java 17

Spring Boot 3

Spring Web, Spring Data JPA

BCrypt hashing

WebSocket (STOMP)

SQLite (JDBC driver)

Maven

Client
JavaFX 17

REST API komunikácia (RestTemplate)

FXML + MVC architektúra

Maven + javafx-maven-plugin

🗄️ Funkčné moduly
1️⃣ Autentifikácia a používatelia
Backend:

Registrácia používateľa (POST /auth/register)

Prihlásenie (POST /auth/login)

Získanie profilu (GET /users/{id})

Úprava profilu (PUT /users/{id})

Hashovanie hesla pomocou BCrypt

JavaFX klient:

Login okno

Register okno

Profil používateľa (zmena mena, emailu, hesla)

2️⃣ Skupiny a členovia
Backend umožňuje:

vytvárať skupiny

upravovať skupiny

pridávať členov do skupiny

získavať zoznam skupín pre daného používateľa

JavaFX klient:

zobrazenie skupín

vytvorenie skupiny

pridanie používateľa do skupiny

3️⃣ Úlohy v skupine
Backend:

create/update task

change status (OPEN/DONE)

list tasks in group

logovanie aktivít

JavaFX klient:

zobrazenie úloh

vytvorenie úlohy

úprava úlohy

zmena statusu

real-time obnovovanie (bez WS klienta)

4️⃣ Aktivita používateľa (Activity Log)
Backend:

logovanie udalostí TASK_CREATED / TASK_UPDATED / STATUS_CHANGED

endpoint: /activity/user/{id}

JavaFX klient:

tabuľkový prehľad aktivít v samostatnom okne

5️⃣ WebSocket (len backendová implementácia)
Backend obsahuje:

konfiguráciu WebSocket

STOMP broker na /topic/group.{groupId}

broadcast udalostí do skupinyechnológie
Backend

Java 17

Spring Boot 3

Spring Web, Spring Data JPA

BCrypt hashing

WebSocket (STOMP)

SQLite (JDBC driver)

Maven

Client

JavaFX 17

REST API komunikácia (RestTemplate)

FXML + MVC architektúra

Maven + javafx-maven-plugin

🗄️ Funkčné moduly
1️⃣ Autentifikácia a používatelia

Backend:

Registrácia používateľa (POST /auth/register)

Prihlásenie (POST /auth/login)

Získanie profilu (GET /users/{id})

Úprava profilu (PUT /users/{id})

Hashovanie hesla pomocou BCrypt

JavaFX klient:

Login okno

Register okno

Profil používateľa (zmena mena, emailu, hesla)

2️⃣ Skupiny a členovia

Backend umožňuje:

vytvárať skupiny

upravovať skupiny

pridávať členov do skupiny

získavať zoznam skupín pre daného používateľa

JavaFX klient:

zobrazenie skupín

vytvorenie skupiny

pridanie používateľa do skupiny

3️⃣ Úlohy v skupine

Backend:

create/update task

change status (OPEN/DONE)

list tasks in group

logovanie aktivít

JavaFX klient:

zobrazenie úloh

vytvorenie úlohy

úprava úlohy

zmena statusu

real-time obnovovanie (bez WS klienta)

4️⃣ Aktivita používateľa (Activity Log)

Backend:

logovanie udalostí TASK_CREATED / TASK_UPDATED / STATUS_CHANGED

endpoint: /activity/user/{id}

JavaFX klient:

tabuľkový prehľad aktivít v samostatnom okne

5️⃣ WebSocket (len backendová implementácia)

Backend obsahuje:

konfiguráciu WebSocket

STOMP broker na /topic/group.{groupId}

broadcast udalostí do skupiny

Setup & Spustenie

cd semestralna
mvn clean install
mvn spring-boot:run

Client

cd client
mvn clean install
mvn javafx:run

API – Príklady

POST /auth/login
{
"email": "test@gmail.com",
"password": "1234"
}

Úprava profilu

PUT /users/5
{
"name": "New Name",
"email": "new@mail.com",
"password": "optional"
}

Získanie aktivít

GET /activity/user/5


Splnené požiadavky zadania
Funkcia	Stav
Registrácia a login	✔
Správa profilu	✔
Skupiny – create/edit/list	✔
Členovia skupiny	✔
Úlohy – create/edit/status	✔
Deadliny	✔ (uložené v DB)
Aktivita používateľa	✔
Zdieľanie materiálov	✔ (backend)
WebSocket notifikácie	✔ (len backend)
JavaFX UI klient	✔
Databáza SQLite	✔

REST API – tabuľková dokumentácia
AUTH /auth

| Metóda   | Endpoint         | Request body                                  | Response                           | Popis                                      |
| -------- | ---------------- | --------------------------------------------- | ---------------------------------- | ------------------------------------------ |
| **POST** | `/auth/register` | `{ "name": "", "email": "", "password": "" }` | `201 CREATED`                      | Registrácia nového používateľa.            |
| **POST** | `/auth/login`    | `{ "email": "", "password": "" }`             | `{ "userId": number, "name": "" }` | Prihlásenie používateľa, vracia ID a meno. |

USERS /users

| Metóda  | Endpoint      | Request body                                   | Response | Popis                                            |
| ------- | ------------- | ---------------------------------------------- | -------- | ------------------------------------------------ |
| **GET** | `/users/{id}` | –                                              | `{User}` | Získa údaje o používateľovi.                     |
| **PUT** | `/users/{id}` | `{ "name": "", "email": "", "password": ""? }` | `200 OK` | Úprava profilu používateľa (meno, email, heslo). |

GROUPS /groups

| Metóda   | Endpoint                             | Request body                        | Response  | Popis                                          |
| -------- | ------------------------------------ | ----------------------------------- | --------- | ---------------------------------------------- |
| **GET**  | `/groups/list?userId={id}`           | –                                   | `[Group]` | Zoznam skupín, v ktorých je používateľ členom. |
| **POST** | `/groups?userId={id}`                | `{ "name": "", "description": "" }` | `{Group}` | Vytvorenie novej skupiny.                      |
| **POST** | `/groups/{groupId}/join?userId={id}` | –                                   | `200 OK`  | Pridanie používateľa do skupiny.               |

TASKS /groups/{groupId}/tasks

Úlohy v skupine

| Metóda   | Endpoint                              | Request body                                         | Response | Popis                         |
| -------- | ------------------------------------- | ---------------------------------------------------- | -------- | ----------------------------- |
| **GET**  | `/groups/{groupId}/tasks`             | –                                                    | `[Task]` | Vráti všetky úlohy v skupine. |
| **POST** | `/groups/{groupId}/tasks?userId={id}` | `{ "title": "", "description": "", "deadline": "" }` | `{Task}` | Vytvorí novú úlohu.           |
| **PUT**  | `/groups/{groupId}/tasks/{taskId}`    | `{ "title": "", "description": "", "deadline": "" }` | `200 OK` | Úprava úlohy.                 |

Task status

| Metóda   | Endpoint                                  | Body               | Response | Popis                                          |
| -------- | ----------------------------------------- | ------------------ | -------- | ---------------------------------------------- |
| **POST** | `/groups/{groupId}/tasks/{taskId}/status` | `{ "status": "" }` | `200 OK` | Zmena stavu úlohy (TODO → IN_PROGRESS → DONE). |

Activity Log /logs

| Metóda  | Endpoint          | Request | Response        | Popis                                                       |
| ------- | ----------------- | ------- | --------------- | ----------------------------------------------------------- |
| **GET** | `/logs/user/{id}` | –       | `[ActivityLog]` | Získa aktivity používateľa (vytvorené/úpravené úlohy atď.). |






