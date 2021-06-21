#include<Arduino.h>
#include "Buzzer.cpp"
#ifndef LED_CPP
#define LED_CPP
class Led {
  public:
  static void setValue(int pin, bool val, int ledNum, int delayTime) {
    digitalWrite(pin, val);

    // if it's being turned on, also make a sound with it
    if (val) {
      // includes delay
      Buzzer::ledSound(ledNum, delayTime);
    } else {
      // if not then do the delay here
      delay(delayTime);
    }
  }
};
#endif
