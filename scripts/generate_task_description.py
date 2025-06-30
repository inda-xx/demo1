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
        "The tasks should be challenging, pedagogically valuable, and include detailed descriptions. "
        "The students are first year computer science students with basic programming capabilities. "
        f"The goal of the course is to introduce and help them learn key programming concepts based on the weekly learning goals:\n{learning_goals}\n. "
        "The tasks should be solvable within one week time frame. "
        "The code snippets provided should only provide scaffolding and not reveal any answers or solutions to the students. "
        "Format your response with clear sections using markdown headers and include relevant emojis for visual appeal."
    )
    user_message = (
        f"Create a comprehensive programming task description in {language} with the following theme:\n\n"
        f"**Theme**: {theme}\n\n"
        f"The task must include and integrate the following learning goals:\n{learning_goals}\n\n"
        "Structure your task description with these sections:\n"
        "1. **🎯 Task Overview**: A compelling 2-3 paragraph introduction that sets the context and excites students about the project\n"
        "2. **📚 Background/Story**: Create an engaging narrative or real-world scenario that motivates the task\n"
        "3. **🔧 Technical Requirements**: High-level overview of what students will build (save specifics for exercises)\n"
        "4. **📋 Prerequisites**: What students should know before starting\n"
        "5. **🗓️ Timeline**: Suggested time allocation for different parts\n\n"
        "Make the task relatable to students' experiences or current technology trends. "
        "Use a conversational but professional tone. "
        "Include 2-3 'fun facts' or 'did you know' boxes related to the concepts."
        
    )

    task_description = generate_task_step(system_message, user_message)
    print(task_description)

    # Step 2: Generate Exercises 1 & 2
    user_message = (
        f"The following is the task description so far:\n\n{task_description}\n\n"
        "Create Exercises 1 & 2 that focus on theoretical understanding and conceptual preparation:\n\n"
        "**Exercise 1** (📖 Conceptual Understanding - 300-400 words):\n"
        "- Start with a thought-provoking question or scenario\n"
        "- Include 3-4 sub-questions that build understanding progressively\n"
        "- Add a 'Think About It' box with reflection questions\n"
        "- Provide hints in collapsible sections using <details> tags\n"
        "- Include references to real-world applications\n\n"
        "**Exercise 2** (🔍 Design & Analysis - 300-400 words):\n"
        "- Present a design challenge or analysis task\n"
        "- Include visual elements (suggest ASCII diagrams or flowcharts)\n"
        "- Provide a partially completed example for guidance\n"
        "- Add 'Common Pitfalls' section to help students avoid mistakes\n"
        "- Bridge to the coding exercises by having students sketch pseudocode or class diagrams\n\n"
        "Use engaging titles with emojis for each exercise."
    )

    exercises_1_2 = generate_task_step(system_message, user_message, Config.TASK_SETTINGS['exercises_1_2_tokens'])
    print(exercises_1_2)

    # Step 3: Generate Exercises 3 & 4
    user_message = (
    f"The following is the task description and Exercises 1 & 2:\n\n{task_description}\n\n{exercises_1_2}\n\n"
    "Create Exercises 3 & 4 that transition from theory to practice:\n\n"
    "**Exercise 3** (🏗️ Building Foundations):\n"
    "- Title should indicate the core implementation task\n"
    "- Provide starter code with TODO comments\n"
    "- Include 3-4 incremental implementation steps\n"
    "- Add test cases students can use to verify their work\n"
    "- Include a 'Code Quality Checklist' with 4-5 items\n"
    "- Provide debugging tips in a dedicated section\n\n"
    "**Exercise 4** (🔄 Integration & Enhancement):\n"
    "- Build directly on Exercise 3's code\n"
    "- Introduce 1-2 new concepts or techniques\n"
    "- Include performance or design considerations\n"
    "- Provide multiple solution approaches with trade-offs\n"
    "- Add 'Extension Ideas' for ambitious students\n\n"
    "Each exercise should include:\n"
    "- Clear success criteria\n"
    "- Expected output examples\n"
    "- Time estimate (e.g., '⏱️ Estimated time: 45-60 minutes')"
    )

    exercises_3_4 = generate_task_step(system_message, user_message)
    print(exercises_3_4)

    # Step 4: Generate Exercises 5 & 6
    user_message = (
    f"The following is the task description and Exercises 1-4:\n\n{task_description}\n\n{exercises_1_2}\n\n{exercises_3_4}\n\n"
    "Create Exercises 5 & 6 as challenging culmination tasks:\n\n"
    "**Exercise 5** (🚀 Advanced Implementation):\n"
    "- Present a significant feature addition or system enhancement\n"
    "- Break down into 4-5 manageable sub-tasks\n"
    "- Provide architectural guidance without giving away the solution\n"
    "- Include edge cases students must handle\n"
    "- Add a 'Design Decisions' section where students document their choices\n"
    "- Incorporate file I/O or external data interaction\n\n"
    "**Exercise 6** (🌟 Creative Extension - Slightly beyond CS1):\n"
    "- Offer 2-3 different paths students can choose\n"
    "- Include optional bonus challenges marked with '🏆 Bonus'\n"
    "- Require students to write documentation (Javadoc example)\n"
    "- Include a reflection component on what they learned\n"
    "- Provide evaluation criteria or rubric outline\n\n"
    "End with:\n"
    "- ✅ Comprehensive checklist (5-10 items)\n"
    )

    exercises_5_6 = generate_task_step(system_message, user_message)
    print(exercises_5_6)

    # Combine all generated content
    complete_task = f"{task_description}\n\n{exercises_1_2}\n\n{exercises_3_4}\n\n{exercises_5_6}"
    
    # Step 5: Apply overall structure template to complete task
    user_message = (
        f"Take the following complete task content and restructure it using the standardized template:\n\n"
        f"{complete_task}\n\n"
        "Apply this overall structure template to organize all the content:\n\n"
        "# Task Title with Emoji\n\n"
        "## 📋 Overview\n"
        "[Use the existing task overview/introduction content]\n\n"
        "## 🎯 Learning Objectives\n"
        "- Objective 1\n"
        "- Objective 2\n"
        "- Objective 3\n\n"
        "## 📚 Prerequisites\n"
        "- Prior knowledge needed\n"
        "- Tools required\n\n"
        "## 🚀 Getting Started\n"
        "[Setup instructions from existing content]\n\n"
        "## Exercises\n\n"
        "### Exercise X: Title with Emoji\n"
        "**⏱️ Estimated Time:** XX minutes\n\n"
        "**🎯 Goal:** [What students will accomplish]\n\n"
        "**📝 Instructions:**\n"
        "[Step-by-step guide]\n\n"
        "**💡 Hints:**\n"
        "<details>\n"
        "<summary>Click for hint</summary>\n"
        "[Helpful hint]\n"
        "</details>\n\n"
        "**✅ Success Criteria:**\n"
        "- [ ] Criterion 1\n"
        "- [ ] Criterion 2\n\n"
        "[Repeat for all 6 exercises]\n\n"
        "## 🤔 Reflection Questions\n"
        "[Add reflection questions to deepen understanding]\n\n"
        "Preserve all the existing content but organize it according to this structure. "
        "Ensure each exercise follows the standardized format with estimated time, goal, instructions, hints, and success criteria."
    )
    
    final_structured_task = generate_task_step(system_message, user_message)
    complete_task = final_structured_task

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