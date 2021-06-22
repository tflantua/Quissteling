#include<Arduino.h>
#include "Buzzer.cpp"
#ifndef LED_CPP
#define LED_CPP
class Led {
  public:
  // set tje govem ledpin to a be on or off according to the given bool for a time
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
