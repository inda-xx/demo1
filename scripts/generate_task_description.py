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

    # Define learning goals with more structure
    learning_goals = """
    1. Using Data from Files to Instantiate Objects
       - Reading and parsing file formats (CSV, JSON, text files)
       - Converting file data into object attributes
       - Handling file I/O exceptions and data validation
       - Dynamic object creation based on external data

    2. Designing Classes
       - Identifying class responsibilities and relationships
       - Implementing encapsulation with appropriate access modifiers
       - Creating cohesive class structures with clear interfaces
       - Applying basic design patterns and principles

    3. Programming Creatively
       - Exploring multiple solution approaches to problems
       - Implementing user-friendly interfaces and error handling
       - Adding engaging features that enhance functionality
       - Iterating and improving solutions based on testing
    """

    # Enhanced system message for better pedagogical content
    system_message = (
        "You are an expert CS1 instructor with deep knowledge of pedagogical best practices. "
        "You excel at creating engaging, scaffolded programming tasks that build student confidence while challenging them appropriately. "
        "Your tasks should:\n"
        "- Use real-world, relatable scenarios that motivate students\n"
        "- Provide clear, incremental progression from basic to advanced concepts\n"
        "- Include practical code examples that guide without giving away solutions\n"
        "- Incorporate interactive elements that keep students engaged\n"
        "- Balance theoretical understanding with hands-on programming practice\n\n"
        f"Target audience: First-year computer science students with basic Java knowledge\n"
        f"Time constraint: One week completion time\n"
        f"Learning objectives: {learning_goals}\n\n"
        "Focus on creating tasks that students will find meaningful and enjoyable to complete."
    )

    # Step 1: Generate enhanced task theme description
    user_message = (
        f"Create an engaging programming task description in {language} based on this theme:\n\n"
        f"**Theme**: {theme}\n\n"
        "The task description should:\n"
        "1. **Hook the reader** with an interesting scenario or story that makes the programming meaningful\n"
        "2. **Clearly connect** the learning goals to practical, real-world applications\n"
        "3. **Provide context** about why these skills matter in software development\n"
        "4. **Include a brief overview** of what students will build by the end\n"
        "5. **Set appropriate expectations** for difficulty and time investment\n\n"
        
        "Structure the response with:\n"
        "- An engaging title with relevant emoji\n"
        "- A compelling opening that establishes the scenario\n"
        "- Clear learning objectives that connect to the theme\n"
        "- A brief roadmap of what students will accomplish\n"
        "- Practical applications or extensions they might explore\n\n"
        
        "Make it sound exciting and achievable, not intimidating. Use concrete examples where possible."
    )
    task_description = generate_task_step(system_message, user_message)
    print(task_description)

    # Step 2: Generate Exercises 1 & 2
    user_message = (
        f"Continuing from the previous exercises:\n\n{task_description}\n\n{exercises_1_2}\n\n"
        "Create Exercises 3 & 4 that **bridge theory to practice**:\n\n"
        
        "**Exercise 3** should:\n"
        "- Implement basic file reading with simple object creation\n"
        "- Provide starter code that demonstrates the pattern\n"
        "- Include step-by-step instructions for key parts\n"
        "- Focus on ONE main concept (file I/O OR object creation)\n"
        "- Include error handling basics and validation\n\n"
        
        "**Exercise 4** should:\n"
        "- Combine file reading with more complex object relationships\n"
        "- Introduce class design implementation\n"
        "- Build directly on Exercise 3's foundation\n"
        "- Include debugging or testing components\n"
        "- Provide scaffolding code with clear TODOs\n\n"
        
        "For both exercises:\n"
        "- Include relevant emoji in titles\n"
        "- Provide code templates with helpful comments\n"
        "- Offer specific hints for common pitfalls\n"
        "- Connect each step back to the learning goals\n"
        "- Make the progression feel natural and achievable\n\n"
        
        "Ensure students can complete these exercises and feel confident moving to the final challenges."
    )

    exercises_1_2 = generate_task_step(system_message, user_message, Config.TASK_SETTINGS['exercises_1_2_tokens'])
    print(exercises_1_2)

    # Step 3: Generate Exercises 3 & 4
    user_message = (
        f"Building on all previous work:\n\n{task_description}\n\n{exercises_1_2}\n\n{exercises_3_4}\n\n"
        "Create the culminating Exercises 5 & 6 that **integrate and extend** all concepts:\n\n"
        
        "**Exercise 5** should:\n"
        "- Integrate file I/O, object creation, and class design into a cohesive solution\n"
        "- Require creative problem-solving while staying within CS1 scope\n"
        "- Include multiple interacting classes and data sources\n"
        "- Provide clear requirements but allow for creative implementation\n"
        "- Include comprehensive testing or validation requirements\n\n"
        
        "**Exercise 6** should:\n"
        "- Be the most challenging, requiring synthesis of all previous work\n"
        "- Introduce ONE slightly advanced concept (e.g., collections, inheritance basics)\n"
        "- Encourage creative extensions or personalization\n"
        "- Include options for different difficulty levels or approaches\n"
        "- Culminate in a complete, working application\n\n"
        
        "For both exercises:\n"
        "- Include relevant emojis in titles\n"
        "- Provide sufficient scaffolding without over-constraining creativity\n"
        "- Include clear success criteria and testing guidelines\n"
        "- Offer extension challenges for advanced students\n"
        "- Connect back to real-world applications\n"
        "- Include reflection questions about the learning process\n\n"
        
        "Make these exercises challenging but achievable, with a clear sense of accomplishment upon completion."
    )

    exercises_3_4 = generate_task_step(system_message, user_message)
    print(exercises_3_4)

    # Step 4: Generate Exercises 5 & 6
    user_message = (
        f"Building on all previous work:\n\n{task_description}\n\n{exercises_1_2}\n\n{exercises_3_4}\n\n"
        "Create the culminating Exercises 5 & 6 that **integrate and extend** all concepts:\n\n"
        
        "**Exercise 5** should:\n"
        "- Integrate file I/O, object creation, and class design into a cohesive solution\n"
        "- Require creative problem-solving while staying within CS1 scope\n"
        "- Include multiple interacting classes and data sources\n"
        "- Provide clear requirements but allow for creative implementation\n"
        "- Include comprehensive testing or validation requirements\n\n"
        
        "**Exercise 6** should:\n"
        "- Be the most challenging, requiring synthesis of all previous work\n"
        "- Introduce ONE slightly advanced concept (e.g., collections, inheritance basics)\n"
        "- Encourage creative extensions or personalization\n"
        "- Include options for different difficulty levels or approaches\n"
        "- Culminate in a complete, working application\n\n"
        
        "For both exercises:\n"
        "- Include relevant emojis in titles\n"
        "- Provide sufficient scaffolding without over-constraining creativity\n"
        "- Include clear success criteria and testing guidelines\n"
        "- Offer extension challenges for advanced students\n"
        "- Connect back to real-world applications\n"
        "- Include reflection questions about the learning process\n\n"
        
        "Make these exercises challenging but achievable, with a clear sense of accomplishment upon completion."
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