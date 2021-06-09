#include "GameManager.hpp"
#include "Sequence.cpp"
#include "Led.cpp"


 GameManager::GameManager(int ledPin1, int ledPin2, int ledPin3, int ledPin4) {
    Sequence sequence();
    leds[0] = Led(ledPin1);
    leds[1] = Led(ledPin2);
    leds[2] = Led(ledPin3);
    leds[3] = Led(ledPin4);
 }
 void GameManager::RunGame() {
   sequence.StartSequence(leds);
 }

 void CheckPlayerInput() {

 }

 void GenerateQuizCode() {

 }

 void ShowQuizCode() {

 }
