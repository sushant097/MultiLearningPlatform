# MultiLearningPlatform
This is the Web Based learning website build with Spring 5 and latest web technology specially designed for the student to learn different things in a single website. Main focus is given on live training conduct by teacher to the student and live video streaming is based on the Java websocket protocol. This is build for Tribhuvan University of NEPAL.
# Requirements
- Glassfish Server 4.0 installed. Required for server side implemenation of  JSR 356.
- Java 7 or Java 8 which supports Websocket API

Steps to perform Live Video Streaming:
1.    At first take the video of webcam of sender end.
2.    We use [MediaStreamRecorder] API for recording of video of certain chunk.
3.    Then, Once the Websocket connection is established then we send the chunk of video in form of blob and send to through the websocket.
> Blobs allow you to construct file like objects on the client that you can pass to apis that expect urls instead of requiring the server provides the file. See [here](https://www.javascripture.com/Blob)
4.   In sender end we receive the blob through [JavaScript Websocket](https://www.linode.com/docs/development/introduction-to-websockets/) and with the help of [WebRTC](https://en.wikipedia.org/wiki/WebRTC) we convert blob into video url and play in Browser.
5.    <b> The above steps are repeatedly perform to perform live video streaming.
  

#### Note: No any database is add here, you can add make your own database SQL or ORACLE and integrate it. But the project Schema Diagram and extra ProjectTable MYSQL Query is included there inside /images directory. This project is suceesfully implemented in SQL maria-DB of XAMPP server.

**Any Improvement on this project is highly appreciated! Feel free to open the issue and ask question.**
