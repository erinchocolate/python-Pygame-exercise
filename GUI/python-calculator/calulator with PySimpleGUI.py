import PySimpleGUI as sg

def create_window(theme):
    sg.theme(theme)
    sg.set_options(font = 'Calibri 20', button_element_size = (6, 3))
    button_size = (6, 3)

    layout = [
    [sg.Text('', font = 'Calibri 25', justification = 'right', expand_x = True, right_click_menu=theme_menu, key = '-TEXT-')],
    [sg.Button('Enter', expand_x = True), sg.Button('Clear', expand_x = True)],
    [sg.Button(7, size = button_size),sg.Button(8, size = button_size),sg.Button(9, size = button_size),sg.Button('+', size = button_size, key = '+')],
    [sg.Button(4, size = button_size),sg.Button(5, size = button_size),sg.Button(6, size = button_size),sg.Button('-', size = button_size, key = '-')],
    [sg.Button(1, size = button_size),sg.Button(2, size = button_size),sg.Button(3, size = button_size),sg.Button('×', size = button_size, key = "*")],
    [sg.Button(0, expand_x = True),sg.Button('.', size = button_size),sg.Button('÷', size = button_size, key = "/")]
    ]
    return sg.Window('Calculator', layout)

theme_menu = ['menu',['dark', 'graygraygray', 'random']]

window = create_window('random')
current_num = []
calculation = []
display = []

while True:
    event, values = window.read()
    if event == sg.WIN_CLOSED:
        break

    if event in theme_menu[1]:
        window.close()
        window = create_window(event)

    if event in ['0','1','2','3','4','5','6','7','8','9']:
        current_num.append(event)
        display.append(event)
        current_str = "".join(current_num)
        window['-TEXT-'].update("".join(display))

    if event in ['+','-','*','/']:
        calculation.append(current_str)
        display.append(event)
        current_num = []
        calculation.append(event)
        window['-TEXT-'].update("".join(display))

    if event == 'Enter':
        if calculation or current_num:
            calculation.append(current_str)
            result = eval("".join(calculation))
            window['-TEXT-'].update(result)
        current_num = []
        calculation = []
        display = []

    if event == 'Clear':
        window['-TEXT-'].update("")
        current_num = []
        calculation = []
        display = []

window.close()
