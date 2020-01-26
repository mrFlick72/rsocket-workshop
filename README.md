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
For instance think how Spring manage websocket on STOMP

Follow the TODO marks and have fun! 😎 

## Exercise 1: Request Reply
For this task you should implement an echo use case that resend to the sender the same sent message. 
Implements a simple echo that once received a message republish inbound message as response using the request reply interaction model

## Exercise 2: fire and forget
For this task you should fire a message on consumer and a simple message logging on publisher, 
pay attention of the nature of protocol that consumer and publisher role can be mixed according to the chosen interaction semantic.
 
## Exercise 3: request stream
For this task you should implement an streaming use case in which on consumer side you fire an streaming request and on publisher side you should react to this request starting to push echo events.