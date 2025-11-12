ecobazaar-backend
-----------------
This is a simple Spring Boot scaffold configured to run with an in-memory H2 database
and some sample products loaded at startup.

How to run:
1) Ensure Java 17+ and Maven are installed.
2) From this folder run: mvn spring-boot:run
3) API endpoints:
   - GET  /api/products
   - POST /api/products

H2 Console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:ecobazaardb)
