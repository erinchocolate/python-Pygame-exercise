import PySimpleGUI as sg
from PIL import Image, ImageFilter, ImageOps
from io import BytesIO

def update_image(original, blur, contrast, emboss, contour, flipx, flipy):
    image = original.filter(ImageFilter.GaussianBlur(blur))
    image = image.filter(ImageFilter.UnsharpMask(contrast))

    if emboss:
        image = image.filter(ImageFilter.EMBOSS())
    if contour:
        image = image.filter(ImageFilter.CONTOUR())
    if flipx:
        image = ImageOps.mirror(image)
    if flipy:
        image = ImageOps.flip(image)

    try:
        bio = BytesIO()
        image.save(bio, format='PNG')
        window['-IMAGE-'].update(data = bio.getvalue())
    except:
        pass
    return image


# initialize a start window
start_layout = [[sg.Button("Open File", key = 'START')]]
window = sg.Window('Image Editor', start_layout)

while True:
    event, values = window.read(timeout=50)
    if event == sg.WIN_CLOSED:
        break

    if event == 'START':
        window.close()

        # open an image file
        image_path = sg.popup_get_file('Open an image file', no_window = True)
        original = Image.open(image_path)

        # initialize a Editor GUI window
        image_col = sg.Column([[sg.Image(image_path, key = '-IMAGE-')]])

        control_col = sg.Column([
            [sg.Frame('Blur', layout = [[sg.Slider(range = (0, 10), orientation = 'h', key = '-BLUR-')]])],
            [sg.Frame('Contrast', layout = [[sg.Slider(range = (0, 10), orientation = 'h', key = '-CONTRAST-')]])],
            [sg.Checkbox('Emboss', key = '-EMBOSS-'), sg.Checkbox('Contour', key = '-CONTOUR-')],
            [sg.Checkbox('Flip x', key = '-FLIPX-'), sg.Checkbox('Flip y', key = '-FLIPY-')],
            [sg.Button('Save Image', key = '-SAVE-'), sg.Button('Open New Image', key = '-OPEN-')],
            ])

        layout = [[control_col,image_col]]
        window = sg.Window('Image Editor', layout, finalize = True)

    try:
        # Edit images after the Editor GUI initialized
        image = update_image(
        original,
        values['-BLUR-'],
        values['-CONTRAST-'],
        values['-EMBOSS-'],
        values['-CONTOUR-'],
        values['-FLIPX-'],
        values['-FLIPY-'])

        if event == '-SAVE-':
            save_path = sg.popup_get_file('Save as', save_as = True, no_window = True) + '.png'
            image.save(save_path, 'PNG')

        if event == '-OPEN-':
            image_path = sg.popup_get_file('Open an image file', no_window = True)
            original = Image.open(image_path)
    except:
        pass

window.close()
