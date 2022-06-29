# include<Servo.h>
Servo door;

void setup(){
door.attach(9);
door.write(0);
}

void loop(){
door.write(90);
}
