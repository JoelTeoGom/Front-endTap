package RingActor;

import Estructura.*;
import HelloWorld.*;
import Message.Message;
import Observer.Event;
import Observer.MonitorService;


public class RingActor extends Actor {
    private ActorProxy next = null;

    public RingActor(){
        super();
    }

    @Override
    public void process(Message message) throws InterruptedException {

        MonitorService.getInstance().publish(Event.RECEIVE,this,message);

        System.out.println(message.getMessage());
        if(message.getFrom().getSourceActor().equals(this)){
            System.out.println("Fin de la vuelta "+ Integer.parseInt(message.getMessage()));
        }else {
            next.send(message);
            MonitorService.getInstance().publish(Event.SEND,this,message);
        }
        if(Integer.parseInt(message.getMessage()) == 100){
            this.exit = true;    //No hay manera limpia de parar un thread
        }

    }

    public ActorProxy getNext() {
        return next;
    }

    public void setNext(ActorProxy next) {
        this.next = next;
    }
}