#Written by Kellen McKinney,
#last revision 6/2/2020 (addition of comments)

#This version is written solely with torus mosaic boards in mind

from collections import deque

class TorusBoard:
    def __init__(self, m, n, tiles=()):
        self.m=m
        self.n=n
        self.grid = [[0 for x in range(n)] for y in range(m)]
        self.queue=deque()
        self.det=0
        
        # set tiles from input queue (if any)
        #numtiles= len(tiles)
        for i in range (len(tiles)):
            rc=tiles[i]
            self.set(rc[0],rc[1])
            
    def set(self,r,c):
        N=((r-1)%self.m,c)  #tile coordinate to the 'north'
        E=(r,(c+1)%self.n)  #tile to the 'east'
        S=((r+1)%self.m,c)  #tile to the 'south'
        W=(r,(c-1)%self.n)  #tile to the 'west'
        self.grid[r][c] = 4
        self.grid[N[0]][N[1]]+=1
        if self.grid[N[0]][N[1]] == 3:
            self.queue.append(N)
        self.grid[E[0]][E[1]]+=1
        if self.grid[E[0]][E[1]] == 3:
            self.queue.append(E)
        self.grid[S[0]][S[1]]+=1
        if self.grid[S[0]][S[1]] == 3:
            self.queue.append(S)
        self.grid[W[0]][W[1]]+=1
        if self.grid[W[0]][W[1]] == 3:
            self.queue.append(W)

    def update(self):
        #check: do we have tiles to update?
        while self.queue:
            i = self.queue.pop()
            if self.grid[i[0]][i[1]]!=4:
                self.set(i[0],i[1])
        #now, check if determined
        self.det = 1
        for i in range (self.m):
            for j in range (self.n):
                if self.grid[i][j] < 4:
                    self.det = 0
            

    def print(self):
        return(self.grid)

   
    


