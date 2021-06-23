package com.example.myapplication.ViewModel;
import com.example.myapplication.Model.Model;

// The View model of the application part of the MVVM architecture
public class ViewModel {
    private Model model;

    //constructor gets model
    public ViewModel(Model m){
        model = m;
    }
    // connect to FG by the model
    public void connectVM(String ip,int port){
        model.connect(ip,port);
    }
    // set the Aileron attribute
    public void setAileron(double var){
        model.setAttribute(var,"set /controls/flight/aileron ");
    }
    // set the Elevator attribute
    public void setElevator(double var){
        model.setAttribute(var,"set /controls/flight/elevator ");
    }
    // set the Rudder attribute
    public void setRudder(double var){
        model.setAttribute(var,"set /controls/flight/rudder ");
    }
    // set the Throttle attribute
    public void setThrottle(double var){
        model.setAttribute(var,"set /controls/engines/current-engine/throttle ");
    }

}
