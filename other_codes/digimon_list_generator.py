import json

# Load the JSON file
with open('/path/to/your/database-structured.json', 'r') as f:
    digimons = json.load(f)

# Create the markdown content
markdown_content = "# Digimon List\n\n"

# Iterate over the digimons to create a formatted markdown string
for idx, digimon in enumerate(digimons, start=1):
    markdown_content += f"## {idx}. {digimon['name']}\n"
    markdown_content += f"![{digimon['name']}]({digimon['img']})\n\n"
    markdown_content += f"- **ID**: {idx}\n"
    markdown_content += f"- **Name**: {digimon['name']}\n"
    markdown_content += f"- **Level**: {digimon['level']}\n\n"

# Save the markdown content to a file
with open("/path/to/your/digimon_list.md", "w") as md_file:
    md_file.write(markdown_content)

print("Markdown file created successfully!")
