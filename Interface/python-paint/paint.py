import tkinter as tk
from tkinter import filedialog, messagebox, colorchooser
import PIL
from PIL import ImageDraw

WIDTH, HEIGHT = 500, 500
WHITE = (255, 255, 255)

class PaintGUI(tk.Tk):

    def __init__(self):
        super().__init__()

        self.title = ("Paint")

        # Paint brush
        self.brush_width = 5
        self.current_color = "#000000"

        # Canvas
        self.cnv = tk.Canvas(self, width=WIDTH-10, height=HEIGHT-10, bg="white")
        self.cnv.pack()
        # Left click mouse to start painting
        self.cnv.bind("<B1-Motion>", self.paint)

        # Image file
        self.image = PIL.Image.new("RGB", (WIDTH, HEIGHT), WHITE)
        self.draw = ImageDraw.Draw(self.image)

        # Buttons
        self.btn_frame = tk.Frame(self)
        self.btn_frame.pack(fill="x")

        self.btn_frame.columnconfigure(0, weight=1)
        self.btn_frame.columnconfigure(1, weight=1)
        self.btn_frame.columnconfigure(2, weight=1)

        self.clear_button = tk.Button(self.btn_frame, text="Clear", command = self.clear)
        self.clear_button.grid(row=0, column=2, sticky="W"+"E")

        self.save_button = tk.Button(self.btn_frame, text="Save", command = self.save)
        self.save_button.grid(row=1, column=2, sticky="W"+"E")

        self.brush_plus_button = tk.Button(self.btn_frame, text="B+", command = self.brush_plus)
        self.brush_plus_button.grid(row=0, column=0, sticky="W"+"E")

        self.brush_minus_button = tk.Button(self.btn_frame, text="B-", command = self.brush_minus)
        self.brush_minus_button.grid(row=1, column=0, sticky="W"+"E")

        self.color_button = tk.Button(self.btn_frame, text="Change Color", command = self.change_color)
        self.color_button.grid(row=0, column=1, sticky="W"+"E")

        # Closing window
        self.protocol("WM_DELETE_WINDOW", self.closing)
        self.attributes("-topmost", True)
        self.mainloop()

    def paint(self, event):
        x1, y1 = event.x, event.y
        x2, y2 = event.x, event.y
        # draw on the canvas
        self.cnv.create_rectangle(x1, y1, x2, y2, outline = self.current_color, fill=self.current_color, width=self.brush_width)
        # draw on image file
        self.draw.rectangle([x1, y1, x2+self.brush_width, y2+self.brush_width], outline=self.current_color, fill=self.current_color, width=self.brush_width)

    def clear(self):
        # clear the canvas and image file
        self.cnv.delete("all")
        self.draw.rectangle([0, 0, 1000, 1000], fill = "white")

    def save(self):
        filename = tk.filedialog.asksaveasfilename(initialfile="untitled.png",
                                                    defaultextension="png",
                                                    filetypes=[("PNG",".png"),("JPG", ".jpg")])

        if filename != "":
            self.image.save(filename)

    def brush_plus(self):
        self.brush_width += 1

    def brush_minus(self):
        if self.brush_width > 1:
            self.brush_width -= 1

    def change_color(self):
        _, self.current_color = tk.colorchooser.askcolor(title="Choose A Color")

    def closing(self):
        # closing window message
        answer = tk.messagebox.askyesnocancel("Save Changes", "New file has been modified, save changes?", parent = self)
        if answer is not None:
            if answer:
                self.save()
            self.destroy()
            exit(0)

if __name__ == "__main__":
    paint = PaintGUI()
