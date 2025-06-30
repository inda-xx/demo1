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

    # Step 1: Generate overview + exercises 1-2 in one call with template structure
    user_message = (
        f"Create the first part of a comprehensive programming task in {language} with the following context:\n\n"
        f"**Theme**: {theme}\n\n"
        f"**Learning Goals**: {learning_goals}\n\n"
        "Generate the overview and first two exercises using this exact structure:\n\n"
        "# [Task Title with Emoji]\n\n"
        "## 📋 Overview\n"
        "[Engaging 2-3 paragraph introduction that sets context and excites students]\n\n"
        "## 🎯 Learning Objectives\n"
        "- [Objective 1 derived from learning goals]\n"
        "- [Objective 2 derived from learning goals]\n"
        "- [Objective 3 derived from learning goals]\n\n"
        "## 📚 Prerequisites\n"
        "- [Prior knowledge needed]\n"
        "- [Tools required]\n\n"
        "## 🚀 Getting Started\n"
        "[Setup instructions and initial guidance]\n\n"
        "## Exercises\n\n"
        "### Exercise 1: [Title with Emoji] (📖 Conceptual Understanding)\n"
        "**⏱️ Estimated Time:** 30-45 minutes\n\n"
        "**🎯 Goal:** [What students will accomplish in conceptual understanding]\n\n"
        "**📝 Instructions:**\n"
        "[3-4 sub-questions that build understanding progressively with thought-provoking scenarios]\n\n"
        "**💡 Hints:**\n"
        "<details>\n"
        "<summary>Click for hint</summary>\n"
        "[Helpful hint with real-world applications]\n"
        "</details>\n\n"
        "**✅ Success Criteria:**\n"
        "- [ ] [Specific criterion 1]\n"
        "- [ ] [Specific criterion 2]\n"
        "- [ ] [Specific criterion 3]\n\n"
        "### Exercise 2: [Title with Emoji] (🔍 Design & Analysis)\n"
        "**⏱️ Estimated Time:** 30-45 minutes\n\n"
        "**🎯 Goal:** [What students will accomplish in design/analysis]\n\n"
        "**📝 Instructions:**\n"
        "[Design challenge with visual elements, partially completed examples, bridge to coding]\n\n"
        "**💡 Hints:**\n"
        "<details>\n"
        "<summary>Click for hint</summary>\n"
        "[Common pitfalls to avoid]\n"
        "</details>\n\n"
        "**✅ Success Criteria:**\n"
        "- [ ] [Specific criterion 1]\n"
        "- [ ] [Specific criterion 2]\n"
        "- [ ] [Specific criterion 3]\n\n"
        "Include 1-2 'fun facts' or 'did you know' boxes. Use conversational but professional tone."
    )

    overview_and_exercises_1_2 = generate_task_step(system_message, user_message, Config.TASK_SETTINGS.get('overview_exercises_1_2_tokens', 4000))
    print("=== OVERVIEW AND EXERCISES 1-2 ===")
    print(overview_and_exercises_1_2)

    # Step 2: Generate exercises 3-6 with full context
    enhanced_system_message = (
        f"{system_message}\n\n"
        f"CONTEXT FROM PREVIOUS GENERATION:\n"
        f"Theme: {theme}\n"
        f"Learning Goals: {learning_goals}\n"
        f"Language: {language}\n\n"
        "Continue building on the established task structure and narrative tone."
    )
    
    user_message = (
        f"Here is the task overview and first two exercises:\n\n{overview_and_exercises_1_2}\n\n"
        "Continue the task by creating Exercises 3-6 that build logically on the foundation. "
        "Maintain consistency with the established theme, tone, and structure.\n\n"
        "Generate the remaining exercises using the same format:\n\n"
        "### Exercise 3: [Title with Emoji] (🏗️ Building Foundations)\n"
        "**⏱️ Estimated Time:** 45-60 minutes\n\n"
        "**🎯 Goal:** [Core implementation goal that connects to Exercises 1-2]\n\n"
        "**📝 Instructions:**\n"
        "[Starter code with TODO comments, 3-4 incremental steps, test cases]\n\n"
        "**💡 Hints:**\n"
        "<details>\n"
        "<summary>Click for hint</summary>\n"
        "[Debugging tips and code quality guidance]\n\n"
        "**Code Guidance:**\n"
        "```java\n"
        "[Basic code snippet showing key pattern/structure]\n"
        "// TODO: Students need to implement the core logic here\n"
        "```\n"
        "</details>\n\n"
        "**✅ Success Criteria:**\n"
        "- [ ] [Specific criterion 1]\n"
        "- [ ] [Specific criterion 2]\n"
        "- [ ] [Specific criterion 3]\n\n"
        "### Exercise 4: [Title with Emoji] (🔄 Integration & Enhancement)\n"
        "**⏱️ Estimated Time:** 45-60 minutes\n\n"
        "**🎯 Goal:** [Integration goal building directly on Exercise 3]\n\n"
        "**📝 Instructions:**\n"
        "[Build on Exercise 3, introduce 1-2 new concepts, performance considerations]\n\n"
        "**💡 Hints:**\n"
        "<details>\n"
        "<summary>Click for hint</summary>\n"
        "[Multiple solution approaches with trade-offs]\n\n"
        "**Code Guidance:**\n"
        "```java\n"
        "[Code snippet showing integration pattern or new concept]\n"
        "// Example of how to extend the previous exercise\n"
        "```\n\n"
        "**Performance Tip:**\n"
        "```java\n"
        "[Small snippet showing efficient approach]\n"
        "```\n"
        "</details>\n\n"
        "**✅ Success Criteria:**\n"
        "- [ ] [Specific criterion 1]\n"
        "- [ ] [Specific criterion 2]\n"
        "- [ ] [Specific criterion 3]\n\n"
        "### Exercise 5: [Title with Emoji] (🚀 Advanced Implementation)\n"
        "**⏱️ Estimated Time:** 60-90 minutes\n\n"
        "**🎯 Goal:** [Advanced feature addition goal]\n\n"
        "**📝 Instructions:**\n"
        "[Significant enhancement, 4-5 sub-tasks, architectural guidance, edge cases, file I/O]\n\n"
        "**💡 Hints:**\n"
        "<details>\n"
        "<summary>Click for hint</summary>\n"
        "[Design decisions guidance]\n\n"
        "**Architectural Pattern:**\n"
        "```java\n"
        "[Code snippet showing class structure or design pattern]\n"
        "// Template for the advanced feature implementation\n"
        "```\n\n"
        "**File I/O Example:**\n"
        "```java\n"
        "[Basic file operation snippet]\n"
        "// Handle exceptions appropriately\n"
        "```\n\n"
        "**Edge Case Handling:**\n"
        "```java\n"
        "[Code showing input validation or error handling]\n"
        "```\n"
        "</details>\n\n"
        "**✅ Success Criteria:**\n"
        "- [ ] [Specific criterion 1]\n"
        "- [ ] [Specific criterion 2]\n"
        "- [ ] [Specific criterion 3]\n\n"
        "### Exercise 6: [Title with Emoji] (🌟 Creative Extension)\n"
        "**⏱️ Estimated Time:** 60-90 minutes\n\n"
        "**🎯 Goal:** [Creative extension goal slightly beyond CS1]\n\n"
        "**📝 Instructions:**\n"
        "[2-3 different paths, bonus challenges marked with 🏆, documentation requirements]\n\n"
        "**💡 Hints:**\n"
        "<details>\n"
        "<summary>Click for hint</summary>\n"
        "[Documentation and evaluation guidance]\n\n"
        "**Extension Pattern:**\n"
        "```java\n"
        "[Code snippet showing extension approach or interface]\n"
        "// Framework for creative implementations\n"
        "```\n\n"
        "**Documentation Template:**\n"
        "```java\n"
        "/**\n"
        " * [Javadoc example for the creative feature]\n"
        " * @param [parameter description]\n"
        " * @return [return value description]\n"
        " */\n"
        "```\n\n"
        "**🏆 Bonus Challenge Starter:**\n"
        "```java\n"
        "[Advanced code snippet for bonus features]\n"
        "// Challenge: Implement this efficiently\n"
        "```\n"
        "</details>\n\n"
        "**✅ Success Criteria:**\n"
        "- [ ] [Specific criterion 1]\n"
        "- [ ] [Specific criterion 2]\n"
        "- [ ] [Specific criterion 3]\n\n"
        "## 🤔 Reflection Questions\n"
        "[3-4 questions that deepen understanding and connect to learning objectives]\n\n"
        "Ensure exercises build logically and maintain narrative coherence with the established task."
    )

    exercises_3_6 = generate_task_step(enhanced_system_message, user_message, Config.TASK_SETTINGS.get('exercises_3_6_tokens', 5000))
    print("=== EXERCISES 3-6 ===")
    print(exercises_3_6)

    # Step 3: Final integration and polish with complete context
    complete_context = f"Theme: {theme}\nLearning Goals: {learning_goals}\nLanguage: {language}"
    final_system_message = (
        f"{system_message}\n\n"
        f"COMPLETE TASK CONTEXT:\n{complete_context}\n\n"
        "You are finalizing a comprehensive programming task. Ensure consistency, flow, and pedagogical effectiveness."
    )
    
    user_message = (
        f"Here is the complete task content:\n\n{overview_and_exercises_1_2}\n\n{exercises_3_6}\n\n"
        "Perform final integration and polish:\n\n"
        "1. Ensure smooth transitions between exercises\n"
        "2. Add a comprehensive final checklist (8-12 items covering all exercises)\n"
        "3. Add any missing cross-references between exercises\n"
        "4. Verify all learning objectives are addressed\n"
        "5. Ensure consistent difficulty progression\n"
        "6. Add final sections:\n\n"
        "## ✅ Final Checklist\n"
        "- [ ] [Comprehensive item 1]\n"
        "- [ ] [Comprehensive item 2]\n"
        "[...8-12 items total covering all key achievements]\n\n"
        "## 📚 Additional Resources\n"
        "[2-3 relevant resources for deeper learning]\n\n"
        "## 🤝 Collaboration Guidelines\n"
        "[Guidelines for working with others on this task]\n\n"
        "Return the complete, polished task ready for students."
    )

    complete_task = generate_task_step(final_system_message, user_message, Config.TASK_SETTINGS.get('final_polish_tokens', 6000))
    print("=== FINAL COMPLETE TASK ===")
    print(complete_task)

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