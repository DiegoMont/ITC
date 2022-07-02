from typing import Dict, List


class NaiveBayes:

    def __init__(self, class_to_predict: str):
        self.class_to_predict = class_to_predict
        self.parse_classes_from_input(class_to_predict)

    def parse_classes_from_input(self, class_to_predict: str) -> Dict[str, Dict[str, int]]:
        self.classes: List[str] = input().split(",")
        self.groups: Dict[str, Dict[str, Dict[str, int]]] = {}
        num_classes = len(self.classes)
        self.total_data = 0
        for i in range(num_classes):
            data_class = self.classes[i]
            if data_class == class_to_predict:
                predicted_class_index = i
                break
        has_next = True
        while has_next:
            try:
                values = input().split(",")
                prediction_group = values[predicted_class_index]
                if prediction_group not in self.groups:
                    self.groups[prediction_group] = {"row_count": 0}
                    for i in range(num_classes):
                        if i != predicted_class_index:
                            data_class = self.classes[i]
                            self.groups[prediction_group][data_class] = {}
                for i in range(num_classes):
                    if i != predicted_class_index:
                        data_class = self.classes[i]
                        class_value = values[i]
                        if class_value in self.groups[prediction_group][data_class]:
                            self.groups[prediction_group][data_class][class_value] += 1
                        else:
                            self.groups[prediction_group][data_class][class_value] = 1
                self.groups[prediction_group]["row_count"] += 1
                self.total_data += 1
            except EOFError:
                has_next = False

    def make_prediction(self: str, data: Dict[str, str]):
        biggest_probability = 0
        predicted_value = ""
        for group in self.groups:
            probability = self.groups[group]["row_count"] / self.total_data
            for data_class in data:
                value = data[data_class]
                probability *= self.groups[group][data_class][value] / self.groups[group]["row_count"]
            if probability > biggest_probability:
                biggest_probability = probability
                predicted_value = group
        return predicted_value


naive_bayes_model = NaiveBayes("CLASE")
print(naive_bayes_model.groups)
test_file = open("DatosTest.csv", "r")
classes = test_file.readline().split(",")
n = len(classes)
while True:
    try:
        known_data = {}
        values = test_file.readline().split(",")
        for i in range(n):
            if values[i] != "?\n":
                known_data[classes[i]] = values[i]
        print(naive_bayes_model.make_prediction(known_data))
    except EOFError:
        break
    except IndexError:
        break