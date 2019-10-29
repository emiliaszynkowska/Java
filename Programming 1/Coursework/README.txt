--------------------------------------------------------------------------------------------------------------
Name: Emilia Szynkowska
Username: eas1g18
Student ID: 30186773
--------------------------------------------------------------------------------------------------------------

This coursework contains:
Main.java
House.java
ApplianceGenerator.java
Appliance.java
CyclicFixed.java
CyclicVaries.java
RandomFixed.java
RandomVaries.java
Meter.java
BatteryMeter.java
Battery.java
Toolbox.java
myHouse.txt
save.txt
README.txt

NOTE: Please note that Toolbox.java and myHouse.java are files provided by the university and have not been created by me. I have used the readStringFromCmd() method from Toolbox to obtain user inputs in my coursework.

--------------------------------------------------------------------------------------------------------------

Main Features:

The SmartHome works by having a class called House, which contains Meters and Appliances. In the main method a house is created and appliances are added to it. The file myHouse.txt contains the information for these appliances. 

ApplianceGenerator is a class which is responsible for reading the config file and getting information from it, and is also responsible for saving data. I thought it was appropriate to make this a separate class to make my code simpler, otherwise the main method would be very long and have too many variables/methods. ApplianceGenerator makes use of the BufferedReader and PrintStream classes provided by java.util to read and write to files.

The House class has two meters called batteryMeter and waterMeter. Its main function is the activate() method. This method calls the method timePasses() on each appliance, which causes the appliances to use/produce units. These units are consumed by a meter. The report() method is then called which prints a summary of the units that have been used and their cost.

The Meter class consumes units from appliances. Its main function is the report() method. This method prints the name of the meter, the reading (units), and cost (£). There is also a BatteryMeter class which is specifically for the electric meter. This is connected to a battery, which contains charge. The BatteryMeter class is similar to Meter but its report() method overrides the default report() method. This method allows the meter to take/add charge to/from the battery.

The Appliance class has variables such as name, meter, and type, and has the methods timePasses() and consumeUnits(). There are four types of Appliance:
1. Cyclic Fixed
This type of appliance uses a fixed amount of units per hour and runs for a fixed number of hours per day. The user must provide the number of units it uses and the number of hours it runs for. For example, when timePasses() is called, the appliance runs for a certain amount of time, produces a certain number of units, and gives these to the meter it is connected to.
2. Cyclic Varies
This type of appliance uses a varying amount of units per hour and runs for a fixed number of hours per day. The user must provide the higher and lower bound for the number of units it uses and the number of hours it runs for. 
3. Random Fixed
This type of appliance uses a fixed amount of units per hour and runs for a varying number of hours per day. The user must provide the number of units it uses and the probability of it running.
4. Random Varies
This type of appliance uses a random amount of units per hour and runs for a varying number of hours per day. The user must provide the higher and lower bound for the number of units it uses and the probability of it running.

--------------------------------------------------------------------------------------------------------------

Extensions:

- A menu. When the main method is run it gets a string input from the user, using the Toolbox command readStringFromCmd(). The menu allows the user to activate the house, see the current appliances, add new appliances, see the usage history, or quit. If the user input is not a number from 1 to 5, an error message is printed and the menu asks again. The menu will continue to ask for an input until the user chooses to quit.

The option “1. Activate the House” allows the user to use the activate() and activate(time) methods. It will ask the user whether they would like to use the default method or the custom method. The default activate() method does not take a parameter and runs the house for 7 days. The custom activate(time) method overrides the default method and takes one integer parameter. It will run the house for the number of hours specified.

The option “2. See the current appliances” prints out information about each appliance. It will print the name, the meter it is connected to, and the type of appliance.

The option “3. Add a new appliance” uses the addAppliance() method in ApplianceGenerator to add a new appliance to the myHouse.txt file. It prompts the user to enter various information about the appliance, then uses BufferedReader and PrintStream to write this information to myHouse.txt. The ApplianceGenerator class must be compiled again to see the effects of this action.

The ApplianceGenerator class writes the date, time activated and total cost to save.txt every time the user activates the house. The option “4. See usage history” uses the printSave() method to print information from this save file. 

The option “5. Quit” uses System.exit(0) to close the program.

- Ability to write to myHouse.txt. The config file can be edited using PrintStream. First, Toolbox is used to get inputs from the user such as name, subclass, meter etc. These are stored as instance variables. Then I use applianceAdder.append() to write all the inputs to the file. 

- Save file. A file called save.txt exists which contains information about the house’s energy usage. Here you will find the date, the time activated and the total cost. I have used PrintStream to write these values, and BufferedReader to read and print them. The getLine() method reads each line in the file.

--------------------------------------------------------------------------------------------------------------


