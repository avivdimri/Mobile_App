# Mobile_App

Link to github-https://github.com/avivdimri/Mobile_App.git

Link for the presentation of Mobile App- https://www.youtube.com/watch?v=WmdxuVWwlyw

A mobile app which connects to FlightGear simulator and control on the airplane with slider and joystic controler movement.

General Description
This program controls an aircraft within the "[FlightGear]" (https://www.flightgear.org/) flight simulator. The program connects to the flight 
simulator as a client.
The client will send the command that he gets from the user by the joystick or the slider

### Collaborators
This program was developed by Ori Abramnovich & Aviv Dimri CS students from Bar-Ilan university, Israel.

### Code Design:
The Mobile app has been programmed by the MVVM design, as we used Java data binding mechanism,and xml.

## Features:

###### Slider:


Using a horizontal slider, which can control the flight's rudder. 
Using a vertical slider, which can control on the speed by the throttle attribute. 


###### Joystic:
A small joystic that control the movement of the plane during the flight.

##### Connection:
A button when is clicked the app connects to the simulator by the ip and port the user enter in the 2 edit text above.


## Documentation
We can see in the UML below of the App which based on the MVVM architecture.
We bulided the main View which contain another view ,the Joystic. The main view has a member the ViewModel and by it make change in the Model.
For the View, the ViewModel is an abstraction of the Model.It passes commands from the view to the model.

The Views Model Converts model information into view information.

when the a View want to make change in the model we used with functions that the ViewModel call from the model.

Also, the Model is a client and responsible on the  connection with the Flight Gear Simulator.

<img width="524" alt="UML" src="https://user-images.githubusercontent.com/80414213/123543720-97deff80-d758-11eb-83e7-3065a52dfc43.png">


