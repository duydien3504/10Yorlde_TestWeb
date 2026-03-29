import os
import uuid

OBJECT_REPO_DIR = r"e:\New folder\10Yordle-TestWeb\Object Repository\Page_LearningGoals"
os.makedirs(OBJECT_REPO_DIR, exist_ok=True)

objects = {
    "btn_Add_New_Goal": "//button[contains(text(), 'Thêm mục tiêu mới')]",
    "input_Title": "//input[@name='title']",
    "input_Description": "//input[@name='description']",
    "select_Type": "//select[@name='type']",
    "select_Skill": "//select[@name='skill']",
    "input_Target": "//input[@name='target']",
    "input_Deadline": "//input[@name='deadline']",
    "input_WorkTime": "//input[@name='workDuration']",
    "input_BreakTime": "//input[@name='shortBreakDuration']",
    "input_Interval": "//input[@name='longBreakInterval']",
    "input_LongBreak": "//input[@name='longBreakDuration']",
    "btn_Submit": "//button[@type='submit']",
    "btn_Cancel": "//button[text()='Hủy']",
    "msg_Toast": "//div[contains(@class, 'ToastWrapper')]"
}

template = """<?xml version="1.0" encoding="UTF-8"?>
<WebElementEntity>
   <description></description>
   <name>{name}</name>
   <tag></tag>
   <elementGuidId>{guid}</elementGuidId>
   <selectorCollection>
      <entry>
         <key>XPATH</key>
         <value>{xpath}</value>
      </entry>
   </selectorCollection>
   <selectorMethod>XPATH</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
</WebElementEntity>
"""

for name, xpath in objects.items():
    file_path = os.path.join(OBJECT_REPO_DIR, f"{name}.rs")
    with open(file_path, "w", encoding="utf-8") as f:
        f.write(template.format(name=name, guid=str(uuid.uuid4()), xpath=xpath))

print("Created 14 object files.")
