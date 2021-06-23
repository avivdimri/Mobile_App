package com.example.myapplication.ViewModel;

import com.example.myapplication.Model.Model;

public class ViewModel {
    private Model model;

    public ViewModel(Model m){
        model = m;
    }
    public void connectVM(String ip,int port){
        model.connect(ip,port);
    }
    public void setAileron(double var){
        model.setAttribute(var,"set /controls/flight/aileron ");
    }
    public void setElevator(double var){
        model.setAttribute(var,"set /controls/flight/elevator ");
    }

    public void setRudder(double var){
        model.setAttribute(var,"set /controls/flight/rudder ");
    }
    public void setThrottle(double var){
        model.setAttribute(var,"set /controls/engines/current-engine/throttle ");
    }

}
