#include <Arduino.h>
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
            
            if (numberOrderArray[i] == 0) {
              digitalWrite(ledPin1, HIGH);
              Serial.println(", showed led 1");           
            }
            if (numberOrderArray[i] == 1) {
              digitalWrite(ledPin2, HIGH);
              Serial.println(", showed led 2");                 
            }
            if (numberOrderArray[i] == 2) {
              digitalWrite(ledPin3, HIGH);  
              Serial.println(", showed led 3");                
            }
            if (numberOrderArray[i] == 3) {
              digitalWrite(ledPin4, HIGH);     
              Serial.println(", showed led 4");             
            }

            // give a small delay before turning the leds off and starting the new led
            delay(1000);
            ResetBlinkAll(ledPin1, ledPin2, ledPin3, ledPin4);
          }
          progress++;
          if (progress >= sizeof(numberOrderArray)) progress = 0;
      }

      boolean isInputEqualToSequence(int input[5]) {
        for (int i = 0; i < progress; i++) {
          if (input[i] != numberOrderArray[i]) return false;
        }
        return true;
      }

      void BlinkAll(int ledPin1, int ledPin2, int ledPin3, int ledPin4) {
        digitalWrite(ledPin1, HIGH);
        digitalWrite(ledPin2, HIGH);
        digitalWrite(ledPin3, HIGH);
        digitalWrite(ledPin4, HIGH);
      }

      void ResetBlinkAll(int ledPin1, int ledPin2, int ledPin3, int ledPin4) {
        digitalWrite(ledPin1, LOW);
        digitalWrite(ledPin2, LOW);
        digitalWrite(ledPin3, LOW);
        digitalWrite(ledPin4, LOW);
      }

      int getProgress() {
        return progress;
      }

      int getSize() {
        Serial.print("Returning sequence size: ");
        Serial.println(sizeof(numberOrderArray)/sizeof(*numberOrderArray));
        return sizeof(numberOrderArray)/sizeof(*numberOrderArray);
      }

      void Reset() {
        GenerateSequence();
        progress = 0;
      }
  };
#endif
