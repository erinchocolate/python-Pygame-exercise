import time
import tkinter as tk
from tkinter import ttk
from tkinter import messagebox
import pygame


class Clock(tk.Tk):
    def __init__(self):
        super().__init__()

        # Para
        self.left_seconds = 60*45
        self.running = False
        self.pomodoros = 0

        # Sound
        pygame.mixer.init()
        self.start_sound = pygame.mixer.Sound("assets/clock.mp3")
        self.stop_sound = pygame.mixer.Sound("assets/end.mp3")

        # UI options
        fg_color = "#1D438A"
        bg_color = "#00BFB2"
        font = "Ubuntu"
        self.style = ttk.Style(self)
        self.style.configure("TNotebook.Tab", padding = 20, font =(font, 20),foreground=fg_color, background=bg_color)
        self.style.configure("TButton", font = (font, 20), foreground=fg_color, background=bg_color)
        self.style.configure("TFrame", foreground=fg_color, background=bg_color)
        self.style.configure("TLabel", font = (font, 45), foreground=fg_color, background=bg_color)

        # Size, title and icon
        self.geometry("600x300")
        self.title("Pomodoro Clock")
        self.config(background=bg_color)
        icon = tk.PhotoImage(file='assets/tomato.png')
        self.iconphoto(True, icon)

        # Tabs
        self.tabs = ttk.Notebook(self)
        self.pomodoro_tab = ttk.Frame(self.tabs, width = 600, height = 100)
        self.short_break_tab = ttk.Frame(self.tabs, width = 600, height = 100)
        self.long_break_tab = ttk.Frame(self.tabs, width = 600, height = 100)
        self.tabs.add(self.pomodoro_tab, text = "Pomodoro")
        self.tabs.add(self.short_break_tab, text = "Short Break")
        self.tabs.add(self.long_break_tab, text = "Long Break")
        self.tabs.pack(padx = 10, pady = 10, expand=True)

        self.pomodoro_timer = ttk.Label(self.pomodoro_tab, text = "45:00")
        self.pomodoro_timer.pack(pady = 20)
        self.short_break_timer = ttk.Label(self.short_break_tab, text = "05:00")
        self.short_break_timer.pack(pady = 20)
        self.long_break_timer = ttk.Label(self.long_break_tab, text = "15:00")
        self.long_break_timer.pack(pady = 20)

        # Button
        self.grid_layout = ttk.Frame(self)
        self.grid_layout.pack(padx = 10, pady = 10)
        self.button = ttk.Button(self.grid_layout, text = "Start", width = 10, command = self.set_timer_mode)
        self.button.grid(row = 0, column = 0)

        # Counter
        self.pomodoro_counter = ttk.Label(self.grid_layout, text = f"Pomodoros: {self.pomodoros}", font=(font, 15))
        self.pomodoro_counter.grid(row = 1, column = 0, columnspan = 3)

        self.mainloop()

    def set_timer_mode(self):
        self.current_tab = self.tabs.index(self.tabs.select())

        # Change button text from "Start" to "Stop"
        self.button.config(text = "Stop", command = self.pause_timer)

        # Different tabs display different timers
        if self.current_tab == 0:
            full_seconds = 60*45
            timer = self.pomodoro_timer
        elif self.current_tab == 1:
            full_seconds = 60*5
            timer = self.short_break_timer
        else:
            full_seconds = 60*15
            timer = self.long_break_timer

        # restart the timer after stop, the timer starts from the stopped time previously
        if self.left_seconds < full_seconds:
            full_seconds = self.left_seconds
        self.countdown(timer, full_seconds)

    def pause_timer(self):
        self.stop_sound.play()
        self.running = False
        self.button.config(text = "Start", command = self.set_timer_mode)

    def countdown(self, timer, full_seconds):
        self.start_sound.play()
        self.timer = timer
        self.full_seconds = full_seconds
        self.running = True

        while self.full_seconds > 0 and self.running:
            minutes, seconds = divmod(self.full_seconds, 60)
            self.timer.config(text=f"{minutes:02d}:{seconds:02d}")
            self.update()
            time.sleep(1)
            self.full_seconds -= 1
            self.left_seconds = self.full_seconds
        # if the user changes the tab while countdown, clear the countdown
            self.tabs.bind('<<NotebookTabChanged>>', self.clear_timer)

        if self.running:
            self.pomodoros += 1
            self.break_message()
            self.pomodoro_counter.config(text=f"Pomodoros: {self.pomodoros}")
            if self.pomodoros % 4 == 0:
                self.tabs.select(2)
            else:
                self.tabs.select(1)

    def break_message(self):
        messagebox.showinfo("Break", "Time to take a break!")

    def clear_timer(self, event):
        self.running = False
        self.left_seconds = 60*45
        self.button.config(text = "Start", command = self.set_timer_mode)
        self.pomodoro_timer.config(text = "45:00")
        self.short_break_timer.config(text = "05:00")
        self.long_break_timer.config(text = "15:00")

if __name__ == "__main__":
    tomato = Clock()
