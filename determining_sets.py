#Written by Kellen McKinney,
#last revision 6/2/2020 (addition of comments)

#This version of the algorithm is written solely for torus mosaic boards.
#It creates a dictionary to store all determined boards which are unique
#through wrapping and mirroring over the diagonal
#(only the (i,i) diagonal, where i=0 to n-1).
#the results were checked by hand for rotational or other 'mirroring' symmetries.
#note: in the comments of this program, the word 'tile' is used to mean 'cell'.

import itertools

#from collections import deque
from Torus_knot_board import TorusBoard
from time import time
start=time()

    #### user interface
    #this segment of code controls board dimensions and amount of cells in
    #the partial board.
m=2     #set board height here
n=2     #set board width here
d=2     #set size of partial board here
    #### end of user interface

    #initial setup: board tiles to choose from
S={(i,j) for i in range (m) for j in range (n)}
anchor=(0,0)    #use anchoring regardless of method as it's quick
S.remove(anchor) #remove the first tile, use later as 'anchor'
        #^-this is the 'upper left tile' efficiency
tilesets=itertools.combinations(S,d-1)

boards={} #create empty dictionary, add elements in loop

numchecked=0
for tileset in tilesets:
    numchecked += 1
    tileset= (anchor,)+tileset    #add the first tile back on
    #run dictionary checks here:
    if not(frozenset(tileset) in boards):
        IN=0
        for (ri,ci) in tileset: #check all transformations
            wrap   = tuple(((r-ri)%m,(c-ci)%n) for r,c in (tileset)) #wrapped transformations
            if (frozenset(wrap)   in boards):
                boards[frozenset(wrap)][1] += 1
                IN=IN+1
                break
            mirror = tuple((c,r) for r,c in (wrap))  #mirrored transformations
            if (frozenset(mirror) in boards):
                boards[frozenset(mirror)][1] += 1
                IN=IN+1
                break
        if IN == 0:
            f=TorusBoard(m,n,tileset)
            f.update()
            boards[frozenset(tileset)] = [f.det,1]

    #displaying data:
print(m,"x",n,"boards choosing",d,"tiles--")
udetboards=0    #asks: (#)of unique determined boards?
detsym=0        #asks: (#)of symmetries of determined boards?
undetboards=0   #asks: (#)of unique UNdetermined boards?
undetsym=0      #asks: (#)of symmetries of UNdetermined boards?
for keys,values in boards.items():
    if values[0]:
        udetboards += 1     #answers: (#)of unique determined boards?
        detsym += values[1] #answers: (#)of symmetries of determined boards?
        print(keys)
    else:
        undetboards += 1        #answers: (#)of unique UNdetermined boards?
        undetsym += values[1]   #answers: (#)of symmetries of UNdetermined boards?
        
print(numchecked,'board combos checked.')
print(len(boards),'boards in the dictionary.')
print(udetboards,'unique determining boards.')
print(detsym,'symmetries of determined boards.')
print(undetboards,'unique UNdetermined boards')
print(undetsym,'symmetries of UNdetermined boards')

end=time()
print('time elapsed:',end-start)
