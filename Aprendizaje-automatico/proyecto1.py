import pandas as pd
from sklearn.metrics import accuracy_score, confusion_matrix, classification_report
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import MultinomialNB
from sklearn.tree import DecisionTreeClassifier, export_text


DATASET_SPLIT_SEED = 40692
TREE_CLASSIFIER = DecisionTreeClassifier()
NAIVE_BAYES_CLASSIFIER = MultinomialNB()


def load_dataset():
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
    colnames = data.columns.array[:-1]
    class_values = set(classes)
    return numeric_data, classes, colnames, class_values

def get_splitted_data(data, classes, train_data_ratio):
    return train_test_split(
        data,
        classes,
        test_size=train_data_ratio,
        random_state=DATASET_SPLIT_SEED
    )

def print_model_evaluation(model, test_data, test_classes, class_values):
    predictions = model.predict(test_data)
    accuracy = accuracy_score(test_classes, predictions)
    print("Accuracy score: ",accuracy)
    confusion_mat = confusion_matrix(test_classes, predictions)
    print(confusion_mat)
    report = classification_report(test_classes, predictions, target_names=class_values, zero_division=0)
    print(report)


(data, classes, colnames, class_values) = load_dataset()
split_ratios = [0.2, 0.25, 0.3, 1/3, 0.5]
test_id = 1
for split_ratio in split_ratios:
    (training_data, test_data, training_classes, test_classes) = get_splitted_data(data, classes, split_ratio)
    naive_bayes_model = NAIVE_BAYES_CLASSIFIER.fit(training_data, training_classes)
    decision_tree_model = TREE_CLASSIFIER.fit(training_data, training_classes) 
    print(f"==== TEST {test_id} ====")
    print(f"Training data: {(1 - split_ratio) * 100}    Test data: {(split_ratio * 100)}")
    print("Decision tree report")
    tree_text = export_text(decision_tree_model, feature_names=colnames)
    # print(tree_text)
    print_model_evaluation(decision_tree_model, test_data, test_classes, class_values)
    print("Naive Bayes report")
    print_model_evaluation(naive_bayes_model, test_data, test_classes, class_values)
    test_id += 1