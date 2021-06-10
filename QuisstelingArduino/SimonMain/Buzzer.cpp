#include<Arduino.h>
#include "pitches.h"

class Buzzer {
  public:
  static void buzz(int freq, int duration) {
    ledcWriteTone(0, freq);
    delay(duration);
    ledcWriteTone(0,0);
  }

  static void buzzError() {
    buzz(NOTE_G2, 500);
  }

  static void buzzPositive() {
    buzz(NOTE_A5, 100);
    delay(100);
    buzz(NOTE_A5, 100);
  }
};
