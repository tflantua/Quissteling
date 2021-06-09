// header for GameManager 
#include "Sequence.cpp"
#include "Led.cpp"
#ifndef GAMEMANAGER_HPP
#define GAMEMANAGER_HPP
class GameManager {
  private:
    Led leds[4];
    // Button[] buttons;
    int quizCode;
    int playerInput[];
    Sequence sequence;
    void CheckPlayerInput();
  public:
    void GenerateQuizCode();
    void ShowQuizCode();
    void RunGame();
    GameManager(int ledPin1, int ledPin2, int ledPin3, int ledPin4);
};
#endif
