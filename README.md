# HibTest

Short demo project showing how Hibernate works in a simple Java application.

**Project**
- **Description:** A minimal Maven-based Java project that demonstrates configuring Hibernate, mapping simple entities (`Car`, `CarOwner`), and performing basic DAO operations via `CarDAO`/`CarDAOImpl`.

**Prerequisites**
- **Java:** Java 8 or later installed.
- **Build tool:** Maven installed and available on `PATH`.

**Build & Run**
- Build the project:

```bash
mvn clean package
```

- Run from your IDE by executing the `org.example.Main` class, or run with the Maven Exec plugin (if configured):

```bash
mvn compile exec:java -Dexec.mainClass="org.example.Main"
```

**What this demonstrates**
- How to configure Hibernate using `hibernate.cfg.xml` / `hibernate.properties`.
- How to map Java classes to database tables (simple entities under `src/main/java/org/example/entities`).
- How to use a DAO pattern (`CarDAO`, `CarDAOImpl`) to perform CRUD operations with Hibernate sessions.

**Key files**
- `src/main/java/org/example/Main.java` – application entry point and example usage.
- `src/main/java/org/example/entities/Car.java` and `CarOwner.java` – entity classes.
- `src/main/java/org/example/dao/CarDAO.java` and `CarDAOImpl.java` – DAO interface and implementation.
- `src/main/resources/hibernate.cfg.xml` – core Hibernate configuration.
- `src/main/resources/hibernate.properties` – additional Hibernate properties.

**Project structure**
- Standard Maven layout: `src/main/java`, `src/main/resources`.

**Notes & next steps**
- The project uses whatever JDBC/DB is configured in `hibernate.cfg.xml`. For in-memory testing, consider using H2 and updating `hibernate.cfg.xml`.
- To make running easier, you can add the Maven Exec plugin to `pom.xml` or provide a small wrapper script.
- Add unit/integration tests to verify DAO behavior and Hibernate mappings.

If you want, I can also add a short run script or example data import for a specific database (H2, PostgreSQL, etc.).
