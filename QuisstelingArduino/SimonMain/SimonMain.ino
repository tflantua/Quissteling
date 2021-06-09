#include "GameManager.hpp"
#include "Led.cpp"

int ledPin1 = 15;
int ledPin2 = 2;
int ledPin3 = 4;
int ledPin4 = 5;

int buttonPin1 = 18;
int buttonPin2 = 19;
int buttonPin3 = 21;
int buttonPin4 = 22;

int buzzerPin = 23;

GameManager gameManager(ledPin1, ledPin2, ledPin3, ledPin4);

void setup() {
  // put your setup code here, to run once:
//  Led led1(ledPin1);
//  Led led2(ledPin2);
//  Led led3(ledPin3);
//  Led led4(ledPin4);

//  Led led[4]{led1, led2, led3, led4};
//  Gamemanager gameManager;
}

void loop() {
  // put your main code here, to run repeatedly:
  gameManager.RunGame();
}
