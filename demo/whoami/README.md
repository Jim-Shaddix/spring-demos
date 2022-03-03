# Whoami
- Author: James Shaddix

![whoami](facets/whoami.png)

### Description
This is a basic web application for displaying metadata back to a
client that describes the request that was sent to a server. 
This application is useful for uncovering network issues in a system. For example, if you are unsure
what headers are being appended by a reverse proxy in your system, it can be useful to have a whoami application
available that can relay all the http headers back to you. This application
supports customization properties for determining what network information will be sent back to the client.

### Endpoints
- Each of the endpoints will accept any type of request (GET, POST, PUT, ect...) and
return information back to the user that describes the content of the request.

| Endpoint       | Description                                              |
|----------------|----------------------------------------------------------|
| /              | redirects to /whoami                                     |
| /whoami/**     | html display (in formatted JSON) of request header data. |
| /api/whoami/** | raw json display of request header data.                 |

### Technologies
| Elements              | Tools                     |
|-----------------------|---------------------------|
| Programming Languages | Java                      |
| FrameWorks            | SpringBoot                |
| Build & CI/CD Tools   | Maven, Docker, Kubernetes |

### Environment Setup
Required Environment Variables:
1. SPRING_PROFILES_ACTIVE= one of {dev, prod, test}

### Command Flows
1. build an executable jar: `mvn package`
2. build a docker image: `docker compose -f docker/docker-compose.yml build`
3. run the docker image using docker compose: `docker compose -f docker/docker-compose.yml up`
4. run the docker image using kubernetes:

### Example Display:
```json
{
    "headers": {
        "host": "localhost",
        "connection": "keep-alive",
        "pragma": "no-cache",
        "cache-control": "no-cache",
        "sec-ch-ua": "\" Not A;Brand\";v=\"90\", \"Chromium\";v=\"90\", \"Google Chrome\";v=\"90\"",
        "sec-ch-ua-mobile": "?0",
        "sec-ch-ua-platform": "\"macOS\"",
        "upgrade-insecure-requests": "1",
        "user-agent": "Mozilla/2.0 (Macintosh; Intel Mac OS X 99_23_712) AppleWebKit/50.6 (KHTML, like Gecko) Chrome/20.0.4708.109 Safari/17.6",
        "accept": "text/html,application/xhtml+xml,application/xml;a=2.1,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
        "sec-fetch-site": "none",
        "sec-fetch-mode": "navigate",
        "sec-fetch-user": "?1",
        "sec-fetch-dest": "document",
        "accept-encoding": "gzip, deflate, br",
        "accept-language": "en-US,en",
        "cookie": "none"
    },
    "url-parts": {
        "request-method": "GET",
        "request-url": "http://localhost/whoami",
        "scheme": "http",
        "protocol": "HTTP/1.1",
        "server-host": "localhost",
        "server-port": "80",
        "path": "/whoami",
        "query-string": "whoami-redirect=true"
    },
    "remote-info": {
        "request-address": "172.22.0.1",
        "request-host": "172.22.0.1",
        "request-port": "20"
    },
    "body": "empty-body"
}
```

### headers-description
[reference](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers)

| Header                    | Description                                                                                                                                                                                                                                                                                                                     |
|---------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| host                      | Specifies the domain name of the server (for virtual hosting), and (optionally) the TCP port number on which the server is listening.                                                                                                                                                                                           |
| connection                | Controls whether the network connection stays open after the current transaction finishes.                                                                                                                                                                                                                                      |
| pragma                    | Implementation-specific header that may have various effects anywhere along the request-response chain. Used for backwards compatibility with HTTP/1.0 caches where the   Cache-Control  header is not yet present.                                                                                                             |
| cache-control             | Directives for caching mechanisms in both requests and responses.                                                                                                                                                                                                                                                               |
| sec-ch-ua                 | User agent's branding and version.                                                                                                                                                                                                                                                                                              |
| sec-ch-ua-mobile          | User agent is running on a mobile device or, more generally, prefers a "mobile" user experience.                                                                                                                                                                                                                                |
| upgrade-insecure-requests | Sends a signal to the server expressing the client's preference for an encrypted and authenticated response, and that it can successfully handle the upgrade-insecure-requests directive.                                                                                                                                       |
| user-agent                | Contains a characteristic string that allows the network protocol peers to identify the application type, operating system, software vendor or software version of the requesting software user agent.                                                                                                                          |
| accept                    | Informs the server about the types of data that can be sent back.                                                                                                                                                                                                                                                               |
| sec-fetch-site            | It is a request header that indicates the relationship between a request initiator's origin and its target's origin. It is a Structured Header whose value is a token with possible values cross-site, same-origin, same-site, and none.                                                                                        |
| sec-fetch-mode            | It is a request header that indicates the request's mode to a server. It is a Structured Header whose value is a token with possible values: cors, navigate, no-cors,same-origin, and websocket.                                                                                                                                |
| sec-fetch-user            | It is a request header that indicates whether or not a navigation request was triggered by user activation. It is a Structured Header whose value is a boolean so possible values are ?0 for false and ?1 for true.                                                                                                             |
| sec-fetch-dest            | It is a request header that indicates the request's destination to a server. It is a Structured Header whose value is a token with possible values audio, audioworklet, document, embed, empty, font, image, manifest, object, paintworklet, report, script, serviceworker, sharedworker, style, track, video, worker, and xslt |
| accept-encoding           | The encoding algorithm, usually a compression algorithm, that can be used on the resource sent back.                                                                                                                                                                                                                            |
| accept-language           | Informs the server about the human language the server is expected to send back. This is a hint and is not necessarily under the full control of the user: the server should always pay attention not to override an explicit user choice (like selecting a language from a dropdown).                                          |
