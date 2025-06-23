import os
import sys
import subprocess
from openai import OpenAI

def main(api_key, branch_name):
    if not api_key:
        print("Error: OpenAI API key is missing.")
        sys.exit(1)

    client = OpenAI(api_key=api_key)

    # Read the existing solution code from .hidden_tasks directory
    solution_dir = ".hidden_tasks"
    solution_files = []
    try:
        for filename in os.listdir(solution_dir):
            if filename.endswith(".java"):
                with open(os.path.join(solution_dir, filename), "r") as file:
                    solution_files.append((filename, file.read()))
        if not solution_files:
            print("Error: No Java solution files found in .hidden_tasks.")
            sys.exit(1)
    except FileNotFoundError:
        print("Error: Solution files not found in .hidden_tasks directory.")
        sys.exit(1)

    # Generate a template from the solution for each file using OpenAI API
    for filename, solution_content in solution_files:
        template_content = generate_template_with_openai(client, solution_content)

        if not template_content:
            print(f"Error: Failed to generate template for {filename}. Using fallback.")
            template_content = generate_template_fallback(solution_content)

        # Write the final template to gen_src directory
        gen_src_dir = "gen_src"
        os.makedirs(gen_src_dir, exist_ok=True)
        file_path = os.path.join(gen_src_dir, filename)

        try:
            with open(file_path, "w") as template_file:
                template_file.write(template_content)
            print(f"Successfully created template for {filename}")
        except IOError as e:
            print(f"Error writing file {filename}: {e}")

    # Commit and push changes
    commit_and_push_changes(branch_name, gen_src_dir)

def generate_template_with_openai(client, solution_content):
    """
    Uses the OpenAI API to generate a code template by removing implementation details
    while retaining class and method signatures.
    """
    prompt = (
        "You are part of a task generation system to create weekly tasks for CS1 students."
        "Your goal is to create templates that provide a template for the students to solve their weekly tasks."
        "The template will be based on the solution files to the task, so you must strip away most of the code but still provide a good enough scaffolding for the students to solve their task"
        "The scaffolding should purely assist them in understanding the general expected structure, without in any way revealing the answer."
        "Given the following Java solution code, remove all implementation details and leave only the class and method signatures. "
        "Ensure that the structure is correct, add comments very sparsely and only in instances that it is absolutely necessary.\n\n"
        "### Solution Code:\n"
        f"{solution_content}\n\n"
        "IMPORTANT: The response must be plain Java code with no markdown formatting or ```java blocks. "
        "Remember to put the right code in the right java file and take consideration to the names of the java classes with consideration to the file they are in."
    )
    
    system_message = "You are a helpful assistant that creates code templates for educational purposes."

    return client.create_response(
        prompt=prompt,
        task_name='generate_template_code',
        system_message=system_message,
        max_tokens=2000,
        temperature=0.3
    )


def generate_template_fallback(solution_content):
    """
    Fallback method to manually generate a template by removing method bodies.
    """
    template_lines = []
    in_method_body = False

    for line in solution_content.splitlines():
        stripped_line = line.strip()

        # Detect the start of a method (line ending with '{' but not class declaration)
        if stripped_line.endswith("{") and not stripped_line.startswith("class"):
            template_lines.append(line)  # Keep the method signature
            template_lines.append("    // TODO: Implement this method.")
            in_method_body = True
        elif in_method_body:
            # Detect the end of a method
            if stripped_line == "}":
                template_lines.append(line)  # Keep the closing brace
                in_method_body = False
            # Skip other lines inside the method body
            continue
        else:
            template_lines.append(line)  # Keep class structure and other elements

    return "\n".join(template_lines)


if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python generate_template_code.py <api_key> <branch_name>")
        sys.exit(1)

    api_key = sys.argv[1]
    branch_name = sys.argv[2]

    main(api_key, branch_name)
