//buttons
int button1 = 4;
int button2 = 5;
int button3 = 18;
int button4 = 19;

//led lights
int ledLight1 = 15;
int ledLight2 = 22;
int ledLight3 = 23;
int ledLight4 = 21;

void setup(){
  Serial.begin(9600);
  pinMode(button1, INPUT);
  pinMode(button2, INPUT);
  pinMode(button3, INPUT);
  pinMode(button4, INPUT);
  
  pinMode(ledLight1, OUTPUT);
  pinMode(ledLight2, OUTPUT);
  pinMode(ledLight3, OUTPUT);
  pinMode(ledLight4, OUTPUT);  
}

void loop(){
  readButton(button1, ledLight1);
  readButton(button2, ledLight2);
  readButton(button3, ledLight3);
  readButton(button4, ledLight4);
  delay(100);
}

void readButton(int button, int light){
  if(digitalRead(button) == HIGH){
    digitalWrite(light, HIGH);
  } else {
    digitalWrite(light, LOW);
  }
}
