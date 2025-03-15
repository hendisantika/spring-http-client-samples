# Spring Http Client Samples

WebClient, HttpClient and RestTemplate are commonly used Java libraries for making HTTP requests. But they have some
differences in terms of features and usage:

### WebClient:

WebClient is a non-blocking, reactive HTTP client introduced in Spring WebFlux.
It is designed to work with reactive programming paradigms and supports both synchronous and asynchronous
request/response flows.

### HttpClient:

HttpClient is part of the standard Java library starting from Java 11
It provides a synchronous and asynchronous API for making HTTP requests.
HttpClient supports both blocking and non-blocking I/O models

### RestTemplate:

RestTemplate is a synchronous HTTP client provided by the Spring Framework.
It offers a convenient and easy-to-use API for making HTTP requests, handling responses, and mapping them to Java
objects.
RestTemplate has been widely used in Spring applications for a long time.
However, it is now in maintenance mode and will be deprecated in the future in favor of WebClient.

### RestClient:

RestClient is not a specific library or component in the Java ecosystem. It is a generic term used to refer to any HTTP
client implementation or library that is used for making RESTful API calls.
It is not associated with a specific library, but rather represents the concept of a client that communicates with
RESTful services.
In the context of Java, RestClient can refer to various HTTP client libraries or frameworks, such as the Apache
HttpClient, OkHttp, or even the aforementioned WebClient and RestTemplate.
