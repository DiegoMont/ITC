from typing import Dict, List


class DecisionTree:
    def __init__(self):
        self.parse_data()

    def parse_data(self):
        self.classes: List[str] = input().split(",")
        self.data: Dict[str, Dict[str, Dict[str, int]]] = {}
        self.total_data = 0
        num_classes = len(self.classes)
        has_next = True
        while has_next:
            try:
                values = input().split(",")
                last_value = values[num_classes-1]
                if last_value not in self.data:
                    self.data[last_value] = {"row_count": 0}
                    for i in range(num_classes-2):
                        self.data[last_value][self.classes[i]] = {}
                for i in range(num_classes-2):
                    data_class = self.classes[i]
                    class_value = values[i]
                    if class_value in self.data[last_value][data_class]:
                        self.data[last_value][data_class][class_value] += 1
                    else:
                        self.data[last_value][data_class][class_value] = 1
                self.data[last_value]["row_count"] += 1
                self.total_data += 1
            except EOFError:
                has_next = False

decision_tree = DecisionTree()
print(decision_tree.data)