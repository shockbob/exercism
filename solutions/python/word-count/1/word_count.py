def word_count(phrase):
    phrase2 = "" 
    badcharacters = set(",:_!!&@$%^&.")
    for ch in phrase.lower():
       if (ch in badcharacters):
           phrase2 = phrase2 + " " 
       else:
           phrase2 = phrase2 + ch
    words = phrase2.split() 
    wordmap = {}
    for word in words:
       word = word.strip("'")
       if (not word in wordmap):
           wordmap[word] = 1
       else:
           wordmap[word] = wordmap[word] + 1
    return wordmap 
