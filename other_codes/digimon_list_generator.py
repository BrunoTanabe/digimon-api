import json

with open('./src/main/resources/digimons_data.json', 'r') as f:
    digimons = json.load(f)

markdown_content = "# Digimon List\n\n"

for idx, digimon in enumerate(digimons, start=1):
    markdown_content += f"## {idx}. {digimon['name']}\n"
    markdown_content += f"![{digimon['name']}]({digimon['img']})\n\n"
    markdown_content += f"- **ID**: {idx}\n"
    markdown_content += f"- **Name**: {digimon['name']}\n"
    markdown_content += f"- **Level**: {digimon['level']}\n\n"

with open("./docs/digimon_list.md", "w") as md_file:
    md_file.write(markdown_content)

print("A lista de Digimons foi criada!")
