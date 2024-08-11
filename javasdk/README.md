# openapi-java-client

llama.cpp Server API
- API version: 1.0.0
  - Build date: 2024-08-06T21:00:43.913362800-07:00[America/Los_Angeles]
  - Generator version: 7.7.0

API for interacting with the llama.cpp server.


*Automatically generated by the [OpenAPI Generator](https://openapi-generator.tech)*


## Requirements

Building the API client library requires:
1. Java 1.8+
2. Maven (3.8.3+)/Gradle (7.2+)

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.branchoftriumph</groupId>
  <artifactId>openapi-java-client</artifactId>
  <version>1.0.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
  repositories {
    mavenCentral()     // Needed if the 'openapi-java-client' jar has been published to maven central.
    mavenLocal()       // Needed if the 'openapi-java-client' jar has been published to the local maven repo.
  }

  dependencies {
     implementation "com.branchoftriumph:openapi-java-client:1.0.0"
  }
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/openapi-java-client-1.0.0.jar`
* `target/lib/*.jar`

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

// Import classes:

import com.branchoftriumph.llamacpp.ApiClient;
import com.branchoftriumph.llamacpp.ApiException;
import com.branchoftriumph.llamacpp.Configuration;
import com.branchoftriumph.llamacpp.openapi.models.*;
import com.branchoftriumph.llamacpp.LlamaCppClient;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080");

        LlamaCppClient apiInstance = new LlamaCppClient(defaultClient);
        Boolean includeSlots = true; // Boolean | Include internal slots data in the response
        Boolean failOnNoSlot = true; // Boolean | Fail if no slot is available
        try {
            GetHealthResponse result = apiInstance.getHealth(includeSlots, failOnNoSlot);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling LlamaCppClient#getHealth");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://localhost:8080*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*LlamaCppClient* | [**getHealth**](docs/LlamaCppClient.md#getHealth) | **GET** /health | Get the health status of the server
*LlamaCppClient* | [**getMetrics**](docs/LlamaCppClient.md#getMetrics) | **GET** /metrics | Get the metrics of the server
*LlamaCppClient* | [**getProps**](docs/LlamaCppClient.md#getProps) | **GET** /props | Get the current settings of the server
*LlamaCppClient* | [**getSlots**](docs/LlamaCppClient.md#getSlots) | **GET** /slots | Returns the current slots processing state.
*LlamaCppClient* | [**postCompletion**](docs/LlamaCppClient.md#postCompletion) | **POST** /completion | Generate a text completion
*LlamaCppClient* | [**postDetokenize**](docs/LlamaCppClient.md#postDetokenize) | **POST** /detokenize | Detokenize given tokens
*LlamaCppClient* | [**postEmbedding**](docs/LlamaCppClient.md#postEmbedding) | **POST** /embedding | Generate embedding of a given text.
*LlamaCppClient* | [**postInfill**](docs/LlamaCppClient.md#postInfill) | **POST** /infill | For code infilling. Takes a prefix and a suffix and returns the predicted completion as stream.
*LlamaCppClient* | [**postSlots**](docs/LlamaCppClient.md#postSlots) | **POST** /slots/{id_slot} | Save, restore, or erase the prompt cache of the specified slot to a file.
*LlamaCppClient* | [**postTokenize**](docs/LlamaCppClient.md#postTokenize) | **POST** /tokenize | Tokenize a given text


## Documentation for Models

 - [CompletionTimings](docs/CompletionTimings.md)
 - [Error](docs/Error.md)
 - [ErrorResponse](docs/ErrorResponse.md)
 - [GenerationSettings](docs/GenerationSettings.md)
 - [GetHealthResponse](docs/GetHealthResponse.md)
 - [GetPropsResponse](docs/GetPropsResponse.md)
 - [Image](docs/Image.md)
 - [NextToken](docs/NextToken.md)
 - [PostCompletionRequest](docs/PostCompletionRequest.md)
 - [PostCompletionResponse](docs/PostCompletionResponse.md)
 - [PostDetokenizeRequest](docs/PostDetokenizeRequest.md)
 - [PostDetokenizeResponse](docs/PostDetokenizeResponse.md)
 - [PostEmbeddingRequest](docs/PostEmbeddingRequest.md)
 - [PostEmbeddingResponse](docs/PostEmbeddingResponse.md)
 - [PostInfillRequest](docs/PostInfillRequest.md)
 - [PostSlotsRequest](docs/PostSlotsRequest.md)
 - [PostSlotsResponse](docs/PostSlotsResponse.md)
 - [PostTokenizeRequest](docs/PostTokenizeRequest.md)
 - [PostTokenizeResponse](docs/PostTokenizeResponse.md)
 - [Slot](docs/Slot.md)
 - [SlotsTimings](docs/SlotsTimings.md)
 - [SystemPrompt](docs/SystemPrompt.md)


<a id="documentation-for-authorization"></a>
## Documentation for Authorization

Endpoints do not require authorization.


## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author


