from PyQt5.QtWidgets import *
from PyQt5.QtGui import QFont
from PyQt5 import uic

class MyGUI(QMainWindow):
    def __init__(self):
        super(MyGUI, self).__init__()
        uic.loadUi("text editor UI.ui",self)
        self.show()

        self.setWindowTitle("Text Editor")
        self.size = 12

        # Open
        self.actionOpen.triggered.connect(self.openFile)
        # Save, Save as
        self.actionSave.triggered.connect(self.saveFile)
        self.actionSave_as.triggered.connect(self.saveFile)
        # New, New Window
        self.actionNew.triggered.connect(self.new)
        self.actionNew_Window.triggered.connect(self.newWindow)
        # Edit Menu
        self.actionUndo.triggered.connect(lambda: self.edit("undo"))
        self.actionCopy.triggered.connect(lambda: self.edit("copy"))
        self.actionCut.triggered.connect(lambda: self.edit("cut"))
        self.actionPaste.triggered.connect(lambda: self.edit("paste"))
        self.actionSelect_All.triggered.connect(lambda: self.edit("select"))
        # Zoom in and out
        self.actionZoom_In.triggered.connect(self.zoomIn)
        self.actionZoom_Out.triggered.connect(self.zoomOut)
        # Font size
        self.actionSizePlus.triggered.connect(lambda: self.fontSize("+"))
        self.actionSizeMinus.triggered.connect(lambda: self.fontSize("-"))

    def openFile(self):
        options = QFileDialog.Options()
        filename, _ = QFileDialog.getOpenFileName(self, "Open File", "", "Text Files (*.txt);; Python Files(*.py)", options=options)
        if filename != "":
            with open(filename, "r") as f:
                self.plainTextEdit.setPlainText(f.read())

    def saveFile(self):
        options = QFileDialog.Options()
        filename, _ = QFileDialog.getSaveFileName(self, "Save File", "", "Text Files (*.txt);; All Files(*)", options=options)
        if filename != "":
            with open(filename, "w") as f:
                f.write(self.plainTextEdit.toPlainText())

    def fontSize(self, operation):
        if operation == "+":
            self.size += 1
        else:
            self.size -= 1
        self.plainTextEdit.setFont(QFont("Arial", self.size))

    def new(self):
        self.show()

    def newWindow(self):
        self.new_window = MyGUI()
        self.new_window.show()

    def edit(self, command):
        if command == "copy":
            self.plainTextEdit.copy()
        elif command == "cut":
            self.plainTextEdit.cut()
        elif command == "paste":
            self.plainTextEdit.paste()
        elif command == "select":
            self.plainTextEdit.selectAll()
        elif command == "undo":
            self.plainTextEdit.undo()

    def zoomIn(self):
        self.plainTextEdit.zoomIn()

    def zoomOut(self):
        self.plainTextEdit.zoomOut()

    def closeEvent(self, event):
        dialog = QMessageBox()
        dialog.setText("Do you want to save your work?")
        dialog.addButton(QPushButton("Yes"), QMessageBox.YesRole)
        dialog.addButton(QPushButton("No"), QMessageBox.NoRole)
        dialog.addButton(QPushButton("Cancel"), QMessageBox.RejectRole)
        answer = dialog.exec_()

        if answer == 0:
            self.save_file()
            event.accept()
        elif answer == 2:
            event.ignore()

if __name__ == "__main__":
    app = QApplication([])
    window = MyGUI()
    app.exec_()
