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

Follow the TODO marks and have fun! 😎 