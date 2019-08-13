README for CenturyLinkProject.java

The intention of the program is to connect to github using the 
URLConnection function, read in the data of the id page, and 
recursively call the connection to find additional id values
of followers.

The only input required for this program is a valid github ID
number.

Test Cases: non-integer value id given, invalid id number given,
	    id number with less than 5 followers

1. The program asks the user for the ID to be used as the base.
	-If an invalid value (ex. "jimmy dean") that is not an 
	int is given, the scanner class will fail.

2. The program calls the function getFollowers and attempts to
   connect to github using the given id number.
	-If the given ID number is an invalid number, the
	connection will fail.
3. The program searches the given id number for 5 followers using
   the BufferedReader to read the entire file and extract the id
   number using the extractor function. It then searches for 5
   followers of the first follower given in that set of followers,
   then the 5 followers of the first follower given of the next set
	-If the given ID number has less than 5 followers, the
	BufferedReader should successfully quit, but the extractor
	function will still expect to have 5 follower ids to extract
	and will fail.

Notes:
This program has connectivitiy issues with github's api program, and
in the debugging and creation of this connection, free outside sources
were used and will be cited here:
https://docs.oracle.com/javase/tutorial/networking/urls/readingWriting.html
https://developer.github.com/v3/users/followers/

