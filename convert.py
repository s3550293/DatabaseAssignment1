import sys


lines = [line.rstrip('\n') for line in open(sys.argv[1])]
checkOne = []
print 'Check One Removing ,'
for line in lines:
    data = line.replace(",", " ")
    checkOne.append(data)
print "Check Three Adding , Writing to file"
file = open("FormattedDataJava.csv","w")
for line in checkOne:
    data = line.replace('\t',',')
    file.write(data)
    file.write('\n')
file.close()