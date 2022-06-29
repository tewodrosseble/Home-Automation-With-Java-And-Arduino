int trigPin = 6;
int echoPin = 7;
int alarm = 5;
float totaltime, distance, distance_in_m, time_sec;
void setup() {

pinMode(trigPin, OUTPUT);
pinMode(echoPin, INPUT);
pinMode(alarm, OUTPUT);

Serial.begin (9600);
}

void loop() {

  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);

  totaltime = pulseIn(echoPin, HIGH);
  time_sec = 0.000001*totaltime;
  
  distance_in_m = 340 * 0.5 * time_sec;
  distance = distance_in_m * 100;

  if(distance<=10){
for(int i=0;i<100;i++)

{

digitalWrite(alarm,HIGH);

delay(2);//wait for 2ms

digitalWrite(alarm,LOW);

delay(2);//wait for 2ms
}

digitalWrite(alarm, HIGH);
}
  
  else{
    digitalWrite(alarm, LOW);
  }

  Serial.print("distance: ");
  Serial.print(distance);
  Serial.println(" cm");

  delay(500);

}
