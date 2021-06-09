#include <Arduino.h>
#include "GameManager.hpp"
#include <sstream>

 GameManager::GameManager() {
 }

  void GameManager::Start(int ledPin1, int ledPin2, int ledPin3, int ledPin4) {
    sequence.StartSequence(ledPin1, ledPin2, ledPin3, ledPin4);
  }
 
 void GameManager::RunGame(int ledPin1, int ledPin2, int ledPin3, int ledPin4, int buttonPin1, int buttonPin2, int buttonPin3, int buttonPin4, int deviceLocationId, int maxPossibleQuestions) {
   CheckPlayerInput(ledPin1, ledPin2, ledPin3, ledPin4, buttonPin1, buttonPin2, buttonPin3, buttonPin4, deviceLocationId, maxPossibleQuestions);
 }

 void GameManager::CheckPlayerInput(int ledPin1, int ledPin2, int ledPin3, int ledPin4, int buttonPin1, int buttonPin2, int buttonPin3, int buttonPin4, int deviceLocationId, int maxPossibleQuestions) {
    // first check if the input is already equal to the sequence
    if (sequence.isInputEqualToSequence(playerInput)) {
      // clear the player input, make the leds all blink and set a delay in before the program can resume
      Serial.println("Player input matched the sequence");
      ClearPlayerInput();
      // make all the leds blink simultaneously to indicate correct input
      sequence.ResetBlinkAll(ledPin1, ledPin2, ledPin3, ledPin4);
      delay(500);
      sequence.BlinkAll(ledPin1, ledPin2, ledPin3, ledPin4);
      delay(500);
      sequence.ResetBlinkAll(ledPin1, ledPin2, ledPin3, ledPin4);
      delay(500);

      // then check if this correct sequence was the final one
      if (sequence.getProgress() == sequence.getSize()) {
        // Show the quiz code
        ShowQuizCode(deviceLocationId, maxPossibleQuestions);
        
        // reset the sequence and player input
        sequence.Reset();
        ClearPlayerInput();

        // start the new sequence
        sequence.StartSequence(ledPin1, ledPin2, ledPin3, ledPin4);
      } else {
        // if the sequence isn't complete yet, start it again with an increased length
        sequence.StartSequence(ledPin1, ledPin2, ledPin3, ledPin4);
      }
      
    } else {
      // if the sequence isn't equal then read new inputs
      if (playerProgress >= sequence.getProgress()) {
        //TODO this is wrong input detection, give user feedback here
        Serial.print("Resetting player progress at progress: ");
        Serial.print(playerProgress);
        Serial.print(" and sequence progress: ");
        Serial.println(sequence.getProgress());
        ClearPlayerInput();
      }

      if(digitalRead(buttonPin1) == HIGH){
        PressedButton(1, ledPin1);
        
      } else {
        digitalWrite(ledPin1, LOW);
      }

      if(digitalRead(buttonPin2) == HIGH){
        PressedButton(2, ledPin2);
        
      } else {
        digitalWrite(ledPin2, LOW);
      }

      if(digitalRead(buttonPin3) == HIGH){
        PressedButton(3, ledPin3);
        
      } else {
        digitalWrite(ledPin3, LOW);
      }

      if(digitalRead(buttonPin4) == HIGH){
        PressedButton(4, ledPin4);
        
      } else {
        digitalWrite(ledPin4, LOW);
      }      
    }
 }

void GameManager::PressedButton(int button, int ledPin) {
        Serial.print("Pressed button ");
        Serial.println(button);
        digitalWrite(ledPin, HIGH);
        playerInput[playerProgress] = button - 1;
        playerProgress++;
        delay(1000);
}

 String GameManager::GenerateQuizCode(int deviceLocationId, int maxPossibleQuestions) {
  // get a random code between 0 and (maxPossibleQuestions - 1)
  int code = rand() % maxPossibleQuestions;

  String s = "";
  if (deviceLocationId < 10) s = s + "0";
  s = s + deviceLocationId;
  if (code < 10) s = s + "0";
  s = s + code;
  
  return s;
 }

 void GameManager::ShowQuizCode(int deviceLocationId, int maxPossibleQuestions) {
  // generate the code
    String quizCode = GenerateQuizCode(deviceLocationId, maxPossibleQuestions);
    Serial.print("Generated code: ");
    Serial.println(quizCode);
    // show the code
    Serial.println("Showing quiz code and locking the minigame");
    delay(10000);
    Serial.println("Done showing the quiz code");
 }

 void GameManager::ClearPlayerInput() {
  // TODO if cleared because wrong make an error sound with the buzzer
  for (int i = 0; i < 5; i++) {
    playerInput[i] = -1;
  }
  playerProgress = 0;
 }
