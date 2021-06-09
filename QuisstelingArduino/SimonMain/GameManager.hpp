// header for GameManager 
#include "Sequence.cpp"
#ifndef GAMEMANAGER_HPP
#define GAMEMANAGER_HPP
class GameManager {
  private:
//    Led leds[4];
//    Button buttons[4];
int ledPin1;
int ledPin2;
int ledPin3;
int ledPin4;

int buttonPin1;
int buttonPin2;
int buttonPin3;
int buttonPin4;
    int quizCode;
    int playerProgress;
    int playerInput[5];
    Sequence sequence;
    void CheckPlayerInput(int ledPin1, int ledPin2, int ledPin3, int ledPin4, int buttonPin1, int buttonPin2, int buttonPin3, int buttonPin4, int deviceLocationId, int maxPossibleQuestions);
    void ClearPlayerInput();
    void PressedButton(int button, int ledPin);
  public:
    void Start(int ledPin1, int ledPin2, int ledPin3, int ledPin4);
    String GenerateQuizCode(int deviceLocationId, int maxPossibleQuestions);
    void ShowQuizCode(int deviceLocationId, int maxPossibleQuestions);
    void RunGame(int ledPin1, int ledPin2, int ledPin3, int ledPin4, int buttonPin1, int buttonPin2, int buttonPin3, int buttonPin4, int deviceLocationId, int maxPossibleQuestions);
    GameManager();
};
#endif
