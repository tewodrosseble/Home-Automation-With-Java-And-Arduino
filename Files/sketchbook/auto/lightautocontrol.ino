int prstr = A0; 
int light=8;       

int value;
void setup(){

 pinMode(light, OUTPUT);
 pinMode(prstr, INPUT);
 Serial.begin(9600);
}
void loop(){
   value = analogRead(prstr);

  if (value > 25){
    digitalWrite(light, LOW);
  }
  else{
    digitalWrite(light, HIGH);
  }

  Serial.println(value);
}