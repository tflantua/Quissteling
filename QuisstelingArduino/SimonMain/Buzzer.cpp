#include<Arduino.h>
#include "pitches.h"
#ifndef BUZZER_CPP
#define BUZZER_CPP
class Buzzer {
  public:
  // buzz to a frequency for a duration
  static void buzz(int freq, int duration) {
    ledcWriteTone(0, freq);
    delay(duration);
    ledcWriteTone(0,0);
  }

  // standard error buzz
  static void buzzError() {
    buzz(NOTE_G1, 500);
  }

  // standard positive user feedback buzz
  static void buzzPositive() {
    buzz(NOTE_A5, 100);
    delay(100);
    buzz(NOTE_A5, 100);
    delay(100);
  }

  // buzz a note corresponding to a given led number
  static void ledSound(int ledNum, int delayTime) {
    if (ledNum == 1) {
      buzz(NOTE_A2, delayTime); 
    } else if (ledNum == 2) {
      buzz(NOTE_B3, delayTime); 
    } else if (ledNum == 3) {
      buzz(NOTE_C4, delayTime); 
    } else if (ledNum == 4) {
      buzz(NOTE_D5, delayTime); 
    }
  }
};
#endif
