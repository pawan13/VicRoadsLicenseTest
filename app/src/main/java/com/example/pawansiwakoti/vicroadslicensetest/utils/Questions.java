package com.example.pawansiwakoti.vicroadslicensetest.utils;

public class Questions {
    public String mQuestions[] = {
            "One of the real challenges of driving is",
            "Good drivers can predict what may happen around them. How do they get this skill?",
            "What is hazard perception?",
            "Vehicles A and B have collided. The most likely cause of this collision is that",
            "When is fatigue likely to be a significant problem for you as a driver?",
            "You are driving vehicle A. How should you manage the driving risk in this situation?"

    };
    private String mChoices[] [] = {
            {
                "adjusting the radio as you drive.",
		        "keeping your tyres at the correct pressure.",
		        "driving safely on gravel roads."},

            {
                " They have a natural ability to drive well.",
		        "They have had lots of experience driving in different situations.",
	            "They only drive in the same areas."
            },

            {
                    "The ability to identify, assess and respond to potential risks safely",
                    "The ability to control your vehicle effectively in skids or spins",
                    "The ability to maintain your vehicle in good condition"
            },

            {
                    "the driver of vehicle A failed to give way to vehicle B",
                    "the driver of vehicle B was not concentrating and didn't see vehicle A",
                    "the driver of vehicle B was travelling too fast to enter the intersection safely"
            },

            {
                "During those times when you are normally asleep",
                "After you drink coffee",
                 "After you fail to take medication"
            },

            {
               "Slow down so that you can stop if the cyclist crosses the road.",
               "Continue because the cyclist must give way to you.",
                "Brake heavily to give way to the cyclist."
            }

    };
    private String mCorrectAnswers[] =
            {
                    "driving safely on gravel roads.",
              "They have had lots of experience driving in different situations.",
                    "The ability to identify, assess and respond to potential risks safely",
                    "the driver of vehicle A failed to give way to vehicle B",
                    "During those times when you are normally asleep",
                    "Slow down so that you can stop if the cyclist crosses the road."
            };
    public String getQuestion(int a) {
    String question = mQuestions[a];
    return question;
    }
    public String getChoice1(int a) {
        String Choice = mChoices[a][0];
        return Choice;
    }

    public String getChoice2(int a) {
        String Choice = mChoices[a][1];
        return Choice;
    }
    public String getChoice3(int a) {
        String Choice = mChoices[a][2];
        return Choice;
    }


    public String getmCorrectAnswers(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }
}
