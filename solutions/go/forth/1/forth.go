package forth

import (
	"errors"
	"strconv"
	"strings"
)

type WordDefinition struct {
	word       string
	definition []string
}
type Operation struct {
	word            string
	stackSizeNeeded int
	what            func([]int) ([]int, error)
}


func pop2(stack []int) ([]int, int, int) {
	a := stack[len(stack)-1]
	b := stack[len(stack)-2]
	stack = stack[0 : len(stack)-2]
	return stack, a, b
}

func add(stack []int) ([]int, error) {
	var a int
	var b int
	stack, a, b = pop2(stack)
	return append(stack, a+b), nil
}

func subtract(stack []int) ([]int, error) {
	var a int
	var b int
	stack, a, b = pop2(stack)
	return append(stack, b-a), nil
}

func multiply(stack []int) ([]int, error) {
	var a int
	var b int
	stack, a, b = pop2(stack)
	return append(stack, b*a), nil
}
func divide(stack []int) ([]int, error) {
	var a int
	var b int
	stack, a, b = pop2(stack)
	if a == 0 {
		return nil, errors.New("Divide by zero")
	}
	return append(stack, b/a), nil
}

var customWords []WordDefinition

type OpFunction func([]int) ([]int,error)

var operations = []Operation{
	{"+", 2, add},
	{"-", 2, subtract},
	{"*", 2, multiply},
	{"/", 2, divide},
	{"dup", 1, func(stack []int)([]int,error){return append(stack, stack[len(stack)-1]), nil}},
	{"drop", 1, func(stack []int)([]int,error){return stack[0 : len(stack)-1], nil}},
	{"swap", 2, func(stack []int)([]int,error){return append(stack[:len(stack)-2], stack[len(stack)-1], stack[len(stack)-2]), nil}},
	{"over", 2, func(stack []int)([]int,error){return append(stack, stack[len(stack)-2]), nil}},
}

func findCustomWord(word string) (int, error) {
	if customWords != nil {
		for customIndex := range customWords {
			if customWords[customIndex].word == strings.ToLower(word) {
				return customIndex, nil
			}
		}
	}
	return -1, errors.New("Word not found")
}
func defineNewWord(wordIndex int, words []string) (int, error) {
	var definition []string
	wordIndex++
	wordName := strings.ToLower(words[wordIndex])
	_, err := strconv.ParseInt(wordName, 10, 16)
	if err == nil {
		return 0, errors.New("Dont redefine ints")
	}
	wordIndex++
	for words[wordIndex] != ";" {
		customIndex, err := findCustomWord(words[wordIndex])
		if err == nil {
			definition = append(definition, customWords[customIndex].definition...)
		} else {
			definition = append(definition, words[wordIndex])
		}
		wordIndex++
	}
	wordIndex++
	customIndex, err := findCustomWord(wordName)
	if err == nil {
		customWords[customIndex].definition = definition
	} else {
		customWords = append(customWords, WordDefinition{wordName, definition})
	}
	return wordIndex, nil
}

func interpret(word string, stack []int) ([]int, error) {
	word = strings.ToLower(word)
	value, err := strconv.ParseInt(word, 10, 16)
	if err == nil {
		stack = append(stack, int(value))
		return stack, nil
	} else {
		if customWords != nil {
			for customIndex := range customWords {
				if customWords[customIndex].word == word {
					definition := customWords[customIndex].definition
					for definitionIndex := range definition {
						var err error
						stack, err = interpret(definition[definitionIndex], stack)
						if err != nil {
							return nil, err
						}
					}
					return stack, nil
				}
			}
		}
		for operationIndex := range operations {
			operation := operations[operationIndex]
			if operation.word == word {
				if len(stack) < operation.stackSizeNeeded {
					return nil, errors.New("Stack underflow during operation " + word)
				}
				var err error
				stack, err = operation.what(stack)
				if err == nil {
					return stack, nil
				} else {
					return nil, err
				}
			}
		}
	}
	return nil, errors.New("Word not found")
}


func Forth(input []string) ([]int, error) {
	customWords = nil
	var stack []int
	for inputIndex := range input {
		words := strings.Split(input[inputIndex], " ")
		wordIndex := 0
		for wordIndex < len(words) {
			word := strings.ToLower(words[wordIndex])
			if word == ":" {
				var err error
				wordIndex, err = defineNewWord(wordIndex, words)
				if err != nil {
					return nil, err
				}
			} else {
				var err2 error
				stack, err2 = interpret(word, stack)
				if err2 != nil {
					return nil, err2
				}
				wordIndex++
			}
		}
	}
	return stack, nil
}

