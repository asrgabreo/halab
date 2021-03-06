import 'package:flutter/material.dart';
import 'package:quiz/question.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return const QuizPage();
  }
}

class QuizPage extends StatefulWidget {
  const QuizPage({Key? key}) : super(key: key);
  @override
  _QuizPageState createState() => _QuizPageState();
}

class _QuizPageState extends State<QuizPage> {
  List<Question> questions = [
    Question(questionText: "Question 1", answer: true),
    Question(questionText: "Question 2", answer: false),
    Question(questionText: "Question 3", answer: true),
    Question(questionText: "Question 4", answer: false),
    Question(questionText: "Question 5", answer: true),
  ];

  int _currScore = 0;
  int _qNum = 0;

  void checkAnswer(bool answer, bool choice) {
    if (answer == choice) {
      setState(() {
        _currScore++;
      });
    }
    setState(() {
      _qNum++;
    });
  }

  void reset() {
    setState(() {
      _currScore = 0;
      _qNum = 0;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Quiz App'),
          centerTitle: true,
        ),
        body: Center(
          child: _qNum < questions.length
              ? Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Text(
                      'Current Score : $_currScore',
                      style: const TextStyle(
                          fontSize: 20.0, fontWeight: FontWeight.bold),
                    ),
                    const SizedBox(height: 50.0),
                    Text(
                      questions[_qNum].questionText,
                      style: const TextStyle(
                          fontWeight: FontWeight.bold, fontSize: 18),
                    ),
                    const SizedBox(height: 50.0),
                    ElevatedButton(
                        onPressed: () {
                          checkAnswer(questions[_qNum].answer, true);
                        },
                        child: const Text('True')),
                    ElevatedButton(
                        onPressed: () {
                          checkAnswer(questions[_qNum].answer, false);
                        },
                        child: const Text('False')),
                    const SizedBox(height: 50.0),
                    Text('Score: $_currScore')
                  ],
                )
              : Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Text('Your final score is $_currScore !'),
                    ElevatedButton(
                        onPressed: reset, child: const Text('Play again'))
                  ],
                ),
        ),
      ),
    );
  }
}
