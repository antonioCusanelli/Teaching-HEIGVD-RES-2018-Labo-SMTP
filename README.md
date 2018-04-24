# HEIG-RES-2018-SMTP Basler-Cusanelli

**The project:**

The purpose of this project is to send forged emails to a certain number of groups. The groups are defined by the user using configuration files. This project is a Maven project in Java. We also provide a simple mock server which can be run in a docker container. This readme explains how to set up the program on Linux.

**Mock server with docker:**
* The docker directory is the directory `docker-MockServer`. It contains the Dockerfile and a source folder required by the Dockerfile. You can build the docker image simply by going in this folder and runing the following command in a bash terminal : `docker build -t yourContainerName .`. You can check if your image was correctly build by typing `docker images`. This command should list all builded image on your system.

* In order to create and run the docker container of the mockmock server, just write `docker run yourContainerName -p 25:25` in your terminal. The server will use the port 8282 for the HTTP and the 25 for SMTP. If you need to change those ports, the dockerfile must be edited and the image rebuild.

* Now that the server is running, you can obtain his ip address. You need to run `docker ps`, which list all runing container. In the *container id* column you can copy the container id of the server. Then you can run the following command `docker inspect yourContainerId`. This command will output a lot of informations about the container, on of them is the ip address. We will need it for accessing the web page of the mock server and for the configuration of the smtp client.

* Now you can access the web page of the mock server by typing `yourContainerIPaddress:8282` on your favorite web browser.


**Configuration:**
* The configuration files of the program are in the `PrankMail/configuration` directory in the root folder of the project.

* The `config.properties` file contains the configuration for the smtp protocol (SMTP server adress and port), the number of groups and the user to CC. Note: a group must contain at least 3 people. If there is not enough people for the amount of groups required, less groups will be generated. (If you run the server from docker you need to replace the ip address by the ip address of the docker container you got before)

* The `victims.utf8` file contains the list of email addresses (one per line). You can add how many addresses as you want.

* The `messages.utf8` file contains the messages that will be send. They are separated by ``==``. The first line of each message must be `Subject: ` otherwise, the mail received by the victims won't have any. When you first download the project, there is 3 messages. You can use them as examples and set your own messages.

**Implementation:**
The program is in two parts: the SMTP client code and the pranks one.
For the pranks :
* We have a JavaObject called PrankGenerator that have a method creating then returning a list of pranks.
* Each pranks contain a sender, some victims and a message. Those informations are read from the config file using the ConfigurationManager Object.

For the SMTP Client:
* We start a connection with the SMTP server then we send the messages through the protocol for each pranks in the list.
* Once all the mails are sent, the client drop the connection with the server and exit the program.
