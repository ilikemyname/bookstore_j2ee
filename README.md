# bookstore_j2ee
J2EE Application
										-----------------------------------------------------------------------------------
										Assignment02 - s3230237 - Le Phat Huy - COSC2465 - eCommerce and Enterprise Systems
										-----------------------------------------------------------------------------------

* The changes for Assignment 1:
	- almost changes is made in Customer.java
	- declare static for shoppingCart ArrayList
	- "edit" function is modified to check updated email has not been used
	- Whenever user decides to edit profile, he cannot use old email unless he cancel the process
	- "addComment" function does not get Date commentDate parameter from web browser, the date will be set by server
	- small change is given for "addBookToCart" which update purchased quantity for each book.
	- Some neccessary function is added for assignment 02
		- public ArrayList getAllCategories()
			+ return all category dto which is used to display on web browser
		- private ArrayList transferToCategoryDTOColl(Collection categories)
			+ function is used by getAllCategories()
		- public TreeSet browseBook(int categoryID)
			+ return all book in particular category which is used to display in a table on web browser
			+ I used TreeSet to order book by title. This TreeSet will be used for pagination
		- private TreeSet convertToBookDTOTreeSet(Collection bookCollection)
			+ this function is used for browseBook(int categoryID)
		- public int getBookAmountInCart()
			+ this function is used to get amount of book in cart of a user session
		- public double getTotalPrice()
			+ this function is used to get total price of book in cart
		- public boolean removeBookInCart(int bookID)
			+ this function is used to remove each book from cart when user click on remove button on web browser
		- public TreeSet searchBookByTitle(String keyword, int categoryid)
			+ this function will return a treeset when user enter a keyword to find book by title
			+ this function will check categoryid to find book in which category
		- public TreeSet searchBookByAuthorName(String keyword, int categoryid)
			+ this function will return a treeset when user enter a keyword to find book by author's name
			+ this function will check categoryid to find book in which category
			
* How to deploy and execute whole application
	- Create
		+ DatabaseName "BookStoreDB"
	- Create schema
		+ User			"admin"
		+ Password		"admin"
	- Execute sql file in "admin" table
	
* All features were implemented to satisfy all required function.

				-------------------------------------------------------------------------------------------------------------------------





