#include <Arduino.h>
#include "Buzzer.cpp"
#include "GameManager.hpp"
#include "pitches.h"
#include "Led.cpp"
#include <sstream>

 GameManager::GameManager() {
 }

  void GameManager::Start(int ledPin1, int ledPin2, int ledPin3, int ledPin4) {
    sequence.StartSequence(ledPin1, ledPin2, ledPin3, ledPin4);
  }
 
 void GameManager::RunGame(int ledPin1, int ledPin2, int ledPin3, int ledPin4, int buttonPin1, int buttonPin2, int buttonPin3, int buttonPin4, int deviceLocationId, int maxPossibleQuestions, LiquidCrystal_I2C lcd) {
   CheckPlayerInput(ledPin1, ledPin2, ledPin3, ledPin4, buttonPin1, buttonPin2, buttonPin3, buttonPin4, deviceLocationId, maxPossibleQuestions, lcd);
   ShowProgress(lcd);
 }

 void GameManager::CheckPlayerInput(int ledPin1, int ledPin2, int ledPin3, int ledPin4, int buttonPin1, int buttonPin2, int buttonPin3, int buttonPin4, int deviceLocationId, int maxPossibleQuestions, LiquidCrystal_I2C lcd) {
    // first check if the input is already equal to the sequence
    if (sequence.isInputEqualToSequence(playerInput)) {
      // buzz to say the user did something right
      Buzzer::buzzPositive();
      
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
        ShowQuizCode(deviceLocationId, maxPossibleQuestions, lcd);
        
        // reset the sequence and player input
        sequence.Reset(lcd);
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
        // buzz to say the user entered a wrong sequence
        Buzzer::buzzError();
        
        Serial.print("Resetting player progress at progress: ");
        Serial.print(playerProgress);
        Serial.print(" and sequence progress: ");
        Serial.println(sequence.getProgress());
        ClearPlayerInput();
      }

      // check if any button is pressed
      if(digitalRead(buttonPin1) == HIGH){
        PressedButton(1, ledPin1);       
      } else {
          Led::setValue(ledPin1, false, 1, 0);
      }

      if(digitalRead(buttonPin2) == HIGH){
        PressedButton(2, ledPin2);        
      } else {
        Led::setValue(ledPin2, false, 2, 0);
      }

      if(digitalRead(buttonPin3) == HIGH){
        PressedButton(3, ledPin3);        
      } else {
        Led::setValue(ledPin3, false, 3, 0);
      }

      if(digitalRead(buttonPin4) == HIGH){
        PressedButton(4, ledPin4);    
      } else {
        Led::setValue(ledPin4, false, 4, 0);
      }      
    }
 }

void GameManager::PressedButton(int button, int ledPin) {
  Serial.print("Pressed button ");
  Serial.println(button);
  playerInput[playerProgress] = button - 1;
  playerProgress++;
  Led::setValue(ledPin, true, button, 1000);
}

 String GameManager::GenerateQuizCode(int deviceLocationId, int maxPossibleQuestions) {
  // get a random code between 0 and (maxPossibleQuestions - 1)
  int code = random(maxPossibleQuestions);

  String s = "";
  if (deviceLocationId < 10) s = s + "0";
  s = s + deviceLocationId;
  if (code < 10) s = s + "0";
  s = s + code;
  
  return s;
 }

 void GameManager::ShowQuizCode(int deviceLocationId, int maxPossibleQuestions, LiquidCrystal_I2C lcd) {
  // generate the code
    String quizCode = GenerateQuizCode(deviceLocationId, maxPossibleQuestions);
    Serial.print("Generated code: ");
    Serial.println(quizCode);
    // show the code
    lcd.clear();
    lcd.print("Quiz code:");
    lcd.print(quizCode);
    Serial.println("Showing quiz code and locking the minigame");
    delay(10000);
    lcd.clear();
    Serial.println("Done showing the quiz code");
 }

 void GameManager::ClearPlayerInput() {
  for (int i = 0; i < 5; i++) {
    playerInput[i] = -1;
  }
  playerProgress = 0;
 }

  // show user progress on the lcd
 void GameManager::ShowProgress(LiquidCrystal_I2C lcd) {
  Serial.println("Showing on lcd player progress");
  // show progress
  lcd.setCursor(0,1);
  lcd.print(playerProgress);
  lcd.print("/");
  lcd.print(sequence.getProgress());
  // show how many sequences are necessary
  lcd.print(" (van de ");
  lcd.print(sequence.getSize());
  lcd.print(")");
  lcd.setCursor(0,0);
 }
