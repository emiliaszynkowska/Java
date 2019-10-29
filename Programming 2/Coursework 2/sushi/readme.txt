This file explains the classes I have added to this project.
Name: Emilia Szynkowska
Username: eas1g18@soton.ac.uk
ID: 30186773

1. Configuration
-there is a Configuration class
-the server reads "data.txt" by default as this file is used for data persistence
-other configurations can also be loaded afterwards

2. Stock Management, Drones and Staff
-there is a Stock class
-each staff/drone is connected to an instance of Stock and can use the methods checkDishes(), checkIngredients(), check(), checkOrders(), restockDish(), restockIngredient(), and deliver()
-Thread.sleep() is used each time the staff/drone check stock
-staff start with a fatigue of 0 which increases after each task. maximum fatigue is 10. at fatigue 10 the staff rest for 10 seconds
-staff have a status of "Idle", "Working" and "Resting"
-drones start with a battery of 100 which decreases after each task. when battery reaches 0 the drone rests for 10 seconds
-drones have a status of "Idle", "Charging", "Waiting for restock", "Working" (for restocking) and "Flying" (for delivering orders)
-drones only show progress when delivering orders, otherwise progress is null

3. Persistence
-there is a DataPersistence class and DataPersistenceWriter class
-when the server starts up it reads the configuration data.txt by default
-data.txt is written in text format e.g. "RESTAURANT:Name:Postcode" etc.
-the server data is rewritten to data.txt every 5 seconds
-you can delete data.txt if you want to reset the data

4. Communication
-there are extra classes called ServerComms, ServerCommsInputThread, ServerCommsOutputThread, and ClientComms
-ServerComms: creates sockets and threads
-ServerCommsInputThread: handles input from clients
-ServerCommsOutputThread: sends data to clients
-ClientComms: sends users and orders to the server, recieves data from the server
-these all use ObjectOutputStreams and ObjectInputStreams
-if a client is opened on its own it closes as it cannot connect (please open a server first)
-the server shows messages when clients are connected and disconnected
-an internet connection is needed
-dishes only update in the client when it is started up
-orders update in the client every few seconds
-orders have a status of "Recieved", "Delivering", "Delivered" and "Cancelled"

List of extra classes:
ClientComms
ServerComms
ServerCommsInputThread
ServerCommsOutputThread
Stock
Configuration
DataPersistence
DataPersistenceWriter
