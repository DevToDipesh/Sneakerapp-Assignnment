# Sneakerapp-Assignnment

In this Android Project, we are showing the list of top 100 sneakers , where user can add the sneaker to cart, remove , go to detail 
Library being used:
* Room db
* ViewModel
* Hilt (But Dependency injection implementation is pending as of now)
* Navigation graph dependency

Features Included in the app
* User can see the list of sneakers of different brands.
* User can Search the sneaker.
* User can add the sneaker straight forwardly to the card.
* User can select options for color and sizes.
* User can view the sneaker detail
* User can view the Cart detail
* User can view the cart and remove the item

Android Architecture
* This project follows clean architecture with MVVM pattern for all modules like sneaker listing, search, sneaker detail and cart listing.
* ViewModelfactory is attached to respective viewmodels, all the string files is in separte string.xml.
* This app have only one activity rest all are Fragments only, being handled by Navigation graph.

Pending task
* Little bit design cutting edge experience  is pending
* Unit test cases not added yet.
* Had confusion for sort by options.
