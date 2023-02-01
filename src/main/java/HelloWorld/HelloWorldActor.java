package HelloWorld;

import Estructura.Actor;
import Message.*;
import Observer.Event;
import Observer.MonitorService;

public class HelloWorldActor extends Actor{
    public HelloWorldActor() {
        super();
    }

    /**
     * metode process: mirem quin tipus de missatge i actuem depenent d'aquest
     * @param m
     */
    @Override
    public void process(Message m) {  //en esta funcion actualizaremos estado
        MonitorService.getInstance().publish(Event.RECEIVE, this, m);
        if (m instanceof HelloWorldMessage) {
            System.out.println(m.getMessage());
        } else if (m instanceof QuitMessage) {
            System.out.println("Oh hell naw!!!");
            MonitorService.getInstance().publish(Event.STOPPED, this, m);
            exit = true;
        } else{
             System.out.print("No se ha registrado");
        }
    }
}
