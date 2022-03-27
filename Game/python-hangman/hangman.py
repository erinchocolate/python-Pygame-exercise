# Hangman game

import random

WORDLIST_FILENAME = "words.txt"

def loadWords():
    """
    Returns a list of valid words. Words are strings of lowercase letters.
    
    Depending on the size of the word list, this function may
    take a while to finish.
    """
    print("Loading word list from file...")
    # inFile: file
    inFile = open(WORDLIST_FILENAME, 'r')
    # line: string
    line = inFile.readline()
    # wordlist: list of strings
    wordlist = line.split()
    print(len(wordlist), "words loaded.")
    return wordlist

def chooseWord(wordlist):
    """
    wordlist (list): list of words (strings)

    Returns a word from wordlist at random
    """
    return random.choice(wordlist)

def hangman(secretWord):
    '''
    secretWord: string, the secret word to guess.

    Starts up an interactive game of Hangman.

    * At the start of the game, let the user know how many 
      letters the secretWord contains.

    * Ask the user to supply one guess (i.e. letter) per round.

    * The user should receive feedback immediately after each guess 
      about whether their guess appears in the computers word.

    * After each round, you should also display to the user the 
      partially guessed word so far, as well as letters that the 
      user has not yet guessed.

    '''
    # Game starts
    print("Welcome to the game Hangman!")
    print("I am thinking of a word that is " + str(len(secretWord)) +" " + "letters long.")
    guesstimes = 8
    lettersGuessed = []
    
    def isWordGuessed(secretWord, lettersGuessed):
        '''
    secretWord: string, the word the user is guessing
    lettersGuessed: list, what letters have been guessed so far
    returns: boolean, True if all the letters of secretWord are in lettersGuessed;
      False otherwise
        '''
        for letters in lettersGuessed:
            if letters in secretWord:
                continue
            else:
                return False
        return True

    def getGuessedWord(secretWord, lettersGuessed):
        '''
    secretWord: string, the word the user is guessing
    lettersGuessed: list, what letters have been guessed so far
    returns: string, comprised of letters and underscores that represents
      what letters in secretWord have been guessed so far.
        '''
        userguess = list()
        for letters in secretWord:
            if letters in lettersGuessed:
                userguess.append(letters)
            else:
                userguess.append("_ ")
        return ("".join(userguess))

    def getAvailableLetters(lettersGuessed):
        '''
    lettersGuessed: list, what letters have been guessed so far
    returns: string, comprised of letters that represents what letters have not
      yet been guessed.
        '''
        import string
        availableLetters = string.ascii_lowercase
        for letters in lettersGuessed:
            if letters in string.ascii_lowercase:
                availableLetters = availableLetters.replace(letters,"")
                continue
        return availableLetters
   
    while guesstimes > 0:
        # start the guess
        while True:
            print("-------------")
            print("You have " + str(guesstimes) + " " + "guesses left.")
            print("Available letters:", getAvailableLetters(lettersGuessed))
            guess = str(input("Please guess a letter: "))
        
        # tell users to guess a new letter if they already guessed the letter before
            if guess in lettersGuessed:
                print("Oops! You've already guessed that letter: ", getGuessedWord(secretWord, lettersGuessed))
                continue
            else:
                lettersGuessed.append(guess)
                availableLetters = getAvailableLetters(lettersGuessed)
                break
        
        # show the position of letter in the word
        if guess in secretWord:
            print("Good guess: ", getGuessedWord(secretWord, lettersGuessed))
            if getGuessedWord(secretWord, lettersGuessed) == secretWord:
                print("-------------")
                print("Congratulations, you won!")
        else:
            print("Oops! That letter is not in my word: ", getGuessedWord(secretWord, lettersGuessed))
            guesstimes -= 1
    print("-------------")
    print("Sorry, you ran out of guesses. The word was", secretWord, "." )


wordlist = loadWords()
secretWord = chooseWord(wordlist).lower()
hangman(secretWord)
