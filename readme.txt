/* *****************************************************************************
 *  Name: Minh Vu     
 *  NetID:    
 *  Precept:  
 *
 *  Partner Name:       
 *  Partner NetID:      
 *  Partner Precept:    
 *
 *  Hours to complete assignment (optional): 12
 *
 **************************************************************************** */

Programming Assignment 4: Slider Puzzle


/* *****************************************************************************
 *  Explain briefly how you represented the Board data type.

Board has a 2-d array as its "block". Board has different variables about this block such as size n, blank row/col, manhattan distance and hamming.
 **************************************************************************** */




/* *****************************************************************************
 *  Explain briefly how you represented a search node
 *  (board + number of moves + previous search node).

It's a nested class inside Solver class. This object will have a board, the number of moves leading up to this board from the original board and rpevious search node
 **************************************************************************** */





/* *****************************************************************************
 *  Explain briefly how you detected unsolvable puzzles.
 
 *  What is the order of growth of the running time of your isSolvable() ~O(n^2)
 *  method in the worst case as function of the board size n? Recall that with
 *  order-of-growth notation, you should discard leading coefficients and
 *  lower-order terms, e.g., n log n or n^3. 
 **************************************************************************** */

Description: Count the number of inversions and follow the rule. 



Order of growth of running time: ~O(n^2)



/* *****************************************************************************
 *  For each of the following instances, give the minimum number of moves to
 *  solve the instance (as reported by your program). Also, give the amount
 *  of time your program takes with both the Hamming and Manhattan priority
 *  functions. If your program can't solve the instance in a reasonable
 *  amount of time (say, 5 minutes) or memory, indicate that instead. Note
 *  that your program may be able to solve puzzle[xx].txt even if it can't
 *  solve puzzle[yy].txt and xx > yy.
 **************************************************************************** */


                 min number          seconds
     instance     of moves     Hamming     Manhattan
   ------------  ----------   ----------   ----------
   puzzle28.txt 	28
   puzzle30.txt 	30
   puzzle32.txt 	32
   puzzle34.txt 	34
   puzzle36.txt 	36
   puzzle38.txt 	38
   puzzle40.txt 	40
   puzzle42.txt 	42



/* *****************************************************************************
 *  If you wanted to solve random 4-by-4 or 5-by-5 puzzles, which
 *  would you prefer: a faster computer (say, 2x as fast), more memory
 *  (say 2x as much), a better priority queue (say, 2x as fast),
 *  or a better priority function (say, one on the order of improvement
 *  from Hamming to Manhattan)? Why?
 **************************************************************************** */

Better priority function because with move+manhattan, there will be multiple neighbor with the sam epriority function



/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */



/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */
Professor


/* *****************************************************************************
 *  Describe any serious problems you encountered.                    
 **************************************************************************** */



/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */







/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **************************************************************************** */
