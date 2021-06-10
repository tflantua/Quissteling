#include <Arduino.h>
#include<LiquidCrystal_I2C.h>
#include "Led.cpp"
#ifndef SEQUENCE_CPP
#define SEQUENCE_CPP
  class Sequence {
    private: 
      int numberOrderArray[5];
      int progress;

      void GenerateSequence() {
        Serial.print("Sequence: ");
        for (int i = 0; i < 5; i++) {
          numberOrderArray[i] = rand() % 4;
          Serial.print(numberOrderArray[i]);
          Serial.print(" ");
        }
        
        Serial.println("");
      }

    public: 
      Sequence() {
        GenerateSequence();
        progress = 0;
      }
      
      void StartSequence(int ledPin1, int ledPin2, int ledPin3, int ledPin4) {
        Serial.println("Playing a sequence");
          for (int i = 0; i <= progress; i++) {
            Serial.print("Showing sequence part ");
            Serial.print(i);
            Serial.print(" Of ");
            Serial.print(progress);

            int delayTime = 1000;
            
            if (numberOrderArray[i] == 0) {
              Led::setValue(ledPin1, true, 1, delayTime);
              Serial.println(", showed led 1");           
            }
            if (numberOrderArray[i] == 1) {
              Led::setValue(ledPin2, true, 2, delayTime);
              Serial.println(", showed led 2");                 
            }
            if (numberOrderArray[i] == 2) {
              Led::setValue(ledPin3, true, 3, delayTime);
              Serial.println(", showed led 3");                
            }
            if (numberOrderArray[i] == 3) { 
              Led::setValue(ledPin4, true, 4, delayTime);   
              Serial.println(", showed led 4");             
            }

            // give a small delay before turning the leds off and starting the new led
            ResetBlinkAll(ledPin1, ledPin2, ledPin3, ledPin4);
          }
          progress++;
      }

      boolean isInputEqualToSequence(int input[5]) {
        for (int i = 0; i < progress; i++) {
          if (input[i] != numberOrderArray[i]) return false;
        }
        return true;
      }

      void BlinkAll(int ledPin1, int ledPin2, int ledPin3, int ledPin4) {
          Led::setValue(ledPin1, true, 1, 0);
          Led::setValue(ledPin2, true, 2, 0);
          Led::setValue(ledPin3, true, 3, 0);
          Led::setValue(ledPin4, true, 4, 0);
      }

      void ResetBlinkAll(int ledPin1, int ledPin2, int ledPin3, int ledPin4) {
        Led::setValue(ledPin1, false, 1, 0);
          Led::setValue(ledPin2, false, 2, 0);
          Led::setValue(ledPin3, false, 3, 0);
          Led::setValue(ledPin4, false, 4, 0);
      }

      int getProgress() {
        return progress;
      }

      int getSize() {
        return sizeof(numberOrderArray)/sizeof(*numberOrderArray);
      }

      void Reset(LiquidCrystal_I2C lcd) {
        GenerateSequence();
        progress = 0;

        // show on the lcd that the game is running
        lcd.clear();
        lcd.print("Simon says spel");
      }
  };
#endif
