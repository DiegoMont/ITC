import pandas as pd
from sklearn.metrics import accuracy_score, confusion_matrix, classification_report
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import MultinomialNB
from sklearn.tree import DecisionTreeClassifier, export_text


def read_and_divide_data(division_value):
    global data, classes, numeric_data, train_data, test_data, train_classes, test_classes
    data = pd.read_csv("car.data")
    text_to_data_convertion = {
        "vhigh": 4, "high": 3, "med": 2, "low": 1,
        "5more": 5,
        "more": 5,
        "small": 1, "med": 2, "big": 3,
        "unacc": 1, "acc": 2, "good": 3, "vgood": 4
    }
    classes = data.iloc[:, -1]
    numeric_data = data.iloc[:,:-1].replace(text_to_data_convertion)
    # print(numeric_data.head)
    #print(classes)
    train_data, test_data, train_classes, test_classes = train_test_split(numeric_data, classes, test_size=division_value, random_state=1234)

def get_model_predictions(model_function, train_data, train_classes, data):
    classifier = model_function
    model = classifier.fit(train_data, train_classes)
    predictions = model.predict(test_data)
    if type(model_function) == type(DecisionTreeClassifier()):
        colnames = data.columns.array[:-1]
        tree_text = export_text(model, feature_names=colnames)
        print(tree_text)
    return predictions

def print_model_evaluation(model, predictions, classes, test_classes):
    print("Decision Tree Report") if type(model) == type(DecisionTreeClassifier()) else print("Naive Bayes Report")
    accuracy = accuracy_score(test_classes, predictions)
    print("Accuracy score: ",accuracy)
    confusion_mat = confusion_matrix(test_classes, predictions)
    print(confusion_mat)
    report = classification_report(test_classes, predictions, target_names=set(classes))
    print(report)


read_and_divide_data(0.5)

tree_classifier = DecisionTreeClassifier()
bayes_classifier = MultinomialNB()

predictions = get_model_predictions(tree_classifier, train_data, train_classes, data)

print_model_evaluation(tree_classifier, predictions, classes, test_classes)


predictions = get_model_predictions(bayes_classifier, train_data, train_classes, data)

print(predictions)
print(test_classes)

# print_model_evaluation(MultinomialNB(), predictions, classes, test_classes)


