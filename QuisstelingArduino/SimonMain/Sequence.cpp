#include "Led.cpp"
#ifndef SEQUENCE_CPP
#define SEQUENCE_CPP
  class Sequence {
    private: 
      int numberOrderArray[5];
      int progress;

      void GenerateSequence() {
        for (int i = 0; i < 5; i++) {
          numberOrderArray[i] = rand() % 4;
        }
      }

    public: 
      Sequence() {
        GenerateSequence();
        progress = 0;
      }
      
      void StartSequence(Led leds[]) {
          for (int i = 0; i <= progress; i++) {
            leds[numberOrderArray[i]].turnOn();
            delay(1000);
            leds[numberOrderArray[i]].turnOff();
            delay(1000);
          }
          progress++;
          if (progress >= sizeof(numberOrderArray)) progress = 0;

//          leds[0].turnOn();
//          leds[1].turnOn();
//          leds[2].turnOn();
//          leds[3].turnOn();
//
//          delay(2000);
//
//          leds[0].turnOff();
//          leds[1].turnOff();
//          leds[2].turnOff();
//          leds[3].turnOff();
//
//          delay(2000);
      }
  };
#endif
