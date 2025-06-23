import os
import sys
from datetime import datetime
import pytz
from pytz import timezone
from utils import OpenAIClient, GitOperations, FileOperations, validate_environment, print_error_and_exit
from config import Config

def main(api_key):
    """Generate comprehensive task description with exercises."""
    validate_environment()
    
    # Initialize the OpenAI client
    client = OpenAIClient()

    # Extract theme and language from environment variables
    theme = os.getenv("TASK_THEME", "Create a basic Java application with the following requirements.")
    language = os.getenv("TASK_LANGUAGE", "English")

    # Function to make API calls with configurable token limit
    def generate_task_step(system_message, user_message, max_tokens=None):
        """Generate task step using OpenAI API."""
        try:
            return client.create_response(
                prompt=user_message,
                task_name='generate_task_description',
                system_message=system_message,
                max_tokens=max_tokens or Config.TASK_SETTINGS['default_tokens']
            )
        except Exception as e:
            print_error_and_exit(f"Error generating task step: {str(e)}")

    # Step 1: Generate Task Theme Description

    language = "English"
    learning_goals = """
    * Using Data from Files to Instantiate Objects
    * Designing Classes
    * Programming Creatively

        {
            "main_point": "Using Data from Files to Instantiate Objects",
            "sub_points": [
                "Description: This concept introduces the process of reading data from files and using it to create and initialize objects in Java. Mastery of this skill is essential for applications that require dynamic data handling and object creation based on external data sources.",
                "Subpoint 1: Understanding file formats and parsing techniques for data extraction.",
                "Subpoint 2: Using file I/O classes to read data and convert it into object attributes.",
                "Subpoint 3: Handling exceptions and ensuring data integrity during object instantiation."
            ]
        },
        {
            "main_point": "Designing Classes",
            "sub_points": [
                "Description: This concept focuses on the principles and practices of designing well-structured Java classes, which are fundamental to object-oriented programming. Effective class design is crucial for creating maintainable and scalable software systems.",
                "Subpoint 1: Identifying class responsibilities and defining clear interfaces.",
                "Subpoint 2: Implementing cohesive class structures with appropriate fields and methods.",
                "Subpoint 3: Leveraging design patterns to solve common problems and enhance class design."
            ]
        },
        {
            "main_point": "Programming Creatively",
            "sub_points": [
                "Description: This concept encourages students to apply creative thinking and problem-solving techniques in their programming projects. Creative programming is vital for developing innovative solutions and enhancing the functionality and user experience of software applications.",
                "Subpoint 1: Exploring different approaches to problem-solving and algorithm design.",
                "Subpoint 2: Encouraging experimentation and iteration to refine solutions.",
                "Subpoint 3: Integrating user feedback and testing to improve program effectiveness and usability."
            ]
        }
    """

    system_message = (
        "You are an experienced programming instructor creating detailed tasks for university-level students. "
        "The tasks should be challenging, pedagogically valuable, and include detailed descriptions."
        "The students are first year computer science students with basic programming capabilities."
        f"The goal of the course is to introduce and help them learn key programming concepts based on the weekly learning goals:\n{learning_goals}\n."
        "The tasks should be solvable within one week time frame."
        "The code snippets provided should only provide a scaffolding and not reveal any answer or solutions to the students."
    )
    user_message = (
        f"Create a high-level programming task description in {language} with the following theme:\n\n"
        f"**Theme**: {theme}\n\n"
        f"The task must include and integrate the following learning goals:\n{learning_goals}\n\n"
        "The task description should be engaging, detailed, and structured to provide a foundation for exercises."
        "Include relevant emojies beside the title of each exercise."
        "The task description should include proper scaffolding with small code snippets to help students stay on track and not be confused."
        "The first two exercises are supposed to be more on the theoretical side with the concepts in Exercise 2 flowing to exercise 3 which is more about programming."
        "Exercise 3 and 4 are more programming based but are focused on more basic programming contexts"
        "Exercise 5 and 6 are challenging but feasible programmin exercises as part of a weekly task for a CS1 student."
        
    )

    task_description = generate_task_step(system_message, user_message)
    print(task_description)

    # Step 2: Generate Exercises 1 & 2
    user_message = (
        f"The following is the task description so far:\n\n{task_description}\n\n"
        "Based on this, create the first two exercises:\n"
        "- **Exercises 1 & 2**: Focus on theoretical aspects of the learning goals. Challenge students' understanding through conceptual questions about CS concepts involved in the task without requiring significant coding."
        "These exercises should introduce the overall task and prepare students for subsequent coding activities, as noted they are only the 1&2 exercises out of 6"
        "Hold these exercises short and concise each of them to ca 300 - 400 words max."
    )

    exercises_1_2 = generate_task_step(system_message, user_message, Config.TASK_SETTINGS['exercises_1_2_tokens'])
    print(exercises_1_2)

    # Step 3: Generate Exercises 3 & 4
    user_message = (
        f"The following is the task description and Exercises 1 & 2:\n\n{task_description}\n\n{exercises_1_2}\n\n"
        "Based on this, create Exercises 3 & 4:\n"
        "- **Exercises 3 & 4**: Focus on combining and integrating the concepts into coding. Require students to write code that applies the concepts in practical scenarios."
        "These exercises should transition seamlessly from the theoretical groundwork laid in Exercises 1 & 2."
        "The first two exercises focus on the theoretical aspects of the task and the subsequent ones (5&6) will be more pure coding, so these exercises (3&4) need to act as a balancing act and transition between the two. "
        "Take into consideration that the exercises are to be completed in a one week time span by first year computer science students."
    )

    exercises_3_4 = generate_task_step(system_message, user_message)
    print(exercises_3_4)

    # Step 4: Generate Exercises 5 & 6
    user_message = (
        f"The following is the task description and Exercises 1-4:\n\n{task_description}\n\n{exercises_1_2}\n\n{exercises_3_4}\n\n"
        "Based on this, create Exercises 5 & 6:\n"
        "- **Exercises 5 & 6**: These are challenging coding tasks that require significant learning and coding effort to complete. Build these step-by-step tasks upon previous exercises."
        "Ensure that these exercises finalize the learning journey and consolidate all previous steps into a cohesive outcome."
        "Take into consideration that the exercises are to be completed in a one week time span by first year computer science students."
        "The exercises should provide a good scaffolding for the students so that they can stay on course and not be confused by the objectives of the exercise."
        "The final exercise, exercise 6, should be slightly more challenging and only very slightly expand the scope of CS1 knowledge. "
    )

    exercises_5_6 = generate_task_step(system_message, user_message)
    print(exercises_5_6)

    # Combine all generated content
    complete_task = f"{task_description}\n\n{exercises_1_2}\n\n{exercises_3_4}\n\n{exercises_5_6}"

    # Create a new branch with a unique name
    stockholm_tz = timezone('Europe/Stockholm')
    branch_name = f"task-{datetime.now(stockholm_tz).strftime('%Y%m%d%H%M')}"
    
    try:
        GitOperations.create_branch(branch_name)
        
        # Write the response content to a markdown file
        task_file_path = os.path.join(Config.get_path('tasks_dir'), "new_task.md")
        FileOperations.write_file(task_file_path, complete_task)
        
        # Commit and push changes
        GitOperations.commit_and_push(
            branch_name, 
            task_file_path, 
            f"Add new task description: {branch_name}"
        )
    except Exception as e:
        print_error_and_exit(f"Error creating branch and committing changes: {str(e)}")

    # Output the branch name for the next job
    print(f"::set-output name=branch_name::{branch_name}")


if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python generate_task_description.py <api_key>")
        sys.exit(1)

    api_key = sys.argv[1]
    main(api_key)