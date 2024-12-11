import os

def print_directory_tree(root_dir, ignore=None, indent=""):
    """Recursively prints the directory tree starting from root_dir."""
    if ignore is None:
        ignore = []
    items = [item for item in os.listdir(root_dir) if item not in ignore]
    for idx, item in enumerate(items):
        path = os.path.join(root_dir, item)
        is_last = idx == len(items) - 1

        # Print the item
        if os.path.isdir(path):
            print(f"{indent}├── {item}/")
            print_directory_tree(path, ignore, indent + "│   " if not is_last else indent + "    ")
        else:
            print(f"{indent}├── {item}")

def write_directory_tree(root_dir, file, ignore=None, indent=""):
    """Recursively writes the directory tree starting from root_dir to a file."""
    if ignore is None:
        ignore = []
    items = [item for item in os.listdir(root_dir) if item not in ignore]
    for idx, item in enumerate(items):
        path = os.path.join(root_dir, item)
        is_last = idx == len(items) - 1

        # Write the item to the file
        if os.path.isdir(path):
            file.write(f"{indent}├── {item}/\n")
            write_directory_tree(path, file, ignore, indent + "│   " if not is_last else indent + "    ")
        else:
            file.write(f"{indent}├── {item}\n")

def main():
    project_root = "C:\\Users\\PC\\Desktop\\Courseworks\\OOP 2nd Year CW\\UI\\"  
    ignore_list = ["frontend", ".idea", ".vscode", ".git", "node_modules"] 

    print(f"{project_root}/")
    print_directory_tree(project_root, ignore=ignore_list)
    
    with open("project_structure.txt", "w", encoding="utf-8") as file:
        file.write(f"{project_root}/\n")
        write_directory_tree(project_root, file, ignore=ignore_list)

if __name__ == "__main__":
    main()
