# Home Automation with Java and Arduino

This project uses java GUI to control a house using built with arduino microcontrller. 

## Features

     ### Control Lights using buttons
     We can turn on and off the light inside the house using on/off button on the admin panel.
     
     ### Authentication System 
     Any user registered on the database can access the admin panel by entring his/her username and password.
     
     ### Auto Control light
     This feature is used to turn on/off the a light using the intensity of the the light. This uses light sensor(photo resistor) with arduino.
     
     ### Auto Control light activation
     This feature is used to control the working of the auto control light by activting or deactivating it using button.
     
     ### Door Open/Close
     This feature enables the opening and the closing of a door usin buttons by connecting the door to servo motor.
     
     ### Alarm System
     The intruder alarm rings when someone is near the house. I have used ultrasonic sensor to detect the intruder and a buzzer to ring the sound.
     
     ### Alarm Activation
     This feature enables the activation of the alarming system using a button.
     
## Technologies

   For this project I have used the following technlogies
   
        ### Java : to handle the processes and to the graphical user interface
        ### Mysql: to store the user information (username and password)
        ### Arduino : to interact with the microcontroller and the sensors.
      
##  Libraries

    When I am working on this project I have used the following libraries
    
        ### JSerialComm : this library is used to control the connection between the java program and arduino microcontroller.
        ### MysqlConnectorjava : this library is used to control the connection between java program and mysql database. It also helps us to excute sql queries inside java program.
        
        
 ## To Run the project: 
     ~ connect the circuit based on the sketches /HomeAutomationWithJava/Files/Sketchbook
     ~ Create database using mysql and add a table with username and password raws
     ~ Configure the database name, username and password LogIn.java file line 66
     ~ now you can control the your house using Java GUI

## How does the project work
   I made the java project to send the command to the arduino in two ways:
   
       Jserialcomm library ~ Java library that is used to connect arduino with java project through the port.
       Process builder ~ Java class is used to create operating system processes.
