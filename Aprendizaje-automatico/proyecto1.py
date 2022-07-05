import pandas as pd
from sklearn.metrics import accuracy_score
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier


data = pd.read_csv("car.data")
text_to_data_convertion = {
    "vhigh": 4, "high": 3, "med": 2, "low": 1,
    "5more": 5,
    "more": 5,
    "small": 1, "med": 2, "big": 3,
    "unacc": 1, "acc": 2, "good": 3, "vgood": 4
}
numeric_data = data.replace(text_to_data_convertion)
classes = numeric_data.iloc[:, -1]
# print(numeric_data.head)
#print(classes)

train_data, test_data, train_classes, test_classes = train_test_split(numeric_data, classes, test_size=0.1, random_state=1234)
classifier = DecisionTreeClassifier()
model = classifier.fit(train_data, train_classes)

predictions = model.predict(test_data)
print(test_classes)
print(predictions)
accuracy = accuracy_score(test_classes, predictions)
print(accuracy)