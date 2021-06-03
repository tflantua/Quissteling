#include <Arduino.h>
#ifndef LED_CPP
#define LED_CPP
class Led {
  private: 
    int pin;
    bool isOn;
  public:
    Led() {
      pin = -1;
    }
    Led(int gpioPin) {
      pin = gpioPin;
      pinMode(pin, OUTPUT);
      turnOff();
    }
  
    void turnOn() {
      digitalWrite(pin, HIGH);
      isOn = true;
    }
  
    void turnOff() {
      digitalWrite(pin, LOW);
      isOn = false;
    }
};
#endif 
