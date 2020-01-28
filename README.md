# rsocket-workshop
It is a very basic workshop on rsocket with spring boot

Taken from official RSocket documentation 

```
RSocket is a popular and powerful application protocol providing Reactive Streams semantics.

RSocket is a binary protocol for use on byte stream transports such as TCP, WebSockets, and Aeron.

It enables the following symmetric interaction models via async message passing over a single connection:

- request/response (stream of 1)
- request/stream (finite stream of many)
- fire-and-forget (no response)
- channel (bi-directional streams)  

It supports session resumption, to allow resuming long-lived streams across different transport connections. 
This is particularly useful for mobile⬄server communication when network connections drop, switch, and reconnect frequently.
```

The goal of workshop is provide a simple tutorial on how implements all the 4 programming RSocket paradigms.

Tip think to RSocket interaction model in Spring to a messaging interaction model and how Spring manage typically a situation like this.
For instance think how Spring manage websocket on STOMP. 

Moreover follow spring messaging programming model on rsocket message reciver like below:

```java


``` 

Follow the TODO marks and have fun! 😎 

## Exercise 1: play with Reactor
For this task follow the test in order to play and get confidence with reactor in order to be more comfortable on the follow exercises. 

## Exercise 2: Request Reply
For this task you should implement an echo use case that resend to the sender the same sent message. 

## Exercise 3: fire and forget
For this task you should fire a message from client to the server, that do a simple message log on the server
 
## Exercise 4: request stream
For this task you should implement a streaming use case in which the client fire a streaming request on the server and  
then the server start to publish as echo events the same message fired on stream request.