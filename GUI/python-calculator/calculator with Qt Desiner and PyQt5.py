from PyQt5.QtWidgets import *
from PyQt5 import QtWidgets
from PyQt5 import uic

class MyGUI(QMainWindow):
    def __init__(self):
        super(MyGUI, self).__init__()
        uic.loadUi("calculator UI.ui",self)
        self.show()

        self.temp = []
        self.calulation = []

        # Window Size and Title
        self.setFixedSize(434, 511)
        self.setWindowTitle("Calculator")

        # Enter/Clear Buttons
        self.EnterButton.clicked.connect(self.enter)
        self.ClearButton.clicked.connect(self.clear)

        # Operation Buttons
        self.AddButton.clicked.connect(lambda:self.func_press('+'))
        self.MinusButton.clicked.connect(lambda:self.func_press('-'))
        self.MultiButton.clicked.connect(lambda:self.func_press('*'))
        self.DivideButton.clicked.connect(lambda:self.func_press('/'))

        # Number Buttons
        self.Button1.clicked.connect(lambda:self.number_press('1'))
        self.Button2.clicked.connect(lambda:self.number_press('2'))
        self.Button3.clicked.connect(lambda:self.number_press('3'))
        self.Button4.clicked.connect(lambda:self.number_press('4'))
        self.Button5.clicked.connect(lambda:self.number_press('5'))
        self.Button6.clicked.connect(lambda:self.number_press('6'))
        self.Button7.clicked.connect(lambda:self.number_press('7'))
        self.Button8.clicked.connect(lambda:self.number_press('8'))
        self.Button9.clicked.connect(lambda:self.number_press('9'))
        self.Button0.clicked.connect(lambda:self.number_press('0'))

    def enter(self):
        try:
            calulation_string = ''.join(self.calulation) + ''.join(self.temp)
            result_string = eval(calulation_string)
            calulation_string += "="
            calulation_string += str(result_string)
            self.result.setText(calulation_string)
        except:
            self.result.setText("Invalid Input!")
            self.calulation = []

    def clear(self):
        self.calulation = []
        self.temp = []
        self.result.clear()

    def func_press(self, operator):
        temp_string = ''.join(self.temp)
        self.calulation.append(temp_string)
        self.calulation.append(operator)
        calulation_string = ''.join(self.calulation)
        self.temp = []
        self.result.setText(calulation_string)

    def number_press(self, number):
        self.temp.append(number)
        temp_string = ''.join(self.temp)
        if self.calulation:
            self.result.setText(''.join(self.calulation) + temp_string)
        else:
            self.result.setText(temp_string)

if __name__ == "__main__":
    app = QApplication([])
    window = MyGUI()
    app.exec_()
