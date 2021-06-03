#include "GameManager.hpp"
#include "Sequence.cpp"
#include "Led.cpp"


 GameManager::GameManager(Led ledArray[]) {
    Sequence sequence();
//    for (int i = 0; i < sizeof(ledArray); i++) {
//      leds[i] = ledArray[i];
//    }
    leds[0] = Led(15);
    leds[1] = Led(2);
    leds[2] = Led(4);
    leds[3] = Led(5);
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
