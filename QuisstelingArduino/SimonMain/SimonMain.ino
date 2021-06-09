#include "GameManager.hpp"

int ledPin1 = 15;
int ledPin2 = 2;
int ledPin3 = 4;
int ledPin4 = 5;

int buttonPin1 = 18;
int buttonPin2 = 19;
int buttonPin3 = 21;
int buttonPin4 = 22;

int buzzerPin = 23;

// Location id in the theme park and max possible questions at the location, used for generating quiz codes.
// In actual implementation linked through networking
int deviceLocationId = 00;
int maxPossibleQuestions = 6;

GameManager gameManager;

void setup() {
  Serial.begin(115200);
  Serial.println("Starting");

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
  gameManager.RunGame(ledPin1, ledPin2, ledPin3, ledPin4, buttonPin1, buttonPin2, buttonPin3, buttonPin4, deviceLocationId, maxPossibleQuestions);
  delay(100);
}
