# Spring Http Client Samples

[![Java CI with Maven](https://github.com/hendisantika/spring-http-client-samples/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-http-client-samples/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1-6DB33F?logo=springboot)

Side-by-side samples of the HTTP clients available in Spring — `RestClient`, `RestTemplate` and `WebClient` — each
used both directly and through a declarative [HTTP interface](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-http-interface)
(`@HttpExchange`), with OpenTelemetry tracing via Micrometer and [Digma](https://digma.ai/).

## Tech stack

| Component       | Version                                     |
|-----------------|---------------------------------------------|
| Java            | 25 (Temurin in CI)                          |
| Spring Boot     | 4.1.x (Spring Framework 7)                  |
| Build           | Maven 3.9+ (Maven Wrapper in each module)   |
| Observability   | Micrometer Tracing + OpenTelemetry (OTLP), Digma |

## Modules

| Module            | Port | Stack          | Description                                                                 |
|-------------------|------|----------------|-----------------------------------------------------------------------------|
| `echo-service`    | 8080 | Spring MVC     | Target service. Returns `{"message":"Echo:<message>"}`.                     |
| `blocking-caller` | 8081 | Spring MVC     | Calls `echo-service` with `RestClient`, `RestTemplate` and `WebClient`.    |
| `reactive-caller` | 8082 | Spring WebFlux | Calls `echo-service` with `WebClient`, returning `Mono` responses.         |

```
              ┌──────────────────────┐
  :8081  ───► │   blocking-caller    │ ──┐
              │ RestClient / RT / WC │   │      ┌──────────────┐
              └──────────────────────┘   ├────► │ echo-service │ :8080
              ┌──────────────────────┐   │      └──────────────┘
  :8082  ───► │   reactive-caller    │ ──┘
              │       WebClient      │
              └──────────────────────┘
```

## Prerequisites

- JDK 25
- Maven 3.9+ (or use the bundled `./mvnw` in each module)

## Build & test

Build and test every module from the repository root:

```bash
mvn verify
```

Or build a single module:

```bash
cd echo-service && ./mvnw verify
```

## Run

Start each application in its own terminal, `echo-service` first:

```bash
cd echo-service    && ./mvnw spring-boot:run   # http://localhost:8080
cd blocking-caller && ./mvnw spring-boot:run   # http://localhost:8081
cd reactive-caller && ./mvnw spring-boot:run   # http://localhost:8082
```

> Tracing is exported over OTLP. Without a collector (e.g. Digma or an OpenTelemetry Collector) running locally,
> you will see exporter connection warnings in the logs; the endpoints still work.

## Endpoints

### echo-service (`:8080`)

| Method | Path               | Body                  |
|--------|--------------------|-----------------------|
| GET    | `/echo/{message}`  | –                     |
| POST   | `/echo`            | `{"message":"hello"}` |

### blocking-caller (`:8081`)

| Client         | Direct call               | HTTP interface (`@HttpExchange`)    |
|----------------|---------------------------|-------------------------------------|
| `RestClient`   | `GET /rc/echo/{message}`  | `GET /rc/interface/echo/{message}`  |
| `RestTemplate` | `GET /rt/echo/{message}`  | `GET /rt/interface/echo/{message}`  |
| `WebClient`    | `GET /wc/echo/{message}`  | `GET /wc/interface/echo/{message}`  |

### reactive-caller (`:8082`)

| Client      | Direct call              | HTTP interface (`@HttpExchange`)   |
|-------------|--------------------------|------------------------------------|
| `WebClient` | `GET /wc/echo/{message}` | `GET /wc/interface/echo/{message}` |

Example:

```bash
curl http://localhost:8081/rc/echo/hello
# {"message":"Echo:hello"}
```

## The clients at a glance

### RestClient

`RestClient` is the synchronous HTTP client introduced in Spring Framework 6.1. It offers a modern, fluent API
(similar to `WebClient`) on top of blocking I/O and is the recommended choice for new blocking code. In Spring Boot 4
its auto-configured `RestClient.Builder` comes from `spring-boot-starter-restclient`.

### RestTemplate

`RestTemplate` is the original synchronous, template-method style HTTP client of the Spring Framework. It is in
maintenance mode — prefer `RestClient` for new code. In Spring Boot 4, `RestTemplateBuilder` lives in
`org.springframework.boot.restclient` (provided by `spring-boot-starter-restclient`).

### WebClient

`WebClient` is the non-blocking, reactive HTTP client from Spring WebFlux. It supports both asynchronous
(`Mono`/`Flux`) and blocking usage, and is the right fit for reactive applications. In Spring Boot 4 its
auto-configured `WebClient.Builder` comes from `spring-boot-starter-webclient`.

### Java `HttpClient`

`java.net.http.HttpClient` is part of the JDK since Java 11 and supports synchronous and asynchronous requests.
Spring can use it as the underlying request factory/connector for `RestClient`, `RestTemplate` and `WebClient`.

## Spring Boot 4 notes

Spring Boot 4 split auto-configuration into smaller modules. Compared with Boot 3, this project needed:

- `spring-boot-starter-aop` → `spring-boot-starter-aspectj`
- `spring-boot-starter-restclient` for `RestClient.Builder` / `RestTemplateBuilder`
- `spring-boot-starter-webclient` for `WebClient.Builder`
- `spring-boot-starter-opentelemetry` for OpenTelemetry tracing auto-configuration (required by Digma)

## CI & dependency updates

- **GitHub Actions** (`.github/workflows/maven.yml`) builds and tests all modules on JDK 25 for every push and pull
  request to `main`, and submits the Maven dependency graph on pushes to `main`.
- **Dependabot** (`.github/dependabot.yml`) checks Maven dependencies (all modules) and GitHub Actions daily at
  05:00 Asia/Jakarta, grouping related updates (Spring Boot, observability, test, Maven plugins, Actions) into
  fewer pull requests.

## License

[MIT](LICENSE)
