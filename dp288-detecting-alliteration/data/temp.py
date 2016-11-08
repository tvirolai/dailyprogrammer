stop = []
with open('stopwords.txt', 'rt') as f:
    stop = [x.strip().lower() for x in f.read().split('\n') if len(x) > 0]
    print(stop)

t = "Three grey geese in a green field grazing, Grey were the geese and green was the grazing"

def alliteration(string):
    words = [x for x in string.lower().strip().split(' ') if x not in stop]
    return " ".join([x for i, x in enumerate(words) if x[0] == words[i-1][0] or x[0] == words[i+1][0]])

print(alliteration(t))
