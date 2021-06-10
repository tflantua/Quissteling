#include "GameManager.hpp"
#include<LiquidCrystal_I2C.h>

int ledPin1 = 15;
int ledPin2 = 2;
int ledPin3 = 4;
int ledPin4 = 5;

int buttonPin1 = 18;
int buttonPin2 = 19;
int buttonPin3 = 35;
int buttonPin4 = 34;

int buzzerPin = 32;

// Location id in the theme park and max possible questions at the location, used for generating quiz codes.
// In actual implementation linked through networking
int deviceLocationId = 01;
int maxPossibleQuestions = 6;

// adress, columns, rows
LiquidCrystal_I2C lcd(0x27, 16, 2);  

GameManager gameManager;

void setup() {
  Serial.begin(115200);
  Serial.println("Starting");

  // buzzer
  ledcSetup(0, 2E4, 8);
  ledcAttachPin(buzzerPin, 0);

  // lcd
  lcd.init();
  lcd.backlight();
  // show on the lcd that the game is running
  lcd.clear();
  lcd.print("Simon says spel");

  pinMode(ledPin1, OUTPUT);
  pinMode(ledPin2, OUTPUT);
  pinMode(ledPin3, OUTPUT);
  pinMode(ledPin4, OUTPUT);
  pinMode(buttonPin1, INPUT);
  pinMode(buttonPin2, INPUT);
  pinMode(buttonPin3, INPUT);
  pinMode(buttonPin4, INPUT);

  gameManager.Start(ledPin1, ledPin2, ledPin3, ledPin4);
}

void loop() {
  // put your main code here, to run repeatedly:
  gameManager.RunGame(ledPin1, ledPin2, ledPin3, ledPin4, buttonPin1, buttonPin2, buttonPin3, buttonPin4, deviceLocationId, maxPossibleQuestions, lcd);
  delay(100);
}
