### Author :- Shashi Bhushan Yadav

### Course :- CS6650 - Project 3

This zip contains all the files for the project including Executable jars in folder named "Executables". There are two executables Server.jar and Client.jar for the project.

### **How to run Server Executable** :-

Before running the server put a server configuration file named config.json in the same directory.

File example is given below:- 
	
	{
		"coordinatorServer":	{
			"host": "localhost",
			"port": 40000,
			"serviceName": "CoordinatorService"
		},
		"participants": {
			"addressList": [
					{
						"host": "localhost",
						"port": 40001,
						"serviceNameList": ["StoreService"]
					},
					{
						"host": "localhost",
						"port": 40002,
						"serviceNameList": ["StoreService"]
					},
					{
						"host": "localhost",
						"port": 40003,
						"serviceNameList": ["StoreService"]
					},
					{
						"host": "localhost",
						"port": 40004,
						"serviceNameList": ["StoreService"]
					},
					{
						"host": "localhost",
						"port": 40005,
						"serviceNameList": ["StoreService"]
					}
					]
			}
	}

In this configuration file, key "coordinatorServer" contains Coordinator host, port and ClassName to load as Coordinator server.

"addressList" key in "participants" object contains the list of objects containing host, port and ClassName for each participants.

So, in this example there will be one coordinator and five participants. You can add as much participants you want just by adding them to the list.

Once you have the right config file just start server with the simple command.

`-> java -jar Server.jar`

Once started Server will create another directory "logs" that will contain logs of individual servers by following naming convention:- 

`Server_{server port}_{timestamp}.txt`

Also, whenever client issues a request to log state all participants will log the snapshot of store in their corresponding log files.


### **How to run Client Executable** :- 

The Client executable can be start by following command:-

`-> java -jar Client.jar RPC <server-host> <RPC-server-port> <RPC-Service-Key>`

Here, Since we are building upon the code of project 2, we will be using same servers except they will be participants in this case.

And, Client can connect to any one of the participants to run. 

Ex. `java -jar Client.jar RPC localhost 40002 StoreService`

This command will connect to participant running on `localhost:40002`

--------------------------------------------------------------------
Entry point Java files are located in src/main/java/com/project/three

How to run Server using ClusterServerDebug.java :-
	->	`javac ClusterServerDebug.java`

Then,

	->	`java ClusterServerDebug`

Similarly, this Class will require config.json just as in Server.jar executable mentioned above.


How to run Client using ClientDebug.java :- 
	->	`javac ClientDebug.java`

Then,

		-> java ClientDebug RPC <server-host> <RPC-server-port> <RPC-Service-Key>

Same as in executables:-

Ex. `java ClientDebug RPC localhost 40002 StoreService`

--------------------------------------------------------------------

Screenshots of the terminal Output are present in folder named Screenshots

--------------------------------------------------------------------

# **Summary of working** 

After starting the server first using any of the start command start the client corresponding to the protocol. 
Client is written to test dummy data when it starts it will put 5 key-value pairs then fetch them. 
Then it will initiate state logging. After printing those five fetched values client will ask for deletion of these five keys and fetch them from server which will result in values shown as null as client didn't receive any data for corresponding keys from Server.
After this client becomes interactive and user can perform PUT, GET, DELETE operations.
User can also exit the client or can ask server and client both to exit or can initiate a state save depending upon the selection.
Also, Server creates a log file starting with the name "Server_{server-port}_*" that can be used to asses server health or status as well as to check logged states of the servers.
Each client can only connect to participants and GET as we as PUT and DELETE under transaction is responsibility of this participant to execute utilizing coordinator.

---------------------------------------------------------------------

# **Executive Summary** 

### _Assignment Overview_:-

As an increment over Project 2, this project clarifies how Two-phase commit work in the background. 
I have used Java RMI for RPC communication implemented over TCP/IP communication utilizing serialization, networking, and class facilities already available in Java. 
It helped me in understanding how to implement data replication utilizing two-phase commit protocol. Providing me initial insights in cluster management and implementing coordinator and orchestrating replication on multiple server.
It provides further understanding of synchronization between servers in a cluster as well as inter-thread communication in java.
It also handles all the concurrent and multithreading operations on the server, however, I have already utilized multi-threading and thread pools with traditional TCP/IP and UDP communications in the first and second project implementation. 
The purpose of this project is to have a decent understanding of working of data replication, implementing transactions/locks and how concurrency on clusters as well as How Debugging/ Exceptional Handling works in a distributed systems.


### _Technical Impression_:-

Completing this project has been a thought-provoking experience. 
A more structured method of communication was made possible by implementing RPC using Java RMI, which also enabled smooth communication between the client and server. 
RPC brought a new degree of efficiency and organization to communication that had previously relied on sockets. Utilizing Java RMI, I implemented two-phase protocol using a cluster of servers(numbers can be changed simply by adding more participants in config file) and learned a lot about synchronization and inter-thread communication using `Object.wait()` and `Object.notify()` methods provided in java.

Concurrent client requests were handled by thread pools provided by Java RMI, and data consistency required careful synchronization. 
A primary goal in preventing data conflicts was managing mutual exclusion for individual clients.

This project has improved my knowledge of transactions and inter-thread(or process) communications while highlighting the value of concurrent programming in practical situations. 
Regarding enhancements, offering more detailed instructions on selecting an RPC framework and multi-threading techniques would be beneficial. 
Overall, this project has expanded my knowledge of database programming and data integrity management on distributed systems.

By working of this project I understood about the workings of two-phase protocol and how to build a server cluster in multi-threaded environment. 
My implementation of Cluster will start one coordinator and number of defined participants servers in config file. 
I understood how client and server communicate and implement replication in a cluster and what kind errors or issues can arise in multiple clients (Ex. two different clients accessing common store cluster concurrently) thereby increasing reliablity and throughput of the entire system. 
There is a lot of improvement that can be performed in my implementation but I guess for now it satisfies the requirements of this project. 